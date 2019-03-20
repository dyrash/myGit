package com.sh.account.base.dao;

import java.util.ArrayList;

import com.sh.account.base.to.AccountDetailBean;

public interface AccountDetailDAO {

	ArrayList<AccountDetailBean> findControlItemList();

}
