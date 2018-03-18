FROM openjdk:8-jre

ADD wfe-services.jar wfe-services.jar
ADD application.properties application.properties

CMD java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5080 -jar wfe-services.jar -Dspring.config.location="file:wfe-services/application.properties"

EXPOSE 8080 5080