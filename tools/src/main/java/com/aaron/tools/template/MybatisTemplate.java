package com.aaron.tools.template;

import com.aaron.tools.pager.ORDERBY;
import com.aaron.tools.pager.PagerModel;
import com.aaron.tools.pager.Query;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class MybatisTemplate {

	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	public <T> T selectOne(String statement) {
		return this.sqlSessionTemplate.selectOne(statement);
	}

	public <T> T selectOne(String statement, Object parameter) {
		return this.sqlSessionTemplate.selectOne(statement, parameter);
	}

	public <K, V> Map<K, V> selectMap(String statement, String mapKey) {
		return this.sqlSessionTemplate.selectMap(statement, mapKey);
	}

	public <K, V> Map<K, V> selectMap(String statement, Object parameter,
			String mapKey) {
		return this.sqlSessionTemplate.selectMap(statement, parameter, mapKey);
	}

	public <K, V> Map<K, V> selectMap(String statement, Object parameter,
			String mapKey, RowBounds rowBounds) {
		return this.sqlSessionTemplate.selectMap(statement, parameter, mapKey,
				rowBounds);
	}

	public <E> List<E> selectList(String statement) {
		return this.sqlSessionTemplate.selectList(statement);
	}

	public <E> List<E> selectList(String statement, Object parameter) {
		return this.sqlSessionTemplate.selectList(statement, parameter);
	}

	public <E> List<E> selectList(String statement, Object parameter,
			RowBounds rowBounds) {
		return this.sqlSessionTemplate.selectList(statement, parameter,
				rowBounds);
	}

	public void select(String statement, ResultHandler handler) {
		this.sqlSessionTemplate.select(statement, handler);
	}

	public void select(String statement, Object parameter, ResultHandler handler) {
		this.sqlSessionTemplate.select(statement, parameter, handler);
	}

	public void select(String statement, Object parameter, RowBounds rowBounds,
			ResultHandler handler) {
		this.sqlSessionTemplate
				.select(statement, parameter, rowBounds, handler);
	}

	public int insert(String statement) {
		return this.sqlSessionTemplate.insert(statement);
	}

	public int insert(String statement, Object parameter) {
		return this.sqlSessionTemplate.insert(statement, parameter);
	}

	public int update(String statement) {
		return this.sqlSessionTemplate.update(statement);
	}

	public int update(String statement, Object parameter) {
		return this.sqlSessionTemplate.update(statement, parameter);
	}

	public int delete(String statement) {
		return this.sqlSessionTemplate.delete(statement);
	}

	public int delete(String statement, Object parameter) {
		return this.sqlSessionTemplate.delete(statement, parameter);
	}

	protected <E> List<E> selectList(String statement, Object params,
			Map<String, ORDERBY> orderBy) {
		PageBounds pageBounds = new PageBounds(1, 2147483647);

		pageBounds.setContainsTotalCount(false);

		setOrderInfo(orderBy, pageBounds);
		return this.sqlSessionTemplate
				.selectList(statement, params, pageBounds);
	}

	protected <E> List<E> getPagerModelListByQuery(String statement,
			Object params, Query query) {
		PageBounds pageBounds = new PageBounds(query.getPageNumber(),
				query.getPageSize());

		pageBounds.setContainsTotalCount(false);

		setOrderInfo(query.getSqlOrderBy(), pageBounds);
		return this.sqlSessionTemplate
				.selectList(statement, params, pageBounds);
	}

	protected <T> PagerModel<T> getPagerModelByQuery(Object params,
													 Query query, String dataSql) {
		PageBounds pageBounds = new PageBounds(query.getPageNumber(),
				query.getPageSize());

		setOrderInfo(query.getSqlOrderBy(), pageBounds);
		List datas = this.sqlSessionTemplate.selectList(dataSql, params,
				pageBounds);
		PageList pageList = (PageList) datas;

		int count = pageList.getPaginator().getTotalCount();
		return new PagerModel(count, datas);
	}

	private void setOrderInfo(Map<String, ORDERBY> orderBy,
			PageBounds pageBounds) {
		List orders = null;
		if ((orderBy != null) && (orderBy.size() > 0)) {
			orders = new ArrayList();
			for (Map.Entry order : orderBy.entrySet()) {
				orders.add(Order.create((String) order.getKey(),
						((ORDERBY) order.getValue()).toString()));
			}
			if (CollectionUtils.isNotEmpty(orders))
				pageBounds.setOrders(orders);
		}
	}
}
