package org.asu.cse545.group4.client.utils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import org.asu.cse545.group4.server.sharedobjects.TblAccount;
import org.asu.cse545.group4.server.sharedobjects.TblAppointment;
import org.asu.cse545.group4.server.sharedobjects.TblRequest;
import org.asu.cse545.group4.server.sharedobjects.TblTransaction;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
import org.asu.cse545.group4.server.sharedobjects.TblUserProfile;

public class AccountAppointmentStrategy implements ExclusionStrategy{
	@Override
	 public boolean shouldSkipClass(Class<?> clazz) {
	     return false;
	   }

	@Override
	   public boolean shouldSkipField(FieldAttributes f) {
	     return ((f.getDeclaringClass() == TblUserProfile.class && f.getName().equals("tblUser"))
	    		 || (f.getDeclaringClass() == TblAppointment.class && f.getName().equals("tblUserByAssignedToUserId"))
	    		 || (f.getDeclaringClass() == TblAccount.class && f.getName().equals("tblTransactionsForFromAccount"))
	    		 || (f.getDeclaringClass() == TblAccount.class && f.getName().equals("tblTransactionsForToAccount"))
	    		 || (f.getDeclaringClass() == TblTransaction.class && f.getName().equals("tblUser"))
	    		 || (f.getDeclaringClass() == TblTransaction.class && f.getName().equals("tblAccountByFromAccount"))
	    		 || (f.getDeclaringClass() == TblTransaction.class && f.getName().equals("tblAccountByToAccount"))
	    		 || (f.getDeclaringClass() == TblRequest.class && f.getName().equals("tblUserByRequestAssignedTo"))
	    		 || (f.getDeclaringClass() == TblUser.class && f.getName().equals("password"))
	    		 || (f.getDeclaringClass() == TblUser.class && f.getName().equals("incorrectAttempts"))
	    		 || (f.getDeclaringClass() == TblUser.class && f.getName().equals("tblAppointmentsForAssignedToUserId"))
	    		 || (f.getDeclaringClass() == TblUser.class && f.getName().equals("tblRequestsForRequestAssignedTo"))
	    		 || (f.getDeclaringClass() == TblUser.class && f.getName().equals("tblAppointmentsForAppointmentUserId"))
	    		 || (f.getDeclaringClass() == TblUser.class && f.getName().equals("tblAccounts"))
	    		 || (f.getDeclaringClass() == TblUser.class && f.getName().equals("tblTransactions"))
	    		 || (f.getDeclaringClass() == TblUser.class && f.getName().equals("tblRequestsForRequestedBy"))
	    		 || (f.getDeclaringClass() == TblRequest.class && f.getName().equals("tblRequestsForRequestedBy")));  
	}
}
