package org.asu.cse545.group4.server.loginservice.dao;

import org.asu.cse545.group4.server.sharedobjects.TblCatalog;
import org.asu.cse545.group4.server.sharedobjects.TblUser;

public abstract interface LoginDAO
{
  public abstract void insertUser(TblUser paramTblUser);
  
  public abstract TblCatalog searchUser(TblUser user);
}
