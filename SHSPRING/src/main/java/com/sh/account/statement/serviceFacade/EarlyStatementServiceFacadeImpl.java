package com.sh.account.statement.serviceFacade;

import java.util.ArrayList;

import com.sh.account.statement.applicationService.EarlyStatementApplicationService;
import com.sh.account.statement.serviceFacade.EarlyStatementServiceFacadeImpl;
import com.sh.account.statement.to.EarlyStatementBean;
import com.sh.common.exception.DataAccessException;


public class EarlyStatementServiceFacadeImpl implements EarlyStatementServiceFacade{
	private EarlyStatementApplicationService earlyStatementApplicationService;
	public void setEarlyStatementApplicationService(EarlyStatementApplicationService earlyStatementApplicationService) {
		this.earlyStatementApplicationService = earlyStatementApplicationService;
	}

	@Override
	public ArrayList<EarlyStatementBean> findEarlyAssets() {
		// TODO Auto-generated method stub
		ArrayList<EarlyStatementBean> assetList = null;
		try{
			assetList=earlyStatementApplicationService.findEarlyAssets();
			//System.out.println("		@ 얼리서비스포사드"+assetList.get(0).getGroupCode());
		}catch(DataAccessException e){
		   throw e;
	   }	
		return assetList;
	}

	@Override
	public ArrayList<EarlyStatementBean> findEarlyLiabilitiseNequity() {
		// TODO Auto-generated method stub
		ArrayList<EarlyStatementBean> liabNequiList = null;
		try{
			liabNequiList=earlyStatementApplicationService.findEarlyLiabilitiseNequity();
			//System.out.println("		@ 얼리서비스포사드"+liabNequiList.get(0).getGroupCode());
		}catch(DataAccessException e){
			throw e;
		}	
		return liabNequiList;
	}
	
	@Override
	public ArrayList<EarlyStatementBean> findEarlyFinancialPosition() {
		// TODO Auto-generated method stub
		ArrayList<EarlyStatementBean> earlyFinacialList = null;
		try{
			earlyFinacialList=earlyStatementApplicationService.findEarlyFinancialPosition();
			//System.out.println("		@ 얼리서비스포사드"+earlyFinacialList.get(0));
		}catch(DataAccessException e){
			throw e;
		}	
		return earlyFinacialList;
	}
}
