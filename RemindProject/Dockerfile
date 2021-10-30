FROM tomcat:9-jre8-alpine
RUN rm -Rf /usr/local/tomcat/webapps/ROOT
COPY target/ROOT.war /usr/local/tomcat/webapps/
ENV TZ=Asia/Seoul