package org.asu.cse545.group4.client.appointmentRestService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.asu.cse545.group4.server.appointmentService.service.AppointmentService;
import org.asu.cse545.group4.server.sharedobjects.TblAppointment;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
import org.asu.cse545.group4.server.sharedobjects.AppointmentUserInfo;
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
	
@Controller
public class AppointmentRestService
{
	@Autowired
	private AppointmentService appointmentService;
	
	@PostMapping(value="/createAppointment",consumes="application/json",produces="application/json")
	  public @ResponseBody TblAppointment create(@RequestBody TblAppointment paramTblAppointment) {
		appointmentService.insertAppointment(paramTblAppointment);
	    return paramTblAppointment;
	  }
	
	@PostMapping(value="/searchAppointments", consumes="application/json" , produces = "application/json")
	  public @ResponseBody String search(@RequestBody TblUser paramTblUser)
	  {
			List<AppointmentUserInfo> result = appointmentService.searchAppointments(paramTblUser);
			Gson gson = new GsonBuilder().setExclusionStrategies(new UserExclusionStrategyAppointment()).create();								
			return gson.toJson(result);
			
	  }
}








