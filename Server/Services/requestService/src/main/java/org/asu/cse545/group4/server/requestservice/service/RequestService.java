package org.asu.cse545.group4.server.requestservice.service;
import org.asu.cse545.group4.server.sharedobjects.TblRequest;
import org.asu.cse545.group4.server.sharedobjects.TblTransaction;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
import java.util.List;
public abstract interface RequestService
{
  public abstract String test(String req);
  public abstract void addRequest(TblRequest request);
  public abstract void assignTo(TblRequest request);
  public abstract List<TblRequest> getPendingRequests(TblUser user);
  public abstract TblRequest getRequest(TblTransaction transaction);
  public abstract boolean isTierOneEmployee(TblUser user);

  public abstract boolean isTierTwoEmployee(TblUser user);

  public abstract boolean isTierThreeEmployee(TblUser user);

  public abstract boolean isAdmin(TblUser user);

  public abstract boolean isCustomer(TblUser user);

  public abstract boolean isMerchant(TblUser user);
}
