package com.sh.base.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sh.base.to.BaseBean;

@JsonIgnoreProperties(ignoreUnknown = true) 

public class CustomerBean extends BaseBean{
	   String customerCode;
	   String customerName;
	
	   public String getCustomerCode() {
		return customerCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
}
