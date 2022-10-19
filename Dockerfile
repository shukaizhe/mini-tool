
# 将当前目录下的jar包复制到docker容器的/目录下
#ADD /usr/local/lib/target/house-0.0.1-SNAPSHOT.jar /usr/local/lib/house-0.0.1-SNAPSHOT.jar
# 声明服务运行在8080端口
# 指定docker容器启动时运行jar包
#ENTRYPOINT ["java", "-jar","/usr/local/lib/house-0.0.1-SNAPSHOT.jar"]
# 指定维护者的名字
# Build stage
#
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
CMD ["java","-jar","/usr/local/lib/java-spring-boot-mongodb-starter-1.0.0.jar"]
EXPOSE 8080
MAINTAINER yugan2023