package org.asu.cse545.group4.server.transactionservice.dao.impl;

import java.util.Date;
import org.asu.cse545.group4.server.transactionservice.dao.TransactionDAO;
import org.asu.cse545.group4.server.sharedobjects.TblTransaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionDAOImpl implements TransactionDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void addTransaction(TblTransaction transaction) {
		Session session = this.sessionFactory.getCurrentSession();
		Date date = new Date();
		transaction.setTransactionCreatedDate(date);
		transaction.setTransactionUpdatedDate(date);
		transaction.setTransactionStatus(1);
		transaction.setIsCriticalTransaction(0);
		this.sessionFactory.getCurrentSession().save(transaction);
	}
}
