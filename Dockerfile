FROM openjdk:8u191-jdk-alpine3.9
ARG PROJECT_NAME=${PROJECT_NAME}
ARG PROJECT_VERSION=${PROJECT_VERSION}
COPY ${PROJECT_NAME}-${PROJECT_VERSION}.jar app.jar
ENTRYPOINT java -XX:+UnlockExperimentalVMOptions \
  -XX:+UseCGroupMemoryLimitForHeap -Djava.security.egd=file:/dev/./urandom ${JAVA_OPTS} -jar /app.jar