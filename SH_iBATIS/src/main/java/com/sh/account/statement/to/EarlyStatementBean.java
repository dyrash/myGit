package com.sh.account.statement.to;

import com.sh.base.to.BaseBean;

public class EarlyStatementBean extends BaseBean{
	
	private String groupCode;
	private String accountInnerCode;
	private String accountName;
	private String price;
	private String groupName;
	private String totalPrice;
	private String totalCreditPrice;
	
	
	
	public String getTotalCreditPrice() {
		return totalCreditPrice;
	}
	public void setTotalCreditPrice(String totalCreditPrice) {
		this.totalCreditPrice = totalCreditPrice;
	}
	public String getGroupName() {
		return groupName;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public String getAccountInnerCode() {
		return accountInnerCode;
	}
	public String getAccountName() {
		return accountName;
	}
	public String getPrice() {
		return price;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public void setAccountInnerCode(String accountInnerCode) {
		this.accountInnerCode = accountInnerCode;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	
}
