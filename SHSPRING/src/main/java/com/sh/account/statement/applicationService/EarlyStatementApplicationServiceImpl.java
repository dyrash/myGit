package com.sh.account.statement.applicationService;

import java.util.ArrayList;

import com.sh.account.statement.applicationService.EarlyStatementApplicationServiceImpl;
import com.sh.account.statement.to.EarlyStatementBean;
import com.sh.account.statement.dao.EarlyAssetDAO;

public class EarlyStatementApplicationServiceImpl implements EarlyStatementApplicationService{
	private EarlyAssetDAO earlyAssetDAO;
	public void setEarlyAssetDAO(EarlyAssetDAO earlyAssetDAO) {
		this.earlyAssetDAO = earlyAssetDAO;
	}	
	
	@Override
	public ArrayList<EarlyStatementBean> findEarlyAssets() {
		// TODO Auto-generated method stub
		ArrayList<EarlyStatementBean> assetList = null;
		try {
			assetList = earlyAssetDAO.findEarlyAssets();
			//System.out.println("		@ 얼리서비스어플"+assetList.get(0).getGroupCode());
			} catch (Exception error) {	
			throw error;
		}
		return assetList;
	}
	
	public ArrayList<EarlyStatementBean> findEarlyLiabilitiseNequity() {
		// TODO Auto-generated method stub
		ArrayList<EarlyStatementBean> liabNequiList = null;
		try {
			liabNequiList = earlyAssetDAO.findEarlyLiabilitiseNequity();
			//System.out.println("		@ 얼리서비스어플"+liabNequiList.get(0).getGroupCode());
		} catch (Exception error) {	
			throw error;
		}
		return liabNequiList;
	}
	
	public ArrayList<EarlyStatementBean> findEarlyFinancialPosition() {
		// TODO Auto-generated method stub
		ArrayList<EarlyStatementBean> earlyFinacialList = null;
		try {
			earlyFinacialList = earlyAssetDAO.findEarlyFinancialPosition();
			//System.out.println("		@ 얼리서비스어플"+earlyFinacialList.get(0));
		} catch (Exception error) {	
			throw error;
		}
		return earlyFinacialList;
	}
	
}
