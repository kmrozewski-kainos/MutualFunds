FROM openjdk:8-jre

ADD wfe-services.jar wfe-services.jar
ADD config.yml config.yml

CMD java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5080 -jar wfe-services.jar --spring.config.location="file:/config.yml"

EXPOSE 8080 5080