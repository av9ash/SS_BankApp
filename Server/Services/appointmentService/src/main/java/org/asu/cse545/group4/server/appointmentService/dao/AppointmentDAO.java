package org.asu.cse545.group4.server.appointmentService.dao;

import org.asu.cse545.group4.server.sharedobjects.TblAppointment;

public abstract interface AppointmentDAO
{
  public abstract void insertAppointment(TblAppointment paramTblAppointment);
}
