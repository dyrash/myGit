package com.sh.account.slip.dao;

import java.util.ArrayList;

import com.sh.account.slip.to.JournalBean;

public interface JournalDAO {

	public ArrayList<JournalBean> selectJournalList(String slipNo);

	public void insertJournal(JournalBean journalBean);

	public void deleteJournal(JournalBean journalBean);

	public void updateJournal(JournalBean journalBean);

}
