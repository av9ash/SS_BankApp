package org.asu.cse545.group4.server.requestservice.dao.impl;

import java.util.Date;
import org.asu.cse545.group4.server.requestservice.dao.RequestDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import static java.lang.Math.toIntExact;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.query.Query;
import java.util.*;
import org.hibernate.Hibernate;
 import org.hibernate.SQLQuery;
import org.json.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.asu.cse545.group4.server.sharedobjects.TblRequest;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
import org.asu.cse545.group4.server.sharedobjects.TblTransaction;
import org.hibernate.Criteria;
import javax.persistence.criteria.Predicate;

@Repository
public class RequestDAOImpl implements RequestDAO {
	@Autowired
	private SessionFactory sessionFactory;
	private static final int TIER_1 = 1;
	private static final int TIER_2 = 2;
	private static final int TIER_3 = 3;
	private static final int ADMIN = 3;
	private static final int CUSTOMER = 4;
	private static final int MERCHANT = 5;


	public String test(String req)
	{
		return "DONE";
	}

	public void addRequest(TblRequest request)
	{
		Session session = this.sessionFactory.getCurrentSession();
		session.save(request);
	}

	public  void assignTo(TblRequest request)
	{
		TblRequest currentReq = this.sessionFactory.getCurrentSession().get(TblRequest.class , request.getRequestId() );
		currentReq.setTblUserByRequestAssignedTo(request.getTblUserByRequestAssignedTo());
		this.sessionFactory.getCurrentSession().saveOrUpdate(currentReq);
	}

	public List<TblRequest> getPendingRequests(TblUser user)
	{


		Session session = sessionFactory.getCurrentSession();
		StringBuilder sb = new StringBuilder("select * from tbl_request as r left join tbl_transaction as t on r.transaction_id = t.transaction_id  where r.request_assigned_to is NULL ");
		if(isTierOneEmployee(user))
			sb.append("and  t.is_critical_transaction = 0");
		SQLQuery query = session.createSQLQuery(sb.toString());
		query.addEntity("r",TblRequest.class);
		query.addJoin("t","r.tblTransaction");
		List<Object[]> requests = query.list();
		List<TblRequest> returnList = new ArrayList<>();
		for (Object[] r : requests ) 
		{
			TblRequest tmp = (TblRequest) r[0];
			returnList.add(tmp);
		}
		return returnList;


		//if(!isTierOneEmployee(user) && !isTierTwoEmployee(user))
		//	return new ArrayList<TblRequest>();
		//final CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
		// Session session = sessionFactory.getCurrentSession();
		// StringBuilder queryString = new StringBuilder("FROM TblRequest tb");
		// queryString.append("LEFT JOIN TblTransaction tbl");
		// queryString.append("where tb.tblUserByRequestAssignedTo is NULL AND tbl.isCriticalTransaction = 1");
		// Query  q = session.createQuery(queryString.toString());
		// List<TblRequest> requests = q.getResultList();
		//CriteriaQuery<TblRequest> criteriaQuery = builder.createQuery(TblRequest.class);
		// Root<TblRequest> requestQuery = criteriaQuery.from(TblRequest.class);
		// // Join<TblTransaction> t = requestQuery.join()
		// // if(isTierOneEmployee(user)) 
		// // {
		// // 	Predicate pred = builder.and( builder.isNull(requestQuery.get("tblUserByRequestAssignedTo")) ,builder.equal(requestQuery.get("tblTransaction").getIsCriticalTransaction(), 0));
  // //     		criteriaQuery.where(pred);
		// // }
		// // else if(isTierTwoEmployee(user))
		// //{
		// 	criteriaQuery.where(builder.isNull(requestQuery.get("tblUserByRequestAssignedTo")));
		// //}
		// Query<TblRequest> query = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
		// final List<TblRequest> requests = query.getResultList();
		
	}

	public  TblRequest getRequest(TblTransaction transaction)
	{
		TblTransaction dbTrans = this.sessionFactory.getCurrentSession().get(TblTransaction.class , transaction.getTransactionId());
		final CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<TblRequest> criteriaQuery = builder.createQuery(TblRequest.class);
		Root<TblRequest> requestQuery = criteriaQuery.from(TblRequest.class);
		criteriaQuery.where(builder.equal(requestQuery.get("tblTransaction") , dbTrans));
		Query<TblRequest> query = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
		final List<TblRequest> requests = query.getResultList();
		if (requests != null && !requests.isEmpty()) {
			return requests.get(0);
		}
		return null;
	}

	public boolean isTierOneEmployee(TblUser user)
    {
    	TblUser dbUser = sessionFactory.getCurrentSession().get(TblUser.class , user.getUserId());
    	if (dbUser == null) {
    		return false;    		
    	}
    	return dbUser.getIsExternalUser() == TIER_1;
    }

    public boolean isTierTwoEmployee(TblUser user)
    {
    	TblUser dbUser = sessionFactory.getCurrentSession().get(TblUser.class , user.getUserId());
    	if (dbUser == null) {
    		return false;    		
    	}
    	return dbUser.getIsExternalUser() == TIER_2;    	
    }

    public boolean isTierThreeEmployee(TblUser user)
    {
    	TblUser dbUser = sessionFactory.getCurrentSession().get(TblUser.class , user.getUserId());
    	if (dbUser == null) {
    		return false;    		
    	}
    	return dbUser.getIsExternalUser() == TIER_3;    	
    }


    public boolean isAdmin(TblUser user)
    {
    	TblUser dbUser = sessionFactory.getCurrentSession().get(TblUser.class , user.getUserId());
    	if (dbUser == null) {
    		return false;    		
    	}
    	return dbUser.getIsExternalUser() == ADMIN;    	
    }

	
	public boolean isCustomer(TblUser user)
    {
    	TblUser dbUser = sessionFactory.getCurrentSession().get(TblUser.class , user.getUserId());
    	if (dbUser == null) {
    		return false;    		
    	}
    	return dbUser.getIsExternalUser() == CUSTOMER;    	
    }    

	public boolean isMerchant(TblUser user)
    {
    	TblUser dbUser = sessionFactory.getCurrentSession().get(TblUser.class , user.getUserId());
    	if (dbUser == null) {
    		return false;    		
    	}
    	return dbUser.getIsExternalUser() == MERCHANT; 
    }
	
	
}
