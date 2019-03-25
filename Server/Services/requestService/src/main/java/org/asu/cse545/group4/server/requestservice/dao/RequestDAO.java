package org.asu.cse545.group4.server.requestservice.dao;
import org.asu.cse545.group4.server.sharedobjects.TblRequest;
import java.util.List;
public abstract interface RequestDAO
{
  public abstract String test(String req);
  public abstract void addRequest(TblRequest request);
  public abstract void assignTo(TblRequest request);
  public abstract List<TblRequest> getPendingRequests();
}
