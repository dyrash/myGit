package com.sh.base.applicationService;

import java.util.ArrayList;

import com.sh.base.applicationService.CustomerListApplicationServiceImpl;
import com.sh.base.dao.CustomerDAO;
import com.sh.base.to.CustomerBean;

public class CustomerListApplicationServiceImpl implements CustomerListApplicationService{

	private CustomerDAO customerDAO;
	
	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}
	
	@Override
	public ArrayList<CustomerBean> selectCustomerCodeList(String code) {
		// TODO Auto-generated method stub
		ArrayList<CustomerBean> customerList = customerDAO.selectCustomerCodeList(code);
		return customerList;
	}
}
