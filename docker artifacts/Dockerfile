FROM tomcat
LABEL MAINTAINER "pranali.pagariya@gmail.com"
ADD camel.war /usr/local/tomcat/webapps1/ROOT.war
ADD api.war /usr/local/tomcat/webapps2/ROOT.war
RUN apt-get update
RUN apt-get install -y apache2
RUN ln -s /etc/apache2/mods-available/proxy.conf /etc/apache2/mods-enabled/proxy.conf
RUN ln -s /etc/apache2/mods-available/proxy.load /etc/apache2/mods-enabled/proxy.load
RUN ln -s /etc/apache2/mods-available/proxy_http.load /etc/apache2/mods-enabled/proxy_http.load
RUN ln -s /etc/apache2/mods-available/rewrite.load /etc/apache2/mods-enabled/rewrite.load
ADD rewriteRule.conf /etc/apache2/conf-enabled/
RUN sed -i 's|#Include conf-available/serve-cgi-bin.conf|Include conf-enabled/rewriteRule.conf|' /etc/apache2/sites-available/000-default.conf
COPY server.xml /usr/local/tomcat/conf/server.xml
EXPOSE 80
EXPOSE 9090
CMD  service apache2 start; /usr/local/tomcat/bin/catalina.sh run
