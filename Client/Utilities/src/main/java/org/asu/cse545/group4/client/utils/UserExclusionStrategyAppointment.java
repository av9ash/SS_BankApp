package org.asu.cse545.group4.client.utils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import org.asu.cse545.group4.server.sharedobjects.*;
public class UserExclusionStrategyAppointment implements ExclusionStrategy{

	@Override
	 public boolean shouldSkipClass(Class<?> clazz) {
	     return false;
	   }
	@Override
	   public boolean shouldSkipField(FieldAttributes f) {
	     return (f.getDeclaringClass() == TblUser.class);  
	}
}
