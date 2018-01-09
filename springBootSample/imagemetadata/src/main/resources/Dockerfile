FROM daftano/java:8

LABEL maintainer-twitter="@daftano"

EXPOSE 8080

ADD build/image-metadata-1.0.0.jar /opt/image-metadata.jar

ENTRYPOINT java -jar /opt/image-metadata.jar