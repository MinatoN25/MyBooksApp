package com.stackroute.userservice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import io.swagger.annotations.ApiModelProperty;

@Entity
public class User {

	@ApiModelProperty(name="User Id",notes="Id of the user",dataType="string")
	@Id
	@Column(length=50)
	private String userId;
	
	@ApiModelProperty(name="Password",notes="Password for the user",dataType="string")
	@Column
	private String password;
	
	@ApiModelProperty(name="First Name",notes="First Name of the user",dataType="string")
	@Column
	private String firstName;
	
	@ApiModelProperty(name="Last Name",notes="Last Name of the user",dataType="string")
	@Column
	private String lastName;

	
	public User(String userId, String firstName, String lastName, String password) {
		super();
		this.userId = userId;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public User(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}

	public User() {
		super();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + "]";
	}
	
	
}