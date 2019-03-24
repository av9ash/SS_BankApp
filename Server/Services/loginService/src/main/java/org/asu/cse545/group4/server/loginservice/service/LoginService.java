package org.asu.cse545.group4.server.loginservice.service;

import java.util.Map;

import org.asu.cse545.group4.server.sharedobjects.TblCatalog;
import org.asu.cse545.group4.server.sharedobjects.TblUser;

public abstract interface LoginService
{
  public abstract void insertUser(TblUser paramTblUser);
  
  //public abstract Map<Integer, Map<String, Boolean>> searchUser(TblUser user);
  
  public abstract Map<String, Object> searchUser(TblUser user);
}
