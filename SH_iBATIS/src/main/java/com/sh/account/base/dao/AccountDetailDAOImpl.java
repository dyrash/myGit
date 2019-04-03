package com.sh.account.base.dao;

import java.util.ArrayList;

import com.sh.account.base.dao.AccountDetailDAO;
import com.sh.account.base.dao.AccountDetailDAOImpl;
import com.sh.account.base.to.AccountDetailBean;
import com.sh.common.dao.IBatisDAOSupport;

public class AccountDetailDAOImpl extends IBatisDAOSupport implements AccountDetailDAO{
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public ArrayList<AccountDetailBean> findControlItemList() {
		// TODO Auto-generated method stub
		return (ArrayList<AccountDetailBean>)getSqlMapClientTemplate().queryForList("accountDetail.findControlItemList");
	}
	

}
