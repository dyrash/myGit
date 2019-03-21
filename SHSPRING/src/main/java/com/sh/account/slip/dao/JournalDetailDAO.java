package com.sh.account.slip.dao;

import com.sh.account.slip.to.JournalDetailBean;

public interface JournalDetailDAO {

	public JournalDetailBean selectJournalDetailList(String journalCode);

	public void deleteJournalDetail(JournalDetailBean journalDetailBean);

	public void updateJournalDetail(JournalDetailBean journalDetailBean);

	public void insertJournalDetail(JournalDetailBean journalDetailBean);

}
