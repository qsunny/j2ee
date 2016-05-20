package com.aaron.search.core.service.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetRequestBuilder;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.support.replication.ReplicationRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aaron.search.bean.IndexMeta;
import com.aaron.search.core.service.IElasticSearchClientService;
import com.aaron.search.core.service.IIndexSearchServcie;

@Service
public class IndexSearchServiceImpl implements IIndexSearchServcie {

	@Autowired
	@Qualifier("elasticSearchClientService")
	public IElasticSearchClientService elasticSearchClientService;
	
	@Override
	public IndexResponse createIndex(IndexMeta indexMeta,String jsonStr) {
		return elasticSearchClientService.getEsClient()
		.prepareIndex(indexMeta.getIndexName(),indexMeta.getIndexType(),indexMeta.getIndexId())
		.setSource(jsonStr)
		.execute().actionGet();
		
	}

	@Override
	public IndexResponse createIndex(IndexMeta indexMeta,Map<String, Object> map) {
		return elasticSearchClientService.getEsClient()
			.prepareIndex(indexMeta.getIndexName(),indexMeta.getIndexType(),indexMeta.getIndexId())
			.setSource(map)
			.execute().actionGet();
	}

	@Override
	public IndexResponse createIndex(IndexMeta indexMeta,byte[] json) {
		return elasticSearchClientService.getEsClient()
		.prepareIndex(indexMeta.getIndexName(),indexMeta.getIndexType(),indexMeta.getIndexId())
		.setSource(json)
		.execute().actionGet();
	}

	@Override
	public IndexResponse createIndex(IndexMeta indexMeta,XContentBuilder builder) {
		return elasticSearchClientService.getEsClient()
		.prepareIndex(indexMeta.getIndexName(),indexMeta.getIndexType(),indexMeta.getIndexId())
		.setSource(builder)
		.execute().actionGet();
	}
	
	@Override
	public GetResponse indexSearchByIndexMeta(IndexMeta indexMeta) {
		return elasticSearchClientService.getEsClient().prepareGet(indexMeta.getIndexName(), 
				indexMeta.getIndexType(), indexMeta.getIndexId())
				.setOperationThreaded(false) //set true executed on a different thread
				.get();
	}
	
	@Override
	public DeleteResponse deleteIndex(IndexMeta indexMeta) {
		return 	elasticSearchClientService.getEsClient().prepareDelete(indexMeta.getIndexName(), 
				indexMeta.getIndexType(), indexMeta.getIndexId())
		        //.setOperationThreaded(false)
		        .get();
	}
	
	@Override
	public UpdateResponse updateIndexByPrepareUpdate(IndexMeta indexMeta, XContentBuilder builder) throws InterruptedException, ExecutionException {

		return elasticSearchClientService.getEsClient().prepareUpdate(indexMeta.getIndexName()
			,indexMeta.getIndexType(),indexMeta.getIndexId())
	        .setDoc(builder)
	        .get();
	}
	
	@Override
	public UpdateResponse updateIndexByUpdateRequest(IndexMeta indexMeta, XContentBuilder builder) throws InterruptedException, ExecutionException {
		UpdateRequest updateRequest = new UpdateRequest();
		updateRequest.index(indexMeta.getIndexName());
		updateRequest.type(indexMeta.getIndexType());
		updateRequest.id(indexMeta.getIndexId());
		updateRequest.doc(builder);
		return elasticSearchClientService.getEsClient().update(updateRequest).get();
		
	}
	
	@Override
	public UpdateResponse updateIndexByUpsert(IndexMeta indexMeta, XContentBuilder srcBuilder,XContentBuilder builder)
			throws InterruptedException, ExecutionException {
		IndexRequest indexRequest = new IndexRequest(indexMeta.getIndexName(),indexMeta.getIndexType(), indexMeta.getIndexId())
	        .source(srcBuilder);
		
		UpdateRequest updateRequest = new UpdateRequest();
		updateRequest.index(indexMeta.getIndexName());
		updateRequest.type(indexMeta.getIndexType());
		updateRequest.id(indexMeta.getIndexId());
		
		//如果不存在就添加，存在就更新
		updateRequest.doc(builder).upsert(indexRequest);
		
		return elasticSearchClientService.getEsClient().update(updateRequest).get();
	}
	
	@Override
	public MultiGetResponse getMultiGetSearch(IndexMeta... indexMetas) throws IllegalArgumentException{
		if(null==indexMetas || indexMetas.length<=0) throw new IllegalArgumentException("indexMetas参数不能为空");
		MultiGetRequestBuilder mgrb = elasticSearchClientService.getEsClient().prepareMultiGet();
		
		for(int i=0,len=indexMetas.length;i<len;i++) {
			IndexMeta indexMeta = indexMetas[i];
			mgrb.add(indexMeta.getIndexName(), indexMeta.getIndexType(), indexMeta.getIndexId());
		}
		
		return mgrb.get();
	}
	
	@Override
	public BulkResponse createMultiIndexByBulk(IndexMeta[] indexMetas, XContentBuilder[] contentBuilders)
			throws IllegalArgumentException {
		if(null==indexMetas || indexMetas.length<=0) throw new IllegalArgumentException("indexMetas参数不能为空");
		if(null==contentBuilders || contentBuilders.length<=0) throw new IllegalArgumentException("XContentBuilder参数不能为空");
		if(indexMetas.length!=contentBuilders.length) throw new IllegalArgumentException("XContentBuilder和indexMetas参数长度不一致");
		
		BulkRequestBuilder bulkRequest = elasticSearchClientService.getEsClient().prepareBulk();

		
		for(int i=0,len=indexMetas.length;i<len;i++) {
			IndexMeta indexMeta = indexMetas[i];
			XContentBuilder contentBuilder = contentBuilders[i];
			
			// either use client#prepare, or use Requests# to directly build index/delete requests
			bulkRequest.add(elasticSearchClientService.getEsClient().prepareIndex(indexMeta.getIndexName(),
				indexMeta.getIndexType(),indexMeta.getIndexId())
		        .setSource(contentBuilder)
				);
			
			}
		
		return bulkRequest.get();
	}
	
	@Override
	public BulkResponse deleteMultiIndexByBulk(IndexMeta[] indexMetas)
			throws IllegalArgumentException {
		if(null==indexMetas || indexMetas.length<=0) throw new IllegalArgumentException("indexMetas参数不能为空");
		
		BulkRequestBuilder bulkRequest = elasticSearchClientService.getEsClient().prepareBulk();

		
		for(int i=0,len=indexMetas.length;i<len;i++) {
			IndexMeta indexMeta = indexMetas[i];
			
			// either use client#prepare, or use Requests# to directly build index/delete requests
			bulkRequest.add(elasticSearchClientService.getEsClient().prepareDelete(indexMeta.getIndexName(),
				indexMeta.getIndexType(),indexMeta.getIndexId())
				);
			
			}
		
		return bulkRequest.get();
	}
	
	@Override
	public BulkProcessor indexBulkProcessor(List<ReplicationRequest<?>> requestList) {
		
		if(null==requestList || requestList.size()<=0) throw new IllegalArgumentException("requestList参数不能为空");
		
		BulkProcessor bulkProcessor = BulkProcessor.builder(elasticSearchClientService.getEsClient(),
				new BulkProcessor.Listener(){
			
			@Override
			public void beforeBulk(long executionId, BulkRequest request) {
				// This method is called just before bulk is executed
				System.out.println("==========bulk before execute=========");
			}
			
			@Override
			public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
				//This method is called after bulk execution
				System.out.println("==========bulk after execute=========");
			}
			
			@Override
			public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
				// his method is called when the bulk failed and raised a Throwable
				System.out.println("==========bulk execute failed and throwable=========");
			}
		})
		.setBulkActions(10) //We want to execute the bulk every 10 requests
		.setBulkSize(new ByteSizeValue(1, ByteSizeUnit.KB))  //We want to flush the bulk every 1gb
		.setFlushInterval(TimeValue.timeValueSeconds(3)) //We want to flush the bulk every 3 seconds whatever the number of requests
		.setConcurrentRequests(1) 
		.setBackoffPolicy(
	            BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3)) 
	        .build();
		
		requestList.forEach(request->{
			bulkProcessor.add(request);
		});
		
		return bulkProcessor;
	}
	
	@Override
	public SearchResponse indexSearch(String[] indexs,String[] types,QueryBuilder queryBuilder,RangeQueryBuilder rangeQueryBuilder,int size) {
		
		return elasticSearchClientService.getEsClient().prepareSearch(indexs)
		.setTypes(types)
		.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
		.setQuery(queryBuilder) // Query
		.setPostFilter(rangeQueryBuilder)     // Filter QueryBuilders.rangeQuery("age").from(12).to(18)
		.setFrom(0).setSize(size).setExplain(true)
        .execute()
        .actionGet();
	}

}
