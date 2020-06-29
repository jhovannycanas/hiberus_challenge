FROM openjdk:8
ADD build/libs/chekout-0.0.1-SNAPSHOT.jar chekout-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "chekout-0.0.1-SNAPSHOT.jar"]