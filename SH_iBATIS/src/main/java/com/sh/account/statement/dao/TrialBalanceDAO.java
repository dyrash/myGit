package com.sh.account.statement.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.sh.account.statement.to.TrialBalanceBean;

public interface TrialBalanceDAO {

	
	public HashMap<String, Object> findTrialBalanceList(String fromDate, String toDate);

}
