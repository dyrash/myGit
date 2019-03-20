package com.sh.account.base.serviceFacade;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sh.account.base.applicationService.AccountBaseApplicationService;
import com.sh.account.base.applicationService.AccountBaseApplicationServiceImpl;
import com.sh.account.base.applicationService.AccountDetailBaseApplicationService;
import com.sh.account.base.applicationService.AccountDetailBaseApplicationServiceImpl;
import com.sh.account.base.to.AccountBean;
import com.sh.account.base.to.AccountControlBean;
import com.sh.account.base.to.AccountDetailBean;
import com.sh.common.db.DataSourceTransactionManager;
import com.sh.common.exception.DataAccessException;

public class AccountServiceFacadeImpl implements AccountServiceFacade{
	protected final Log logger = LogFactory.getLog(this.getClass());
	private DataSourceTransactionManager dataSourceTransactionManager = DataSourceTransactionManager.getInstance();
	AccountBaseApplicationService accountBaseApplicationService = AccountBaseApplicationServiceImpl.getInstance();
	AccountDetailBaseApplicationService accountDetailBaseApplicationService = AccountDetailBaseApplicationServiceImpl.getInstance();
	
	private static AccountServiceFacade instance ;
	private AccountServiceFacadeImpl(){}
	public static AccountServiceFacade getInstance() {
		// TODO Auto-generated method stub
		if(instance ==null){
			instance = new AccountServiceFacadeImpl();
		}
		return instance;
	}
	
	@Override
	public ArrayList<AccountBean> findAccountList() {
		// TODO Auto-generated method stub
		 dataSourceTransactionManager.beginTransaction();
		if(logger.isDebugEnabled()){ logger.debug(" AccountServiceFacadeImpl : findAccountList 시작 "); }
		ArrayList<AccountBean> accountList=null;
		
		 try{
			
			accountList=accountBaseApplicationService.findAccountList();
			dataSourceTransactionManager.commitTransaction();
		 }catch(DataAccessException e){
			 logger.fatal(e.getMessage());
	           dataSourceTransactionManager.rollbackTransaction();
	           throw e;
	     }
		 if(logger.isDebugEnabled()){ logger.debug(" AccountServiceFacadeImpl : findAccountList 종료 "); }
		 return accountList;
	}
	
	@Override
	public ArrayList<AccountBean> findDetailAccountList(String code) {
		// TODO Auto-generated method stub
		dataSourceTransactionManager.beginTransaction();
		if(logger.isDebugEnabled()){ logger.debug(" AccountServiceFacadeImpl : findDetailAccountList 시작 "); }
		ArrayList<AccountBean> accountList=null;
		
		try{
			
			accountList=accountBaseApplicationService.findDetailAccountList(code);
			dataSourceTransactionManager.commitTransaction();
		}catch(DataAccessException e){
			logger.fatal(e.getMessage());
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
		if(logger.isDebugEnabled()){ logger.debug(" AccountServiceFacadeImpl : findDetailAccountList 종료 "); }
		return accountList;
	}
	
	
	
	
	@Override
	public ArrayList<AccountControlBean> findAccountControlList(String accountInnerCode) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" AccountDetailServiceFacadeImpl : findAccountControlList 시작 "); }
		 dataSourceTransactionManager.beginTransaction();
	        ArrayList<AccountControlBean> accountControlList=null; //?
	        try{
	        	accountControlList=accountDetailBaseApplicationService.findAccountControlList(accountInnerCode);
	        	dataSourceTransactionManager.commitTransaction();
	        }catch(DataAccessException e){
	        	logger.fatal(e.getMessage());
	        	dataSourceTransactionManager.rollbackTransaction();
	        	throw e;
	        }
	        if(logger.isDebugEnabled()){ logger.debug(" AccountDetailServiceFacadeImpl : findAccountControlList 종료 "); }
			return accountControlList;
		}

	
	@Override
	public ArrayList<AccountBean> findSelectAccountList(String code) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" AccountDetailServiceFacadeImpl : findSelectAccountList 시작 "); }
		dataSourceTransactionManager.beginTransaction();
		ArrayList<AccountBean> accountList=null; //?
		//System.out.println(code);
		try{
			accountList=accountBaseApplicationService.findSelectAccountList(code);
			dataSourceTransactionManager.commitTransaction();
		}catch(DataAccessException e){
			logger.fatal(e.getMessage());
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
		if(logger.isDebugEnabled()){ logger.debug(" AccountDetailServiceFacadeImpl : findSelectAccountList 종료 "); }
		return accountList;
	}
	
	
	@Override
	public ArrayList<AccountDetailBean> findAccountDetailList() {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" AccountDetailServiceFacadeImpl : findAccountDetailList 시작 "); }
		 dataSourceTransactionManager.beginTransaction();
			ArrayList<AccountDetailBean> controlItemList=null;
	        try{
	        	controlItemList=accountDetailBaseApplicationService.findAccountDetailList();
	        	dataSourceTransactionManager.commitTransaction();
	        }catch(DataAccessException e){
	        	logger.fatal(e.getMessage());
	    		dataSourceTransactionManager.rollbackTransaction();
	    	throw e;
	        }
	        if(logger.isDebugEnabled()){ logger.debug(" AccountDetailServiceFacadeImpl : findAccountDetailList 종료 "); }
			return controlItemList;
	}
	@Override
	public void batchAccountControlListProcess(ArrayList<AccountControlBean> accountControlList) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" AccountDetailServiceFacadeImpl : batchAccountControlListProcess 시작 "); }
		 dataSourceTransactionManager.beginTransaction();
	        try{
	        	accountDetailBaseApplicationService.batchAccountControlListProcess(accountControlList);
	        	dataSourceTransactionManager.commitTransaction();
	        }catch(DataAccessException e){
	        	logger.fatal(e.getMessage());
	        	dataSourceTransactionManager.rollbackTransaction();
	        	
	        	throw e;
	        }
	        if(logger.isDebugEnabled()){ logger.debug(" AccountDetailServiceFacadeImpl : batchAccountControlListProcess 종료 "); }
	}
	

	
	@Override
	public ArrayList<AccountBean> editAccountList(AccountBean bean) {
		// TODO Auto-generated method stub
		 dataSourceTransactionManager.beginTransaction();
		if(logger.isDebugEnabled()){ logger.debug(" AccountServiceFacadeImpl : editAccountList 시작 "); }
		ArrayList<AccountBean> accountList=null;
		
		 try{
			
			accountList=accountBaseApplicationService.editAccountList(bean);
			dataSourceTransactionManager.commitTransaction();
		 }catch(DataAccessException e){
			 logger.fatal(e.getMessage());
	           dataSourceTransactionManager.rollbackTransaction();
	           throw e;
	     }
		 if(logger.isDebugEnabled()){ logger.debug(" AccountServiceFacadeImpl : editAccountList 종료 "); }
		 return accountList;
	}
	
	@Override
	public ArrayList<AccountBean> findParentAccountList() {
		// TODO Auto-generated method stub
		dataSourceTransactionManager.beginTransaction();
		if(logger.isDebugEnabled()){ logger.debug(" AccountServiceFacadeImpl : findParentAccountList 시작 "); }
		ArrayList<AccountBean> accountList=null;
		
		try{
			
			accountList=accountBaseApplicationService.findParentAccountList();
			dataSourceTransactionManager.commitTransaction();
		}catch(DataAccessException e){
			logger.fatal(e.getMessage());
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
		if(logger.isDebugEnabled()){ logger.debug(" AccountServiceFacadeImpl : findParentAccountList 종료 "); }
		return accountList;
	}
	
	
	
	
}
