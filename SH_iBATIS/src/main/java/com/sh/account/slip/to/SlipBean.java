package com.sh.account.slip.to;

import java.util.ArrayList;
import java.util.List;

import com.sh.account.slip.to.JournalBean;
import com.sh.base.to.BaseBean;

public class SlipBean extends BaseBean{
	private String slipNo;
	private String accountPeriodNo;
	private String deptCode;
	private String slipType;
	private String expenseReport;
	private String authorizationStatus;
	private String reportingEmpCode;
	private String reportingDate;
	private String reportingTime;
	private String approvalEmpCode;
	private String approvalDate;
	private String approvalTime;
	private String modifyingEmpCode;
	private String modifyingDate;
	private String modifyingTime;
	private String accountDifference;
	private String slipDescription;
	private String slipStatus;
	private String reportingEmpName;
	private String approvalEmpName;
	private List<JournalBean> journalList;
	
	
	public List<JournalBean> getJournalList() {
		return journalList;
	}
	public void setJournalList(List<JournalBean> journalList) {
		this.journalList = journalList;
	}
	public String getSlipNo() {
		return slipNo;
	}
	public void setSlipNo(String slipNo) {
		this.slipNo = slipNo;
	}
	public String getAccountPeriodNo() {
		return accountPeriodNo;
	}
	public void setAccountPeriodNo(String accountPeriodNo) {
		this.accountPeriodNo = accountPeriodNo;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getSlipType() {
		return slipType;
	}
	public void setSlipType(String slipType) {
		this.slipType = slipType;
	}
	public String getExpenseReport() {
		return expenseReport;
	}
	public void setExpenseReport(String expenseReport) {
		this.expenseReport = expenseReport;
	}
	public String getAuthorizationStatus() {
		return authorizationStatus;
	}
	public void setAuthorizationStatus(String authorizationStatus) {
		this.authorizationStatus = authorizationStatus;
	}
	public String getReportingEmpCode() {
		return reportingEmpCode;
	}
	public void setReportingEmpCode(String reportingEmpCode) {
		this.reportingEmpCode = reportingEmpCode;
	}
	public String getReportingDate() {
		return reportingDate;
	}
	public void setReportingDate(String reportingDate) {
		this.reportingDate = reportingDate;
	}
	public String getReportingTime() {
		return reportingTime;
	}
	public void setReportingTime(String reportingTime) {
		this.reportingTime = reportingTime;
	}
	public String getApprovalEmpCode() {
		return approvalEmpCode;
	}
	public void setApprovalEmpCode(String approvalEmpCode) {
		this.approvalEmpCode = approvalEmpCode;
	}
	public String getApprovalDate() {
		return approvalDate;
	}
	public void setApprovalDate(String approvalDate) {
		this.approvalDate = approvalDate;
	}
	public String getApprovalTime() {
		return approvalTime;
	}
	public void setApprovalTime(String approvalTime) {
		this.approvalTime = approvalTime;
	}
	public String getModifyingEmpCode() {
		return modifyingEmpCode;
	}
	public void setModifyingEmpCode(String modifyingEmpCode) {
		this.modifyingEmpCode = modifyingEmpCode;
	}
	public String getModifyingDate() {
		return modifyingDate;
	}
	public void setModifyingDate(String modifyingDate) {
		this.modifyingDate = modifyingDate;
	}
	public String getModifyingTime() {
		return modifyingTime;
	}
	public void setModifyingTime(String modifyingTime) {
		this.modifyingTime = modifyingTime;
	}
	public String getAccountDifference() {
		return accountDifference;
	}
	public void setAccountDifference(String accountDifference) {
		this.accountDifference = accountDifference;
	}
	public String getSlipDescription() {
		return slipDescription;
	}
	public void setSlipDescription(String slipDescription) {
		this.slipDescription = slipDescription;
	}
	public String getSlipStatus() {
		return slipStatus;
	}
	public void setSlipStatus(String slipStatus) {
		this.slipStatus = slipStatus;
	}
	/*public ArrayList<JournalBean> getJournalList() {
		return journalList;
	}
	public void setJournalList(ArrayList<JournalBean> journalList) {
		this.journalList = journalList;
	}*/
	public String getReportingEmpName() {
		return reportingEmpName;
	}
	public void setReportingEmpName(String reportingEmpName) {
		this.reportingEmpName = reportingEmpName;
	}
	public String getApprovalEmpName() {
		return approvalEmpName;
	}
	public void setApprovalEmpName(String approvalEmpName) {
		this.approvalEmpName = approvalEmpName;
	}

	   
}
