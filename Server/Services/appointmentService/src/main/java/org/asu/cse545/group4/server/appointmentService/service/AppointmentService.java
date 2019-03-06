package org.asu.cse545.group4.server.appointmentService.service;

import org.asu.cse545.group4.server.sharedobjects.TblAppointment;

public abstract interface AppointmentService
{
  public abstract void insertAppointment(TblAppointment paramTblAppointment);
}
