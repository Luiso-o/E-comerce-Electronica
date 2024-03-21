FROM openjdk:17

WORKDIR /pcstore

COPY build/libs/pcstore-0.0.1-SNAPSHOT.jar /pcstore/store.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/pcstore/store.jar"]

