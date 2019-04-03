package com.sh.account.slip.dao;

import java.util.ArrayList;

import com.sh.account.slip.dao.JournalDetailDAO;
import com.sh.account.slip.dao.JournalDetailDAOImpl;
import com.sh.account.slip.to.JournalDetailBean;
import com.sh.common.dao.IBatisDAOSupport;

public class JournalDetailDAOImpl extends IBatisDAOSupport implements JournalDetailDAO{
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public ArrayList<JournalDetailBean> selectJournalDetailList(String journalNo) {
		// TODO Auto-generated method stub
		return (ArrayList<JournalDetailBean>)getSqlMapClientTemplate().queryForList("journalDetail.selectJournalDetailList", journalNo);
	}
	@SuppressWarnings("deprecation")
	@Override
	public void deleteJournalDetail(JournalDetailBean journalDetailBean) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("journalDetail.deleteJournalDetail", journalDetailBean);    
	}
	@SuppressWarnings("deprecation")
	@Override
	public void updateJournalDetail(JournalDetailBean journalDetailBean) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("journalDetail.updateJournalDetail", journalDetailBean);
	}
	@SuppressWarnings("deprecation")
	@Override
	public void insertJournalDetail(JournalDetailBean journalDetailBean) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("journalDetail.insertJournalDetail", journalDetailBean);
	}

}
