package org.asu.cse545.group4.server.transactionservice.dao.impl;

import java.util.Date;
import org.asu.cse545.group4.server.transactionservice.dao.TransactionDAO;
import org.asu.cse545.group4.server.sharedobjects.TblTransaction;
import org.asu.cse545.group4.server.sharedobjects.TblAccount;
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
		// authorize check
		if(transaction.getTransactionAmount() > toIntExact(userAccount.getCurrentAmount()))
		{
			status = TransactionStatus.INSUFFICIENT_BALANCE;
		}
		else
		{
			status = TransactionStatus.OK;
		}
		return status;
	}
}
