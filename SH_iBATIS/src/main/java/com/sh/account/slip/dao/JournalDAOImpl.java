package com.sh.account.slip.dao;

import java.util.ArrayList;

import com.sh.account.slip.dao.JournalDAO;
import com.sh.account.slip.dao.JournalDAOImpl;
import com.sh.account.slip.to.JournalBean;
import com.sh.common.dao.IBatisDAOSupport;

public class JournalDAOImpl extends IBatisDAOSupport implements JournalDAO{
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public ArrayList<JournalBean> selectJournalList(String slipNo) {
		// TODO Auto-generated method stub
		return (ArrayList<JournalBean>)getSqlMapClientTemplate().queryForList("journal.selectJournalList", slipNo);    
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void insertJournal(JournalBean journalBean) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("journal.insertJournal", journalBean);
	}
	@SuppressWarnings("deprecation")
	@Override
	public void deleteJournal(JournalBean journalBean) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("journal.deleteJournal", journalBean);
	}
	@SuppressWarnings("deprecation")
	@Override
	public void updateJournal(JournalBean journalBean) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("journal.updateJournal", journalBean);
	}
}
