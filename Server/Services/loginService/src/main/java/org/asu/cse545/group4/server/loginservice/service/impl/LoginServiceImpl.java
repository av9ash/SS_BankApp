package org.asu.cse545.group4.server.loginservice.service.impl;

import org.asu.cse545.group4.server.loginservice.dao.LoginDAO;
import org.asu.cse545.group4.server.loginservice.service.LoginService;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginDAO loginDAO;

	@Transactional
	public void insertUser(TblUser user) {
		this.loginDAO.insertUser(user);
	}
}
