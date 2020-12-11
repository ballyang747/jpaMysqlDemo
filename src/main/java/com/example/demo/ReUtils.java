package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReUtils {

    public static String GetObjectString(Object object)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Class<?> clz = object.getClass();
        String strResult = new String();
        if(object instanceof Date){
            Date dateTime =  (Date)object;
            strResult = sdf.format(dateTime);
        }else
        if(object instanceof String){
            strResult = (String)object;
        }else
        if(object instanceof Double){
            strResult = Double.toString((Double)object);
        }else
        if(object instanceof Integer){
            strResult = Integer.toString((Integer)object);
        }else {
            strResult = object.toString();
        }
        return  strResult;
    }

}
