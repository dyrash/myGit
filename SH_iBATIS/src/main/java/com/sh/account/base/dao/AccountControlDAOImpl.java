package com.sh.account.base.dao;

import java.util.ArrayList;

import com.sh.account.base.dao.AccountControlDAO;
import com.sh.account.base.dao.AccountControlDAOImpl;
import com.sh.account.base.to.AccountControlBean;
import com.sh.common.dao.IBatisDAOSupport;

public class AccountControlDAOImpl extends IBatisDAOSupport implements AccountControlDAO{
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public ArrayList<AccountControlBean> selectAccountControlList(String accountCode) {
		// TODO Auto-generated method stub
		return (ArrayList<AccountControlBean>)getSqlMapClientTemplate().queryForList("accountControl.selectAccountControlList", accountCode);
	}
	@SuppressWarnings("deprecation")
	@Override
	public void insertAccountControl(AccountControlBean accountControl) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("accountControl.insertAccountControl", accountControl);
	}
	@SuppressWarnings("deprecation")
	@Override
	public void deleteAccountControl(AccountControlBean accountControl) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("accountControl.deleteAccountControl", accountControl);
	}
	
	
}
