package com.sh.account.base.applicationService;

import java.util.ArrayList;

import com.sh.account.base.to.AccountControlBean;
import com.sh.account.base.to.AccountDetailBean;

public interface AccountDetailBaseApplicationService {

	public ArrayList<AccountDetailBean> findAccountDetailList();

	public ArrayList<AccountControlBean> findAccountControlList(String accountCode);

	public void batchAccountControlListProcess(ArrayList<AccountControlBean> accountControlList);

}
