package org.asu.cse545.group4.client.utils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import org.asu.cse545.group4.server.sharedobjects.*;
public class UserExclusionStrategy implements ExclusionStrategy{

	@Override
	 public boolean shouldSkipClass(Class<?> clazz) {
	     return false;
	   }

	@Override
	   public boolean shouldSkipField(FieldAttributes f) {
	     return ((f.getDeclaringClass() == TblUserProfile.class && f.getName().equals("tblUser"))
	    		 || (f.getDeclaringClass() == TblAppointment.class && f.getName().equals("tblUserByAssignedToUserId"))
	    		 || (f.getDeclaringClass() == TblAccount.class && f.getName().equals("tblUser"))
	    		 || (f.getDeclaringClass() == TblAccount.class && f.getName().equals("tblTransactionsForFromAccount"))
	    		 || (f.getDeclaringClass() == TblAccount.class && f.getName().equals("tblTransactionsForToAccount"))
	    		 || (f.getDeclaringClass() == TblTransaction.class && f.getName().equals("tblUser"))
	    		 || (f.getDeclaringClass() == TblTransaction.class && f.getName().equals("tblAccountByFromAccount"))
	    		 || (f.getDeclaringClass() == TblTransaction.class && f.getName().equals("tblAccountByToAccount"))
	    		 || (f.getDeclaringClass() == TblRequest.class && f.getName().equals("tblUserByRequestAssignedTo"))
	    		 || (f.getDeclaringClass() == TblRequest.class && f.getName().equals("tblUserByRequestedBy"))) ;  
	}
}
