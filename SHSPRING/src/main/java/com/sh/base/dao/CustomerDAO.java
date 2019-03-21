package com.sh.base.dao;

import java.util.ArrayList;

import com.sh.base.to.CustomerBean;

public interface CustomerDAO {

	ArrayList<CustomerBean> selectCustomerCodeList(String code);


}
