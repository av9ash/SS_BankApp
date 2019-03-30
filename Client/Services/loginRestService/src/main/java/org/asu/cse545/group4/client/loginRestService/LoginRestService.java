package org.asu.cse545.group4.client.loginRestService;

import java.util.Map;

import org.asu.cse545.group4.client.utils.UserExclusionStrategy;
import org.asu.cse545.group4.server.eventservice.service.EventService;
import org.asu.cse545.group4.server.loginservice.service.LoginService;
import org.asu.cse545.group4.server.sharedobjects.TblEventLog;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@SessionAttributes({"currentUser"})
public class LoginRestService
{
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private EventService eventService;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	
	@PostMapping(value="/insertUser",consumes="application/json",produces="application/json")
	  public @ResponseBody String verify(@RequestBody TblUser newUser) {
	    //model.addAttribute("message", "You are logged in as " + principal.getName());
		System.out.println("Inside Rest");
		loginService.insertUser(newUser);
		TblEventLog event = new TblEventLog();
		Gson gson = new GsonBuilder().setExclusionStrategies(new UserExclusionStrategy()).create();
		event.setEventType(1);
		event.setEventName("User "+newUser.getUsername()+" created Successfully");
		eventService.logEvent(event);
		return gson.toJson(newUser);
		//return newUser;
	  }
	
	@PostMapping(value="/unlockUser",consumes="application/json",produces="application/json")
	  public @ResponseBody String unlockUser(@RequestBody TblUser reqUser) {
	    //model.addAttribute("message", "You are logged in as " + principal.getName());
		System.out.println("Inside Rest of unlockUser");
		loginService.unlockUser(reqUser);
		TblEventLog event = new TblEventLog();
		Gson gson = new GsonBuilder().setExclusionStrategies(new UserExclusionStrategy()).create();
		event.setEventType(1);
		event.setEventName("User "+reqUser.getUsername()+" unlocked Successfully");
		eventService.logEvent(event);
		return gson.toJson(reqUser);
		//return newUser;
	  }
	
	@PostMapping(value="/loginUser", consumes="application/json",produces="application/json")
	public @ResponseBody String search(@RequestBody TblUser user)
	{
		System.out.println("inside search");
		Map<String, Object> returnedUser = loginService.searchUser(user);
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
	

	/*@PostMapping(value="/loginUser", consumes="application/json",produces="application/json")
	public @ResponseBody String search(@RequestBody TblUser user)
	{
		Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		user.getUsername(),
                		user.getPassword()
                )
        );

		System.out.println("auth::"+authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //String jwt = tokenProvider.generateToken(authentication);
        //return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
		return "";
	}
	*/
	/*@PostMapping(value="/loginUser", consumes="application/json",produces="application/json")
    public void signUp(@RequestBody TblUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDetailsDao.save(user);
    }*/

	@PostMapping(value = "/getUser" , consumes="application/json" , produces = "application/json")
	public @ResponseBody String getUser(@RequestBody TblUser user)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth::"+auth);
		TblUser db_user = loginService.getUser(user);
		Gson gson = new GsonBuilder().setExclusionStrategies(new UserExclusionStrategy()).create();
		return gson.toJson(db_user);
	}

	@PostMapping(value = "/updateUser" , consumes="application/json" , produces = "application/json")
	public @ResponseBody String updateUser(@RequestBody TblUser user)
	{
		loginService.updateUser(user);
		Gson gson = new GsonBuilder().setExclusionStrategies(new UserExclusionStrategy()).create();
		return gson.toJson(user);
	}


}
