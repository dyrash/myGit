package com.sh.account.base.applicationService;


import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sh.account.base.applicationService.AccountBaseApplicationServiceImpl;

import com.sh.account.base.to.AccountControlBean;
import com.sh.account.base.to.AccountDetailBean;
import com.sh.account.base.dao.AccountControlDAO;
import com.sh.account.base.dao.AccountControlDAOImpl;

import com.sh.account.base.applicationService.AccountBaseApplicationService;
import com.sh.account.base.dao.AccountDAO;
import com.sh.account.base.dao.AccountDAOImpl;
import com.sh.account.base.dao.AccountDetailDAO;
import com.sh.account.base.dao.AccountDetailDAOImpl;
import com.sh.account.base.to.AccountBean;


@SuppressWarnings("unused")
public class AccountBaseApplicationServiceImpl implements AccountBaseApplicationService{

	protected final Log logger = LogFactory.getLog(this.getClass());
	
	private AccountDAO accountDAO;

	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}
	
	@Override
	public ArrayList<AccountBean> findAccountList() {
			// TODO Auto-generated method stub
			ArrayList<AccountBean> accountList = accountDAO.selectAccountList();
			return accountList;
		}
	
	public ArrayList<AccountBean> editAccountList(AccountBean bean) {
		// TODO Auto-generated method stub
		ArrayList<AccountBean> accountList = accountDAO.editAccountList(bean);
		return accountList;
	}
	
	public ArrayList<AccountBean> findParentAccountList() {
		// TODO Auto-generated method stub
		ArrayList<AccountBean> accountList = accountDAO.findParentAccountList();
		return accountList;
	}

	
	public ArrayList<AccountBean> findDetailAccountList(String code) {
		// TODO Auto-generated method stub
		ArrayList<AccountBean> accountList = accountDAO.findDetailAccountList(code);
		return accountList;
	}
	
	public ArrayList<AccountBean> findSelectAccountList(String code) {
		// TODO Auto-generated method stub
		ArrayList<AccountBean> accountList = accountDAO.findSelectAccountList(code);
		return accountList;
	}
}
