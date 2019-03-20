package com.sh.account.statement.serviceFacade;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sh.account.statement.serviceFacade.StatementServiceFacade;
import com.sh.account.statement.serviceFacade.StatementServiceFacadeImpl;
import com.sh.account.statement.applicationService.StatementApplicationService;
import com.sh.account.statement.applicationService.StatementApplicationServiceImpl;
import com.sh.common.db.DataSourceTransactionManager;
import com.sh.common.exception.DataAccessException;


public class StatementServiceFacadeImpl implements StatementServiceFacade{
	protected final Log logger = LogFactory.getLog(this.getClass());

	private DataSourceTransactionManager dataSourceTransactionManager = DataSourceTransactionManager.getInstance();
	StatementApplicationService statementApplicationService = StatementApplicationServiceImpl.getInstance();
	private static StatementServiceFacade instance;
	private StatementServiceFacadeImpl(){}
	public static StatementServiceFacade getInstance() {
		// TODO Auto-generated method stub
		if(instance == null){
			instance = new StatementServiceFacadeImpl();
			System.out.println("		@ StatementServiceFacadeImpl에 접근");
		}
		return instance;
	}
	

	@Override
	public HashMap<String, Object> findTrialBalanceList(String fromDate, String toDate) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" StatementServiceFacadeImpl : findTrialBalanceList 시작 "); }
		dataSourceTransactionManager.beginTransaction();
	    HashMap<String, Object> trialBalanceList=null;
		try{
			System.out.println("		@ 조회일자 : "+toDate+" 까지");
			trialBalanceList=statementApplicationService.findTrialBalanceList(fromDate, toDate);
		}catch(DataAccessException e){
			logger.fatal(e.getMessage());
		   dataSourceTransactionManager.rollbackTransaction();
		   throw e;
	   }	
		if(logger.isDebugEnabled()){ logger.debug(" StatementServiceFacadeImpl : findTrialBalanceList 종료 "); }
		return trialBalanceList;
	}


	@Override
	public HashMap<String,Object> findincomeStatementList(String fromDate, String toDate){
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" StatementServiceFacadeImpl : findincomeStatementList   시작 "); }
		dataSourceTransactionManager.beginTransaction();
	    HashMap<String, Object> incomeStatementList=null;
		try{
			incomeStatementList=statementApplicationService.findincomeStatementList(fromDate, toDate);
			System.out.println("		@ 조회일자 : "+toDate+" 까지");
		}catch(DataAccessException e){
			logger.fatal(e.getMessage());
		   dataSourceTransactionManager.rollbackTransaction();
		   throw e;
	   }	
		if(logger.isDebugEnabled()){ logger.debug(" StatementServiceFacadeImpl : findincomeStatementList   종료 "); }
		return incomeStatementList;
		
	}
	@Override
	public HashMap<String, Object> findFinancialPositionList(String fromDate, String toDate) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" StatementServiceFacadeImpl : findFinancialPositionList   시작 "); }
		dataSourceTransactionManager.beginTransaction();
	    HashMap<String, Object> financialPositionList=null;
		try{
			financialPositionList=statementApplicationService.findFinancialPositionList(fromDate, toDate);
			System.out.println("		@ 조회일자 : "+toDate+" 까지");
		}catch(DataAccessException e){
			logger.fatal(e.getMessage());
		   dataSourceTransactionManager.rollbackTransaction();
		   throw e;
	   }	
		if(logger.isDebugEnabled()){ logger.debug(" StatementServiceFacadeImpl : findFinancialPositionList   종료 "); }
		return financialPositionList;
	}
}
