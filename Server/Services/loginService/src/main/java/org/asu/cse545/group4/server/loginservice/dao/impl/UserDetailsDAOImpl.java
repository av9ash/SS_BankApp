package org.asu.cse545.group4.server.loginservice.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.asu.cse545.group4.server.loginservice.dao.UserDetailsDao;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.security.authentication.BadCredentialsException;


@Repository
public class UserDetailsDAOImpl implements UserDetailsDao {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public TblUser findUserByUsername(String username) {
	  System.out.println("inside dao"+username);
	  //TblUser returnedUser = sessionFactory.getCurrentSession().get(TblUser.class, username);
	  
	  final CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();

	  CriteriaQuery<TblUser> criteriaQuery = builder.createQuery(TblUser.class);
	
      Root<TblUser> userQuery = criteriaQuery.from(TblUser.class);
      
      int status = 1;
      Predicate pred = builder.and(builder.equal(userQuery.get("username"),username), builder.equal(userQuery.get("status"),status));
      criteriaQuery.where(pred);
      //criteriaQuery.where(builder.equal(userQuery.get("username"),username));
      //criteriaQuery.where(builder.equal(userQuery.get("status"),status));
      
      
      Query<TblUser> query = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
	  
      final List<TblUser> results = query.getResultList();
      
      TblUser returnedUser = results.get(0);
    return returnedUser;
  }
}