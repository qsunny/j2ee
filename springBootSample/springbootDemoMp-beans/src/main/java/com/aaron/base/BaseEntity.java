package com.aaron.base;

import java.io.Serializable;

import com.aaron.base.utils.JsonUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by zengx on 2016/8/10.
 */
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public String toString() {
        return JsonUtils.toJson(this);
    }

}
