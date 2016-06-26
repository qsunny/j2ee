package com.aaron.search.core.service;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.replication.ReplicationRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.aaron.search.bean.AtmIndex;
import com.aaron.search.bean.IndexMeta;
import com.aaron.search.exception.SearchException;
import com.aaron.search.web.config.AppConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration("test-servlet-context.xml")
//@ContextConfiguration(classes={AppConfig.class})
public class TestEsIndex {

	private ApplicationContext applicationContext = null;
	private Client client = null;
	
	private IIndexSearchServcie indexSearchServcie;
	
	private String getDate(String format,Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	
	@Before
	public void setUp() throws Exception {
		applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		//client = applicationContext.getBean(IElasticSearchClientService.class).getEsClient();
		indexSearchServcie = applicationContext.getBean(IIndexSearchServcie.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetClient() {
		//assertNotNull(applicationContext);
		//assertNotNull(indexSearchServcie);
		//IElasticSearchClientService iec = applicationContext.getBean(IElasticSearchClientService.class);
		//assertNotNull(iec);
		
		assertNotNull(indexSearchServcie);
	}
	
	@Test
	public void testIndexJsonStr() {
		IndexMeta indexMeta = new IndexMeta();
		indexMeta.setIndexName("atm");
		indexMeta.setIndexType("blog");		
		JsonObject json = new JsonObject();
		json.addProperty("title", "My first blog entry");
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		json.addProperty("date",sdf.format(new Date()));
		System.out.println(json.toString());
		IndexResponse indexResponse = indexSearchServcie.createIndex(indexMeta, json.toString());
		System.out.println(indexResponse.isCreated());
	}
	
	@Test
	public void testIndeXContentBuilder() throws IOException {
		IndexMeta indexMeta = new IndexMeta();
		indexMeta.setIndexName("atm");
		indexMeta.setIndexType("blog");		
		XContentBuilder builder = XContentFactory.jsonBuilder()
	    .startObject()
	        .field("title", "My secend blog entry")
	        .field("date", getDate("yyyy/MM/dd HH:mm:ss",new Date()))
	    .endObject();
		IndexResponse indexResponse = indexSearchServcie.createIndex(indexMeta, builder);
		System.out.println(indexResponse.isCreated());
	}
	
	@Test
	public void testIndexMap() {
		IndexMeta indexMeta = new IndexMeta();
		indexMeta.setIndexName("atm");
		indexMeta.setIndexType("blog");	
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("title", "My three blog entry");
		map.put("date",getDate("yyyy/MM/dd HH:mm:ss",new Date()));
		System.out.println(map.toString());
		IndexResponse indexResponse = indexSearchServcie.createIndex(indexMeta, map);
		System.out.println(indexResponse.isCreated());
	}
	
	@Test
	public void testIndexByte() throws JsonProcessingException {
		IndexMeta indexMeta = new IndexMeta();
		indexMeta.setIndexName("atm");
		indexMeta.setIndexType("blog");	
		AtmIndex index = new AtmIndex();
		index.setDate(getDate("yyyy/MM/dd HH:mm:ss",new Date()));
		index.setTitle("My five blog entry");
		index.setViews("100");
		index.setText("A,ya a ya!");
		System.out.println(index.toString());
		ObjectMapper mapper = new ObjectMapper();
		byte[] json = mapper.writeValueAsBytes(index);
		IndexResponse indexResponse = indexSearchServcie.createIndex(indexMeta, json);
		System.out.println(indexResponse.isCreated());
	}
	
	@Test
	public void testIndexSearchByIndexMeta() {
		IndexMeta indexMeta = new IndexMeta();
		indexMeta.setIndexName("atm");
		indexMeta.setIndexType("blog");	
		indexMeta.setIndexId("AVS0LzcO7XdditAKzDmB");
		GetResponse response = indexSearchServcie.indexSearchByIndexMeta(indexMeta);
		response.getSource().forEach((key,value)->{
			System.out.println(key + "=====>" + value);
		});
	}
	
	@Test
	public void testDeleteIndex() {
		IndexMeta indexMeta = new IndexMeta();
		indexMeta.setIndexName("atm");
		indexMeta.setIndexType("blog");	
		//indexMeta.setIndexId("AVS0GIJu7XdditAKzDl6");
		DeleteResponse response = indexSearchServcie.deleteIndex(indexMeta);
		System.out.println(response.isFound()? "delete success": "delete failture");
	}
	
	@Test
	public void testUpdateIndexByPrepareUpdate() throws Exception {
		IndexMeta indexMeta = new IndexMeta();
		indexMeta.setIndexName("atm");
		indexMeta.setIndexType("blog");	
		indexMeta.setIndexId("1");
		XContentBuilder builder = XContentFactory.jsonBuilder()
			.startObject()
				.field("views", 10)
			.endObject();
		UpdateResponse response = indexSearchServcie.updateIndexByPrepareUpdate(indexMeta, builder);
		System.out.println(response.getShardInfo());
	}
	
	@Test
	public void testupdateIndexByUpdateRequest() throws Exception {
		IndexMeta indexMeta = new IndexMeta();
		indexMeta.setIndexName("atm");
		indexMeta.setIndexType("blog");	
		indexMeta.setIndexId("1");
		XContentBuilder builder = XContentFactory.jsonBuilder()
			.startObject()
				.field("views", "1000")
			.endObject();
		UpdateResponse response = indexSearchServcie.updateIndexByUpdateRequest(indexMeta, builder);
		System.out.println(response.getShardInfo());
	}
	
	@Test
	public void testUpdateIndexByUpsert() throws Exception {
		IndexMeta indexMeta = new IndexMeta();
		indexMeta.setIndexName("atm");
		indexMeta.setIndexType("blog");	
		indexMeta.setIndexId("5");
		XContentBuilder srcBuilder = XContentFactory.jsonBuilder()
			.startObject()
				.field("view","10")
				.field("views", "108")
			.endObject();
		
		XContentBuilder builder = XContentFactory.jsonBuilder()
				.startObject()
					.field("views", "1000")
				.endObject();
		
		UpdateResponse response = indexSearchServcie.updateIndexByUpsert(indexMeta, srcBuilder, builder);
		System.out.println(response.getShardInfo());
	}
	
	@Test
	public void testMultiGet() throws SearchException{
		IndexMeta indexMeta1 = new IndexMeta();
		indexMeta1.setIndexName("atm");
		indexMeta1.setIndexType("blog");
		indexMeta1.setIndexId("1");
		
		IndexMeta indexMeta2 = new IndexMeta();
		indexMeta2.setIndexName("atm");
		indexMeta2.setIndexType("blog");
		indexMeta2.setIndexId("123");
		
		IndexMeta indexMeta3 = new IndexMeta();
		indexMeta3.setIndexName("bank");
		indexMeta3.setIndexType("account");
		indexMeta3.setIndexId("25");
		
		MultiGetResponse multiGetItemResponses = indexSearchServcie.getMultiGetSearch(new IndexMeta[]{indexMeta1,indexMeta2,indexMeta3});
		
		for (MultiGetItemResponse itemResponse : multiGetItemResponses) { 
		    GetResponse response = itemResponse.getResponse();
		    if (response.isExists()) {                      
		        String json = response.getSourceAsString(); 
		        System.out.println(json);
		    }
		}
		
	}
	
	@Test
	public void testcreateMultiIndexByBulk() throws Exception {
		IndexMeta indexMeta1 = new IndexMeta();
		indexMeta1.setIndexName("atm");
		indexMeta1.setIndexType("blog");
		indexMeta1.setIndexId("6");
		
		IndexMeta indexMeta2 = new IndexMeta();
		indexMeta2.setIndexName("atm");
		indexMeta2.setIndexType("blog");
		indexMeta2.setIndexId("7");
		
		IndexMeta indexMeta3 = new IndexMeta();
		indexMeta3.setIndexName("atm");
		indexMeta3.setIndexType("blog");
		indexMeta3.setIndexId("8");
		
		XContentBuilder contentBuilder1 = XContentFactory.jsonBuilder()
        .startObject()
            .field("title", "title6")
            .field("text", "test6 another post")
            .field("date", getDate("yyyy/MM/dd HH:mm:ss",new Date()))
            .field("views", "60")
            .field("view", "6")
        .endObject();
		
		XContentBuilder contentBuilder2 = XContentFactory.jsonBuilder()
		        .startObject()
	            .field("title", "title7")
	            .field("text", "test7 another post")
	            .field("date", getDate("yyyy/MM/dd HH:mm:ss",new Date()))
	            .field("views", "70")
	            .field("view", "7")
	        .endObject();
		
		XContentBuilder contentBuilder3 = XContentFactory.jsonBuilder()
		        .startObject()
	            .field("title", "title8")
	            .field("text", "test8 another post")
	            .field("date", getDate("yyyy/MM/dd HH:mm:ss",new Date()))
	            .field("views", "80")
	            .field("view", "8")
	        .endObject();
		
		BulkResponse bulkResponse = indexSearchServcie.createMultiIndexByBulk(new IndexMeta[]{
				indexMeta1,indexMeta2,indexMeta3
			}, new XContentBuilder[]{
					contentBuilder1,contentBuilder2,contentBuilder3	
			});
		
		if (bulkResponse.hasFailures()) {
		    // process failures by iterating through each bulk response item
			System.out.println(".......execute failure.......");
			System.out.println(bulkResponse.buildFailureMessage());
		}
		
	}
	
	@Test
	public void testdeleteMultiIndexByBulk() throws Exception {
		IndexMeta indexMeta1 = new IndexMeta();
		indexMeta1.setIndexName("atm");
		indexMeta1.setIndexType("blog");
		indexMeta1.setIndexId("6");
		
		IndexMeta indexMeta2 = new IndexMeta();
		indexMeta2.setIndexName("atm");
		indexMeta2.setIndexType("blog");
		indexMeta2.setIndexId("7");
		
		IndexMeta indexMeta3 = new IndexMeta();
		indexMeta3.setIndexName("atm");
		indexMeta3.setIndexType("blog");
		indexMeta3.setIndexId("");
		
		BulkResponse bulkResponse = indexSearchServcie.deleteMultiIndexByBulk(new IndexMeta[]{
				indexMeta1,indexMeta2,indexMeta3
			});
		
		if (bulkResponse.hasFailures()) {
		    // process failures by iterating through each bulk response item
			System.out.println(".......execute failure.......");
			System.out.println(bulkResponse.buildFailureMessage());
		}
		
	}
	
	@Test
	public void testIndexBulkProcessor() throws Exception {
		List<ReplicationRequest<?>> requestList = new ArrayList<ReplicationRequest<?>>(); 
		for(int i=1;i<=300;i++) {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("title", "processor title"+i);
			map.put("text", "processor test text"+i);
			map.put("date",getDate("yyyy/MM/dd HH:mm:ss",new Date()));
			map.put("views", i+10);
			map.put("view", i);
			IndexRequest request = new IndexRequest("atm","blog").source(map);
			requestList.add(request);
		}
		
		BulkProcessor processor = indexSearchServcie.indexBulkProcessor(requestList);
		processor.awaitClose(5, TimeUnit.MINUTES);
	}
	
	@Test
	public void tsetIndexSearch() {
		String[] indexs = new String[]{"bank"};
		String[] types = new String[]{"account"};
		
		QueryBuilder ctq = QueryBuilders.commonTermsQuery("firstname","Virginia");
		//termQuery 只能查询匹配到小写
		QueryBuilder tq = QueryBuilders.termQuery("firstname", "virginia");
		QueryBuilder maq = QueryBuilders.matchAllQuery();
		QueryBuilder wcq = QueryBuilders.wildcardQuery("firstname", "b*");
		QueryBuilder regq = QueryBuilders.regexpQuery("firstname","s.*y");  
		
		QueryBuilder fuq = QueryBuilders.fuzzyQuery("firstname", "ri");
		
		RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("age").from(12).to(45);
		
		QueryBuilder smtqb = QueryBuilders.spanMultiTermQueryBuilder(
				QueryBuilders.prefixQuery("firstname", "nie")                   
			);
		SearchResponse response = indexSearchServcie.indexSearch(indexs, types, wcq, rangeQueryBuilder, 30);
		
		response.getHits().forEach(hit -> {
			System.out.println(hit.getSourceAsString());
			hit.getSource().forEach((key,value)->{
				System.out.println("====" +key + "======>" + value);
			});
		});
	}
	
	@Test
	public void testScrollSearch() {
		String[] indexs = new String[]{"bank","atm"};
		String[] types = new String[]{"account","blog"};
		//termQuery 只能查询匹配到小写
		QueryBuilder tq = QueryBuilders.termQuery("firstname", "virginia");
		RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("age").from(12).to(45);
		SortBuilder sortBuilder = SortBuilders.fieldSort("age").order(SortOrder.ASC);
		
		SearchResponse scrollResp = indexSearchServcie.indexScrollSearch(indexs, types, null, null, 5, sortBuilder);
		
	}
	
	@Test
	public void testCountIndex() {
		String[] indexs = new String[]{"bank","atm"};
		String[] types = new String[]{"account","blog"};
		long count = indexSearchServcie.indexCount(indexs, types, null);
		System.out.println(count);
	}
	
 
}
