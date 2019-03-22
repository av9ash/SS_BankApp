package org.asu.cse545.group4.server.requestservice.service.impl;

import org.asu.cse545.group4.server.requestservice.dao.RequestDAO;
import org.asu.cse545.group4.server.requestservice.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.asu.cse545.group4.server.sharedobjects.TblRequest;
import java.util.List;
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
	public  List<TblRequest> getPendingRequests()
	{
		return this.requestDAO.getPendingRequests();
	}
}
