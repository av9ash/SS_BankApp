package org.asu.cse545.group4.server.transactionservice.dao.impl;

import java.util.Date;
import org.asu.cse545.group4.server.transactionservice.dao.TransactionDAO;
import org.asu.cse545.group4.server.sharedobjects.TblTransaction;
import org.asu.cse545.group4.server.sharedobjects.TblAccount;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
import org.asu.cse545.group4.server.sharedobjects.TblUserProfile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import static java.lang.Math.toIntExact;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.query.Query;
import java.util.List;
import org.hibernate.Hibernate;
import org.json.*;
import com.fasterxml.jackson.databind.ObjectMapper;

enum TransactionStatus
{
	INSUFFICIENT_BALANCE,
	OK,
	ERROR,
	NOT_ATHOURIZED;
}

enum Action
{
	CREDIT,
	DEBIT;
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
		TblAccount fromAccount = this.sessionFactory.getCurrentSession().get(TblAccount.class , transaction.getTblAccountByFromAccount().getAccountId());
		//TODO
		// authorize check for userId
		int transType = transaction.getTransactionType();
		if( ( transType == DEBIT || transType == TRANSFER) &&  transaction.getTransactionAmount() > toIntExact(fromAccount.getCurrentAmount()))
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
			boolean success = performTransaction(transaction);
			if(!success)
			{
				status = TransactionStatus.ERROR;
				return status.name();
			}
			transaction.setTransactionStatus(2);
			transaction.setTblUser(this.sessionFactory.getCurrentSession().get(TblUser.class,approverId));
			transaction.setTransactionUpdatedDate(new Date());
			this.sessionFactory.getCurrentSession().saveOrUpdate(transaction);
		}
		return status.name();
	}


	public boolean persistTransaction(int accountId, int amount, Action action)
	{
		boolean status = false;
		try
		{
			TblAccount account = this.sessionFactory.getCurrentSession().get(TblAccount.class , accountId);
			long currAmount = account.getCurrentAmount();
			if(action == Action.DEBIT)
			{
				currAmount = currAmount - amount;
			}
			else if(action == Action.CREDIT)
			{
				currAmount = currAmount + amount;
			}
			account.setCurrentAmount(currAmount);
			this.sessionFactory.getCurrentSession().saveOrUpdate(account);
			status = true;
		}
		catch(Exception e)
		{

		}
		return status;
	}


	public boolean performTransaction(TblTransaction transaction)
	{
		boolean status = false;
		try
		{
			int type = transaction.getTransactionType();
			if(type == CREDIT)
			{
				status = persistTransaction(transaction.getTblAccountByFromAccount().getAccountId() , transaction.getTransactionAmount(), Action.CREDIT);
			}
			else if(type == DEBIT)
			{
				status = persistTransaction(transaction.getTblAccountByFromAccount().getAccountId() , transaction.getTransactionAmount(), Action.DEBIT);
			}
			else if(type == TRANSFER)
			{
				status = persistTransaction(transaction.getTblAccountByFromAccount().getAccountId() , transaction.getTransactionAmount(), Action.DEBIT);
				status &= persistTransaction(transaction.getTblAccountByToAccount().getAccountId() , transaction.getTransactionAmount(), Action.CREDIT);
			}
		}
		catch(Exception e)
		{

		}
		return status;
	}

	public String declineTransaction(int transactionId , int declinerId)
	{
		TransactionStatus status = TransactionStatus.OK;
		try
		{
			TblTransaction transaction = this.sessionFactory.getCurrentSession().get(TblTransaction.class, transactionId);
			transaction.setTransactionStatus(3);
			transaction.setTblUser(this.sessionFactory.getCurrentSession().get(TblUser.class,declinerId));
			transaction.setTransactionUpdatedDate(new Date());
			this.sessionFactory.getCurrentSession().saveOrUpdate(transaction);
		}
		catch(Exception e)
		{
			status = TransactionStatus.ERROR;
		}
		return status.name();
	}

	public  String searchAccount(TblUserProfile userProfile)
	{
		try
		{
			TblUserProfile ans = searchProfile(userProfile);
			JSONObject returnObj = new JSONObject();
			if (ans == null) {
				return returnObj.toString();
			}
			Query query = sessionFactory.getCurrentSession().createSQLQuery("select * from tbl_account as t where t.user_id = :userId");
			query.setParameter("userId", ans.getUserId());
			List<Object[]> result = query.list();
			
			JSONArray jsonArray = new JSONArray();
			if (result != null && !result.isEmpty()) 
			{				
				for(Object[] obj : result)
				{
					JSONObject json = new JSONObject();
					json.put("account_id" , obj[0]);
					//TODO account type as String
					json.put("account_type" , obj[2]);
					jsonArray.put(json);
				}
			}
			returnObj.put("first_name" ,ans.getFirstName());
			returnObj.put("middle_name" ,ans.getMiddleName());
			returnObj.put("last_name" ,ans.getLastName());			
			returnObj.put("accounts" ,jsonArray );
			return returnObj.toString();
		}
		catch(Exception e)
		{
			return "";
		}
	}

	public TblUserProfile searchProfile(TblUserProfile userProfile)
	{
		try
		{
			final CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
	        CriteriaQuery<TblUserProfile> criteriaQuery = builder.createQuery(TblUserProfile.class);
	       Root<TblUserProfile> userQuery = criteriaQuery.from(TblUserProfile.class);       
	        if(userProfile.getEmail() != null)
	       {
	           criteriaQuery.where(builder.equal(userQuery.get("email"),userProfile.getEmail()));
	       }        
	       if(userProfile.getPhone() != null)
	       {
	           criteriaQuery.where(builder.equal(userQuery.get("phone"),userProfile.getPhone()));
	       }        
	       Query<TblUserProfile> query = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
	       final List<TblUserProfile> results = query.getResultList();
	        if(!results.isEmpty())
	       {
	           TblUserProfile returnedUser = results.get(0);
	           return returnedUser;
	       }
	       else
	       {
	           return null;
	       }
		}
		catch(Exception e)
		{
			return null;
		}
	}
}
