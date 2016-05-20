package com.aaron.search.core.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.replication.ReplicationRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.RangeQueryBuilder;

import com.aaron.search.bean.IndexMeta;
import com.aaron.search.exception.SearchException;

public interface IIndexSearchServcie {
	
	public IndexResponse createIndex(IndexMeta indexMeta,String jsonStr);
	
	public IndexResponse createIndex(IndexMeta indexMeta,Map<String, Object> map);
	
	public IndexResponse createIndex(IndexMeta indexMeta,byte[] json);
	
	public IndexResponse createIndex(IndexMeta indexMeta,XContentBuilder builder);
	
	/**
	 * 查询索引
	 * @param indexMeta
	 * @return
	 */
	public GetResponse indexSearchByIndexMeta(IndexMeta indexMeta);
	
	/**
	 * 跨一个或多个索引，类型搜索
	 * @param indexs 索引
	 * @param types 索引类型　
	 * @return
	 */
	public SearchResponse indexSearch(String[] indexs,String[] types,QueryBuilder queryBuilder,RangeQueryBuilder rangeQueryBuilder,int size);
	
	/**
	 * 删除索引
	 * @param indexMeta
	 * @return
	 */
	public DeleteResponse deleteIndex(IndexMeta indexMeta);
	
	/**
	 * 更新索引
	 * @param indexMeta
	 * @param builder
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public UpdateResponse updateIndexByPrepareUpdate(IndexMeta indexMeta,XContentBuilder builder) throws InterruptedException, ExecutionException;
	
	/**
	 * 更新索引
	 * @param indexMeta
	 * @param builder
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public UpdateResponse updateIndexByUpdateRequest(IndexMeta indexMeta,XContentBuilder builder) throws InterruptedException, ExecutionException;
	
	/**
	 * 更新索引
	 * @param indexMeta
	 * @param builder
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public UpdateResponse updateIndexByUpsert(IndexMeta indexMeta,XContentBuilder srcBuilder,XContentBuilder builder) throws InterruptedException, ExecutionException;
	
	/**
	 * 根据多个索引查询
	 * @param indexMeta[]
	 * @return
	 */
	public MultiGetResponse getMultiGetSearch(IndexMeta... indexMetas) throws IllegalArgumentException;
	
	/**
	 * 通过单个请求执行多个索引创建
	 * @return
	 * @throws SearchException
	 */
	public BulkResponse createMultiIndexByBulk(IndexMeta[] indexMetas,XContentBuilder[] contentBuilders) throws IllegalArgumentException;
	
	/**
	 * 通过单个请求执行多个索引创建
	 * @return
	 * @throws SearchException
	 */
	public BulkResponse deleteMultiIndexByBulk(IndexMeta[] indexMetas) throws IllegalArgumentException;
	
	/**
	 * 周期刷新索引操作,调用完成后需要调用
	 * bulkProcessor.awaitClose(10, TimeUnit.MINUTES);
	 * 或者 bulkProcessor.close();　
	 * @param request
	 * @return
	 */
	public BulkProcessor indexBulkProcessor(List<ReplicationRequest<?>> requestList);
}
