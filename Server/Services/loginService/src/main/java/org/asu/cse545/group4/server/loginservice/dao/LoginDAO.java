package org.asu.cse545.group4.server.loginservice.dao;

import java.util.List;

import org.asu.cse545.group4.server.sharedobjects.TblUser;

public abstract interface LoginDAO
{

  public abstract void insertUser(TblUser paramTblUser);
  
  public abstract List<Object> searchUser(TblUser user);

  public abstract TblUser getUser(TblUser user);

  public abstract void updateUser(TblUser user);
  
  public  abstract TblUser getUserByUserName(String userName);
  
  public abstract void updateUserForAuth(TblUser user);
  
  public abstract void unlockUser(TblUser user);

}
