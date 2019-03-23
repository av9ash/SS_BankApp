package org.asu.cse545.group4.client.transactionrestservice;

import java.security.Principal;
import org.asu.cse545.group4.server.transactionservice.service.TransactionService;
import org.asu.cse545.group4.server.transactionservice.service.TransactionJson;
import org.asu.cse545.group4.server.sharedobjects.TblTransaction;
import org.asu.cse545.group4.server.sharedobjects.TblUserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.json.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.asu.cse545.group4.client.utils.UserExclusionStrategy;
import java.io.IOException;
@Controller
public class TransactionRestService
{	

	@Autowired
	private TransactionService transactionService;


	@PostMapping(value="/getTransaction",consumes="application/json",produces="application/json")
	public @ResponseBody String getTransaction(@RequestBody TransactionJson newTransaction) throws IOException
	{
		// check for user auth
		TblTransaction transaction = newTransaction.getTransactionObj();
		TblTransaction ret =  this.transactionService.getTransaction(transaction);
		Gson gson = new GsonBuilder().setExclusionStrategies(new UserExclusionStrategy()).create();
		return gson.toJson(ret);
	}

	@PostMapping(value="/transaction",consumes="application/json",produces="application/json")
	  public @ResponseBody String transaction(@RequestBody TransactionJson newTransaction) {
	  	TblTransaction transaction = null;
	  	try
	  	{
	  		transaction = newTransaction.getTransactionObj();	  		
	  		// TODO
	  		// check for User authorization
			String response = this.transactionService.addTransaction(transaction , newTransaction.getUserId());
			return response;
		}
		catch(Exception e)
		{
			// handle 
			return e.toString();
		}
	  }


	  @PostMapping(value="/approveTransaction", consumes = "application/json" , produces = "application/json")
	  public @ResponseBody String approveTransaction(@RequestBody String requestString)
	  {
	  		JSONObject request = new JSONObject(requestString);
	  		if (!request.has("transaction_id") || !request.has("approved_by")) {
	  			return "INVALID_REQUEST";
	  		}
	  		return this.transactionService.approveTransaction(request.getInt("transaction_id") , request.getInt("approved_by"));
	  }


	  @PostMapping(value="/declineTransaction" , consumes = "application/json" , produces = "application/json")
	  public @ResponseBody String declineTransaction(@RequestBody String requestString)
	  {
	  		JSONObject request = new JSONObject(requestString);
	  		if (!request.has("transaction_id") || !request.has("declined_by")) {
	  			return "INVALID_REQUEST";
	  		}
	  		return this.transactionService.declineTransaction(request.getInt("transaction_id") , request.getInt("declined_by"));
	  }

	  @PostMapping(value="/searchAccount", consumes="application/json" , produces = "application/json")
	  public @ResponseBody String searchAccount(@RequestBody TblUserProfile profile)
	  {
	  	return this.transactionService.searchAccount(profile);
	  }

}