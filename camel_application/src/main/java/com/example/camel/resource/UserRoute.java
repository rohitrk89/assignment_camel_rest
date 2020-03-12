package com.example.camel.resource;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class UserRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// route to post Api
		from("jetty:http://localhost:8080/addUser").process(new Processor() {

			public void process(Exchange exchange) throws Exception {
				HttpServletRequest request = exchange.getIn().getBody(HttpServletRequest.class);
				Map<String, String> map = new HashMap<String, String>();
				for (Entry<String, String[]> entry : request.getParameterMap().entrySet())
					map.put(entry.getKey(), entry.getValue()[0]);
				exchange.getIn().setBody(map);
			}

		}).marshal().json(JsonLibrary.Jackson).setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
				.to("http://localhost:8081/api/addUser?bridgeEndpoint=true&throwExceptionOnFailure=false").endRest();

		// get user with id
		from("jetty:http://localhost:8080/users/{id}").setHeader(Exchange.CONTENT_TYPE, constant("text"))
				.to("http://localhost:8081/api?bridgeEndpoint=true&throwExceptionOnFailure=false").endRest();

		// route to get with names
		from("jetty:http://localhost:8080/").setHeader(Exchange.CONTENT_TYPE, constant("text"))
				.to("http://localhost:8081/api/?copyHeaders=false&bridgeEndpoint=true&throwExceptionOnFailure=false")
				.setHeader(Exchange.CONTENT_TYPE, constant("application/json")).endRest();

		// route to getAllUsers
		from("jetty:http://localhost:8080/getUsers").setHeader(Exchange.CONTENT_TYPE, constant("text")).to(
				"http://localhost:8081/api/getUsers?copyHeaders=false&bridgeEndpoint=true&throwExceptionOnFailure=false")
				.setHeader(Exchange.CONTENT_TYPE, constant("application/json")).endRest();

	}

}
