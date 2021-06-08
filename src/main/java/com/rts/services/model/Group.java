package com.rts.services.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;

import lombok.Data;

@Entity
@Data
@Subselect("select * from group")
public class Group {
	
	
	@Id
	private String name;
	private long count;
	
	public Group(String name, long count)
	{
		this.name = name;
		this.count = count;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	
	

}
