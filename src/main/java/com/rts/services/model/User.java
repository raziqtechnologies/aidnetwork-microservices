package com.rts.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty("id")
	
	private int id;
	@JsonProperty("addressline")
	private String addressline;
	@JsonProperty("createdate")
	private long createdate;
	@Column(unique = true)
	@JsonProperty("emailid")
	private String emailid;
	@JsonProperty("firstname")
	private String firstname;
	@JsonProperty("lastname")
	private String lastname;
	@JsonProperty("landmark")
	private String landmark;
	@JsonProperty("mobilenumber")
	private String mobilenumber;
	@JsonProperty("ngo")
	private String ngo;
	@JsonProperty("parentid")
	private String parentid;
	@JsonProperty("pincode")
	private String pincode;
	@JsonProperty("role")
	private String role;
	
	private String password;
	
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	

}
