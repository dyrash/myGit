package com.sh.account.statement.applicationService;

import java.util.ArrayList;

import com.sh.account.statement.to.EarlyStatementBean;

public interface EarlyStatementApplicationService {

	public ArrayList<EarlyStatementBean> findEarlyAssets();
	public ArrayList<EarlyStatementBean> findEarlyLiabilitiseNequity();
	public ArrayList<EarlyStatementBean> findEarlyFinancialPosition();
}
