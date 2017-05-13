1. 运行示例addressbook.proto需要安装protobuf编译器,安装详细参考它的官方文档: https://github.com/golang/protobuf/
2. 示例代码参考自它的官方文档: https://developers.google.com/protocol-buffers/docs/javatutorial#builders
3. 编译它的protoc文件命令: 
 `D:\workspaceidea\j2ee\protobuf\helloworld\src\main\resources>protoc -I=. --java_out=..\java addressbook.proto`