package org.asu.cse545.group4.server.transactionservice.dao.impl;

import java.util.Date;
import org.asu.cse545.group4.server.transactionservice.dao.TransactionDAO;
import org.asu.cse545.group4.server.sharedobjects.TblTransaction;
import org.asu.cse545.group4.server.sharedobjects.TblAccount;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import static java.lang.Math.toIntExact;

enum TransactionStatus
{
	INSUFFICIENT_BALANCE,
	OK,
	ERROR,
	NOT_ATHOURIZED;
}

@Repository
public class TransactionDAOImpl implements TransactionDAO {
	@Autowired
	private SessionFactory sessionFactory;
	private static final int CREDIT=1;
	private static final int DEBIT=2;
	private static final int TRANSFER=3;

	public String addTransaction(TblTransaction transaction , int userId)
	{
		TransactionStatus status = validateTransaction(transaction , userId);
		if( status == TransactionStatus.OK)
		{
			Session session = this.sessionFactory.getCurrentSession();
			Date date = new Date();
			transaction.setTransactionCreatedDate(date);
			transaction.setTransactionUpdatedDate(date);
			transaction.setTransactionStatus(1);
			transaction.setIsCriticalTransaction(0);
			this.sessionFactory.getCurrentSession().save(transaction);
		}
		return status.name();
	}


	public TransactionStatus validateTransaction(TblTransaction transaction, int userId)
	{
		TransactionStatus status = TransactionStatus.ERROR;
		TblAccount userAccount = this.sessionFactory.getCurrentSession().get(TblAccount.class , userId);
		//TODO
		// authorize check for userId
		int transType = transaction.getTransactionType();
		if( ( transType == DEBIT || transType == TRANSFER) &&  transaction.getTransactionAmount() > toIntExact(userAccount.getCurrentAmount()))
		{
			status = TransactionStatus.INSUFFICIENT_BALANCE;
		}
		else
		{
			status = TransactionStatus.OK;
		}
		return status;
	}

	public String approveTransaction(int transactionId , int approverId)
	{
		TblTransaction transaction = this.sessionFactory.getCurrentSession().get(TblTransaction.class, transactionId);
		if(transaction == null)
			return TransactionStatus.ERROR.name();
		//TODO
		// check approver has priviliges to approve a transaction

		//TODO validate again
		// int transactionUserId = transaction.getTblAccountByFromAccount().getTblUser().getUserId();
		// TransactionStatus status = validateTransaction( transaction , transactionUserId);
		TransactionStatus status  = TransactionStatus.OK;
		if (status == TransactionStatus.OK)
		{
			//TODO
			// performTransaction
			transaction.setTransactionStatus(2);
			transaction.setTblUser(this.sessionFactory.getCurrentSession().get(TblUser.class,approverId));
			transaction.setTransactionUpdatedDate(new Date());
			this.sessionFactory.getCurrentSession().saveOrUpdate(transaction);
		}
		return status.name();
	}
}
