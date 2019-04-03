package com.sh.base.to;

public class MenuBean {
	   String menuCode;
	   String menuName;
	   String url;
	   String parentMenuCode;
	   String authorityName;
	   String isAccessDenied;
	   
	   


	   public String getIsAccessDenied() {
		return isAccessDenied;
	}
	public void setIsAccessDenied(String isAccessDenied) {
		this.isAccessDenied = isAccessDenied;
	}

	   public String getMenuCode() {
	      return menuCode;
	   }
	   public void setMenuCode(String menuCode) {
	      this.menuCode = menuCode;
	   }
	   public String getMenuName() {
	      return menuName;
	   }
	   public void setMenuName(String menuName) {
	      this.menuName = menuName;
	   }
	   public String getUrl() {
	      return url;
	   }
	   public void setUrl(String url) {
	      this.url = url;
	   }
	   public String getParentMenuCode() {
	      return parentMenuCode;
	   }
	   public void setParentMenuCode(String parentMenuCode) {
	      this.parentMenuCode = parentMenuCode;
	   }
	   public String getAuthorityName() {
	      return authorityName;
	   }
	   public void setAuthorityName(String authorityName) {
	      this.authorityName = authorityName;
	   }
}
