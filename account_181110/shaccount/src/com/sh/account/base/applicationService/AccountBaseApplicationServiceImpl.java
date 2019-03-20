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
import com.sh.common.exception.DataAccessException;

@SuppressWarnings("unused")
public class AccountBaseApplicationServiceImpl implements AccountBaseApplicationService{

	protected final Log logger = LogFactory.getLog(this.getClass());
	
	private AccountDAO accountDAO = AccountDAOImpl.getInstance();
	private AccountDetailDAO detailAccountDAO = AccountDetailDAOImpl.getInstance();
	
	private static AccountBaseApplicationService instance;
	private AccountBaseApplicationServiceImpl(){}
	public static AccountBaseApplicationService getInstance() {
		// TODO Auto-generated method stub
		if(instance ==null){
			instance = new AccountBaseApplicationServiceImpl();
		}
		return instance;
	}
	
	public ArrayList<AccountBean> findAccountList() {
			// TODO Auto-generated method stub
			if(logger.isDebugEnabled()){ logger.debug(" AccountBaseApplicationServiceImpl : findAccountList 시작 "); }
			ArrayList<AccountBean> accountList = null;
			
			try {
				accountList = accountDAO.selectAccountList();
				
			} catch (DataAccessException e) {
				logger.fatal(e.getMessage());
				throw e;
			}
			if(logger.isDebugEnabled()){ logger.debug(" AccountBaseApplicationServiceImpl : findAccountList 종료 "); }
			return accountList;
		}
	
	public ArrayList<AccountBean> editAccountList(AccountBean bean) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" AccountBaseApplicationServiceImpl : editAccountList 시작 "); }
		ArrayList<AccountBean> accountList = null;
		
		try {
			accountList = accountDAO.editAccountList(bean);
			
		} catch (DataAccessException e) {
			logger.fatal(e.getMessage());
			throw e;
		}
		if(logger.isDebugEnabled()){ logger.debug(" AccountBaseApplicationServiceImpl : editAccountList 종료 "); }
		return accountList;
	}
	
	public ArrayList<AccountBean> findParentAccountList() {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" AccountBaseApplicationServiceImpl : findParentAccountList 시작 "); }
		ArrayList<AccountBean> accountList = null;
		
		try {
			accountList = accountDAO.findParentAccountList();
			
		} catch (DataAccessException e) {
			logger.fatal(e.getMessage());
			throw e;
		}
		if(logger.isDebugEnabled()){ logger.debug(" AccountBaseApplicationServiceImpl : findParentAccountList 종료 "); }
		return accountList;
	}

	
	public ArrayList<AccountBean> findDetailAccountList(String code) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" AccountBaseApplicationServiceImpl : findDetailAccountList 시작 "); }
		ArrayList<AccountBean> accountList = null;
		
		try {
			accountList = accountDAO.findDetailAccountList(code);
			
		} catch (DataAccessException e) {
			logger.fatal(e.getMessage());
			throw e;
		}
		if(logger.isDebugEnabled()){ logger.debug(" AccountBaseApplicationServiceImpl : findDetailAccountList 종료 "); }
		return accountList;
	}
	
	public ArrayList<AccountBean> findSelectAccountList(String code) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" AccountBaseApplicationServiceImpl : findDetailAccountList 시작 "); }
		ArrayList<AccountBean> accountList = null;
		//System.out.println(code);
		try {
			accountList = accountDAO.findSelectAccountList(code);
			
		} catch (DataAccessException e) {
			logger.fatal(e.getMessage());
			throw e;
		}
		if(logger.isDebugEnabled()){ logger.debug(" AccountBaseApplicationServiceImpl : findDetailAccountList 종료 "); }
		return accountList;
	}
	
	
	

	

}
