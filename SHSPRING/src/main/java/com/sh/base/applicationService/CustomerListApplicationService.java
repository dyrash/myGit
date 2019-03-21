package com.sh.base.applicationService;

import java.util.ArrayList;

import com.sh.base.to.CustomerBean;

public interface CustomerListApplicationService {

	ArrayList<CustomerBean> selectCustomerCodeList(String code);
	

}
