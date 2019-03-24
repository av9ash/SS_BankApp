package org.asu.cse545.group4.server.appointmentService.dao;

import java.util.List;

import org.asu.cse545.group4.server.sharedobjects.TblAppointment;

public abstract interface AppointmentDAO
{
  public abstract void insertAppointment(TblAppointment paramTblAppointment);
  public abstract List<TblAppointment> searchAppointments(TblAppointment paramTblAppointment);

}
