package com.sh.base.applicationService;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sh.base.applicationService.CustomerListApplicationServiceImpl;
import com.sh.base.dao.CustomerDAO;
import com.sh.base.dao.CustomerDAOImpl;
import com.sh.base.to.CustomerBean;

public class CustomerListApplicationServiceImpl implements CustomerListApplicationService{

	protected final Log logger = LogFactory.getLog(this.getClass());
	
	private CustomerDAO customerDAO = CustomerDAOImpl.getinstance();
	
	private static CustomerListApplicationService instance ;
	private CustomerListApplicationServiceImpl(){};
	public static CustomerListApplicationService getInstance() {
		// TODO Auto-generated method stub
		if(instance ==null){
			instance = new CustomerListApplicationServiceImpl();
			System.out.println("		@ CustomerListApplicationServiceImpl에 접근");
		}
		return instance;
	}
	
	@Override
	public ArrayList<CustomerBean> selectCustomerCodeList(String code) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" CustomerListApplicationServiceImpl : selectCustomerCodeList 시작 "); }
		
		ArrayList<CustomerBean> customerList = null;
		try{
			customerList = customerDAO.selectCustomerCodeList(code);
		}catch(Exception e){
			logger.fatal(e.getMessage());
			throw e;    
		}
		if(logger.isDebugEnabled()){ logger.debug(" CustomerListApplicationServiceImpl : selectCustomerCodeList 종료 "); }
		return customerList;
	}
}
