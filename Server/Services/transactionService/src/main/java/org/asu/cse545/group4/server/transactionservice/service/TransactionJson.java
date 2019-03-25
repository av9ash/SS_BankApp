package org.asu.cse545.group4.server.transactionservice.service;

import org.asu.cse545.group4.server.sharedobjects.TblTransaction;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class TransactionJson
{
	@JsonProperty("transaction_id")
	private int transactionId;
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
	@JsonProperty("approved_by")
	private int approvedBy;
	@JsonProperty("transaction_status")
	private int transactionStatus;

	public void setTransactionStatus(int status)
	{
		this.transactionStatus = status;
	}

	public int getTransactionStatus()
	{
		return this.transactionStatus;
	}

	public void setApprovedBy(int id)
	{
		this.approvedBy = id;
	}

	public int getApprovedBy()
	{
		return this.approvedBy;
	}

	public void setTransactionId(int id)
	{
		this.transactionId = id;
	}

	public int getTransactionId()
	{
		return this.transactionId;
	}

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
		if(this.getTransactionId() != 0){
			transaction.put("transactionId" , this.getTransactionId());
		}
		if(this.getToAccount() != 0)
		{
  			transaction.put("tblAccountByToAccount" , this.getToAccount());
  		}
		transaction.put("tblAccountByFromAccount" , this.getFromAccount());
		transaction.put("transactionType" , this.getTransactionType());
		transaction.put("transactionAmount" , this.getTransactionAmount());
		if(this.getApprovedBy() != 0){
			transaction.put("tblUser" , this.getApprovedBy());
		}
		transaction.put("transactionStatus" , this.getTransactionStatus());
  		ObjectMapper objectMapper = new ObjectMapper();
		TblTransaction transObj = objectMapper.readValue(transaction.toString(), TblTransaction.class);
		return transObj;
	}
}