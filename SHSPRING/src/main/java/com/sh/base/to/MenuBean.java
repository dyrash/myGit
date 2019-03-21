package com.sh.base.to;

public class MenuBean {
	   String menuCode;
	   String menuName;
	   String menuUrl;
	   String superMenuCode;
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
	   public String getMenuUrl() {
	      return menuUrl;
	   }
	   public void setMenuUrl(String menuUrl) {
	      this.menuUrl = menuUrl;
	   }
	   public String getSuperMenuCode() {
	      return superMenuCode;
	   }
	   public void setSuperMenuCode(String superMenuCode) {
	      this.superMenuCode = superMenuCode;
	   }
	   public String getAuthorityName() {
	      return authorityName;
	   }
	   public void setAuthorityName(String authorityName) {
	      this.authorityName = authorityName;
	   }
}
