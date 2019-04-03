package com.sh.account.statement.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.sh.account.statement.to.FinancialPositionBean;
import com.sh.account.statement.to.TrialBalanceBean;
import com.sh.common.dao.IBatisDAOSupport;

public class FinancialPositionDAOImpl extends IBatisDAOSupport implements FinancialPositionDAO{
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public HashMap<String, Object> findFinancialPositionList(String fromDate, String toDate) {
		// TODO Auto-generated method stub
		  Map<String, Object> map = new HashMap<>();
		  map.put("fromDate", fromDate);
	      map.put("toDate", toDate);
	      HashMap<String, Object> financialPositionResultList = new HashMap<String, Object>(); 
	      ArrayList<FinancialPositionBean> financialPositionList = (ArrayList<FinancialPositionBean>)getSqlMapClientTemplate().queryForList("financial.findFinancialPositionList", map);
	      financialPositionResultList.put("financialPositionList", financialPositionList);
	      return financialPositionResultList;
		
		/*HashMap<String, String> map = new HashMap<>();
    	map.put("fromDate", fromDate);
    	map.put("toDate", toDate);
    	return (HashMap<String, Object>)getSqlMapClientTemplate().queryForList("financial.findFinancialPositionList", map);*/
	}
}
