package com.sh.account.base.applicationService;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sh.account.base.applicationService.AccountDetailBaseApplicationService;
import com.sh.account.base.applicationService.AccountDetailBaseApplicationServiceImpl;
import com.sh.account.base.dao.AccountControlDAO;
import com.sh.account.base.dao.AccountControlDAOImpl;
import com.sh.account.base.dao.AccountDetailDAO;
import com.sh.account.base.dao.AccountDetailDAOImpl;
import com.sh.account.base.to.AccountControlBean;
import com.sh.account.base.to.AccountDetailBean;
import com.sh.common.exception.DataAccessException;

public class AccountDetailBaseApplicationServiceImpl implements AccountDetailBaseApplicationService{
	protected final Log logger = LogFactory.getLog(this.getClass());
	private AccountControlDAO accountControlDAO = AccountControlDAOImpl.getInstance();
	private AccountDetailDAO accountDetailDAO = AccountDetailDAOImpl.getInstance();
	
	private static AccountDetailBaseApplicationService instance;
	private AccountDetailBaseApplicationServiceImpl(){}
	public static AccountDetailBaseApplicationService getInstance() {
		// TODO Auto-generated method stub
		if(instance ==null){
			instance = new AccountDetailBaseApplicationServiceImpl();
		}
		return instance;
	}

	public ArrayList<AccountControlBean> findAccountControlList(String accountInnerCode) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" AccountDetailBaseApplicationServiceImpl : findAccountControlList 시작 "); }
		ArrayList<AccountControlBean> accountControlList = null;
		try {
			accountControlList = accountControlDAO.selectAccountControlList(accountInnerCode);
		} catch (DataAccessException e) {
			logger.fatal(e.getMessage());
			throw e;
		}		
		if(logger.isDebugEnabled()){ logger.debug(" AccountDetailBaseApplicationServiceImpl : findAccountControlList 종료 "); }
		return accountControlList;
	}
	
	public ArrayList<AccountDetailBean> findAccountDetailList() {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" AccountDetailBaseApplicationServiceImpl : findAccountDetailList 시작 "); }
		ArrayList<AccountDetailBean> controlItemList = null;
		try {
			controlItemList = accountDetailDAO.findControlItemList();
		} catch (DataAccessException e) {
			logger.fatal(e.getMessage());
			throw e;
		}
		if(logger.isDebugEnabled()){ logger.debug(" AccountDetailBaseApplicationServiceImpl : findAccountDetailList 종료 "); }
		return controlItemList;
	}
	public void batchAccountControlListProcess(ArrayList<AccountControlBean> accountControlList) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" AccountDetailBaseApplicationServiceImpl : batchAccountControlListProcess 시작 "); }
		try {
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
		} catch (Exception e) {
			logger.fatal(e.getMessage());
			
			throw e;
		}
		if(logger.isDebugEnabled()){ logger.debug(" AccountDetailBaseApplicationServiceImpl : batchAccountControlListProcess 종료 "); }
	}

	
	

}
