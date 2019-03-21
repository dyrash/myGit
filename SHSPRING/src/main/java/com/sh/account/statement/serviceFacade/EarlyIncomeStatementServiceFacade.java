package com.sh.account.statement.serviceFacade;

import java.util.ArrayList;

import com.sh.account.statement.to.EarlyIncomeStatementBean;

public interface EarlyIncomeStatementServiceFacade {
	
	//public ArrayList<EarlyStatementBean> findEarlyAssets();
	//public ArrayList<EarlyStatementBean> findEarlyLiabilitiseNequity();
	public ArrayList<EarlyIncomeStatementBean> findEarlyIncomeStatementList();
	public ArrayList<EarlyIncomeStatementBean> findEarlyIncomeList();
	
	
}

