FROM openjdk:8-jdk-alpine
COPY target/zuul-server-0.0.1-SNAPSHOT.jar /opt/lib/
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/opt/lib/zuul-server-0.0.1-SNAPSHOT.jar"]
EXPOSE 8184