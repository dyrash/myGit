package com.sh.account.statement.serviceFacade;

import java.util.ArrayList;
import java.util.HashMap;

import com.sh.account.statement.exception.FinancialPositionException;
import com.sh.account.statement.exception.IncomeStatemenException;
import com.sh.account.statement.exception.TrialBalanceListException;
import com.sh.account.statement.to.EarlyIncomeStatementBean;
import com.sh.account.statement.to.EarlyStatementBean;

public interface StatementServiceFacade {

	public HashMap<String, Object> findTrialBalanceList(String fromDate, String toDate) throws TrialBalanceListException;
	
	public HashMap<String,Object> findincomeStatementList(String fromDate, String toDate) throws IncomeStatemenException;
	
	public HashMap<String,Object> findFinancialPositionList(String fromDate, String toDate) throws FinancialPositionException;
	
	
	public ArrayList<EarlyIncomeStatementBean> findEarlyIncomeStatementList();
	public ArrayList<EarlyIncomeStatementBean> findEarlyIncomeList();
	
	public ArrayList<EarlyStatementBean> findEarlyAssets();
	public ArrayList<EarlyStatementBean> findEarlyLiabilitiseNequity();
	public ArrayList<EarlyStatementBean> findEarlyFinancialPosition();
	
	
	
}


