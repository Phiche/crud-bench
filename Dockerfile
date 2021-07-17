FROM openjdk:8-alpine
VOLUME /tmp
COPY build/libs/*.jar crud-bench.jar
ENTRYPOINT ["java","-jar","/crud-bench.jar"]
EXPOSE 8080