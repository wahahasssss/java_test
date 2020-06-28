FROM centos
WORKDIR /root
VOLUME /tmp
ADD docker-headers.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
RUN yum  -y remove java*
RUN yum  -y install java-1.8.0-openjdk*
RUN yum install -y openssh-server
RUN mkdir /var/run/sshd
RUN echo "root:123456" | chpasswd
EXPOSE 22