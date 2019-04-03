package com.sh.account.slip.applicationService;

import java.util.ArrayList;

import com.sh.account.slip.to.SlipBean;

public interface SlipApplicationService {

	public ArrayList<SlipBean> findSlipDataList(String slipDate1,String slipDate2);

	public void batchListProcess(ArrayList<SlipBean> slipList);

	public ArrayList<SlipBean> findRangedSlipList(String fromDate, String toDate);

	public ArrayList<SlipBean> findDisApprovalSlipList();

}
