package org.asu.cse545.group4.server.transactionservice.service;

import org.asu.cse545.group4.server.sharedobjects.TblTransaction;
import org.asu.cse545.group4.server.sharedobjects.TblUserProfile;
import org.asu.cse545.group4.server.sharedobjects.TblAccount;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
import org.asu.cse545.group4.server.sharedobjects.TblRequest;
import java.util.List;
public abstract interface TransactionService
{
  public abstract String addTransaction(TblTransaction paramTransaction, int userId, int type);

  public abstract String approveTransaction(int transactionId, int approverId);

  public abstract String declineTransaction(int transactionId, int declinerId);

  public abstract String searchAccount(TblUserProfile userProfile);
  
  
  public abstract TblTransaction getTransaction(TblTransaction transaction);

  public abstract List<TblTransaction> getTransactionsForAccount(TblAccount account);

  public abstract List<TblAccount> getAccountsForUser(TblUser user);

  public abstract TblAccount updateAccount(TblAccount account, TblUser user);

  public abstract String deleteAccount(TblAccount account, TblUser user);
  
  public abstract List<TblAccount> searchAccountByAccountParams(TblAccount account);

  public abstract boolean isThisUserAccount(TblAccount account, TblUser user);

  public abstract TblAccount createAccount( TblRequest  request  , TblUser approver);

  public abstract TblUser getUser(TblUser user);
  
  public abstract String getAllAccounts();

}
