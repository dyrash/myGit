package com.sh.account.base.dao;

import java.util.ArrayList;

import com.sh.account.base.to.AccountBean;

public interface AccountDAO {

	public ArrayList<AccountBean> selectAccountList();
	
	public ArrayList<AccountBean> findAccountList();
	public ArrayList<AccountBean> findParentAccountList();
	public ArrayList<AccountBean> editAccountList(AccountBean bean);
	
	public ArrayList<AccountBean> findDetailAccountList(String code);
	public ArrayList<AccountBean> findSelectAccountList(String code);
}
