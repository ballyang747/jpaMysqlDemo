package com.example.demo;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SeletTest1 {
    public static <T> List<T> selectResultByNative(T defaults, String dataSource,   String prefix ,  String[] list,   String sufix, Class<T> clazt) throws  Exception {
        //类签名
        String hashCode = "hashCode";
        String toString="equals";
        String eqaulsString="toString";
        Class<?> clazz = defaults.getClass();
        List<String> tempList =  new ArrayList<String>();
        List<T> result = new ArrayList<T>();

        String signName = clazz.getSimpleName();
        Table annotation =clazz.getAnnotation(Table.class);

      /*  EntityManager entityManager = null;
        if ("Paas".equals(dataSource)) {
            entityManager = em_paas;
        } else if ("Iaas".equals(dataSource)) {
            entityManager = em_iaas;
        }*/
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        //
        if(StringUtils.isEmpty(prefix)){
            Field[] fields  = clazz.getDeclaredFields();
            int j = 0;
            for(int i=0,len = fields.length;i<len;i++){
                if(j != 0){
                    sb.append(" , ");
                }
                fields[i].setAccessible(true);
                //输出字段名
                System.out.println(fields[i].getName()+":"+i);
                String fieldName =fields[i].getName();
                if(hashCode.equals(fieldName)||toString.equals(fieldName)||eqaulsString.equals(fieldName))
                {
                    continue;
                }
                //获取值注解
                Column columnAno =fields[i].getAnnotation(Column.class);
                String colName;
                if(columnAno!=null)
                {
                    colName= columnAno.name();
                    sb.append(colName);
                    sb.append("  AS  "+ fields[i].getName());

                }else{
                    colName =fields[i].getName();
                    sb.append(colName);
                }



                j++;
                tempList.add(fields[i].getName());
            }
        }else{
            sb.append(prefix);
            for(String name : list){
                Field field= clazz.getField(name);
                field.setAccessible(true);
                tempList.add(name);
            }

        }
        //读取数据库名字
        sb.append("FROM");
        if(annotation != null){
            sb.append("  "+annotation.name());
        }else{
            sb.append("  "+signName);
        }
        sb.append("  WHERE  ");
        //读取后缀
        if(StringUtils.isEmpty(sufix)){
            Field[] fields  = clazz.getDeclaredFields();
            int k =0;
            for(int i=0,len = fields.length;i<len;i++){
                Field field = fields[i];
                field.setAccessible(true);
                //输出字段名
                //System.out.println(fields[i].getName()+":"+i);
                String fieldName =field.getName();
                if(hashCode.equals(fieldName)||toString.equals(fieldName)||eqaulsString.equals(fieldName))
                {
                    continue;
                }
                Object value = field.get(defaults);
                if(value!=null){
                    //获取值注解
                    Column columnAno =fields[i].getAnnotation(Column.class);
                    String colName;
                    if(columnAno!=null){

                        colName= columnAno.name();
                    }else{

                        colName =fields[i].getName();
                    }
                    if(k!=0){
                        sb.append("  AND  ");
                    }
                    sb.append(colName);
                    sb.append(" =  ");
                    String resultContent = ReUtils.GetObjectString(value);
                    sb.append("'"+resultContent+"'");

                    k++;
                }
            }

        }else{
            sb.append(sufix);
        }

        System.out.println(sb.toString());
       /* try {
            //Query query = entityManager.createNativeQuery(sb.toString());
            //拼接结果给factType
            List resultList = query.getResultList();
            for(Object obj : resultList){
                Map row = (Map) obj;
                T res = clazt.newInstance();
                Class<?> clz = res.getClass();
                for(String name :tempList){
                    Field ff = clz.getField(name);
                    ff.setAccessible(true);
                    ff.set(res,row.get(name));
                }
                result.add(res);
            }
        }catch (Exception ex){
            log.error("exec sql Script Exception:" + ex.getMessage());
        }*/
        return result;
    }



}
