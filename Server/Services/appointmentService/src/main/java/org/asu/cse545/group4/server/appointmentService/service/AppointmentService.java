package org.asu.cse545.group4.server.appointmentService.service;

import java.util.*;


import org.asu.cse545.group4.server.sharedobjects.TblAppointment;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
import org.asu.cse545.group4.server.sharedobjects.AppointmentUserInfo;



public abstract interface AppointmentService
{
  public abstract void insertAppointment(TblAppointment paramTblAppointment);
  public abstract List<AppointmentUserInfo>  searchAppointments(TblUser paramTblUser);
  public abstract List<TblUser> getEmployees();


}
