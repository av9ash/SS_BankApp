package org.asu.cse545.group4.server.transactionservice.dao;

import org.asu.cse545.group4.server.sharedobjects.TblTransaction;

public abstract interface TransactionDAO
{
  public abstract void addTransaction(TblTransaction paramTransaction);
}
