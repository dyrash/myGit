package com.sh.account.statement.to;

import com.sh.base.to.BaseBean;

public class FinancialPositionBean extends BaseBean{
	
	private String accountName;
	private String clPrice;
	private String crPrice;
	private String elPrice;
	private String erPrice;
	private String gId;
	
	public String getAccountName() {
		return accountName;
	}
	public String getClPrice() {
		return clPrice;
	}
	public String getCrPrice() {
		return crPrice;
	}
	public String getElPrice() {
		return elPrice;
	}
	public String getErPrice() {
		return erPrice;
	}
	public String getgId() {
		return gId;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public void setClPrice(String clPrice) {
		this.clPrice = clPrice;
	}
	public void setCrPrice(String crPrice) {
		this.crPrice = crPrice;
	}
	public void setElPrice(String elPrice) {
		this.elPrice = elPrice;
	}
	public void setErPrice(String erPrice) {
		this.erPrice = erPrice;
	}
	public void setgId(String gId) {
		this.gId = gId;
	}
	
	
	
}
