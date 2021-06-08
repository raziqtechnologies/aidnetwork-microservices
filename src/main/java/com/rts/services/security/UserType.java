package com.rts.services.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserType {

	private  boolean selected = false;
	
	private boolean operator = false;
	private boolean poc = false;
	private boolean volunteer = false;
	private boolean administrator = false;
	private String name = null;
	

	public UserType() {
		String usercred[] = getRoleAndName();
		if (usercred[0].equals("ROLE_OPERATOR")) {
			operator = true;
			
	
		}
		else if(usercred[0].equals("ROLE_POC"))
		{
			poc = true;
			
		}
		else if(usercred[0].equals("ROLE_VOLUNTEER"))
		{
			volunteer = true;
			
		}
		else if(usercred[0].equals("ROLE_ADMINISTRATOR"))
		{
			administrator = true;
		}
		this.name = usercred[1];
	}

	public  void init() {
		if (!selected) {
			
		}
		
	}
	
	public void setPOC(String name)
	{
		poc = true;
		this.name = name;
	}
	
	public String getPOC() {
		if(isPoc())
		{
			return name;
		}
		return null;
	}
	
	public String getVolunteer() {
		if(isVolunteer())
		{
			return name;
		}
		return null;
	}
	
	public String getOperator() {
		if(isOperator())
		{
			return name;
		}
		return null;
	}

	public boolean isPoc() {
		return poc;
	}
	
	public boolean isAdministrator() {
		return administrator;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isOperator() {
		return operator;
	}
	public boolean isVolunteer() {
		return volunteer;
	}

	public void setPoc(boolean poc) {
		this.poc = poc;
	}

	public void setOperator(boolean operator) {
		this.operator = operator;
	}

	public void setVolunteer(boolean volunteer) {
		this.volunteer = volunteer;
	}

	public static String[] getRoleAndName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		GrantedAuthority role = authentication.getAuthorities().iterator().next();
		String values = role.getAuthority() + "," + authentication.getName();
		return values.split(",");
	}

}
