package com.sh.account.statement.dao;

import java.util.HashMap;

public interface FinancialPositionDAO {

	
	public HashMap<String, Object> findFinancialPositionList(String fromDate, String toDate);

}
