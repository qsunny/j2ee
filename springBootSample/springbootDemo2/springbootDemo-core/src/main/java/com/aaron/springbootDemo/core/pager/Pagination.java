package com.aaron.springbootDemo.core.pager;

/**
 * 分页，在分页挡截中使用
 *
 * @since 2019-05-19 20:34
 */
public interface Pagination {

	/**
	 * @return 当前页码
	 */
	int getCurrentPageNo();

	/**
	 * @return 每页记录数
	 */
	int getPageSize();

	/**
	 * @return 总记录数: 负数表尚未设置, 挡截器会使用count语句统计总数; 0或正整数表总数已设置, 挡截器将不会统计总数.
	 */
	long getTotalCount();

	/**
	 * @param totalCount 设置记录总数
	 */
	void setTotalCount(long totalCount);
}
