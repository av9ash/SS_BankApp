package org.asu.cse545.group4.server.sharedobjects;

import java.io.Serializable; 

public class AppointmentUserInfo 
{ 
	private String firstName;
	private String lastName;
	private String email;
	private String dateCreated;
	

	public AppointmentUserInfo(String firstName, String lastName, 
				String email, String dateCreated) 
	{ 
		this.firstName = firstName; 
		this.lastName = lastName; 
		this.email = email; 
		this.dateCreated = dateCreated; 
	} 

	public String getFirstName() 
	{ 
		return this.firstName; 
	} 

	public void setFirstName(String firstName) 
	{ 
		this.firstName = firstName; 
	}  

	public String getLastName() 
	{ 
		return this.lastName; 
	} 

	public void setLastName(String lastName) 
	{ 
		this.lastName = lastName; 
	} 
	public String getEmail() 
	{ 
		return this.email; 
	} 

	public void setEmail(String email) 
	{ 
		this.email = email; 
	} 
	public String getDate() 
	{ 
		return this.dateCreated; 
	} 

	public void setDate(String dateCreated) 
	{ 
		this.dateCreated = dateCreated; 
	} 
	
} 
