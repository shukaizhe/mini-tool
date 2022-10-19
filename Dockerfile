FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

#
# Package stage
#
# 该镜像需要依赖的基础镜像
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/* /usr/local/lib/
CMD ["java","-jar","/usr/local/lib/house-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
MAINTAINER yugan2023