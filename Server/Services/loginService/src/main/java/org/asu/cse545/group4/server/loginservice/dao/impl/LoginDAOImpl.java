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
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(14);
		String hashedPassword = passwordEncoder.encode(userPass);
		user.setPassword(hashedPassword);	
		user.setStatus(1);
		user.setIncorrectAttempts(0);
		System.out.println("is_external_user : "+ user.getIsExternalUser());
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
        
        Query<TblUser> query = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
        final List<TblUser> results = query.getResultList();
        if(!results.isEmpty())
        {
        	TblUser returnedUser = results.get(0);
        	
        	if(returnedUser.getStatus()!=1) {
        		return null;
        	}else {
        		//String encryptedPass = returnedUser.getPassword();
            	//String rawPass = user.getPassword();
            	//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(14);
            	//if(passwordEncoder.matches(rawPass, encryptedPass)) {
            		Hibernate.initialize(returnedUser);
            		int userId = returnedUser.getIsExternalUser();
            		List<TblCatalog> user1 = getUserFromCatalog(userId);
            		List<Object> resultUser = new ArrayList<>();
            		resultUser.add(returnedUser.getUserId());
            		resultUser.add(returnedUser.getUsername());
            		resultUser.add(user1.get(0));
            		//return json object
            		
            		// jwt token - pass it to client call
            		
                	return resultUser;
                	
            	/*}else {
            		int incorrectAttempts = returnedUser.getIncorrectAttempts();
            		if(incorrectAttempts>=3) {
            			returnedUser.setStatus(2);
            		}else {
            			returnedUser.setIncorrectAttempts(incorrectAttempts+1);
            		}
            		sessionFactory.getCurrentSession().save(returnedUser);
            		return null;
            	}*/
        		
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

    public  TblUser getUser(TblUser user)
    {
        TblUser dbUser = this.sessionFactory.getCurrentSession().get(TblUser.class , user.getUserId());
        return dbUser;
    }


    public void updateUser(TblUser user)
    {
        Session session = this.sessionFactory.getCurrentSession();
        TblUser dbUser = session.get(TblUser.class, user.getUserId());

        if (dbUser == null) {
            return;
        }

        Date date = new Date();
        dbUser.setModifiedDate(date);
        
        TblUserProfile userProfile = user.getTblUserProfile();
        if(userProfile != null)
        {        
            TblUserProfile dbUserProfile = dbUser.getTblUserProfile();
           /* if(userProfile.getFirstName() != null)
                dbUserProfile.setFirstName(userProfile.getFirstName());
            if(userProfile.getMiddleName() != null)
                dbUserProfile.setMiddleName(userProfile.getMiddleName());
            if(userProfile.getLastName() != null) 
                dbUserProfile.setLastName(userProfile.getLastName());
            if(userProfile.getEmail() != null) 
                dbUserProfile.setEmail(userProfile.getEmail()); */
            if(userProfile.getPhone() != null) 
                dbUserProfile.setPhone(userProfile.getPhone());
            if(userProfile.getAddress1() != null) 
                dbUserProfile.setAddress1(userProfile.getAddress1());
            if(userProfile.getAddress2() != null) 
                dbUserProfile.setAddress2(userProfile.getAddress2());
            if(userProfile.getCity() != null) 
                dbUserProfile.setCity(userProfile.getCity());
            if(userProfile.getProvince() != null) 
                dbUserProfile.setProvince(userProfile.getProvince());
            if(userProfile.getZip() != null) 
                dbUserProfile.setZip(userProfile.getZip());
            dbUserProfile.setTblUser(dbUser);        
            session.saveOrUpdate(dbUserProfile);
            dbUser.setTblUserProfile(dbUserProfile);
        }
        session.saveOrUpdate(dbUser);
    }

    
    public TblUser getUserByUserName(String userName)
    {
    	final CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<TblUser> criteriaQuery = builder.createQuery(TblUser.class);
        Root<TblUser> userQuery = criteriaQuery.from(TblUser.class);
        
        if(userName != null)
        {
        	criteriaQuery.where(builder.equal(userQuery.get("username"),userName));
        }
        
        Query<TblUser> query = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
        final List<TblUser> results = query.getResultList();
        if(!results.isEmpty())
        {
        	TblUser returnedUser = results.get(0);
        	return returnedUser;
        }else {
        	return null;
        }	
    	
    }
	
	public void unlockUser(TblUser user) {

    	final CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<TblUser> criteriaQuery = builder.createQuery(TblUser.class);
        Root<TblUser> userQuery = criteriaQuery.from(TblUser.class);
        
        if(user.getUsername() != null)
        {
        	criteriaQuery.where(builder.equal(userQuery.get("username"),user.getUsername()));
        }
        
        Query<TblUser> query = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
        final List<TblUser> results = query.getResultList();
        System.out.println("Inside : unlockUser "+ results.size());
        if(!results.isEmpty())
        {
        	TblUser returnedUser = results.get(0);
        	if(returnedUser.getStatus()==2) {
        		returnedUser.setStatus(1);
        		returnedUser.setIncorrectAttempts(0);
        		this.sessionFactory.getCurrentSession().saveOrUpdate(returnedUser);
        		System.out.println("Inside : unlockUser "+returnedUser.getUsername()+" successfully.");
        	}else {
        		System.out.println("Inside : unlockUser "+returnedUser.getUsername()+" is already unlocked.");
        	}
        	
        }else {
        	System.out.println("Requested user not found to update.");
        }	
    	
  
	}

	public void updateUserForAuth(TblUser user) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(user);
	}
}
