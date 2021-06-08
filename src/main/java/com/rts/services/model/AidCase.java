package com.rts.services.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rts.services.extension.CaseIdGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class AidCase {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "case_seq")
    @GenericGenerator(
        name = "case_seq", 
        strategy = "com.rts.services.extension.CaseIdGenerator", 
        parameters = {
        		@Parameter(name = CaseIdGenerator.INCREMENT_PARAM, value = "1"),
                @Parameter(name = CaseIdGenerator.VALUE_PREFIX_PARAMETER, value = "MM"),
                @Parameter(name = CaseIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") 
        })
	@JsonProperty("id")
	private String id;
	@JsonProperty("address1")
	private String address1;
	@JsonProperty("areagroupid")
	private String areagroupid;
	@JsonProperty("areagroupname")
	private String areagroupname;
	@JsonProperty("areaid")
	private String areaid;
	@JsonProperty("areaname")
	private String areaname;
	@JsonProperty("bencardtype")
	private String bencardtype;
	@JsonProperty("bendescription")
	private String bendescription;
	@JsonProperty("benname")
	private String benname;
	@JsonProperty("benphone")
	private String benphone;
	@JsonProperty("callercategory")
	private String callercategory;
	@JsonProperty("callername")
	private String callername;
	@JsonProperty("callerphone")
	private String callerphone;
	@JsonProperty("caseaffectno")
	private int caseaffectno;
	@JsonProperty("casecategory")
	private String casecategory;
	@JsonProperty("casedescription")
	private String casedescription;
	@JsonProperty("casepriority")
	private String casepriority;
	@JsonProperty("createdate")
	private Date createdate;
	@JsonProperty("landmark")
	private String landmark;
	@JsonProperty("operator")
	private String operator;
	@JsonProperty("pincode")
	private String pincode;
	@JsonProperty("poc")
	private String poc;
	@JsonProperty("sameascaller")
	private boolean sameascaller;
	@JsonProperty("status")
	private String status;
	@JsonProperty("volunteer")
	private String volunteer;
	@JsonProperty("count")
	private int count;
	@JsonProperty("calcatvalues")
	private String calcatvalues;
	
	public String getBenname() {
		return benname;
	}
	public void setBenname(String benname) {
		this.benname = benname;
	}
	public String getPoc() {
		return poc;
	}
	public void setPoc(String poc) {
		this.poc = poc;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	

}
