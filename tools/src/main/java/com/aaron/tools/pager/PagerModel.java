package com.aaron.tools.pager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页相关操作
 * @author aaron.qiu
 * @since 2015-2016
 */
public class PagerModel<T> implements Serializable {
	private static final long serialVersionUID = 4804053559968742915L;
	private long total;
	private List<T> rows = new ArrayList<T>();

	public PagerModel() {
	}

	public PagerModel(long total, List<T> rows) {
		this.total = total;
		this.rows = rows;
	}

	public long getTotal() {
		return this.total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return this.rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}