package org.asu.cse545.group4.server.appointmentService.service.impl;

import org.asu.cse545.group4.server.appointmentService.dao.AppointmentDAO;
import org.asu.cse545.group4.server.appointmentService.service.AppointmentService;
import org.asu.cse545.group4.server.sharedobjects.TblAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("appointmentService")
public class AppointmentServiceImpl implements AppointmentService {
	@Autowired
	private AppointmentDAO appointmentDAO;

	@Transactional
	public void insertAppointment(TblAppointment paramTblAppointment) {
		this.appointmentDAO.insertAppointment(paramTblAppointment);
	}
}
