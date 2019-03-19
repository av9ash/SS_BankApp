package org.asu.cse545.group4.server.transactionservice.service.impl;

import org.asu.cse545.group4.server.transactionservice.dao.TransactionDAO;
import org.asu.cse545.group4.server.transactionservice.service.TransactionService;
import org.asu.cse545.group4.server.sharedobjects.TblTransaction;
import org.asu.cse545.group4.server.sharedobjects.TblUserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("transactionService")
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	private TransactionDAO transactionDAO;

	@Transactional
	public String addTransaction(TblTransaction transaction, int userId) {
		return this.transactionDAO.addTransaction(transaction,userId);
	}

	@Transactional
	public String approveTransaction(int transactionId, int approverId)
	{
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
	public  TblTransaction searchTransaction(TblTransaction transaction)
	{
		return this.transactionDAO.searchTransaction(transaction);
	}
}
