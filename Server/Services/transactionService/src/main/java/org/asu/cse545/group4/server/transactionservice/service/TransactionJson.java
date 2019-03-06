package org.asu.cse545.group4.server.transactionservice.service;

import org.asu.cse545.group4.server.sharedobjects.TblTransaction;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class TransactionJson
{
	@JsonProperty("user_id")
	private int userId;
	@JsonProperty("type")
	private int transactionType;
	@JsonProperty("transaction_amount")
	private int transactionAmount;
	@JsonProperty("from_account")
	private int fromAccount;
	@JsonProperty("to_account")
	private int toAccount;

	public void setUserId(int id)
	{
		this.userId = id;
	}

	public int getUserId()
	{
		return this.userId;
	}

	public void setTransactionType(int type)
	{
		this.transactionType = type;
	}

	public int getTransactionType()
	{
		return this.transactionType;
	}


	public void setTransactionAmount(int amount)
	{
		this.transactionAmount = amount;
	}

	public int getTransactionAmount()
	{
		return this.transactionAmount;
	}

	public void setFromAccount(int from_account)
	{
		this.fromAccount = from_account;
	}

	public int getFromAccount()
	{
		return this.fromAccount;
	}

	public void setToAccount(int to_account)
	{
		this.toAccount = to_account;
	}

	public int getToAccount()
	{
		return this.toAccount;
	}

	public TblTransaction getTransactionObj() throws IOException
	{
		JSONObject transaction = new JSONObject();
  		transaction.put("tblAccountByToAccount" , this.getToAccount());
		transaction.put("tblAccountByFromAccount" , this.getFromAccount());
		transaction.put("transactionType" , this.getTransactionType());
		transaction.put("transactionAmount" , this.getTransactionAmount());
  		ObjectMapper objectMapper = new ObjectMapper();
		TblTransaction transObj = objectMapper.readValue(transaction.toString(), TblTransaction.class);
		return transObj;
	}
}