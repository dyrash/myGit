package com.sh.account.base.applicationService;

import java.util.ArrayList;

import com.sh.account.base.applicationService.AccountDetailBaseApplicationService;
import com.sh.account.base.applicationService.AccountDetailBaseApplicationServiceImpl;
import com.sh.account.base.dao.AccountControlDAO;
import com.sh.account.base.dao.AccountDetailDAO;
import com.sh.account.base.to.AccountControlBean;
import com.sh.account.base.to.AccountDetailBean;

public class AccountDetailBaseApplicationServiceImpl implements AccountDetailBaseApplicationService{
	private AccountControlDAO accountControlDAO;
	private AccountDetailDAO accountDetailDAO;

	public void setAccountControlDAO(AccountControlDAO accountControlDAO) {
		this.accountControlDAO = accountControlDAO;
	}
	public void setAccountDetailDAO(AccountDetailDAO accountDetailDAO) {
		this.accountDetailDAO = accountDetailDAO;
	}

	public ArrayList<AccountControlBean> findAccountControlList(String accountInnerCode) {
		// TODO Auto-generated method stub
		ArrayList<AccountControlBean> accountControlList = accountControlDAO.selectAccountControlList(accountInnerCode);
		return accountControlList;
	}
	
	public ArrayList<AccountDetailBean> findAccountDetailList() {
		// TODO Auto-generated method stub
		ArrayList<AccountDetailBean> controlItemList = accountDetailDAO.findControlItemList();
		return controlItemList;
	}
	public void batchAccountControlListProcess(ArrayList<AccountControlBean> accountControlList) {
		// TODO Auto-generated method stub
			for (AccountControlBean accountControl : accountControlList) { 
				switch (accountControl.getStatus()) {
				case "insert":
					accountControlDAO.insertAccountControl(accountControl);
					break;
				
				case "delete":
					accountControlDAO.deleteAccountControl(accountControl);
					break;				
				}
			}
	}
}
