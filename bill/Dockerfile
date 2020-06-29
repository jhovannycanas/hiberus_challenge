FROM openjdk:8
ADD build/libs/bill-0.0.1-SNAPSHOT.jar bill-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "bill-0.0.1-SNAPSHOT.jar"]