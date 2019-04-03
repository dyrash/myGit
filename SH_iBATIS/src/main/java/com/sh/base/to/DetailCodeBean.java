package com.sh.base.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sh.base.to.BaseBean;

@JsonIgnoreProperties(ignoreUnknown = true) 

public class DetailCodeBean extends BaseBean{
	   String divisionCode;
	   String codeNo;
	   String codeName;
	   String codeUseCheck;
	   String divisionCodeNo;
	   String detailCode;
	   String detailCodeName;
	public String getDivisionCode() {
		return divisionCode;
	}
	public String getCodeNo() {
		return codeNo;
	}
	public String getCodeName() {
		return codeName;
	}
	public String getCodeUseCheck() {
		return codeUseCheck;
	}
	public String getDivisionCodeNo() {
		return divisionCodeNo;
	}
	public String getDetailCode() {
		return detailCode;
	}
	public String getDetailCodeName() {
		return detailCodeName;
	}
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}
	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public void setCodeUseCheck(String codeUseCheck) {
		this.codeUseCheck = codeUseCheck;
	}
	public void setDivisionCodeNo(String divisionCodeNo) {
		this.divisionCodeNo = divisionCodeNo;
	}
	public void setDetailCode(String detailCode) {
		this.detailCode = detailCode;
	}
	public void setDetailCodeName(String detailCodeName) {
		this.detailCodeName = detailCodeName;
	}

	   
	   
}
