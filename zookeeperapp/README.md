zookeeper安装使用

1. [下载](http://apache.fayea.com/zookeeper/zookeeper-3.4.9/ "zookeeper download")
2.  ` chmod +x zookeeper-3.4.9.tar.gz`
3. `tar zxvf zookeeper-3.4.9.tar.gz`
4. `cd zookeeper-3.4.9/conf`
5. `cp -rf zoo_sample.cfg zoo.cfg`

6. 修改zoo.cfg    
    
       	tickTime=2000
    	dataDir=/data/datazookeeper
    	clientPort=2181

7 `bin/zkServer.sh start `

8 通过客户端连接服务端

    bin/zkCli.sh -server 127.0.0.1:2181
9 一些常用命令:

	ls /
	create /zk_test my_data
	ls /
	get /zk_test
	set /zk_test aaron
	delete /zk_test
	ls /
10 多台zookeeper配置:

    tickTime=2000
    dataDir=/var/lib/zookeeper
    clientPort=2181
    initLimit=5
    syncLimit=2
    server.1=192.168.1.104:2888:3888 #2888连接其它zookeeper的端口 3888用于连接leader端口
    server.2=192.168.1.112:2888:3888
    #server.3=zoo3:2888:3888
11 在dataDir目录新增文件myid，添加内容server.x中的x。指明自己的id.如上面中的1,2

12 [curator](http://curator.apache.org/index.html "curator")访问zookeeper简易库,github [访问路径](https://github.com/apache/curator)

13 注意版本的兼容: 

**VERSIONS
THE ARE CURRENTLY TWO RELEASED VERSIONS OF CURATOR, 2.X.X AND 3.X.X:

CURATOR 2.X.X - COMPATIBLE WITH BOTH ZOOKEEPER 3.4.X AND ZOOKEEPER 3.5.X
CURATOR 3.X.X - COMPATIBLE ONLY WITH ZOOKEEPER 3.5.X AND INCLUDES SUPPORT FOR NEW FEATURES SUCH AS DYNAMIC RECONFIGURATION, ETC.**	
