FROM openjdk:11.0.1-jdk-sid
VOLUME /tmp
EXPOSE 9100
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
ENTRYPOINT exec java $JAVA_OPTS_AUTHENTICATION -Djava.security.egd=file:/dev/./urandom -jar /app.jar --spring.datasource.username=${POSTGRES_USERNAME} --spring.datasource.password=${POSTGRES_PASSWORD} --spring.datasource.url=${POSTGRES_URL} --security.jwt.secret=${JWT_SECRET} --eureka.client.service-url.defaultZone=${EUREKA_SERVICE_URL}
