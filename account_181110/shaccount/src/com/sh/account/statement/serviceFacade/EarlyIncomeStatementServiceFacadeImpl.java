package com.sh.account.statement.serviceFacade;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sh.account.statement.applicationService.EarlyIncomeStatementApplicationService;
import com.sh.account.statement.applicationService.EarlyIncomeStatementApplicationServiceImpl;
import com.sh.account.statement.serviceFacade.EarlyIncomeStatementServiceFacadeImpl;
import com.sh.account.statement.to.EarlyIncomeStatementBean;
import com.sh.common.db.DataSourceTransactionManager;
import com.sh.common.exception.DataAccessException;


public class EarlyIncomeStatementServiceFacadeImpl implements EarlyIncomeStatementServiceFacade{
	protected final Log logger = LogFactory.getLog(this.getClass());
	private DataSourceTransactionManager dataSourceTransactionManager = DataSourceTransactionManager.getInstance();
	EarlyIncomeStatementApplicationService earlyIncomeStatementApplicationService = EarlyIncomeStatementApplicationServiceImpl.getInstance();
	private static EarlyIncomeStatementServiceFacade instance;
	private EarlyIncomeStatementServiceFacadeImpl(){}
	public static EarlyIncomeStatementServiceFacade getInstance() {
		// TODO Auto-generated method stub
		if(instance == null){
			instance = new EarlyIncomeStatementServiceFacadeImpl();
			System.out.println("		@ EarlyIncomeStatementServiceFacadeImpl에 접근");
		}
		return instance;
	}
	

	@Override
	public ArrayList<EarlyIncomeStatementBean> findEarlyIncomeStatementList() {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" EarlyIncomeStatementServiceFacadeImpl : earlyIncomeStatementList 시작 "); }
		dataSourceTransactionManager.beginTransaction();
		ArrayList<EarlyIncomeStatementBean> earlyIncomeStatementList = null;
		try{
			earlyIncomeStatementList=earlyIncomeStatementApplicationService.findEarlyIncomeStatementList();
			//System.out.println("		@ 얼리서비스포사드"+earlyIncomeStatementList.get(0));
			dataSourceTransactionManager.commitTransaction();
		}catch(DataAccessException e){
			logger.fatal(e.getMessage());
		   dataSourceTransactionManager.rollbackTransaction();
		   throw e;
	   }	
		if(logger.isDebugEnabled()){ logger.debug(" EarlyIncomeStatementServiceFacadeImpl : earlyIncomeStatementList 종료 "); }
		return earlyIncomeStatementList;
	}
	
	@Override
	public ArrayList<EarlyIncomeStatementBean> findEarlyIncomeList() {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" EarlyIncomeStatementServiceFacadeImpl : findEarlyIncomeList 시작 "); }
		dataSourceTransactionManager.beginTransaction();
		ArrayList<EarlyIncomeStatementBean> earlyIncomeList = null;
		try{
			earlyIncomeList=earlyIncomeStatementApplicationService.findEarlyIncomeList();
			//System.out.println("		@ 얼리서비스포사드"+earlyIncomeList.get(0));
			dataSourceTransactionManager.commitTransaction();
		}catch(DataAccessException e){
			logger.fatal(e.getMessage());
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}	
		if(logger.isDebugEnabled()){ logger.debug(" EarlyIncomeStatementServiceFacadeImpl : findEarlyIncomeList 종료 "); }
		return earlyIncomeList;
	}
}
