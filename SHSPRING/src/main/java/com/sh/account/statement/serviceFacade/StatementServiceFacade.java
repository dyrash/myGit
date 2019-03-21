package com.sh.account.statement.serviceFacade;

import java.util.HashMap;

import com.sh.account.statement.exception.FinancialPositionException;
import com.sh.account.statement.exception.IncomeStatemenException;
import com.sh.account.statement.exception.TrialBalanceListException;

public interface StatementServiceFacade {

	public HashMap<String, Object> findTrialBalanceList(String fromDate, String toDate) throws TrialBalanceListException;
	
	public HashMap<String,Object> findincomeStatementList(String fromDate, String toDate) throws IncomeStatemenException;
	
	public HashMap<String,Object> findFinancialPositionList(String fromDate, String toDate) throws FinancialPositionException;
}

