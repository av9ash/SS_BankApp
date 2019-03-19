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
import java.util.List;
import org.hibernate.Hibernate;
import org.json.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.asu.cse545.group4.server.sharedobjects.TblRequest;
@Repository
public class RequestDAOImpl implements RequestDAO {
	@Autowired
	private SessionFactory sessionFactory;

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
}
