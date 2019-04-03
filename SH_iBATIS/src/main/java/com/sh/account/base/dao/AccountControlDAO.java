package com.sh.account.base.dao;

import java.util.ArrayList;

import com.sh.account.base.to.AccountControlBean;

public interface AccountControlDAO {

	public ArrayList<AccountControlBean> selectAccountControlList(String accountCode);

	public void insertAccountControl(AccountControlBean accountControl);

	public void deleteAccountControl(AccountControlBean accountControl);

	

}
