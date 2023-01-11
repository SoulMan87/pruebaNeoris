FROM openjdk:17-jre-slim

ENV JAVA_OPTS " -Xms512m -Xmx512m -Djava.security.egd=file:/dev/./urandom"

WORKDIR application

COPY  ./././target/application-programming-interface-1.0.0.jar ./

ENTRYPOINT ["java", "-jar", "application-programming-interface-1.0.0.jar"]