FROM openjdk:8-jdk-alpine
RUN apk --no-cache add netcat-openbsd
COPY target/notificacao-0.0.1-SNAPSHOT.jar /opt/lib/
COPY entrypoint-notificacao.sh /opt/bin/entrypoint-notificacao.sh
RUN chmod 755 /opt/bin/entrypoint-notificacao.sh
ENTRYPOINT ["/opt/bin/entrypoint-notificacao.sh"]
#CMD ["-jar", "/opt/lib/notificacao-0.0.1-SNAPSHOT.jar"]
EXPOSE 8082