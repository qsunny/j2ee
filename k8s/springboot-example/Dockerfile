# FROM frolvlad/alpine-oraclejdk8:slim
# FROM fabric8/java-alpine-openjdk8-jdk
FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.13_8
VOLUME /tmp
ADD build/libs/springboot-example-1.0.0-SNAPSHOT.jar app.jar
ENV JAVA_OPTS="-Xms256m -Xmx1024m -XX:+PrintFlagsFinal -XX:+PrintGCDetails -Duser.timezone=GMT+08"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Dspring.profiles.active=$env -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]









