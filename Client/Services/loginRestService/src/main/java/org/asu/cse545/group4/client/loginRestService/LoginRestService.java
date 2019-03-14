package org.asu.cse545.group4.client.loginRestService;

import java.security.Principal;

import org.asu.cse545.group4.client.utils.UserExclusionStrategy;
import org.asu.cse545.group4.server.eventservice.service.EventService;
import org.asu.cse545.group4.server.loginservice.service.LoginService;
import org.asu.cse545.group4.server.sharedobjects.TblEventLog;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
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
public class LoginRestService
{
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private EventService eventService;
	
	@PostMapping(value="/verifyUser",consumes="application/json",produces="application/json")
	  public @ResponseBody TblUser verify(@RequestBody TblUser lol) {
	    //model.addAttribute("message", "You are logged in as " + principal.getName());
		System.out.println("Inside Rest");
		loginService.insertUser(lol);
		
	    return lol;
	  }
	
	@PostMapping(value="/searchUser", consumes="application/json",produces="application/json")
	public @ResponseBody String search(@RequestBody TblUser user)
	{
		System.out.println("inside search");
		TblUser returnedUser = loginService.searchUser(user);
		Gson gson = new GsonBuilder().setExclusionStrategies(new UserExclusionStrategy()).create();
		//Map<String,Object> returnMap = new HashMap<String, Object> ();
		//returnMap
		
		TblEventLog event = new TblEventLog();
		if(returnedUser != null)
		{
			event.setEventName("User Logged In Successfully");
		}
		else
		{
			event.setEventName("User Logged In Failed");
		}
		event.setEventType(1);
		eventService.logEvent(event);
		return gson.toJson(returnedUser);
		//return returnedUser;
	}
}
