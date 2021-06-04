FROM openjdk:11.0.8-slim

MAINTAINER Victor Eras "veras@ec.krugercorp.com"

# Default to UTF-8 file.encoding
ENV LANG C.UTF-8

EXPOSE 8080

WORKDIR /api

COPY build/libs/*.jar /api/app.jar

RUN ls /api/

ENTRYPOINT exec java -jar app.jar