package org.asu.cse545.group4.client.requestrestservice;

import java.security.Principal;

import org.asu.cse545.group4.server.eventservice.service.EventService;
import org.asu.cse545.group4.server.requestservice.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.json.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.asu.cse545.group4.client.utils.UserExclusionStrategy;
import org.asu.cse545.group4.server.sharedobjects.TblEventLog;
import org.asu.cse545.group4.server.sharedobjects.TblRequest;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
import java.util.List;
import java.util.Map;
@Controller
public class RequestRestService
{	
	@Autowired
	private EventService eventService;
	@Autowired
	private RequestService requestService;


	@PostMapping(value="/request" , consumes = "application/json" , produces = "application/json")
	public @ResponseBody String request(@RequestBody TblRequest request)
	{
		this.requestService.addRequest(request);
		Gson gson = new GsonBuilder().setExclusionStrategies(new UserExclusionStrategy()).create();
		return gson.toJson(request);
	}

	@PostMapping(value="/assignTo" , consumes = "application/json" , produces = "application/json")
	public @ResponseBody String assignTo(@RequestBody TblRequest request)
	{
		this.requestService.assignTo(request);
		Gson gson = new GsonBuilder().setExclusionStrategies(new UserExclusionStrategy()).create();
		return gson.toJson(request);
	}


	@PostMapping(value="/pendingRequests" ,  produces = "application/json")
	public @ResponseBody String getPendingRequests(@RequestBody TblUser user)
	{
		if(requestService.isTierTwoEmployee(user) || requestService.isAdmin(user) || requestService.isTierOneEmployee(user))
		{
			List<TblRequest> requests = this.requestService.getPendingRequests(user);
			Gson gson = new GsonBuilder().setExclusionStrategies(new UserExclusionStrategy()).create();
			return gson.toJson(requests);
		}	
		else
		{
			return "INSUFFICIENT_PRIVILIGES";
		}
		
	}
	
	@GetMapping(value="/searchEvent" ,  produces = "application/json")
	public @ResponseBody String searchEvent(@RequestBody TblUser user)
	{
		System.out.println("inside search Event");
		Gson gson = new GsonBuilder().setExclusionStrategies(new UserExclusionStrategy()).create();
		Boolean isUserAdmin = requestService.isAdmin(user);
		if (isUserAdmin==false)
		{
			return gson.toJson("fail");
		}
		List<TblEventLog> returnedUser = eventService.searchEvents();
		return gson.toJson(returnedUser);
		
	}
}