package com.sh.account.slip.dao;

import java.util.ArrayList;

import com.sh.account.slip.to.JournalDetailBean;

public interface JournalDetailDAO {

	public ArrayList<JournalDetailBean> selectJournalDetailList(String journalNo);

	public void deleteJournalDetail(JournalDetailBean journalDetailBean);

	public void updateJournalDetail(JournalDetailBean journalDetailBean);

	public void insertJournalDetail(JournalDetailBean journalDetailBean);

}
