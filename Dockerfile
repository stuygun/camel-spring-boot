FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /camel-spring-boot/lib
COPY ${DEPENDENCY}/META-INF /camel-spring-boot/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /camel-spring-boot
ENTRYPOINT ["java","-cp","camel-spring-boot:camel-spring-boot/lib/*","com.tuygun.sandbox.camelspringboot.CamelSpringBootApplication"]