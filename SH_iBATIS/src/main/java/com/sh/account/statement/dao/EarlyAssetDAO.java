package com.sh.account.statement.dao;

import java.util.ArrayList;

import com.sh.account.statement.to.EarlyStatementBean;

public interface EarlyAssetDAO {

	public ArrayList<EarlyStatementBean> findEarlyAssets();
	public ArrayList<EarlyStatementBean> findEarlyLiabilitiseNequity();
	public ArrayList<EarlyStatementBean> findEarlyFinancialPosition();

}
