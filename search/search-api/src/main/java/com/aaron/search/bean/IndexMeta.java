package com.aaron.search.bean;

import java.io.Serializable;

/**
 * 索引数据信息实体类
 * @author 	Aaron.Qiu
 * @since	2016-05-15
 */
@SuppressWarnings("serial")
public class IndexMeta implements Serializable{
	
	//索引名称
	private String indexName;
	//索引类型
	private String indexType;
	//索引id
	private String indexId;
	public String getIndexName() {
		return indexName;
	}
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	public String getIndexType() {
		return indexType;
	}
	public void setIndexType(String indexType) {
		this.indexType = indexType;
	}
	public String getIndexId() {
		return indexId;
	}
	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}
	@Override
	public String toString() {
		return "IndexMeta [indexName=" + indexName + ", indexType=" + indexType + ", indexId=" + indexId + "]";
	}
	
}
