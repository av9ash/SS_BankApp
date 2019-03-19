package org.asu.cse545.group4.server.loginservice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.asu.cse545.group4.server.loginservice.dao.LoginDAO;
import org.asu.cse545.group4.server.loginservice.service.LoginService;
import org.asu.cse545.group4.server.sharedobjects.TblCatalog;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("loginService")
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginDAO loginDAO;

	@Transactional
	public void insertUser(TblUser user) {
		this.loginDAO.insertUser(user);
	}
	
	@Transactional
	public Map<Integer, Map<String, Boolean>> searchUser(TblUser user)
	{
		List<Object> returnedUser = this.loginDAO.searchUser(user);
		
		
		Map<Integer,Map<String,Boolean>> returnedModuleList = new HashMap<>();
		
		String catalogDesc = ((TblCatalog) returnedUser.get(1)).getCatalogCategoryDescription();
		
		
		
		String[] allDesc = catalogDesc.split(",");
		
		Map<String,Boolean> moduleList = new HashMap<>();
		moduleList.put("AccountTransactionModule", false);
		moduleList.put("EmailPhoneTransactionModule", false);
		moduleList.put("AppointmentModule", false);
		moduleList.put("RequestModule", false);
		moduleList.put("ViewAccountModule", false);
		moduleList.put("ViewAndModifyModule", false);
		moduleList.put("ViewLogFile", false);
		moduleList.put("BankingStatementModule", false);
		moduleList.put("OpenAccount", false);
		
		for(String moduleName : allDesc) {
			if(moduleList.containsKey(moduleName)){
				moduleList.put(moduleName, true);
			}
		}
		
		returnedModuleList.put((Integer)returnedUser.get(0), moduleList);

		
		return returnedModuleList;
	}
}
