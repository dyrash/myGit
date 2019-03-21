package com.sh.account.statement.serviceFacade;

import java.util.ArrayList;

import com.sh.account.statement.applicationService.EarlyIncomeStatementApplicationService;
import com.sh.account.statement.serviceFacade.EarlyIncomeStatementServiceFacadeImpl;
import com.sh.account.statement.to.EarlyIncomeStatementBean;
import com.sh.common.exception.DataAccessException;

public class EarlyIncomeStatementServiceFacadeImpl implements EarlyIncomeStatementServiceFacade{
	private EarlyIncomeStatementApplicationService earlyIncomeStatementApplicationService;
	public void setEarlyIncomeStatementApplicationService(EarlyIncomeStatementApplicationService earlyIncomeStatementApplicationService) {
		this.earlyIncomeStatementApplicationService = earlyIncomeStatementApplicationService;
	}

	@Override
	public ArrayList<EarlyIncomeStatementBean> findEarlyIncomeStatementList() {
		// TODO Auto-generated method stub
		ArrayList<EarlyIncomeStatementBean> earlyIncomeStatementList = null;
		try{
			earlyIncomeStatementList=earlyIncomeStatementApplicationService.findEarlyIncomeStatementList();
			//System.out.println("		@ 얼리서비스포사드"+earlyIncomeStatementList.get(0));
		}catch(DataAccessException e){
		   throw e;
	   }	
		return earlyIncomeStatementList;
	}
	
	@Override
	public ArrayList<EarlyIncomeStatementBean> findEarlyIncomeList() {
		// TODO Auto-generated method stub
		ArrayList<EarlyIncomeStatementBean> earlyIncomeList = null;
		try{
			earlyIncomeList=earlyIncomeStatementApplicationService.findEarlyIncomeList();
			//System.out.println("		@ 얼리서비스포사드"+earlyIncomeList.get(0));
		}catch(DataAccessException e){
			throw e;
		}	
		return earlyIncomeList;
	}
}
