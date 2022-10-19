# 该镜像需要依赖的基础镜像
FROM openjdk:8
CMD 'mvn package'
# 将当前目录下的jar包复制到docker容器的/目录下
#ADD /usr/local/lib/target/house-0.0.1-SNAPSHOT.jar /usr/local/lib/house-0.0.1-SNAPSHOT.jar
# 声明服务运行在8080端口
EXPOSE 8080
# 指定docker容器启动时运行jar包
#ENTRYPOINT ["java", "-jar","/usr/local/lib/house-0.0.1-SNAPSHOT.jar"]
# 指定维护者的名字
MAINTAINER yugan2023