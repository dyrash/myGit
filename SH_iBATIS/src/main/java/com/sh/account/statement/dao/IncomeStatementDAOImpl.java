package com.sh.account.statement.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.sh.account.statement.to.IncomeStatementBean;
import com.sh.account.statement.to.TrialBalanceBean;
import com.sh.common.dao.IBatisDAOSupport;

public class IncomeStatementDAOImpl extends IBatisDAOSupport implements IncomeStatementDAO{

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public HashMap<String, Object> findincomeStatementList(String fromDate, String toDate) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fromDate", fromDate);
    	map.put("toDate", toDate);
	    HashMap<String, Object> incomeStatementResultList = new HashMap<String, Object>(); 
	    ArrayList<IncomeStatementBean> incomeStatementList = (ArrayList<IncomeStatementBean>)getSqlMapClientTemplate().queryForList("incomeStatement.findincomeStatementList", map);
	    incomeStatementResultList.put("incomeStatementList", incomeStatementList);
	    return incomeStatementResultList;
		
		
		/*HashMap<String, String> map = new HashMap<>();
    	map.put("fromDate", fromDate);
    	map.put("toDate", toDate);
    	return (HashMap<String, Object>)getSqlMapClientTemplate().queryForList("incomeStatement.findincomeStatementList", map);*/
	}
}
