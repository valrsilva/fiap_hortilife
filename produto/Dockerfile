FROM openjdk:8-jdk-alpine
MAINTAINER javaonfly
RUN apk --no-cache add netcat-openbsd
COPY target/produto-0.0.1-SNAPSHOT.jar /opt/lib/
COPY entrypoint-produto.sh /opt/bin/entrypoint-produto.sh
RUN chmod 755 /opt/bin/entrypoint-produto.sh
ENTRYPOINT ["/opt/bin/entrypoint-produto.sh"]
#CMD ["-jar", "/opt/lib/produto-0.0.1-SNAPSHOT.jar"]
EXPOSE 8081