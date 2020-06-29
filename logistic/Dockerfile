FROM openjdk:8
ADD build/libs/logistic-0.0.1-SNAPSHOT.jar logistic-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "logistic-0.0.1-SNAPSHOT.jar"]