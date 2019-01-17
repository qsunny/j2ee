echo '构建新镜像'
gradle clean  build -x test docker --info

if docker ps -a|grep -i springbootdemo/springbootdemo-web;then
   echo '删除旧容器'
   docker rm -f springbootdemo/springbootdemo-web
fi

if docker images|grep "none"|awk '{print $3}';then
   echo '删除旧镜像'
   docker rmi $(docker images|grep "none"|awk '{print $3}')
fi

echo '构建新容器'
docker run -d -p 5050:5050 -m 500m -e 'server.port=5050' -e 'spring.profiles.active=prod' -v /zhuxuan/service/springbootdemo/springbootdemo-web/logs/:/logs/ --name springbootdemo