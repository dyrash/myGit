package com.sh.account.slip.serviceFacade;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sh.account.slip.serviceFacade.SlipServiceFacade;
import com.sh.account.slip.serviceFacade.SlipServiceFacadeImpl;
import com.sh.account.slip.applicationService.SlipApplicationService;
import com.sh.account.slip.applicationService.SlipApplicationServiceImpl;
import com.sh.account.slip.to.SlipBean;
import com.sh.common.db.DataSourceTransactionManager;
import com.sh.common.exception.DataAccessException;

public class SlipServiceFacadeImpl implements SlipServiceFacade{
	protected final Log logger = LogFactory.getLog(this.getClass());
	private DataSourceTransactionManager dataSourceTransactionManager = DataSourceTransactionManager.getInstance();
	SlipApplicationService slipApplicationService = SlipApplicationServiceImpl.getInstance();
	
	
	private static SlipServiceFacade instance;
	private SlipServiceFacadeImpl(){}
	public static SlipServiceFacade getInstance() {
		// TODO Auto-generated method stub
		if(instance == null){
				instance = new SlipServiceFacadeImpl();
				System.out.println("		@ SlipServiceFacadeImpl에 접근");
		}
		return instance;
	}
	
	
	@Override
	public ArrayList<SlipBean> findSlipDataList(String slipDate1,String slipDate2) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" SlipServiceFacadeImpl : findSlipDataList 시작 "); }
		 ArrayList<SlipBean> slipList=null;
		   try{
			   slipList=slipApplicationService.findSlipDataList(slipDate1,slipDate2);
			   dataSourceTransactionManager.commitTransaction();
		   }catch(DataAccessException e){
			   logger.fatal(e.getMessage());
			   dataSourceTransactionManager.rollbackTransaction();
			   throw e;
		   }
		   if(logger.isDebugEnabled()){ logger.debug(" SlipServiceFacadeImpl : findSlipDataList 종료 "); }
		   return slipList;
	}
	@Override
	public void batchListProcess(ArrayList<SlipBean> slipList) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" SlipServiceFacadeImpl : batchListProcess 시작 "); }
		 dataSourceTransactionManager.beginTransaction();
		   try{
		   slipApplicationService.batchListProcess(slipList);
		   dataSourceTransactionManager.commitTransaction();
		   }catch(DataAccessException e){
			   logger.fatal(e.getMessage());
			   dataSourceTransactionManager.rollbackTransaction();
			   throw e;
		   }
		   if(logger.isDebugEnabled()){ logger.debug(" SlipServiceFacadeImpl : batchListProcess 종료 "); }
	}
	@Override
	public ArrayList<SlipBean> findRangedSlipList(String fromDate, String toDate) {
		// TODO Auto-generated method stub
		 if(logger.isDebugEnabled()){ logger.debug(" SlipServiceFacadeImpl : findRangedSlipList 시작 "); }
		 ArrayList<SlipBean> slipList=null;
		   try{
			   slipList=slipApplicationService.findRangedSlipList(fromDate, toDate);
		   }catch(DataAccessException e){
			   logger.fatal(e.getMessage());
			   dataSourceTransactionManager.rollbackTransaction();
			   throw e;
		   }
		   if(logger.isDebugEnabled()){ logger.debug(" SlipServiceFacadeImpl : findRangedSlipList 종료 "); }
		   return slipList;
	}
	@Override
	public ArrayList<SlipBean> findDisApprovalSlipList() {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" SlipServiceFacadeImpl : findDisApprovalSlipList 시작 "); }
		ArrayList<SlipBean> disApprovalSlipList = null;
		dataSourceTransactionManager.beginTransaction();
		try{
			disApprovalSlipList = slipApplicationService.findDisApprovalSlipList();			
		}catch(Exception error){
			logger.fatal(error.getMessage());
			 dataSourceTransactionManager.rollbackTransaction();
	         throw error;
		}	
		if(logger.isDebugEnabled()){ logger.debug(" SlipServiceFacadeImpl : findDisApprovalSlipList 종료 "); }
		return disApprovalSlipList;
	}

}
