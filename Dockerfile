FROM openjdk:17
VOLUME /tmp
COPY --chown=185 src/main/resources/keystore.jks /tmp/keystore.jks
COPY --chown=185 src/main/resources/truststore.jks /tmp/truststore.jks
COPY --chown=185 src/main/resources/test8.p12 /tmp/test8.p12
#COPY --chown=185 src/main/resources/ca_bundle.crt /tmp/ca_bundle.crt
#COPY --chown=185 src/main/resources/certificate.crt /tmp/certificate.crt
#COPY --chown=185 src/main/resources/private.key /tmp/private.key
ENV JAVA_OPTS="-Djavax.net.ssl.trustStore=/tmp/truststore.jks -Djavax.net.ssl.trustStorePassword=secret -Djavax.net.ssl.keyStore=/tmp/keystore.jks -Djavax.net.ssl.keyStorePassword=secret"
EXPOSE 8080
ARG JAR_FILE=target/spring-test-ssl-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} spring-test-ssl.jar
ENTRYPOINT ["java","-Djavax.net.ssl.trustStore=/tmp/truststore.jks","-Djavax.net.ssl.trustStorePassword=secret","-Djavax.net.ssl.keyStore=/tmp/keystore.jks","-Djavax.net.ssl.keyStorePassword=secret","-jar","/spring-test-ssl.jar"]
#CMD java ${JAVA_OPTS} -jar spring-test-ssl.jar





