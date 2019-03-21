package com.sh.account.slip.to;

import java.util.ArrayList;

import com.sh.account.slip.to.JournalBean;
import com.sh.base.to.BaseBean;

public class SlipBean extends BaseBean{
	   private String slipNo;
	   private String reportingEmpCode;
	   private String deptCode;
	   private String approvalEmpCode;
	   private String reportingDate;
	   private int slipSeq;
	   private String requestName;
	   private String slipType;
	   private String accountDifference;
	   private int approvalSeq; 
	   private String slipStatus;
	   private String approvalDate;
	   private String reportingEmpName;
	   private String approvalEmpName;
	   private String accountPeriodNo;
	   private ArrayList<JournalBean> journalList;
	   
	   
	public String getSlipNo() {
		return slipNo;
	}
	public String getReportingEmpCode() {
		return reportingEmpCode;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public String getApprovalEmpCode() {
		return approvalEmpCode;
	}
	public String getReportingDate() {
		return reportingDate;
	}
	public int getSlipSeq() {
		return slipSeq;
	}
	public String getRequestName() {
		return requestName;
	}
	public String getSlipType() {
		return slipType;
	}
	public String getAccountDifference() {
		return accountDifference;
	}
	public int getApprovalSeq() {
		return approvalSeq;
	}
	public String getSlipStatus() {
		return slipStatus;
	}
	public String getApprovalDate() {
		return approvalDate;
	}
	public String getReportingEmpName() {
		return reportingEmpName;
	}
	public String getApprovalEmpName() {
		return approvalEmpName;
	}
	public String getAccountPeriodNo() {
		return accountPeriodNo;
	}
	public ArrayList<JournalBean> getJournalList() {
		return journalList;
	}
	public void setSlipNo(String slipNo) {
		this.slipNo = slipNo;
	}
	public void setReportingEmpCode(String reportingEmpCode) {
		this.reportingEmpCode = reportingEmpCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public void setApprovalEmpCode(String approvalEmpCode) {
		this.approvalEmpCode = approvalEmpCode;
	}
	public void setReportingDate(String reportingDate) {
		this.reportingDate = reportingDate;
	}
	public void setSlipSeq(int slipSeq) {
		this.slipSeq = slipSeq;
	}
	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}
	public void setSlipType(String slipType) {
		this.slipType = slipType;
	}
	public void setAccountDifference(String accountDifference) {
		this.accountDifference = accountDifference;
	}
	public void setApprovalSeq(int approvalSeq) {
		this.approvalSeq = approvalSeq;
	}
	public void setSlipStatus(String slipStatus) {
		this.slipStatus = slipStatus;
	}
	public void setApprovalDate(String approvalDate) {
		this.approvalDate = approvalDate;
	}
	public void setReportingEmpName(String reportingEmpName) {
		this.reportingEmpName = reportingEmpName;
	}
	public void setApprovalEmpName(String approvalEmpName) {
		this.approvalEmpName = approvalEmpName;
	}
	public void setAccountPeriodNo(String accountPeriodNo) {
		this.accountPeriodNo = accountPeriodNo;
	}
	public void setJournalList(ArrayList<JournalBean> journalList) {
		this.journalList = journalList;
	}
	   
}
