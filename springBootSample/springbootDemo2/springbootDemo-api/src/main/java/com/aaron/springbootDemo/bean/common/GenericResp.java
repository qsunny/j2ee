package com.aaron.springbootDemo.bean.common;


/**
 * @Title:
 * @Description:泛型响应类
 */
public class GenericResp<T> extends BaseResp {
    
    private static final long serialVersionUID = 1L;
    
    /** 返回前端数据 */
    private T data;
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    public GenericResp() {
        super();
    }
    
    public GenericResp(Integer code, String message) {
        super(code, message);
    }
    
    public GenericResp(Integer code, String message, T data) {
        super(code, message);
        this.data = data;
    }
    
    public GenericResp(RestAPICode code, T data) {
    	super(code);
        this.data = data;
    }
    
    
}
