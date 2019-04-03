package com.sh.account.base.serviceFacade;

import java.util.ArrayList;

import com.sh.account.base.exception.AccountListNotFoundException;
import com.sh.account.base.to.AccountBean;
import com.sh.account.base.to.AccountControlBean;
import com.sh.account.base.to.AccountDetailBean;

public interface AccountServiceFacade {

	public ArrayList<AccountBean> findAccountList() throws AccountListNotFoundException;
	
	public ArrayList<AccountBean> findParentAccountList() throws AccountListNotFoundException;
	
	public ArrayList<AccountBean> findDetailAccountList(String code) throws AccountListNotFoundException;
	
	public ArrayList<AccountBean> findSelectAccountList(String code) throws AccountListNotFoundException;
	
	public ArrayList<AccountBean> editAccountList(AccountBean bean) throws AccountListNotFoundException;
	
	public ArrayList<AccountControlBean> findAccountControlList(String accountCode);

	public ArrayList<AccountDetailBean> findAccountDetailList();

	public void batchAccountControlListProcess(ArrayList<AccountControlBean> accountControlList);

}
