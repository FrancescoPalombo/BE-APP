FROM registry.access.redhat.com/ubi9/openjdk-21:latest
COPY ./build/libs/be-app-0.0.1-SNAPSHOT.jar /deployments/app.jar