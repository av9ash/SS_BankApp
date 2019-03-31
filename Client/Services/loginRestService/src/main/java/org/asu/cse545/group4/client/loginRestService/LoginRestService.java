package org.asu.cse545.group4.client.loginRestService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.asu.cse545.group4.client.utils.UserExclusionStrategy;
import org.asu.cse545.group4.server.eventservice.service.EventService;
import org.asu.cse545.group4.server.loginservice.service.LoginService;
import org.asu.cse545.group4.server.sharedobjects.TblEventLog;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
import org.asu.cse545.group4.server.sharedobjects.TblUserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

	@PostMapping(value="/getAllLockedUsers",consumes="application/json",produces="application/json")
	public @ResponseBody String getAllLockedUsers() {
		List<TblUser> lockedUsers = loginService.getAllLockedUsers();
		Map<String, Object> lockedUserResult = new HashMap<>();
		lockedUserResult.put("users", lockedUsers);
		Gson gson = new GsonBuilder().setExclusionStrategies(new UserExclusionStrategy()).create();
		return gson.toJson(lockedUserResult);
	}

	@PostMapping(value="/insertUser",consumes="application/json",produces="application/json")
	public @ResponseBody String verify(@RequestBody TblUser newUser) {
		
		
		if(newUser.getUsername()!=null){
			String username = StringEscapeUtils.escapeJava(newUser.getUsername());
			username = StringEscapeUtils.escapeHtml4(username);
			username = StringEscapeUtils.escapeXml(username);

			if(username.length()>255){
				return "FAIL";
			}else {
				newUser.setUsername(username);
			}
		}
		
		if(newUser.getTblUserProfile()!=null) {

			TblUserProfile userProfile = newUser.getTblUserProfile();

			//sanitize input for phone number
			if(userProfile.getPhone()!=null) {
				String phoneNo = StringEscapeUtils.escapeJava(userProfile.getPhone());
				phoneNo = StringEscapeUtils.escapeHtml4(phoneNo);
				phoneNo = StringEscapeUtils.escapeXml(phoneNo);

				if(phoneNo.length()>10){
					return "FAIL";
				}else {
					userProfile.setPhone(phoneNo);
				}
			}


			//Sanitize input for Address 1
			if(userProfile.getAddress1()!=null) {
				String address1 = StringEscapeUtils.escapeJava(userProfile.getAddress1());
				address1 = StringEscapeUtils.escapeHtml4(address1);
				address1 = StringEscapeUtils.escapeXml(address1);

				if(address1.length()>255){
					return "FAIL";
				}else {
					userProfile.setAddress1(address1);
				}
			}


			//Sanitize input for Address 2
			if(userProfile.getAddress2()!=null) {
				String address2 = StringEscapeUtils.escapeJava(userProfile.getAddress2());
				address2 = StringEscapeUtils.escapeHtml4(address2);
				address2 = StringEscapeUtils.escapeXml(address2);

				if(address2.length()>255){
					return "FAIL";
				}else {
					userProfile.setAddress2(address2);
				}
			}

			//Sanitize input for City
			if(userProfile.getCity()!=null) {
				String city = StringEscapeUtils.escapeJava(userProfile.getCity());
				city = StringEscapeUtils.escapeHtml4(city);
				city = StringEscapeUtils.escapeXml(city);

				if(city.length()>255){
					return "FAIL";
				}else {
					userProfile.setCity(city);
				}
			}

			//Sanitize input for Province
			if(userProfile.getProvince()!=null) {
				String province = StringEscapeUtils.escapeJava(userProfile.getProvince());
				province = StringEscapeUtils.escapeHtml4(province);
				province = StringEscapeUtils.escapeXml(province);

				if(province.length()>255){
					return "FAIL";
				}else {
					userProfile.setProvince(province);
				}
			}
			
			//Sanitize input for Zip
			if(userProfile.getZip()!=null && userProfile.getZip()>0) {
				int zip = userProfile.getZip();
				
				if(Integer.toString(zip).length()>5) {
					return "FAIL";
				}else {
					userProfile.setZip(zip);
				}
				
			}
			// update user profile
			newUser.setTblUserProfile(userProfile);
			
		
		}
		
		loginService.insertUser(newUser);
		TblEventLog event = new TblEventLog();
		Gson gson = new GsonBuilder().setExclusionStrategies(new UserExclusionStrategy()).create();
		event.setEventType(1);
		event.setEventName("User "+newUser.getUsername()+" created Successfully on "+new Date());
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
			event.setEventName("User Logged In Successfully on "+ new Date());
		}
		else
		{
			event.setEventName("User Logged In Failed on "+ new Date());
		}
		event.setEventType(1);
		eventService.logEvent(event);
		return gson.toJson(returnedUser);
	}

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
	public @ResponseBody String updateUser(@RequestBody TblUser updatedUser)
	{
		if(updatedUser.getTblUserProfile()!=null){
			TblUserProfile userProfile = updatedUser.getTblUserProfile();


			//sanitize input for phone number
			if(userProfile.getPhone()!=null) {
				String phoneNo = StringEscapeUtils.escapeJava(userProfile.getPhone());
				phoneNo = StringEscapeUtils.escapeHtml4(phoneNo);
				phoneNo = StringEscapeUtils.escapeXml(phoneNo);

				if(phoneNo.length()>10){
					return "FAIL Phone";
				}else {
					userProfile.setPhone(phoneNo);
				}
			}


			//Sanitize input for Address 1
			if(userProfile.getAddress1()!=null) {
				String address1 = StringEscapeUtils.escapeJava(userProfile.getAddress1());
				address1 = StringEscapeUtils.escapeHtml4(address1);
				address1 = StringEscapeUtils.escapeXml(address1);

				if(address1.length()>255){
					return "FAIL Address1";
				}else {
					userProfile.setAddress1(address1);
				}
			}


			//Sanitize input for Address 2
			if(userProfile.getAddress2()!=null) {
				String address2 = StringEscapeUtils.escapeJava(userProfile.getAddress2());
				address2 = StringEscapeUtils.escapeHtml4(address2);
				address2 = StringEscapeUtils.escapeXml(address2);

				if(address2.length()>255){
					return "FAIL Address2";
				}else {
					userProfile.setAddress2(address2);
				}
			}

			//Sanitize input for City
			if(userProfile.getCity()!=null) {
				String city = StringEscapeUtils.escapeJava(userProfile.getCity());
				city = StringEscapeUtils.escapeHtml4(city);
				city = StringEscapeUtils.escapeXml(city);

				if(city.length()>255){
					return "FAIL City";
				}else {
					userProfile.setCity(city);
				}
			}

			//Sanitize input for Province
			if(userProfile.getProvince()!=null) {
				String province = StringEscapeUtils.escapeJava(userProfile.getProvince());
				province = StringEscapeUtils.escapeHtml4(province);
				province = StringEscapeUtils.escapeXml(province);

				if(province.length()>255){
					return "FAIL Province";
				}else {
					userProfile.setProvince(province);
				}
			}
			
			//Sanitize input for Zip
			if(userProfile.getZip()!=null && userProfile.getZip()>0) {
				int zip = userProfile.getZip();
				
				if(Integer.toString(zip).length()>5) {
					return "FAIL Zip";
				}else {
					userProfile.setZip(zip);
				}
				
			}
			// update user profile
			updatedUser.setTblUserProfile(userProfile);
			
		}


		loginService.updateUser(updatedUser);
		TblEventLog event = new TblEventLog();
		Gson gson = new GsonBuilder().setExclusionStrategies(new UserExclusionStrategy()).create();
		event.setEventType(1);
		event.setEventName("User "+updatedUser.getUsername()+" updated Successfully on "+new Date());
		eventService.logEvent(event);
		return gson.toJson(updatedUser);
	}



}
