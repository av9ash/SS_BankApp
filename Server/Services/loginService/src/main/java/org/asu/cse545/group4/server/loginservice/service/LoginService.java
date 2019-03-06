package org.asu.cse545.group4.server.loginservice.service;

import org.asu.cse545.group4.server.sharedobjects.TblUser;

public abstract interface LoginService
{
  public abstract void insertUser(TblUser paramTblUser);
  
  public abstract TblUser searchUser(TblUser user);
}
