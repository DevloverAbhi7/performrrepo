package com.devlover.app.performr.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class Users 
{
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	@NotNull
	private String firstname;
	
	@Column
	@NotNull
	private String lastname;
	
	
	@Pattern(regexp = "^[0-9]{10}$",message = "Enter Valid Mobile Number")
	@Column(unique = true)
	private String username;
	
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",message = "Enter Valid Password")
	@Column
	private String password;
	
	@JsonIgnore
	 public String getPassword() {
	    return password;
	 }
	
	
	@JsonProperty
	 public void setPassword(String password) {
	    this.password = password;
	 }	
}
