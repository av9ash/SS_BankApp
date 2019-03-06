package org.asu.cse545.group4.server.loginservice.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.asu.cse545.group4.server.loginservice.dao.LoginDAO;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
import org.asu.cse545.group4.server.sharedobjects.TblUserProfile;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAOImpl implements LoginDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void insertUser(TblUser user) {
		Session session = this.sessionFactory.getCurrentSession();
		Date date = new Date();
		user.setCreatedDate(date);
		user.setModifiedDate(date);
		this.sessionFactory.getCurrentSession().save(user);
		TblUserProfile userProfile = user.getTblUserProfile();
		userProfile.setTblUser(user);

		session.saveOrUpdate(userProfile);
	}
	
	public TblUser searchUser(TblUser user)
	{
		final CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<TblUser> criteriaQuery = builder.createQuery(TblUser.class);
        Root<TblUser> userQuery = criteriaQuery.from(TblUser.class);
        
        if(user.getUsername() != null)
        {
        	criteriaQuery.where(builder.equal(userQuery.get("username"),user.getUsername()));
        }
        
        if(user.getPassword() != null)
        {
        	criteriaQuery.where(builder.equal(userQuery.get("password"),user.getPassword()));
        }
        
        if(user.getIsExternalUser() != null)
        {
        	criteriaQuery.where(builder.equal(userQuery.get("isExternalUser"),user.getIsExternalUser()));
        }
        
        Query<TblUser> query = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
        final List<TblUser> results = query.getResultList();
        if(!results.isEmpty())
        {
        	TblUser returnedUser = results.get(0);
        	Hibernate.initialize(returnedUser);
        	return returnedUser;
        }
        else
        {
        	return null;
        }
	}
}
