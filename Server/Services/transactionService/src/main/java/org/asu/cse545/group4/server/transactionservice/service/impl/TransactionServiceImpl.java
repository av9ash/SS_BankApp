package org.asu.cse545.group4.server.transactionservice.service.impl;

import org.asu.cse545.group4.server.transactionservice.dao.TransactionDAO;
import org.asu.cse545.group4.server.transactionservice.service.TransactionService;
import org.asu.cse545.group4.server.sharedobjects.TblTransaction;
import org.asu.cse545.group4.server.sharedobjects.TblAccount;
import org.asu.cse545.group4.server.sharedobjects.TblUserProfile;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
import org.asu.cse545.group4.server.sharedobjects.TblRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.asu.cse545.group4.server.requestservice.service.RequestService;

@Service("transactionService")
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	private TransactionDAO transactionDAO;
	@Autowired
	private RequestService reqService;

	@Transactional
	public String addTransaction(TblTransaction transaction, int userId) {
		String status = this.transactionDAO.addTransaction(transaction,userId);
		String[] responses = status.split(":");
		if (responses[0].equals("OK")) {
		 	String transId = responses[1];
		 	TblTransaction thisTrans = new TblTransaction(Integer.parseInt(transId));
		 	TblUser user = new TblUser(userId);
		 	TblRequest newReq = new TblRequest();
		 	newReq.setTblUserByRequestedBy(user);
		 	newReq.setTblTransaction(thisTrans);
		 	reqService.addRequest(newReq);
		 }		 
		 return responses[0];		 
	}

	@Transactional
	public String approveTransaction(int transactionId, int approverId)
	{
		TblTransaction transaction = new TblTransaction(transactionId);
		TblRequest request = reqService.getRequest(transaction);
		request.setTblUserByRequestAssignedTo(new TblUser(approverId));
		reqService.assignTo(request);
		return this.transactionDAO.approveTransaction(transactionId,approverId);
	}

	@Transactional
	public String declineTransaction(int transactionId, int declinerId)
	{
		return this.transactionDAO.declineTransaction(transactionId,declinerId);
	}

	@Transactional
	public  String searchAccount(TblUserProfile userProfile)
	{
		return this.transactionDAO.searchAccount(userProfile);
	}

	@Transactional
	public TblTransaction getTransaction(TblTransaction transaction)
	{
		return this.transactionDAO.getTransaction(transaction);
	}

	@Transactional
	public  List<TblTransaction> getTransactionsForAccount(TblAccount account)
	{
		return this.transactionDAO.getTransactionsForAccount(account);
	}

	@Transactional
	public  List<TblAccount> getAccountsForUser(TblUser user)
	{
		return this.transactionDAO.getAccountsForUser(user);
	}


	@Transactional
	public  TblAccount updateAccount(TblAccount account)
	{
		return this.transactionDAO.updateAccount(account);
	}

	@Transactional
	public void deleteAccount(TblAccount account)
	{
		this.transactionDAO.deleteAccount(account);
	}

	@Transactional
	public  boolean isTierOneEmployee(TblUser user)
	{
		return this.transactionDAO.isTierOneEmployee(user);
	}

	@Transactional
  	public  boolean isTierTwoEmployee(TblUser user)
  	{
  		return this.transactionDAO.isTierTwoEmployee(user);
  	}

  @Transactional	
  public  boolean isTierThreeEmployee(TblUser user)
  {
  	return this.transactionDAO.isTierThreeEmployee(user);
  }

  @Transactional
  public  boolean isAdmin(TblUser user)
  {
  		return this.transactionDAO.isAdmin(user);
  }

  @Transactional
  public  boolean isCustomer(TblUser user)
  {
  	return this.transactionDAO.isCustomer(user);
  }

  @Transactional
  public boolean isMerchant(TblUser user)
  {
  	return this.transactionDAO.isMerchant(user);
  }

	
	@Transactional
	public  List<TblAccount> searchAccountByAccountParams(TblAccount account)
	{
		return this.transactionDAO.searchAccountByAccountParams(account);
	}
}
