package com.sh.account.statement.dao;

import java.util.ArrayList;

import com.sh.account.statement.to.EarlyStatementBean;
import com.sh.common.dao.IBatisDAOSupport;

public class EarlyAssetDAOImpl extends IBatisDAOSupport implements EarlyAssetDAO{
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public ArrayList<EarlyStatementBean> findEarlyAssets() {
		// TODO Auto-generated method stub
		return (ArrayList<EarlyStatementBean>)getSqlMapClientTemplate().queryForList("earlyAsset.findEarlyAssets");
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public ArrayList<EarlyStatementBean> findEarlyLiabilitiseNequity() {
		// TODO Auto-generated method stub
		return (ArrayList<EarlyStatementBean>)getSqlMapClientTemplate().queryForList("earlyAsset.findEarlyLiabilitiseNequity");
	}
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public ArrayList<EarlyStatementBean> findEarlyFinancialPosition() {
		// TODO Auto-generated method stub
		return (ArrayList<EarlyStatementBean>)getSqlMapClientTemplate().queryForList("earlyAsset.findEarlyFinancialPosition");
	}
}
