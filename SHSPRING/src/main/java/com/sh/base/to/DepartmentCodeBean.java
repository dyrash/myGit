package com.sh.base.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sh.base.to.BaseBean;

@JsonIgnoreProperties(ignoreUnknown = true) 

public class DepartmentCodeBean extends BaseBean{
	   String detailCode;
	   String detailCodeName;
	public String getDetailCode() {
		return detailCode;
	}
	public String getDetailCodeName() {
		return detailCodeName;
	}
	public void setDetailCode(String detailCode) {
		this.detailCode = detailCode;
	}
	public void setDetailCodeName(String detailCodeName) {
		this.detailCodeName = detailCodeName;
	}

}
