package com.rts.services.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class AreaGroup {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty("id")
	private int id;
	@JsonProperty("areagroup")
	@NotBlank(message = "Areagroup is mandatory")
	private String areagroup;
	@JsonProperty("createdate")
	private Date createdate;
	
	@JsonProperty("pocsid")
	private String pocsid;
	
	@JsonProperty("areasid")
	private String areasid;
	
	@Transient 
	private List<Area> areas = new ArrayList<Area>();
	@Transient 
	private List<User> pocs = new ArrayList<User>();
	public List<Area> getAreas() {
		return areas;
	}
	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}
	public List<User> getPocs() {
		return pocs;
	}
	public void setPocs(List<User> pocs) {
		this.pocs = pocs;
	}
	public String getPocsid() {
		return pocsid;
	}
	public void setPocsid(String pocsid) {
		this.pocsid = pocsid;
	}
	public String getAreasid() {
		return areasid;
	}
	public void setAreasid(String areasid) {
		this.areasid = areasid;
	}
	public String getAreagroup() {
		return areagroup;
	}
	public void setAreagroup(String areagroup) {
		this.areagroup = areagroup;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	

}
