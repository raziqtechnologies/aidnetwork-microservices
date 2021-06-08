package com.rts.services.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Data
public class Comment {
	@Id
	@JsonProperty("id")
	private int id;
	@JsonProperty("caseid")
	private String caseid;
	@JsonProperty("comments")
	private String comments;
	@JsonProperty("createdate")
	private Date createdate;
	@JsonProperty("role")
	private String role;
	@JsonProperty("user")
	private String user;

}
