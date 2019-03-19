package org.asu.cse545.group4.server.requestservice.service;
import org.asu.cse545.group4.server.sharedobjects.TblRequest;

public abstract interface RequestService
{
  public abstract String test(String req);
  public abstract void addRequest(TblRequest request);
  public abstract void assignTo(TblRequest request);
}
