package org.asu.cse545.group4.server.transactionservice.service;


import org.asu.cse545.group4.server.sharedobjects.TblRequest;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class RequestUser
{
	@JsonProperty("request")
	private TblRequest request;
	
	@JsonProperty("user")
	private TblUser user;

	public void setRequest(TblRequest param)
	{
		this.request = param;
	}

	public TblRequest getRequest()
	{
		return this.request;
	}

	public void setUser(TblUser param)
	{
		this.user = param;
	}

	public TblUser getUser()
	{
		return this.user;
	}
}