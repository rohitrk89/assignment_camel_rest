
#api-application

	1)api-application contains user rest API endpoints implementation.
	2)Consuming the Rest API using Rest DSL Component and Jetty Component of apache camel
	3)Check com.example.api.resource.UserRestRoute.java for implementation

#camel application

	1)camel-application consumes the Rest services exposed by api_application using Jetty component of apache camel .
	2)Consuming data in MULTIPART_FORM_DATA format , converting to JSON and then requesting the Rest Post API .
	3)Check com.example.camel.resource.UserRoute.java for implementation

#URL endpoints:

API endpoints are not directly exposed but can be called by the following endpoints

Flow 1

Camel-Jetty (port 8080) > REST API (port 8081)

	1) Retrieve user with specified ID => http://localhost:80/users/id
	2) Retrieve all the users 	   => http://localhost:80/getUsers
	3) Add users 			   => http://localhost:80/addUser
   		For eg :
		Select form-data body in POSTMAN and set below key value
			
		firstName : "Rani",
		lastName  : "Rajput",
		birthDate : "1990-11-13"

	4) Retrieve users that match firstName and lastName => http://localhost:80?firstName="Rani"&lastName="Rajput"

Flow 2

Camel Rest DSL (Port 9090) > REST API (port 8081)

	1) Retrieve user with specified ID => http://localhost:9090/api/users/id
	2) Retrieve all the users 	   => http://localhost:9090/api/getUsers
	3) Add users 			   => http://localhost:9090/api/addUser
   		For eg :
		{	
			firstName : "Rani",
			lastName  : "Rajput",
			birthDate : "1990-11-13"
		}

	4) Retrieve users that match firstName and lastName => http://localhost:8080/api?firstName="Rani"&lastName="Rajput"

#Docker implementation

In container, have tried to deploy this in standard way i.e. webserver(apache httpd) and Application server (Apache Tomcat),
where httpd acts as reverse proxy and Tomcat is where business logic is deployed.

To build the container you will need
		
	1) api.war
	2) camel.war
	3) server.xml
	4) rewriteRule.conf
	5) Dockerfile

Once you have above mentioned artifacts to build the container run:

` docker build <image_name> . `

` docker build assignment . `


Once built, run container with `` -p 80:80 -p 9090:9090 `` and test


#Few References

	https://camel.apache.org/

	https://camel.apache.org/manual/latest/rest-dsl.html

	https://camel.apache.org/components/latest/http-component.html

	https://www.javainuse.com/camel/camel-consume-rest

	https://opensource.com/article/18/9/camel-rest-dsl

	stackOverflow.com

	https://docs.docker.com/

	https://aspetraining.com/resources/blog/deploying-your-first-web-app-to-tomcat-on-docker

	https://www.javainuse.com/devOps/docker/docker-war

	https://httpd.apache.org/docs/2.4/howto/reverse_proxy.html


#Tools used :

	Eclipse IDE

	Postman
       
#Build Tool :

	Maven