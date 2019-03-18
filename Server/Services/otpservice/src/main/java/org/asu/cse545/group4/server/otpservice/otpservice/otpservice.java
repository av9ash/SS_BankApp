package org.asu.cse545.group4.server.otpservice.otpservice;




import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import com.sun.mail.smtp.SMTPTransport;
import java.security.Security;
import com.google.api.client.util.Base64;
import com.google.api.services.gmail.Gmail;
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
import com.google.api.client.auth.oauth2.Credential;
//import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
//import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class otpservice {
public static final String ACCOUNT_SID = "ACe9e461c81f6540a36a8639de41089ece";
public static final String AUTH_TOKEN = "d6ed3b700cce76f6554dfd2ed0e90d46";
public static final String CLIENT_SECRET="PFlqnrutDE8omT8g-Qqv3dbK";
public static final String CLIENT_ID="768998422893-pve5hdpbg498h2i6oi0s7rs6im51mvn5.apps.googleusercontent.com";



public String generateOTP() {

	String numbers = "1234567890";
      Random random = new Random();
      char[] otp = new char[6];

      for(int i = 0; i< 6 ; i++) {
         otp[i] = numbers.charAt(random.nextInt(numbers.length()));
      }
      return  new String(otp);
}


public void sendMessage(String msg) { 		
	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    //TO FROM MESSAGE
	com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber("+14805659932"),
        new PhoneNumber("+13462495761"), 
        msg).create();

    //System.out.println(message.getSid());	  
}

public void sendEmail(String otp) {
	String from="";
	String pass="";
	String[] to= {""};
	String subject = "OTP";
    String body = "your otp is "+otp;
	
	Properties props = System.getProperties();
    String host = "smtp.gmail.com";
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.user", from);
    props.put("mail.smtp.password", pass);
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");

    Session session = Session.getDefaultInstance(props);
    MimeMessage message = new MimeMessage(session);

    try {
        message.setFrom(new InternetAddress(from));
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
        transport.connect(host, from, pass);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
    catch (AddressException ae) {
        ae.printStackTrace();
    }
    catch (MessagingException me) {
        me.printStackTrace();
    }
}

//public void sendEmailGmail(int otp) throws GeneralSecurityException, IOException, MessagingException {
//	MimeMessage mimemessage;
//	mimemessage=createEmail("sradha10@asu.edu", "sumukharadhakrishna@gmail.com", "OTP", "OTP for the transaction is 12345"+Integer.toString(otp));
//	Message msg;
//	HttpTransport httpTransport=null;
//	JsonFactory jsonFactory=null;
//	HttpRequestInitializer httpRequestInit=null;
//	Gmail service=new Gmail(httpTransport, jsonFactory, httpRequestInit);
//	msg=sendMessage(service , CLIENT_ID, mimemessage);
//
//}
//
//public static Message sendMessage(Gmail service,
//           String userId,
//           MimeMessage emailContent)
//	throws MessagingException, IOException {
//	Message message = createMessageWithEmail(emailContent);
//	message = service.users().messages().send(userId, message).execute();
//	
//	System.out.println("Message id: " + message.getId());
//	System.out.println(message.toPrettyString());
//	return message;
//}
//
//public static MimeMessage createEmail(String to, String from,
//         String subject,
//         String bodyText)
//	throws MessagingException {
//	Properties props = new Properties();
//	Session session = Session.getDefaultInstance(props, null);
//	
//	MimeMessage email = new MimeMessage(session);
//	
//	email.setFrom(new InternetAddress(from));
//	email.addRecipient(javax.mail.Message.RecipientType.TO,
//	new InternetAddress(to));
//	email.setSubject(subject);
//	email.setText(bodyText);
//	return email;
//}
//
//
//public static Message createMessageWithEmail(MimeMessage emailContent)
//        throws MessagingException, IOException {
//    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//    emailContent.writeTo(buffer);
//    byte[] bytes = buffer.toByteArray();
//    String encodedEmail = Base64.encodeBase64URLSafeString(bytes);
//    Message message = new Message();
//    message.setRaw(encodedEmail);
//    return message;
//}
//	 

	 
	
  public static void main(String[] args) {
    otpservice sendotp=new otpservice();
    String otp=null;
    otp=sendotp.generateOTP();
    sendotp.sendMessage(otp);    
    try {
		sendotp.sendEmail(otp);
	} catch (Exception e) {
		e.printStackTrace();
	}
    System.out.println("OTP SENT!");
  }
  

}