package org.asu.cse545.group4.server.transactionservice.service;

import org.asu.cse545.group4.server.sharedobjects.TblTransaction;

public abstract interface TransactionService
{
  public abstract String addTransaction(TblTransaction paramTransaction, int userId);

  public abstract String approveTransaction(int transactionId, int approverId);
}
