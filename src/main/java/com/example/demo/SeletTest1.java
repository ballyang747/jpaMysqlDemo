package com.example.demo;

import com.example.demo.bean.UserEntity12;
import org.hibernate.SQLQuery;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Native;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SeletTest1 {

    @PersistenceContext
    private EntityManager entityManager;

    public <T> List<T> selectResultByNative(T defaults, String dataSource, String prefix, String[] list, String sufix, Class<T> clazt) throws Exception {
        //类签名
        String hashCode = "hashCode";
        String toString = "equals";
        String eqaulsString = "toString";
        Class<?> clazz = defaults.getClass();
        List<String> tempList = new ArrayList<String>();
        List<T> result = new ArrayList<T>();

        String signName = clazz.getSimpleName();
        Table annotation = clazz.getAnnotation(Table.class);

      /*  EntityManager entityManager = null;
        if ("Paas".equals(dataSource)) {
            entityManager = em_paas;
        } else if ("Iaas".equals(dataSource)) {
            entityManager = em_iaas;
        }*/
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        //
        if (StringUtils.isEmpty(prefix)) {
            Field[] fields = clazz.getDeclaredFields();
            int j = 0;
            for (int i = 0, len = fields.length; i < len; i++) {
                if (j != 0) {
                    sb.append(" , ");
                }
                fields[i].setAccessible(true);
                //输出字段名
                System.out.println(fields[i].getName() + ":" + i);
                String fieldName = fields[i].getName();
                if (hashCode.equals(fieldName) || toString.equals(fieldName) || eqaulsString.equals(fieldName)) {
                    continue;
                }
                //获取值注解
                Column columnAno = fields[i].getAnnotation(Column.class);
                String colName;
                if (columnAno != null) {
                    colName = columnAno.name();
                    sb.append(colName);

                }


                j++;
                tempList.add(fields[i].getName());
            }
        } else {
            sb.append(prefix);
            for (String name : list) {
                Field field = clazz.getField(name);
                field.setAccessible(true);
                tempList.add(name);
            }

        }
        //读取数据库名字
        sb.append("  FROM  ");
        if (annotation != null) {
            sb.append("  " + annotation.name());
        } else {
            sb.append("  " + signName);
        }

        //读取后缀
        if (StringUtils.isEmpty(sufix)) {
            Field[] fields = clazz.getDeclaredFields();
            int k = 0;
            for (int i = 0, len = fields.length; i < len; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                //输出字段名
                //System.out.println(fields[i].getName()+":"+i);
                String fieldName = field.getName();
                if (hashCode.equals(fieldName) || toString.equals(fieldName) || eqaulsString.equals(fieldName)) {
                    continue;
                }
                Object value = field.get(defaults);
                if (value != null) {
                    //获取值注解
                    Column columnAno = fields[i].getAnnotation(Column.class);
                    String colName;
                    if (columnAno != null) {

                        colName = columnAno.name();
                    } else {

                        colName = fields[i].getName();
                    }
                    if (k != 0) {
                        sb.append("  AND  ");
                    } else {
                        sb.append("  WHERE  ");
                    }
                    sb.append(colName);
                    sb.append(" =  ");
                    String resultContent = ReUtils.GetObjectString(value);
                    sb.append("'" + resultContent + "'");

                    k++;
                }
            }

        } else {
            sb.append(sufix);
        }
        UserEntity12 userEntity12 = new UserEntity12();

        System.out.println(sb.toString());
        try {
            Query query = entityManager.createNativeQuery(sb.toString());
            //拼接结果给factType  .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);)
            query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            List resultList = query.getResultList();
            System.out.println(resultList);
            for (Object obj : resultList) {
                Map obj1 = (Map)obj;

                result.add((T)convertMap(clazz,obj1));
            }
        } catch (Exception ex) {
            System.out.println("exec sql Script Exception:" + ex.getMessage());
        }
        return result;
    }


    /**
     * 将一个 Map 对象转化为一个 JavaBean
     *
     * @param clazz 要转化的类型
     * @param map   包含属性值的 map
     * @return 转化出来的 JavaBean 对象
     * @throws IntrospectionException    如果分析类属性失败
     * @throws IllegalAccessException    如果实例化 JavaBean 失败
     * @throws InstantiationException    如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */
    public  <T> T convertMap(Class<T> clazz, Map map) throws IntrospectionException, IllegalAccessException, InstantiationException, InvocationTargetException, ParseException {
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz); // 获取类属性
        T obj = clazz.newInstance();
        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            Field privateField = getPrivateField(propertyName, clazz);
            if(null==privateField){continue;}
            Column columnAno = privateField.getAnnotation(Column.class);

            if (columnAno != null&&map.containsKey(columnAno.name())) {
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                Object value = map.get(columnAno.name());

                Object[] args = new Object[1];
                args[0] = value;
                System.out.println(columnAno.name()+"------------"+value);
                if(null==value||"null".equals(value)||"NULL".equals(value)){

                    continue;
                }

                if (privateField == null) {
                }
                privateField.setAccessible(true);
                String type = privateField.getGenericType().toString();

                if (type.equals("class java.lang.String")) {
                    privateField.set(obj, value);
                } else if (type.equals("class java.lang.Boolean")) {
                    privateField.set(obj, Boolean.parseBoolean(String.valueOf(value)));
                } else if (type.equals("class java.lang.Long")) {
                    privateField.set(obj, Long.parseLong(String.valueOf(value)));
                } else if (type.equals("class java.lang.Integer")) {
                    privateField.set(obj, Integer.parseInt(String.valueOf(value)));
                } else if (type.equals("class java.lang.Double")) {
                    privateField.set(obj, Double.parseDouble(String.valueOf(value)));
                } else if (type.equals("class java.lang.Float")) {
                    privateField.set(obj, Float.parseFloat(String.valueOf(value)));
                } else if (type.equals("class java.math.BigDecimal")) {
                    privateField.set(obj, new BigDecimal(String.valueOf(value)));
                }else if (type.equals("class java.util.Date")) {
                    String[] split = String.valueOf(value).split("\\.");
                    Date date = new Date();
                    if(split.length==1){
                        date = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(String.valueOf(value));
                    }else if (split.length==2){
                        if(split[1].length()==1){
                            date = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")).parse(String.valueOf(value));
                        }else if(split[1].length()==2){
                            date = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS")).parse(String.valueOf(value));
                        }else if (split[1].length()==3){
                            date = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")).parse(String.valueOf(value));
                        }
                        //精度不满足条件后面补充
                    }
                    privateField.set(obj,date);
                }
            }
        }
        return obj;
    }

    /*拿到反射父类私有属性*/
    private   Field getPrivateField(String name, Class cls) {
        Field declaredField = null;
        try {
            declaredField = cls.getDeclaredField(name);
        } catch (NoSuchFieldException ex) {

            if (cls.getSuperclass() == null) {
                return declaredField;
            } else {
                declaredField = getPrivateField(name, cls.getSuperclass());
            }
        }
        return declaredField;
    }
}
