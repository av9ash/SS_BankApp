package org.asu.cse545.group4.client.transactionrestservice;

import java.security.Principal;
import org.asu.cse545.group4.server.transactionservice.service.TransactionService;
import org.asu.cse545.group4.server.sharedobjects.TblTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TransactionRestService
{	

	@Autowired
	private TransactionService transactionService;

	@PostMapping(value="/transaction",consumes="application/json",produces="application/json")
	  public @ResponseBody TblTransaction transaction(@RequestBody TblTransaction newTransaction) {
	  	transactionService.addTransaction(newTransaction);
	    return newTransaction;
	  }
}