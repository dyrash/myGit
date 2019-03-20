package com.sh.account.statement.applicationService;

import java.util.ArrayList;

import com.sh.account.statement.to.EarlyIncomeStatementBean;

public interface EarlyIncomeStatementApplicationService {

	public ArrayList<EarlyIncomeStatementBean> findEarlyIncomeStatementList();
	public ArrayList<EarlyIncomeStatementBean> findEarlyIncomeList();
}
