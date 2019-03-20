package com.sh.account.statement.applicationService;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sh.account.statement.applicationService.EarlyStatementApplicationServiceImpl;
import com.sh.account.statement.to.EarlyStatementBean;
import com.sh.account.statement.dao.EarlyAssetDAO;
import com.sh.account.statement.dao.EarlyAssetDAOImpl;

public class EarlyStatementApplicationServiceImpl implements EarlyStatementApplicationService{
	protected final Log logger = LogFactory.getLog(this.getClass());
	private static EarlyAssetDAO earlyAssetDAO = EarlyAssetDAOImpl.getInstance();
	private static EarlyStatementApplicationService instance;
	private EarlyStatementApplicationServiceImpl (){}
	public static EarlyStatementApplicationService getInstance() {
		// TODO Auto-generated method stub
		if(instance == null){
			instance = new EarlyStatementApplicationServiceImpl();
			System.out.println("		@ EarlyStatementApplicationServiceImpl에 접근");
		}
		return instance;
	}              

	@Override
	public ArrayList<EarlyStatementBean> findEarlyAssets() {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" EarlyStatementApplicationServiceImpl : findEarlyAssets 시작 "); }
		ArrayList<EarlyStatementBean> assetList = null;
		try {
			assetList = earlyAssetDAO.findEarlyAssets();
			//System.out.println("		@ 얼리서비스어플"+assetList.get(0).getGroupCode());
			} catch (Exception error) {	
			logger.fatal(error.getMessage());
			throw error;
		}
		if(logger.isDebugEnabled()){ logger.debug(" EarlyStatementApplicationServiceImpl : findEarlyAssets 종료 "); }
		return assetList;
	}
	
	public ArrayList<EarlyStatementBean> findEarlyLiabilitiseNequity() {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" EarlyStatementApplicationServiceImpl : findEarlyLiabilitiseNequity 시작 "); }
		ArrayList<EarlyStatementBean> liabNequiList = null;
		try {
			liabNequiList = earlyAssetDAO.findEarlyLiabilitiseNequity();
			//System.out.println("		@ 얼리서비스어플"+liabNequiList.get(0).getGroupCode());
		} catch (Exception error) {	
			logger.fatal(error.getMessage());
			throw error;
		}
		if(logger.isDebugEnabled()){ logger.debug(" EarlyStatementApplicationServiceImpl : findEarlyLiabilitiseNequity 종료 "); }
		return liabNequiList;
	}
	
	public ArrayList<EarlyStatementBean> findEarlyFinancialPosition() {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" EarlyStatementApplicationServiceImpl : findEarlyFinancialPosition 시작 "); }
		ArrayList<EarlyStatementBean> earlyFinacialList = null;
		try {
			earlyFinacialList = earlyAssetDAO.findEarlyFinancialPosition();
			//System.out.println("		@ 얼리서비스어플"+earlyFinacialList.get(0));
		} catch (Exception error) {	
			logger.fatal(error.getMessage());
			throw error;
		}
		if(logger.isDebugEnabled()){ logger.debug(" EarlyStatementApplicationServiceImpl : findEarlyFinancialPosition 종료 "); }
		return earlyFinacialList;
	}
	
}
