package com.sh.account.statement.dao;

import java.util.ArrayList;

import com.sh.account.statement.to.EarlyIncomeStatementBean;

public interface EarlyIncomeStatementDAO {

	public ArrayList<EarlyIncomeStatementBean> findEarlyIncomeStatementList();
	public ArrayList<EarlyIncomeStatementBean> findEarlyIncomeList();
	
}
