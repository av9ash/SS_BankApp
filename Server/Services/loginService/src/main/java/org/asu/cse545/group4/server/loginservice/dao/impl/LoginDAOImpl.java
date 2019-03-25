package org.asu.cse545.group4.server.loginservice.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.asu.cse545.group4.server.loginservice.dao.LoginDAO;
import org.asu.cse545.group4.server.sharedobjects.TblCatalog;
import org.asu.cse545.group4.server.sharedobjects.TblCatalogId;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
import org.asu.cse545.group4.server.sharedobjects.TblUserProfile;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Repository
public class LoginDAOImpl implements LoginDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void insertUser(TblUser user) {
		Session session = this.sessionFactory.getCurrentSession();
		Date date = new Date();
		user.setCreatedDate(date);
		user.setModifiedDate(date);
		//encrypting password
		String userPass =  user.getPassword();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(16);
		String hashedPassword = passwordEncoder.encode(userPass);
		user.setPassword(hashedPassword);	
		this.sessionFactory.getCurrentSession().save(user);
		TblUserProfile userProfile = user.getTblUserProfile();
		userProfile.setTblUser(user);

		session.saveOrUpdate(userProfile);
	}
	
	public List<Object> searchUser(TblUser user)
	{
		final CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<TblUser> criteriaQuery = builder.createQuery(TblUser.class);
        Root<TblUser> userQuery = criteriaQuery.from(TblUser.class);
        
        if(user.getUsername() != null)
        {
        	criteriaQuery.where(builder.equal(userQuery.get("username"),user.getUsername()));
        }
        
       /* if(user.getPassword() != null)
        {
        	criteriaQuery.where(builder.equal(userQuery.get("password"),user.getPassword()));
        }*/
        
       /* if(user.getIsExternalUser() != null)
        {
        	criteriaQuery.where(builder.equal(userQuery.get("isExternalUser"),user.getIsExternalUser()));
        }*/
        
        Query<TblUser> query = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
        final List<TblUser> results = query.getResultList();
        if(!results.isEmpty())
        {
        	TblUser returnedUser = results.get(0);
        	String encryptedPass = returnedUser.getPassword();
        	String rawPass = user.getPassword();
        	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(16);
        	if(passwordEncoder.matches(rawPass, encryptedPass)) {
        		Hibernate.initialize(returnedUser);
        		int userId = returnedUser.getIsExternalUser();
        		List<TblCatalog> user1 = getUserFromCatalog(userId);
        		List<Object> resultUser = new ArrayList<>();
        		resultUser.add(returnedUser.getUserId());
        		resultUser.add(user1.get(0));
        		
        		//return json object
            	return resultUser;
            	
        	}else {
        		return null;
        	}
        	
        }
        else
        {
        	return null;
        }
	}

	private List<TblCatalog> getUserFromCatalog(int userType) {
		final CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<TblCatalog> criteriaQuery = builder.createQuery(TblCatalog.class);
		
        Root<TblCatalog> userQuery = criteriaQuery.from(TblCatalog.class);
        
        TblCatalogId catalog = new TblCatalogId(105,userType);
        
        if(userType>0){
        	
         criteriaQuery.where(builder.equal(userQuery.get("id"),catalog));
         
        }
       
        Query<TblCatalog> query = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
        final List<TblCatalog> results = query.getResultList();
        if(!results.isEmpty())
        {
        	return results;
        }
        else
        {
        	return null;
        }
		
	}
}
