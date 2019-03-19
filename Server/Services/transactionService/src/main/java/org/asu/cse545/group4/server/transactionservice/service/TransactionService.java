package org.asu.cse545.group4.server.transactionservice.service;

import org.asu.cse545.group4.server.sharedobjects.TblTransaction;
import org.asu.cse545.group4.server.sharedobjects.TblUserProfile;

public abstract interface TransactionService
{
  public abstract String addTransaction(TblTransaction paramTransaction, int userId);

  public abstract String approveTransaction(int transactionId, int approverId);

  public abstract String declineTransaction(int transactionId, int declinerId);

  public abstract String searchAccount(TblUserProfile userProfile);
  
  public abstract TblTransaction searchTransaction(TblTransaction transaction);
}
