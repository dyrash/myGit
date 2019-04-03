package com.sh.account.slip.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.sh.account.slip.dao.SlipDAO;
import com.sh.account.slip.dao.SlipDAOImpl;
import com.sh.account.slip.to.SlipBean;
import com.sh.common.dao.IBatisDAOSupport;

public class SlipDAOImpl extends IBatisDAOSupport implements SlipDAO{
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public ArrayList<SlipBean> selectSlipDataList(String slipDate1,String slipDate2) {
		// TODO Auto-generated method stub
		HashMap<String, String> map = new HashMap<>();
    	map.put("slipDate1", slipDate1);
    	map.put("slipDate2", slipDate2);
		return (ArrayList<SlipBean>)getSqlMapClientTemplate().queryForList("slip.selectSlipDataList", map);
	}
	@SuppressWarnings("deprecation")
	@Override
	public void deleteSlip(SlipBean slipBean) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("slip.deleteSlip", slipBean);
	}
		
	@SuppressWarnings("deprecation")
	@Override
	public void updateSlip(SlipBean slipBean) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("slip.updateSlip", slipBean);	
	}
	
	@SuppressWarnings("deprecation")
	@Override		
	public void insertSlip(SlipBean slipBean) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("slip.insertSlip", slipBean);
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public ArrayList<SlipBean> selectRangedSlipList(String fromDate, String toDate) {	//분개장 보기
		// TODO Auto-generated method stub
		HashMap<String, String> map = new HashMap<>();
    	map.put("fromDate", fromDate);
    	map.put("toDate", toDate);
    	return (ArrayList<SlipBean>)getSqlMapClientTemplate().queryForList("slip.selectRangedSlipList", map);
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public ArrayList<SlipBean> selectDisApprovalSlipList() {	//미승인,승인 전표 조회
		// TODO Auto-generated method stub
		return (ArrayList<SlipBean>)getSqlMapClientTemplate().queryForList("slip.selectDisApprovalSlipList");
	}

}
