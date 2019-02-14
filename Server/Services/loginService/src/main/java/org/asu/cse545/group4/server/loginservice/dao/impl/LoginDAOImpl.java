package org.asu.cse545.group4.server.loginservice.dao.impl;

import java.util.Date;
import org.asu.cse545.group4.server.loginservice.dao.LoginDAO;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
import org.asu.cse545.group4.server.sharedobjects.TblUserProfile;
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
}
