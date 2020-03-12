package com.example.api.resource;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.api.controller.User;
import com.example.api.controller.UserController;
import com.example.api.exception.DataPersistenceException;

@Component
public class UserRestRoute extends RouteBuilder {

	@Autowired
	private UserController userController;

	@Override
	public void configure() throws Exception {

		restConfiguration().component("jetty").bindingMode(RestBindingMode.json)
				.dataFormatProperty("prettyPrint", "true").port(9090).setSkipBindingOnErrorCode(false);;

		rest("/api").description("get User with Id").consumes("application/json").produces("application/json")
				.get("/users/{id}").description("Find user by id").outType(User.class).to("?throwExceptionOnFailure=false")
				.to("bean:userController?method=getUser(${header.id})");

		rest("/api").get("/getUsers").description("Find all users").outType(User.class)
				.to("bean:userController?method=getAllUsers");

		rest("/api").post("/addUser").consumes(MediaType.APPLICATION_JSON_VALUE).description("Post a user ")
				.type(User.class).outType(User.class).route().process(new Processor() {

					@Override
					public void process(Exchange exchange){
						try {
							ResponseEntity<String> response =userController.addUsers(exchange.getIn().getBody(User.class));
							exchange.getIn().setBody(response);
						}catch (DataPersistenceException e) {
							exchange.getIn().setBody(e.getMessage());
						}
						
					}
				}).endRest();

		rest("/api").get("?firstName={firstName}&lastName={lastName}")
				.description("Get the user with specified first and last name").route().process(new Processor() {

					@Override
					public void process(Exchange exchange) {
						
						try {
							exchange.getIn()
							.setBody(userController.getMatchedUsers(
									(String) exchange.getIn().getHeader("firstName"),
									(String) exchange.getIn().getHeader("lastName")));
						}catch(Exception e) {
							exchange.getIn().setBody(e.getMessage());
						}
						
						

					}
				}).endRest();
	}
}
