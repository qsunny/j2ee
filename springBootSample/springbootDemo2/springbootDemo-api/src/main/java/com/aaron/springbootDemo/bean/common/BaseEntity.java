package com.aaron.springbootDemo.bean.common;

import com.aaron.springbootDemo.utils.JsonUtil;

import java.io.Serializable;


/**
 */
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public String toString() {
        return JsonUtil.toJson(this);
    }

}
