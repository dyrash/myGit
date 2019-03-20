package com.sh.account.base.serviceFacade;

import java.util.ArrayList;

import com.sh.account.base.to.AccountBean;
import com.sh.account.base.to.AccountControlBean;
import com.sh.account.base.to.AccountDetailBean;

public interface AccountServiceFacade {

	public ArrayList<AccountBean> findAccountList();
	
	public ArrayList<AccountBean> findParentAccountList();
	
	public ArrayList<AccountBean> findDetailAccountList(String code);
	
	public ArrayList<AccountBean> findSelectAccountList(String code);
	
	public ArrayList<AccountBean> editAccountList(AccountBean bean);
	
	public ArrayList<AccountControlBean> findAccountControlList(String accountCode);

	public ArrayList<AccountDetailBean> findAccountDetailList();

	public void batchAccountControlListProcess(ArrayList<AccountControlBean> accountControlList);

}
