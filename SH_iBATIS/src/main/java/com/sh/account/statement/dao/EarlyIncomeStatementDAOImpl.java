package com.sh.account.statement.dao;

import java.util.ArrayList;
import com.sh.account.statement.to.EarlyIncomeStatementBean;
import com.sh.common.dao.IBatisDAOSupport;

public class EarlyIncomeStatementDAOImpl extends IBatisDAOSupport implements EarlyIncomeStatementDAO{
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public ArrayList<EarlyIncomeStatementBean> findEarlyIncomeStatementList() {
		// TODO Auto-generated method stub
		return (ArrayList<EarlyIncomeStatementBean>)getSqlMapClientTemplate().queryForList("earlyIncomeStatement.findEarlyIncomeStatementList");
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public ArrayList<EarlyIncomeStatementBean> findEarlyIncomeList() {
		// TODO Auto-generated method stub
		return (ArrayList<EarlyIncomeStatementBean>)getSqlMapClientTemplate().queryForList("earlyIncomeStatement.findEarlyIncomeList");
	}
}
