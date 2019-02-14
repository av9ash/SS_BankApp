package org.asu.cse545.group4.client.loginrestservice;

import java.security.Principal;

import org.asu.cse545.group4.server.loginservice.service.LoginService;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class LoginRestService
{
	@Autowired
	private LoginService loginService;
	
	@PostMapping(value="/verifyUser",consumes="application/json",produces="application/json")
	  public @ResponseBody TblUser verify(@RequestBody TblUser lol) {
	    //model.addAttribute("message", "You are logged in as " + principal.getName());
		System.out.println("Inside Rest");
		loginService.insertUser(lol);
	    return lol;
	  }
}