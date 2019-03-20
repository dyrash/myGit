package com.sh.account.statement.applicationService;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sh.account.statement.applicationService.EarlyIncomeStatementApplicationServiceImpl;
import com.sh.account.statement.to.EarlyIncomeStatementBean;
import com.sh.account.statement.dao.EarlyIncomeStatementDAO;
import com.sh.account.statement.dao.EarlyIncomeStatementDAOImpl;

public class EarlyIncomeStatementApplicationServiceImpl implements EarlyIncomeStatementApplicationService{
	protected final Log logger = LogFactory.getLog(this.getClass());
	private static EarlyIncomeStatementDAO earlyIncomeStatementDAO = EarlyIncomeStatementDAOImpl.getInstance();
	private static EarlyIncomeStatementApplicationService instance;
	private EarlyIncomeStatementApplicationServiceImpl (){}
	public static EarlyIncomeStatementApplicationService getInstance() {
		// TODO Auto-generated method stub
		if(instance == null){
			instance = new EarlyIncomeStatementApplicationServiceImpl();
			System.out.println("		@ EarlyStatementApplicationServiceImpl에 접근");
		}
		return instance;
	}              

	@Override
	public ArrayList<EarlyIncomeStatementBean> findEarlyIncomeStatementList() {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" EarlyIncomeStatementApplicationServiceImpl : findEarlyIncomeStatementList 시작 "); }
		ArrayList<EarlyIncomeStatementBean> earlyIncomeStatementList = null;
		try {
			earlyIncomeStatementList = earlyIncomeStatementDAO.findEarlyIncomeStatementList();
			//System.out.println("		@ 얼리서비스어플"+earlyIncomeStatementList.get(0));
			} catch (Exception error) {	
			logger.fatal(error.getMessage());
			throw error;
		}
		if(logger.isDebugEnabled()){ logger.debug(" EarlyIncomeStatementApplicationServiceImpl : findEarlyIncomeStatementList 종료 "); }
		return earlyIncomeStatementList;
	}
	
	
	@Override
	public ArrayList<EarlyIncomeStatementBean> findEarlyIncomeList() {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" EarlyIncomeStatementApplicationServiceImpl : findEarlyIncomeList 시작 "); }
		ArrayList<EarlyIncomeStatementBean> earlyIncomeList = null;
		try {
			earlyIncomeList = earlyIncomeStatementDAO.findEarlyIncomeList();
			//System.out.println("		@ 얼리서비스어플"+earlyIncomeList.get(0));
		} catch (Exception error) {	
			logger.fatal(error.getMessage());
			throw error;
		}
		if(logger.isDebugEnabled()){ logger.debug(" EarlyIncomeStatementApplicationServiceImpl : findEarlyIncomeList 종료 "); }
		return earlyIncomeList;
	}
}
