package org.asu.cse545.group4.server.loginservice.dao;

import org.asu.cse545.group4.server.sharedobjects.TblUser;

public interface UserDetailsDao {
	TblUser findUserByUsername(String username);
	}