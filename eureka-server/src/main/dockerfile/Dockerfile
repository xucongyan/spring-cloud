from java

MAINTAINER xucongyan 838587329@qq.com

VOLUME /tmp

ADD eureka.jar app.jar

RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]