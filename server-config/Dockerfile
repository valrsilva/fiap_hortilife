FROM alpine-jdk:base
MAINTAINER javaonfly
COPY target/server-config-0.0.1-SNAPSHOT.jar /opt/lib/
RUN mkdir /var/lib/config-repo
COPY src/main/resources/config-repo /var/lib/config-repo
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/opt/lib/server-config-0.0.1-SNAPSHOT.jar", "-cp", "/config-repo"]
VOLUME /var/lib/config-repo
EXPOSE 8888