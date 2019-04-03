package com.sh.account.base.to;

import java.util.ArrayList;

import com.sh.account.base.to.AccountControlBean;
import com.sh.base.to.BaseBean;

public class AccountBean extends BaseBean{
	private String accountInnerCode;
	private String accountName;
	private String parentAccountInnerCode;
	private String accountUseCheck;
	private String accountCode;
	private String groupCode;
	private String accountCharacter;
	private String accountDivision;
	private String accountDisplayNameWithCode;
	private String accountDisplayName;
	private String accountDescription;
	private ArrayList<AccountControlBean> accountControlList;
	
	/*private String loaded;
	private String expanded;*/
	
	
	public String getAccountInnerCode() {
		return accountInnerCode;
	}
	public void setAccountInnerCode(String accountInnerCode) {
		this.accountInnerCode = accountInnerCode;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getParentAccountInnerCode() {
		return parentAccountInnerCode;
	}
	public void setParentAccountInnerCode(String parentAccountInnerCode) {
		this.parentAccountInnerCode = parentAccountInnerCode;
	}
	public String getAccountUseCheck() {
		return accountUseCheck;
	}
	public void setAccountUseCheck(String accountUseCheck) {
		this.accountUseCheck = accountUseCheck;
	}
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getAccountCharacter() {
		return accountCharacter;
	}
	public void setAccountCharacter(String accountCharacter) {
		this.accountCharacter = accountCharacter;
	}
	public String getAccountDivision() {
		return accountDivision;
	}
	public void setAccountDivision(String accountDivision) {
		this.accountDivision = accountDivision;
	}
	public String getAccountDisplayNameWithCode() {
		return accountDisplayNameWithCode;
	}
	public void setAccountDisplayNameWithCode(String accountDisplayNameWithCode) {
		this.accountDisplayNameWithCode = accountDisplayNameWithCode;
	}
	public String getAccountDisplayName() {
		return accountDisplayName;
	}
	public void setAccountDisplayName(String accountDisplayName) {
		this.accountDisplayName = accountDisplayName;
	}
	public String getAccountDescription() {
		return accountDescription;
	}
	public void setAccountDescription(String accountDescription) {
		this.accountDescription = accountDescription;
	}
	public ArrayList<AccountControlBean> getAccountControlList() {
		return accountControlList;
	}
	public void setAccountControlList(ArrayList<AccountControlBean> accountControlList) {
		this.accountControlList = accountControlList;
	}
	
	
	
	
	
	
}
