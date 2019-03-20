package com.sh.account.statement.serviceFacade;

import java.util.HashMap;

public interface StatementServiceFacade {

	public HashMap<String, Object> findTrialBalanceList(String fromDate, String toDate);
	
	public HashMap<String,Object> findincomeStatementList(String fromDate, String toDate);
	
	public HashMap<String,Object> findFinancialPositionList(String fromDate, String toDate);
}

