package com.sh.account.base.applicationService;

import java.util.ArrayList;

import com.sh.account.base.to.AccountBean;

public interface AccountBaseApplicationService {

	public ArrayList<AccountBean> findAccountList();
	
	public ArrayList<AccountBean> editAccountList(AccountBean bean);
	
	public ArrayList<AccountBean> findParentAccountList();
	
	public ArrayList<AccountBean> findDetailAccountList(String code);
	
	public ArrayList<AccountBean> findSelectAccountList(String code);
	
}
