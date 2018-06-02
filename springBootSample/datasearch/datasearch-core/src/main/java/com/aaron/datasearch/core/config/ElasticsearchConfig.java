package com.aaron.datasearch.core.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;

/**
 * Created by Aaron.qiu on 2018/6/2.
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.aaron.datasearch.core.dao")
public class ElasticsearchConfig {

    @Value("${elasticsearch.host}")
    private String EsHost;

    @Value("${elasticsearch.port}")
    private int EsPort;

    @Value("${elasticsearch.clustername}")
    private String EsClusterName;
    @Autowired
    ElasticsearchOperations operations;

//    @Bean
//    public NodeClientFactoryBean client() {
//
//        NodeClientFactoryBean bean = new NodeClientFactoryBean(true);
//        bean.setClusterName(UUID.randomUUID().toString());
//        bean.setEnableHttp(false);
//        bean.setPathData("target/elasticsearchTestData");
//        bean.setPathHome("src/test/resources/test-home-dir");
//        return bean;
//    }

    @Bean(destroyMethod = "close")
    public Client client() throws Exception {

        Settings esSettings = Settings.builder()
                .put("cluster.name", EsClusterName)
                .build();

        //https://www.elastic.co/guide/en/elasticsearch/guide/current/_transport_client_versus_node_client.html
//        PreBuiltTransportClient client = new PreBuiltTransportClient(esSettings);

//        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
        TransportClient client = new PreBuiltTransportClient(esSettings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(EsHost), EsPort));
//                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host2"), 9300));
        return client;
    }

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate(Client client) throws Exception {
        return new ElasticsearchTemplate(client);
    }

//    @PreDestroy
//    public void deleteIndex() {
//        operations.deleteIndex(Conference.class);
//    }


}
