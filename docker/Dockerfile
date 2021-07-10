FROM java:8
WORKDIR /root/server/
ENV active=dev
COPY target/*.jar app.jar
VOLUME ["/var/log"]
EXPOSE 8080:8080
MAINTAINER jiangmingyang6ge6@163.com
ENTRYPOINT ["java","-jar","-Xmx=256m -Xms=256m","-Dspring.profiles.active=${active}","app.jar"]
