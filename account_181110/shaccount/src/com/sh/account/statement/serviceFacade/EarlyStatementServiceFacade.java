package com.sh.account.statement.serviceFacade;

import java.util.ArrayList;

import com.sh.account.statement.to.EarlyStatementBean;

public interface EarlyStatementServiceFacade {
	
	public ArrayList<EarlyStatementBean> findEarlyAssets();
	public ArrayList<EarlyStatementBean> findEarlyLiabilitiseNequity();
	public ArrayList<EarlyStatementBean> findEarlyFinancialPosition();
	
	
}

