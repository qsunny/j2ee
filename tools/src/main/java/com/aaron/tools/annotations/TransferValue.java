package com.aaron.tools.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解用于解析实体类中状态的数值为
 * 枚举值中对应的中文或英文描述
 * <p> aaron.qiu </p>
 * <p>Version: 1.0.0</p>
 * <p>Create Date： 2017-09-25 17:18:41 </p>
 * <p>Copyright (c) 2017 ~ 2018 版权所有</p>
 */
@Target( {ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TransferValue {

    /**
     * 枚举类
     * @return
     */
    Class classz();
}

