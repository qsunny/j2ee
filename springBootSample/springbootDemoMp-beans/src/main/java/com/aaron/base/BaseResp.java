package com.aaron.base;


import com.aaron.base.enums.BaseResponse;

public class BaseResp extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 响应编码 */
	private Integer code;
	/** 响应消息 */
	private String message;

	public BaseResp() {
		this.code = BaseResponse.SUCCESS_RESULT.getResponseCode();
		this.message = BaseResponse.SUCCESS_RESULT.getResponseMsgCn();
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
	 * @param code
	 *            code
	 * @version 2015-07-29
	 */
	public BaseResp(BaseResponse code) {
		this.message = code.getResponseMsgCn();
		this.code = code.getResponseCode();
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
