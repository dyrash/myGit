package com.sh.account.statement.serviceFacade;

import java.util.HashMap;

import com.sh.account.statement.serviceFacade.StatementServiceFacade;
import com.sh.account.statement.serviceFacade.StatementServiceFacadeImpl;
import com.sh.account.statement.exception.FinancialPositionException;
import com.sh.account.statement.exception.TrialBalanceListException;
import com.sh.account.statement.exception.IncomeStatemenException;
import com.sh.account.statement.applicationService.StatementApplicationService;

public class StatementServiceFacadeImpl implements StatementServiceFacade{

	private StatementApplicationService statementApplicationService;
	public void setStatementApplicationService(StatementApplicationService statementApplicationService) {
		this.statementApplicationService = statementApplicationService;
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
}
