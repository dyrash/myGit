package com.sh.account.statement.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.sh.account.statement.dao.TrialBalanceDAO;
import com.sh.account.statement.dao.TrialBalanceDAOImpl;
import com.sh.account.statement.to.TrialBalanceBean;
import com.sh.common.dao.IBatisDAOSupport;

public class TrialBalanceDAOImpl extends IBatisDAOSupport implements TrialBalanceDAO{
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public HashMap<String, Object> findTrialBalanceList(String fromDate, String toDate) {
		// TODO Auto-generated method stub
		  Map<String, Object> map = new HashMap<String, Object>();
	      map.put("toDate", toDate);
	      HashMap<String, Object> trialBalanceResultList = new HashMap<String, Object>(); 
	      ArrayList<TrialBalanceBean> trialBalanceList = (ArrayList<TrialBalanceBean>)getSqlMapClientTemplate().queryForList("trialBalance.findTrialBalanceList", map);
	      trialBalanceResultList.put("trialBalanceList", trialBalanceList);
	      return trialBalanceResultList;
	   }
		/*return (HashMap<String, Object>)getSqlMapClientTemplate().queryForList("trialBalance.findTrialBalanceList", V_APPROVAL_DATE);*/
}