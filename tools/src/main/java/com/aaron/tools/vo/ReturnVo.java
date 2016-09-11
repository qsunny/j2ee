package com.aaron.tools.vo;

import java.io.Serializable;
import java.util.List;

/** 
 * @Description:
 * @Author:Aaron.Qiu
 * @Since:2016-01-09
 * @Copyright:Copyright (c) 2016 ~ 2017 版权所有 
 */
public class ReturnVo<T> implements Serializable{
	
	private static final long serialVersionUID = -5580228202640516960L;
	//响应编码
	private Integer responseCode;
	//响应消息
	private String responseMsg;
	//返回的vo
	private T vo;
	//返回的list
	private List<T> list;
	
	public T getVo() {
		return vo;
	}
	public void setVo(T vo) {
		this.vo = vo;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public Integer getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMsg() {
		return responseMsg;
	}
	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}
	public ReturnVo() {
	}
	public ReturnVo(Integer responseCode, T vo) {
		this.responseCode = responseCode;
		this.vo = vo;
	}
	public ReturnVo(Integer responseCode, List<T> list) {
		this.responseCode = responseCode;
		this.list = list;
	}
	public ReturnVo(Integer responseCode, String responseMsg) {
		this.responseCode = responseCode;
		this.responseMsg = responseMsg;
	}
}

	