package com.sh.account.statement.serviceFacade;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.dao.DataAccessException;

import com.sh.account.statement.serviceFacade.StatementServiceFacade;
import com.sh.account.statement.serviceFacade.StatementServiceFacadeImpl;
import com.sh.account.statement.to.EarlyIncomeStatementBean;
import com.sh.account.statement.to.EarlyStatementBean;
import com.sh.account.statement.exception.FinancialPositionException;
import com.sh.account.statement.exception.TrialBalanceListException;
import com.sh.account.statement.exception.IncomeStatemenException;
import com.sh.account.statement.applicationService.EarlyIncomeStatementApplicationService;
import com.sh.account.statement.applicationService.EarlyStatementApplicationService;
import com.sh.account.statement.applicationService.StatementApplicationService;

public class StatementServiceFacadeImpl implements StatementServiceFacade{

	private StatementApplicationService statementApplicationService;
	public void setStatementApplicationService(StatementApplicationService statementApplicationService) {
		this.statementApplicationService = statementApplicationService;
	}
	
	private EarlyStatementApplicationService earlyStatementApplicationService;
	public void setEarlyStatementApplicationService(EarlyStatementApplicationService earlyStatementApplicationService) {
		this.earlyStatementApplicationService = earlyStatementApplicationService;
	}
	
	private EarlyIncomeStatementApplicationService earlyIncomeStatementApplicationService;
	public void setEarlyIncomeStatementApplicationService(EarlyIncomeStatementApplicationService earlyIncomeStatementApplicationService) {
		this.earlyIncomeStatementApplicationService = earlyIncomeStatementApplicationService;
	}
	
	

	@Override
	public HashMap<String, Object> findTrialBalanceList(String fromDate, String toDate) throws TrialBalanceListException{
		// TODO Auto-generated method stub
	    HashMap<String, Object> trialBalanceList=statementApplicationService.findTrialBalanceList(fromDate, toDate);
	    if(trialBalanceList.isEmpty()){				 
			 throw new TrialBalanceListException("합계 잔액 시산표 조회 성공 했습니다.");
		 }
	    return trialBalanceList;
	}


	@Override
	public HashMap<String,Object> findincomeStatementList(String fromDate, String toDate) throws IncomeStatemenException{
		// TODO Auto-generated method stub
	    HashMap<String, Object> incomeStatementList=statementApplicationService.findincomeStatementList(fromDate, toDate);
	    if(incomeStatementList.isEmpty()){				 
			 throw new IncomeStatemenException("손익계산서 조회 성공 했습니다.");
		 }
		return incomeStatementList;
		
	}
	@Override
	public HashMap<String, Object> findFinancialPositionList(String fromDate, String toDate) throws FinancialPositionException {
		// TODO Auto-generated method stub
	    HashMap<String, Object> financialPositionList=statementApplicationService.findFinancialPositionList(fromDate, toDate);
	    if(financialPositionList.isEmpty()){				 
			 throw new FinancialPositionException("재무상태표 조회 성공 했습니다.");
		 }
		return financialPositionList;
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
			earlyIncomeList=earlyIncomeStatementApplicationService.findEarlyIncomeList();
		return earlyIncomeList;
	}
	
	@Override
	public ArrayList<EarlyStatementBean> findEarlyAssets() {
		// TODO Auto-generated method stub
		//ArrayList<EarlyStatementBean> assetList = null;
		/*assetList=earlyStatementApplicationService.findEarlyAssets();*/
		return earlyStatementApplicationService.findEarlyAssets();
		//return assetList;
	}

	@Override
	public ArrayList<EarlyStatementBean> findEarlyLiabilitiseNequity() {
		// TODO Auto-generated method stub
		ArrayList<EarlyStatementBean> liabNequiList = null;
			liabNequiList=earlyStatementApplicationService.findEarlyLiabilitiseNequity();
		return liabNequiList;
	}
	
	@Override
	public ArrayList<EarlyStatementBean> findEarlyFinancialPosition() {
		// TODO Auto-generated method stub
		ArrayList<EarlyStatementBean> earlyFinacialList = null;
			earlyFinacialList=earlyStatementApplicationService.findEarlyFinancialPosition();
		return earlyFinacialList;
	}
	
	
}
