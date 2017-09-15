package com.allchips.product.utils;

import com.allchips.product.annotations.TransferValue;
import com.allchips.tools.utils.CommUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 注解工具类
 * <p> aaron.qiu </p>
 * <p>Version: 1.0.0</p>
 * <p>Create Date： 2017-09-25 17:18:41 </p>
 * <p>Copyright (c) 2017 ~ 2018 版权所有</p>
 */
public class AnnotationBean<T> {
    //语言类型为英文
    public static final String LANG_EN = "en";
    //语言类型为中文
    public static final String LANG_CN = "cn";

    /**
     * entity 实体类标注的注解TransferValue
     * 中的枚举定义需要提供getStatusMsgCn
     * get方法的定义,具体枚举实现参考StatusEnum
     * @param entity 实体类
     * @param lang 语言的类型 en英文，cn为中文(默认为中文)
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
    public String parseEntityFieldAnnotation(T entity,String lang) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        String resultStr = "";
        if(null==lang||"".equals(lang)) lang = LANG_CN;
        Class brandClass = entity.getClass();
        Method[] methods = brandClass.getMethods();
        for(Method method : methods) {
            Annotation annotation = method.getAnnotation(TransferValue.class);
            if(!CommUtils.isNull(annotation)) {
                //获取实体中带有注解field的值
                Object fieldValueObj =  method.invoke(entity,new Object[]{});
                if (fieldValueObj==null) return resultStr;
                int fieldValue = (Integer) fieldValueObj;

                TransferValue transferValue = (TransferValue)annotation;
                Class clazz = transferValue.classz();
                //System.out.println(transferValue);
                Object[] enums = clazz.getEnumConstants();
                if(null==enums||enums.length<=0) return "";
                Method getStatusMsgCnMethod = clazz.getMethod("getStatusMsgCn");
                Method getStatusMsgEnMethod = clazz.getMethod("getStatusMsgEn");

                Method getMethod = clazz.getMethod("get",new Class[]{Integer.class});

                Object object = getMethod.invoke(enums[0],new Object[]{fieldValue});

                Object oValue = null;
                if (LANG_CN.equalsIgnoreCase(lang)) {
                    oValue = getStatusMsgCnMethod.invoke(object);
                    if(null==oValue) return resultStr;
                    resultStr = (String)oValue;
                } else {
                    oValue = getStatusMsgEnMethod.invoke(object);
                    if(null==oValue) return resultStr;
                    resultStr = (String)oValue;
                }

            }
        }

        return resultStr;
    }

}
