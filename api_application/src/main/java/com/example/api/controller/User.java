package com.example.api.controller;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private int age;
	
    @NotEmpty(message="First name cannot be missing or empty")
    @JsonProperty("firstName")
	private String firstName;
	
    @NotEmpty(message="Last name cannot be missing or empty")
    @JsonProperty("lastName")
	private String lastName;
	
    @NotNull(message="Birth date cannot be missing or empty")
    @JsonProperty("birthDate")
	private LocalDate birthDate;
	
}
