package com.aaron.tools.utils;

/**
 * 读取配置文件属性相关操作
 * 
 * @author aaron.qiu
 * @since 2015-2016
 */
public class ReadProperty {
	public DecryptPropertyPlaceholderConfigurer dppc;

	public String getValue(String key) {
		return this.dppc.getValue(key);
	}

	public void setDppc(DecryptPropertyPlaceholderConfigurer dppc) {
		this.dppc = dppc;
	}
}
