package com.sh.account.statement.applicationService;

import java.util.HashMap;

import com.sh.account.statement.applicationService.StatementApplicationService;
import com.sh.account.statement.applicationService.StatementApplicationServiceImpl;
import com.sh.account.statement.dao.TrialBalanceDAO;
import com.sh.account.statement.dao.FinancialPositionDAO;
import com.sh.account.statement.dao.IncomeStatementDAO;


public class StatementApplicationServiceImpl implements StatementApplicationService{
	private  TrialBalanceDAO trialBalanceDAO;
	public  void setTrialBalanceDAO( TrialBalanceDAO trialBalanceDAO) {
		this.trialBalanceDAO = trialBalanceDAO;
	}	
	
	private IncomeStatementDAO incomeStatementDAO;
	public void setIncomeStatementDAO(IncomeStatementDAO incomeStatementDAO) {
		this.incomeStatementDAO = incomeStatementDAO;
	}	
	
	private FinancialPositionDAO financialPositionDAO;
	public  void setFinancialPositionDAO(FinancialPositionDAO financialPositionDAO) {
		this.financialPositionDAO = financialPositionDAO;
	}

	@Override
	public HashMap<String, Object> findTrialBalanceList(String fromDate, String toDate) {
		// TODO Auto-generated method stub
		HashMap<String, Object> trialBalanceList = null;
		try {
			System.out.println("		@ 합계잔액시산표 조회 : "+toDate+" 까지");
			trialBalanceList = trialBalanceDAO.findTrialBalanceList(fromDate, toDate);
			System.out.println("		@ "+trialBalanceList);    
		} catch (Exception error) {	
			throw error;
		}
		return trialBalanceList;
	}
	
	@Override
	 public HashMap<String,Object> findincomeStatementList(String fromDate, String toDate){
		// TODO Auto-generated method stub
		HashMap<String, Object> incomeStatementList = null;
		try {
			incomeStatementList = incomeStatementDAO.findincomeStatementList(fromDate, toDate);
			System.out.println("		@ 손익계산서 조회 : "+toDate+" 까지");
		} catch (Exception error) {	
			throw error;
		}
			return incomeStatementList;
	 }
	@Override
	public HashMap<String, Object> findFinancialPositionList(String fromDate, String toDate) {
		// TODO Auto-generated method stub
		HashMap<String, Object> financialPositionList = null;
		try {
			financialPositionList = financialPositionDAO.findFinancialPositionList(fromDate, toDate);
			System.out.println("		@ 재무상태표 조회 : "+toDate+" 까지");
		} catch (Exception error) {	
			throw error;
		}
		return financialPositionList;
	}

}
