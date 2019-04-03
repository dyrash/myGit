package com.sh.account.slip.to;

import java.util.ArrayList;

import com.sh.account.slip.to.JournalDetailBean;
import com.sh.base.to.BaseBean;

public class JournalBean extends BaseBean{
	private String journalNo;
	private String slipNo;
	private String balanceDivision;
	private String accountInnerCode;
	private String accountName;
	private String summaryNumber;
	private String summaryComment;
	private String customerCode;
	private String customerName;
	private String leftDebtorPrice;
	private String rightCreditPrice;
	private String slipApprovalDate;
	private String price;
	private String journalDescription;
	private JournalDetailBean journalDetailBean;
	private ArrayList<JournalDetailBean> journalDetailList;
	public ArrayList<JournalDetailBean> getJournalDetailList() {
		return journalDetailList;
	}
	public void setJournalDetailList(ArrayList<JournalDetailBean> journalDetailList) {
		this.journalDetailList = journalDetailList;
	}
	public void setJournalNo(String journalNo) {
		this.journalNo = journalNo;
	}
	public void setSlipNo(String slipNo) {
		this.slipNo = slipNo;
	}
	public void setBalanceDivision(String balanceDivision) {
		this.balanceDivision = balanceDivision;
	}
	public void setAccountInnerCode(String accountInnerCode) {
		this.accountInnerCode = accountInnerCode;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public void setSummaryNumber(String summaryNumber) {
		this.summaryNumber = summaryNumber;
	}
	public void setSummaryComment(String summaryComment) {
		this.summaryComment = summaryComment;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public void setLeftDebtorPrice(String leftDebtorPrice) {
		this.leftDebtorPrice = leftDebtorPrice;
	}
	public void setRightCreditPrice(String rightCreditPrice) {
		this.rightCreditPrice = rightCreditPrice;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public void setSlipApprovalDate(String slipApprovalDate) {
		this.slipApprovalDate = slipApprovalDate;
	}
	public void setJournalDetailBean(JournalDetailBean journalDetailBean) {
		this.journalDetailBean = journalDetailBean;
	}
	public String getJournalNo() {
		return journalNo;
	}
	public String getSlipNo() {
		return slipNo;
	}
	public String getBalanceDivision() {
		return balanceDivision;
	}
	public String getAccountInnerCode() {
		return accountInnerCode;
	}
	public String getAccountName() {
		return accountName;
	}
	public String getSummaryNumber() {
		return summaryNumber;
	}
	public String getSummaryComment() {
		return summaryComment;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public String getLeftDebtorPrice() {
		return leftDebtorPrice;
	}
	public String getRightCreditPrice() {
		return rightCreditPrice;
	}
	public String getPrice() {
		return price;
	}
	public String getSlipApprovalDate() {
		return slipApprovalDate;
	}
	public JournalDetailBean getJournalDetailBean() {
		return journalDetailBean;
	}
	public String getJournalDescription() {
		return journalDescription;
	}
	public void setJournalDescription(String journalDescription) {
		this.journalDescription = journalDescription;
	}
	
	
	
	
}
