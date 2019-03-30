package org.asu.cse545.group4.server.transactionservice.service;

import org.asu.cse545.group4.server.sharedobjects.TblTransaction;
import org.asu.cse545.group4.server.sharedobjects.TblAccount;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class AccountUser
{
	@JsonProperty("account")
	private TblAccount account;
	@JsonProperty("user")
	private TblUser user;

	public void setAccount(TblAccount param)
	{
		this.account = param;
	}

	public TblAccount getAccount()
	{
		return this.account;
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