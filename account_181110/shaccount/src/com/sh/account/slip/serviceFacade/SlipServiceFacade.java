package com.sh.account.slip.serviceFacade;

import java.util.ArrayList;
import java.util.List;

import com.sh.account.slip.to.SlipBean;

@SuppressWarnings("unused")
public interface SlipServiceFacade {

	public ArrayList<SlipBean> findSlipDataList(String slipDate1, String slipDate2);

	public void batchListProcess(ArrayList<SlipBean> slipList);

	public ArrayList<SlipBean> findRangedSlipList(String fromDate, String toDate);

	public ArrayList<SlipBean> findDisApprovalSlipList();
}
