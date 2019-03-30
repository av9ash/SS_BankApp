package org.asu.cse545.group4.client.appointmentRestService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringEscapeUtils;
import org.asu.cse545.group4.server.appointmentService.service.AppointmentService;
import org.asu.cse545.group4.server.sharedobjects.TblAppointment;
import org.asu.cse545.group4.server.sharedobjects.TblEventLog;
import org.asu.cse545.group4.server.eventservice.service.EventService;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
import org.asu.cse545.group4.server.sharedobjects.AppointmentUserInfo;
import org.apache.commons.lang3.StringEscapeUtils;
import org.asu.cse545.group4.client.utils.UserExclusionStrategy;
import org.asu.cse545.group4.client.utils.UserExclusionStrategyAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Random;

	
@Controller
public class AppointmentRestService
{
	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private EventService eventService;
	
	@PostMapping(value="/createAppointment",consumes="application/json",produces="application/json")
	  public @ResponseBody String create(@RequestBody TblAppointment paramTblAppointment) {
		List<TblUser> employees = new ArrayList<>();
		
		//sanitize input 
		String desc = StringEscapeUtils.escapeJava(paramTblAppointment.getDescript());
		desc = StringEscapeUtils.escapeHtml4(desc);
		if(desc.length() >= 100) 
			return "FAIL";
		paramTblAppointment.setDescript(desc);
		
		
		employees = appointmentService.getEmployees();
	
		if(employees.size() >= 1) {
			Random randomGenerator = new Random();
			int randomId = randomGenerator.nextInt(employees.size());
			paramTblAppointment.setTblUserByAssignedToUserId(employees.get(randomId));
			
			
			
			appointmentService.insertAppointment(paramTblAppointment);
			
			TblEventLog event = new TblEventLog();
			event.setEventName("Appointment created Successfully");
			eventService.logEvent(event);

			return "success";
		} 
		
		
		return "No employees exists";
	  }
	
	@PostMapping(value="/searchAppointments", consumes="application/json" , produces = "application/json")
	  public @ResponseBody String search(@RequestBody TblUser paramTblUser)
	  {
			List<AppointmentUserInfo> result = appointmentService.searchAppointments(paramTblUser);
			Gson gson = new GsonBuilder().setExclusionStrategies(new UserExclusionStrategyAppointment()).create();								
			return gson.toJson(result);
			
	  }
}








