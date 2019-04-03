package com.sh.account.base.dao;

import java.util.ArrayList;

import com.sh.account.base.dao.AccountDAO;
import com.sh.account.base.dao.AccountDAOImpl;
import com.sh.account.base.to.AccountBean;
import com.sh.common.dao.IBatisDAOSupport;

public class AccountDAOImpl extends IBatisDAOSupport implements AccountDAO{
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public ArrayList<AccountBean> selectAccountList() {
		// TODO Auto-generated method stub
		return (ArrayList<AccountBean>)getSqlMapClientTemplate().queryForList("account.selectAccountList");
	}
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public ArrayList<AccountBean> findSelectAccountList(String code) {
		// TODO Auto-generated method stub
		return (ArrayList<AccountBean>)getSqlMapClientTemplate().queryForList("account.findSelectAccountList",code);
	}
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public ArrayList<AccountBean> findAccountList() {
		// TODO Auto-generated method stub
		return (ArrayList<AccountBean>)getSqlMapClientTemplate().queryForList("account.findAccountList");
	}

	
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	   @Override
	   public ArrayList<AccountBean> findParentAccountList() {
	      // TODO Auto-generated method stub
		return (ArrayList<AccountBean>)getSqlMapClientTemplate().queryForList("account.findParentAccountList");
	   }
	   
	@SuppressWarnings({ "unchecked", "deprecation" })
	   @Override
	   public ArrayList<AccountBean> editAccountList(AccountBean bean) {
		   // TODO Auto-generated method stub
		   return (ArrayList<AccountBean>)getSqlMapClientTemplate().queryForList("account.editAccountList", bean);
	   }
	   
	   
	   
	@SuppressWarnings({ "unchecked", "deprecation" })
	   @Override
	   public ArrayList<AccountBean> findDetailAccountList(String code) {
	      // TODO Auto-generated method stub
		return (ArrayList<AccountBean>)getSqlMapClientTemplate().queryForList("account.findDetailAccountList", code);
	   }
}
