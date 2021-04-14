FROM openjdk:11
VOLUME /tmp
ADD target/BlankPageFillerBackend-0.0.1-SNAPSHOT.jar app.jar
ADD _.lukasmarkert.de_private_key.p12 /src/main/resources/ssl/_.lukasmarkert.de_private_key.p12

ENTRYPOINT ["java","-jar","app.jar"]

EXPOSE 2222