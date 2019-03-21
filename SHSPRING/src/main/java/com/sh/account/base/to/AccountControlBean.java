package com.sh.account.base.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sh.base.to.BaseBean;
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountControlBean extends BaseBean{
	private String id;
	private String accountCode;
	private String controlCode;
	private String detailName;
	private String detailType;
	
	public String getId() {
		return id;
	}
	public String getAccountCode() {
		return accountCode;
	}
	public String getControlCode() {
		return controlCode;
	}
	public String getDetailName() {
		return detailName;
	}
	public String getDetailType() {
		return detailType;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public void setControlCode(String controlCode) {
		this.controlCode = controlCode;
	}
	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}
	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}
	
	
	
}
