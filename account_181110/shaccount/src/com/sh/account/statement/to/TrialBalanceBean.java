package com.sh.account.statement.to;

public class TrialBalanceBean {
	
	private String accountName;
	private String accountCode;
	private String level;
	private String leafs;
	private String leftDebtorBalance;
	private String leftDebtorSum;
	private String rightCreditSum;
	private String rightCreditBalance;

	public String getLeftDebtorBalance() {
		return leftDebtorBalance;
	}
	public void setLeftDebtorBalance(String leftDebtorBalance) {
		this.leftDebtorBalance = leftDebtorBalance;
	}
	public String getLeftDebtorSum() {
		return leftDebtorSum;
	}
	public void setLeftDebtorSum(String leftDebtorSum) {
		this.leftDebtorSum = leftDebtorSum;
	}
	public void setRightCreditSum(String rightCreditSum) {
		this.rightCreditSum = rightCreditSum;
	}
	public String getRightCreditSum() {
		return rightCreditSum;
	}
	public void setRightCreditBalance(String rightCreditBalance) {
		this.rightCreditBalance = rightCreditBalance;
	}
	public String getRightCreditBalance() {
		return rightCreditBalance;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getLeafs() {
		return leafs;
	}
	public void setLeafs(String leafs) {
		this.leafs = leafs;
	}
	
}
