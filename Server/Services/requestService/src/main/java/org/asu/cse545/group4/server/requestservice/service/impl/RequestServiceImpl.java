package org.asu.cse545.group4.server.requestservice.service.impl;

import org.asu.cse545.group4.server.requestservice.dao.RequestDAO;
import org.asu.cse545.group4.server.requestservice.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.asu.cse545.group4.server.sharedobjects.TblRequest;
import org.asu.cse545.group4.server.sharedobjects.TblTransaction;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
import java.util.List;
import java.util.Map;
@Service("requestService")
public class RequestServiceImpl implements RequestService {
	@Autowired
	private RequestDAO requestDAO;

	@Transactional
	public String test(String req) {
		return this.requestDAO.test(req);
	}

	@Transactional
	public void addRequest(TblRequest request)
	{
		this.requestDAO.addRequest(request);
	}

	@Transactional
	public  void assignTo(TblRequest request)
	{
		this.requestDAO.assignTo(request);
	}

	@Transactional
	public  List<TblRequest> getPendingRequests(TblUser user)
	{
		
		return this.requestDAO.getPendingRequests(user);
	}

	@Transactional
	public  TblRequest getRequest(TblTransaction transaction)
	{
		return this.requestDAO.getRequest(transaction);
	}

		@Transactional
	public  boolean isTierOneEmployee(TblUser user)
	{
		return this.requestDAO.isTierOneEmployee(user);
	}

	@Transactional
  	public  boolean isTierTwoEmployee(TblUser user)
  	{
  		return this.requestDAO.isTierTwoEmployee(user);
  	}

	  @Transactional	
	  public  boolean isTierThreeEmployee(TblUser user)
	  {
	  	return this.requestDAO.isTierThreeEmployee(user);
	  }

	  @Transactional
	  public  boolean isAdmin(TblUser user)
	  {
	  		return this.requestDAO.isAdmin(user);
	  }

	  @Transactional
	  public  boolean isCustomer(TblUser user)
	  {
	  	return this.requestDAO.isCustomer(user);
	  }

	  @Transactional
	  public boolean isMerchant(TblUser user)
	  {
	  	return this.requestDAO.isMerchant(user);
	  }

	  
}
