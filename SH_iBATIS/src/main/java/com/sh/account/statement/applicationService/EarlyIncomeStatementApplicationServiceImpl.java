package com.sh.account.statement.applicationService;

import java.util.ArrayList;

import com.sh.account.statement.applicationService.EarlyIncomeStatementApplicationServiceImpl;
import com.sh.account.statement.to.EarlyIncomeStatementBean;
import com.sh.account.statement.dao.EarlyIncomeStatementDAO;

public class EarlyIncomeStatementApplicationServiceImpl implements EarlyIncomeStatementApplicationService{
	private EarlyIncomeStatementDAO earlyIncomeStatementDAO;
	public void setEarlyIncomeStatementDAO(EarlyIncomeStatementDAO earlyIncomeStatementDAO) {
		this.earlyIncomeStatementDAO = earlyIncomeStatementDAO;
	}	

	@Override
	public ArrayList<EarlyIncomeStatementBean> findEarlyIncomeStatementList() {
		// TODO Auto-generated method stub
		ArrayList<EarlyIncomeStatementBean> earlyIncomeStatementList = null;
		try {
			earlyIncomeStatementList = earlyIncomeStatementDAO.findEarlyIncomeStatementList();
			//System.out.println("		@ 얼리서비스어플"+earlyIncomeStatementList.get(0));
			} catch (Exception error) {	
			throw error;
		}
		return earlyIncomeStatementList;
	}
	
	
	@Override
	public ArrayList<EarlyIncomeStatementBean> findEarlyIncomeList() {
		// TODO Auto-generated method stub
		ArrayList<EarlyIncomeStatementBean> earlyIncomeList = null;
		try {
			earlyIncomeList = earlyIncomeStatementDAO.findEarlyIncomeList();
			//System.out.println("		@ 얼리서비스어플"+earlyIncomeList.get(0));
		} catch (Exception error) {	
			throw error;
		}
		return earlyIncomeList;
	}
}
