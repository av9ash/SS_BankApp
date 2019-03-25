package org.asu.cse545.group4.server.appointmentService.dao;

import java.util.List;

import org.asu.cse545.group4.server.sharedobjects.TblAppointment;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
import org.asu.cse545.group4.server.sharedobjects.AppointmentUserInfo;


public abstract interface AppointmentDAO
{
  public abstract void insertAppointment(TblAppointment paramTblAppointment);
  public abstract List<AppointmentUserInfo>  searchAppointments(TblUser paramTblUser);

}
