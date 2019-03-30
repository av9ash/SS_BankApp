package org.asu.cse545.group4.client.otpService;

import java.util.HashMap;
import java.util.Map;

import org.asu.cse545.group4.server.loginservice.service.LoginService;
import org.asu.cse545.group4.server.otpservice.otpservice.OtpServic;
import org.asu.cse545.group4.server.sharedobjects.Otp;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class OtpRestService {
	
	
	
	@Autowired
	public OtpServic otpService;
	
	@Autowired
	public LoginService loginService;
	
	
	@PostMapping(value="/generateOtp",consumes="application/json", produces="application/json")
	public @ResponseBody String generateOtp(@RequestBody TblUser user){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		String username = auth.getName();
		
		int otp = otpService.generateOTP(username);
		 
		System.out.println("OTP : "+otp);
		
		// //Generate The Template to send OTP 
		// EmailTemplate template = new EmailTemplate("SendOtp.html");
		
		// Map<String,String> replacements = new HashMap<String,String>();
		// replacements.put("user", username);
		// replacements.put("otpnum", String.valueOf(otp));
		 
		// String message = template.getTemplate(replacements);
		String message=Integer.toString(otp);
		
		TblUser returnedUser = loginService.getUser(user);
		String email = returnedUser.getTblUserProfile().getEmail();
		//myEmailService.sendOtpMessage("sumukharadhakrishna@gmail.com", "OTP - DevilSafeBank", message);
		
		otpService.sendOtpMessage(email, "OTP - DevilSafeBank", message);
		
		return "";
	}
	
	
	@PostMapping(value="/validateOtp",consumes="application/json", produces="application/json")
	public @ResponseBody String validateOtp(@RequestBody Otp otp){
		
		//int otpnum = Integer.parseInt(otp);
		final String SUCCESS = "SUCCESS";
		
		int otpnum = otp.getOtpValue();
		final String FAIL = "FAIL";
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		String username = auth.getName();
		
		System.out.println(" Otp Number : "+otpnum);
		
		//Validate the Otp 
		if(otpnum >= 0){
			int serverOtp = otpService.getOtp(username);
			System.out.println("Server otp"+serverOtp);
			
			if(serverOtp > 0){
				if(otpnum == serverOtp){
					System.out.println("OTP matched");
					otpService.clearOTP(username);
					return SUCCESS;
				}else{
					System.out.println("OTP notmatched");
					return FAIL;	
				}
			}else {
				return FAIL;			
			}
		}else {
			return FAIL;	
		}
	}
}
