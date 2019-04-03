package com.sh.account.statement.to;


public class IncomeStatementBean {
	
	private String currentPrice;
	private String currentTotal;
	private String earlyPrice;
	private String earlyTotal;
	private String accountName;
	public String getCurrentPrice() {
		return currentPrice;
	}
	public String getCurrentTotal() {
		return currentTotal;
	}
	public String getEarlyPrice() {
		return earlyPrice;
	}
	public String getEarlyTotal() {
		return earlyTotal;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setCurrentPrice(String currentPrice) {
		this.currentPrice = currentPrice;
	}
	public void setCurrentTotal(String currentTotal) {
		this.currentTotal = currentTotal;
	}
	public void setEarlyPrice(String earlyPrice) {
		this.earlyPrice = earlyPrice;
	}
	public void setEarlyTotal(String earlyTotal) {
		this.earlyTotal = earlyTotal;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	
}
