package org.asu.cse545.group4.server.transactionservice.service;

import org.asu.cse545.group4.server.sharedobjects.TblTransaction;

public abstract interface TransactionService
{
  public abstract void addTransaction(TblTransaction paramTransaction);
}
