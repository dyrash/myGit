package com.sh.account.statement.serviceFacade;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sh.account.statement.applicationService.EarlyStatementApplicationService;
import com.sh.account.statement.applicationService.EarlyStatementApplicationServiceImpl;
import com.sh.account.statement.serviceFacade.EarlyStatementServiceFacadeImpl;
import com.sh.account.statement.to.EarlyStatementBean;
import com.sh.common.db.DataSourceTransactionManager;
import com.sh.common.exception.DataAccessException;


public class EarlyStatementServiceFacadeImpl implements EarlyStatementServiceFacade{
	protected final Log logger = LogFactory.getLog(this.getClass());
	private DataSourceTransactionManager dataSourceTransactionManager = DataSourceTransactionManager.getInstance();
	EarlyStatementApplicationService earlyStatementApplicationService = EarlyStatementApplicationServiceImpl.getInstance();
	private static EarlyStatementServiceFacade instance;
	private EarlyStatementServiceFacadeImpl(){}
	public static EarlyStatementServiceFacade getInstance() {
		// TODO Auto-generated method stub
		if(instance == null){
			instance = new EarlyStatementServiceFacadeImpl();
			System.out.println("		@ EarlyStatementApplicationServiceImpl에 접근");
		}
		return instance;
	}
	

	@Override
	public ArrayList<EarlyStatementBean> findEarlyAssets() {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" EarlyStatementServiceFacadeImpl : findEarlyAssets 시작 "); }
		dataSourceTransactionManager.beginTransaction();
		ArrayList<EarlyStatementBean> assetList = null;
		try{
			assetList=earlyStatementApplicationService.findEarlyAssets();
			//System.out.println("		@ 얼리서비스포사드"+assetList.get(0).getGroupCode());
			dataSourceTransactionManager.commitTransaction();
		}catch(DataAccessException e){
			logger.fatal(e.getMessage());
		   dataSourceTransactionManager.rollbackTransaction();
		   throw e;
	   }	
		if(logger.isDebugEnabled()){ logger.debug(" EarlyStatementServiceFacadeImpl : findEarlyAssets 종료 "); }
		return assetList;
	}

	@Override
	public ArrayList<EarlyStatementBean> findEarlyLiabilitiseNequity() {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" EarlyStatementServiceFacadeImpl : findEarlyLiabilitiseNequity 시작 "); }
		dataSourceTransactionManager.beginTransaction();
		ArrayList<EarlyStatementBean> liabNequiList = null;
		try{
			liabNequiList=earlyStatementApplicationService.findEarlyLiabilitiseNequity();
			//System.out.println("		@ 얼리서비스포사드"+liabNequiList.get(0).getGroupCode());
			dataSourceTransactionManager.commitTransaction();
		}catch(DataAccessException e){
			logger.fatal(e.getMessage());
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}	
		if(logger.isDebugEnabled()){ logger.debug(" EarlyStatementServiceFacadeImpl : findEarlyLiabilitiseNequity 종료 "); }
		return liabNequiList;
	}
	
	@Override
	public ArrayList<EarlyStatementBean> findEarlyFinancialPosition() {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" EarlyStatementServiceFacadeImpl : findEarlyFinancialPosition 시작 "); }
		dataSourceTransactionManager.beginTransaction();
		ArrayList<EarlyStatementBean> earlyFinacialList = null;
		try{
			earlyFinacialList=earlyStatementApplicationService.findEarlyFinancialPosition();
			//System.out.println("		@ 얼리서비스포사드"+earlyFinacialList.get(0));
			dataSourceTransactionManager.commitTransaction();
		}catch(DataAccessException e){
			logger.fatal(e.getMessage());
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}	
		if(logger.isDebugEnabled()){ logger.debug(" EarlyStatementServiceFacadeImpl : findEarlyFinancialPosition 종료 "); }
		return earlyFinacialList;
	}
}
