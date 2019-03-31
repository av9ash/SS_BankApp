package org.asu.cse545.group4.server.otpservice.otpservice;

import org.asu.cse545.group4.server.otpservice.otpservice.Constants.*;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.LoadingCache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;
//import com.google.api.services.gmail.model.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Service
@PropertySource("classpath:otp.properties")
public class OtpServic {

	//cache based on username and OPT MAX 8 
	 private static final Integer EXPIRE_MINS = 3;
	 
	 private LoadingCache<String, Integer> otpCache;
	 
	 Properties props = new Properties();
	 
	 /*@Bean
	 JavaMailSender javaMailSender()
	 {
		 return new JavaMailSender();
	 }
	 
	 private JavaMailSender javaMailSender;*/
	 
	 public OtpServic(){
		 super();
		 otpCache = CacheBuilder.newBuilder().
			     expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES).build(new CacheLoader<String, Integer>() {
				      public Integer load(String key) {
				             return 0;
				       }
				   });
	 }
	 
	//This method is used to push the opt number against Key. Rewrite the OTP if it exists
	 //Using user id  as key
	 public int generateOTP(String key){
		 
		Random random = new Random();	
		int otp = 100000 + random.nextInt(900000);
		otpCache.put(key, otp);
		return otp;
	 }
		 
	 //This method is used to return the OPT number against Key->Key values is username
	 public int getOtp(String key){		 
		try{
			 return otpCache.get(key); 
		}catch (Exception e){
		 return 0;			 
		}
	 }
		 
	//This method is used to clear the OTP catched already
	public void clearOTP(String key){		 
		 otpCache.invalidate(key);
	 }
	
	public void sendOtpMessage(String toMail, String subject, String otp) {
		
//		    String from="mysstestuser2019@gmail.com";
//		    String pass="C[xs.fb>Vz3zs3W?";
		    String[] to= new String[] {toMail};
		    //String subject = "OTP-DevilsSafeBank";
		    String body = "your otp is "+otp;
		    
		    Properties props = System.getProperties();
//		    String host = "smtp.gmail.com";
		    props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.smtp.host", Constants.HOST);
		    props.put("mail.smtp.user", Constants.EMAIL);
		    props.put("mail.smtp.password", Constants.PASSWORD);
		    props.put("mail.smtp.port", Constants.PORT);
		    props.put("mail.smtp.auth", "true");
		    
		    Session session = Session.getDefaultInstance(props);
		    MimeMessage message = new MimeMessage(session);
		    try {
		        message.setFrom(new InternetAddress(Constants.EMAIL));
		        InternetAddress[] toAddress = new InternetAddress[to.length];
		        // To get the array of addresses
		        for( int i = 0; i < to.length; i++ ) {
		            toAddress[i] = new InternetAddress(to[i]);
		        }
		        for( int i = 0; i < toAddress.length; i++) {
		            message.addRecipient(Message.RecipientType.TO, toAddress[i]);
		        }
		        message.setSubject(subject);
		        message.setText(body);
		        Transport transport = session.getTransport("smtp");
		        transport.connect(Constants.HOST, Constants.EMAIL, Constants.PASSWORD);
		        transport.sendMessage(message, message.getAllRecipients());
		        transport.close();
		    }
		    catch (AddressException ae) {
		        ae.printStackTrace();
		    }
		    catch (MessagingException me) {
		        me.printStackTrace();
		    }
		
		
		
//		 
//		 SimpleMailMessage simpleMailMessage = new SimpleMailMessage(); 
//		 simpleMailMessage.setTo(to); 
//		 simpleMailMessage.setSubject(subject); 
//		 simpleMailMessage.setText(message);
//		 
//		 
//		 
//		 //Uncomment to send mail
//		 javaMailSender().send(simpleMailMessage);
	}
}