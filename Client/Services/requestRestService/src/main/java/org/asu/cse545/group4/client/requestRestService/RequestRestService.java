package org.asu.cse545.group4.client.requestrestservice;

import java.security.Principal;
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
import org.asu.cse545.group4.server.sharedobjects.TblRequest;
import java.util.List;
@Controller
public class RequestRestService
{	
	@Autowired
	private RequestService requestService;

	@PostMapping(value="/test" , consumes = "application/json" , produces = "application/json")
	public @ResponseBody String test(@RequestBody String request)
	{
		return this.requestService.test(request);
	}

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


	@GetMapping(value="/pendingRequests" ,  produces = "application/json")
	public @ResponseBody String getPendingRequests()
	{
		List<TblRequest> requests = this.requestService.getPendingRequests();
		Gson gson = new GsonBuilder().setExclusionStrategies(new UserExclusionStrategy()).create();
		return gson.toJson(requests);
	}
}