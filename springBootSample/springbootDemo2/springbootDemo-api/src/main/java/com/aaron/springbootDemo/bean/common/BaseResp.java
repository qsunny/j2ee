package com.aaron.springbootDemo.bean.common;

import java.io.Serializable;


public class BaseResp implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 响应编码 */
	private Integer code;
	/** 响应消息 */
	private String message;

	public BaseResp() {
		this.code = RestAPICode.NORMAL.getCode();
		this.message = RestAPICode.NORMAL.getMessage();
	}

	public BaseResp(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * code = 0,自定义返回成功信息<br>
	 * 
	 * @param message
	 * @version 2015-08-10
	 */
	public BaseResp(String message) {
		this();
		this.message = message;
	}

	public BaseResp(String message, int code) {
		this.message = message;
		this.code = code;
	}

	/**
	 * BaseRestAPIResp带参数构造 ，留做复用
	 * 
	 *            code
	 * @version 2015-07-29
	 */
	public BaseResp(RestAPICode code) {
		this.message = code.getMessage();
		this.code = code.getCode();
	}

	/**
	 * GET/POST请求PathVariable、RequestParam参数字段错误时设置自定义message<br>
	 * JSON错误不在此列<br>
	 *
	 * @param message
	 * @version 2015-08-06
	 */
	public void setMessage4ParamError(String message) {
		this.code = RestAPICode.REQUEST_PARAM_FIELD_ERROR.getCode();
		this.message = message;
	}

	/**
	 * 请求JSON参数字段缺失、错误时设置自定义message<br>
	 * GET/POST PathVariable、RequestParam及请求错误不在此列<br>
	 *
	 */
	public void setMessage4JsonParamError(String message) {
		this.code = RestAPICode.REQUEST_JSON_FIELD_ERROR.getCode();
		this.message = message;
	}
	
	public void setRestAPICode(RestAPICode code) {
		this.message = code.getMessage();
		this.code = code.getCode();
	}

	public void setErrorCode(RestAPICode code) {
		this.setRestAPICode(code);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
