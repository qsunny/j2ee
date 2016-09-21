package com.aaron.zookeeper;

import com.aaron.zookeeper.core.service.zookeeperservice.ZookeeperClient;
import com.aaron.zookeeper.manager.config.AppConfig;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.nio.charset.Charset;

/**
 * Created by Administrator on 2016/9/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class TestZookeeper {

    @Autowired
    private ZookeeperClient zookeeperClient;

    private CuratorFramework client;

    @Before
    public void setUp() {
        client = zookeeperClient.createSimple("192.168.1.104");
        client.start();
    }

    @After
    public void tearDown() throws Exception {
        client.close();
    }

    @Test
    public void testCreate() throws Exception {
        client.create().forPath("/test_app","testData".getBytes(Charset.forName("utf-8")));
    }

    @Test
    public void testSetData() throws Exception {
        client.setData().forPath("/test_app","aaronData".getBytes(Charset.forName("utf-8")));
    }

    @Test
    public void testDelete() throws Exception {
        client.delete().forPath("/test_app");
    }

    @Test
    public void testGuaranteedDelete() throws Exception {
        client.delete().guaranteed().forPath("/test_app");
    }

    @Test
    public void testGetData() throws Exception {
        byte[] data = client.getData().forPath("/test_app");
        System.out.println(">>>>>>>>>>>"+new String(data,Charset.forName("utf-8")));
    }

    @Test
    public void testChildren() throws Exception {
        client.getChildren().forPath("/").forEach(item->{
            System.out.println(item);
        });
    }

    @Test
    public void testcCeateEphemeral() throws Exception{
        // this will create the given EPHEMERAL ZNode with the given data
        client.create().withMode(CreateMode.EPHEMERAL).forPath("/test_ephemeral", "ephemeralData".getBytes(Charset.forName("utf-8")));
    }

    @Test
    public void testWatchedGetChildren() throws Exception {
        client.getChildren().watched().forPath("/").forEach(item->{
            System.out.println(item);
        });
    }

    @Test
    public void testWatchedGetChildren2() throws Exception {
        client.getChildren().usingWatcher(new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(watchedEvent.toString());
            }
        }).forPath("/").forEach(item->{
            System.out.println(item);
        });
    }

}
