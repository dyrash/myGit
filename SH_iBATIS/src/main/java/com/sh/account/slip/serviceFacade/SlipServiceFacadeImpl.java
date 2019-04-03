package com.sh.account.slip.serviceFacade;

import java.util.ArrayList;

import com.sh.account.slip.serviceFacade.SlipServiceFacade;
import com.sh.account.slip.serviceFacade.SlipServiceFacadeImpl;
import com.sh.account.slip.exception.SlipDataListException;
import com.sh.account.slip.exception.DisApprovalSlipListException;
import com.sh.account.slip.applicationService.SlipApplicationService;
import com.sh.account.slip.to.SlipBean;

public class SlipServiceFacadeImpl implements SlipServiceFacade{
	private SlipApplicationService slipApplicationService;

	public void setSlipApplicationService(SlipApplicationService slipApplicationService) {
		this.slipApplicationService = slipApplicationService;
	}	
	
	@Override
	public ArrayList<SlipBean> findSlipDataList(String slipDate1,String slipDate2) throws SlipDataListException {
		// TODO Auto-generated method stub
		 ArrayList<SlipBean> slipList=slipApplicationService.findSlipDataList(slipDate1,slipDate2);
		 if(slipList.isEmpty()){
			   throw new SlipDataListException("전표를 조회하지 못했습니다."); 
		   }
		   return slipList;
	}
	@Override
	public void batchListProcess(ArrayList<SlipBean> slipList) {
		// TODO Auto-generated method stub
		   slipApplicationService.batchListProcess(slipList);
	}
	@Override
	public ArrayList<SlipBean> findRangedSlipList(String fromDate, String toDate) throws SlipDataListException{
		// TODO Auto-generated method stub
		 ArrayList<SlipBean> slipList=slipApplicationService.findRangedSlipList(fromDate, toDate);
		 if(slipList.isEmpty()){
			   throw new SlipDataListException("분개장을 조회하지 못했습니다."); 
		 }
		   return slipList;
	}
	@Override
	public ArrayList<SlipBean> findDisApprovalSlipList() throws DisApprovalSlipListException {
		// TODO Auto-generated method stub
		ArrayList<SlipBean> disApprovalSlipList =slipApplicationService.findDisApprovalSlipList();	
		if(disApprovalSlipList.isEmpty()){				 
			 throw new DisApprovalSlipListException("미승인 전표를 조회하지 못했습니다.");
		 }
		return disApprovalSlipList;
	}

}
