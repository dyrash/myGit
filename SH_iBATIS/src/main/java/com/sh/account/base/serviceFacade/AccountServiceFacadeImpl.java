package com.sh.account.base.serviceFacade;

import java.util.ArrayList;

import com.sh.account.base.exception.AccountListNotFoundException;
import com.sh.account.base.applicationService.AccountBaseApplicationService;
import com.sh.account.base.applicationService.AccountDetailBaseApplicationService;
import com.sh.account.base.to.AccountBean;
import com.sh.account.base.to.AccountControlBean;
import com.sh.account.base.to.AccountDetailBean;

public class AccountServiceFacadeImpl implements AccountServiceFacade{
	//protected final Log logger = LogFactory.getLog(this.getClass());
	private AccountDetailBaseApplicationService accountDetailBaseApplicationService;
	
	public void setAccountDetailBaseApplicationService(AccountDetailBaseApplicationService accountDetailBaseApplicationService) {
		this.accountDetailBaseApplicationService = accountDetailBaseApplicationService;
	}
	
	private AccountBaseApplicationService accountBaseApplicationService;
    public void setAccountBaseApplicationService(AccountBaseApplicationService accountBaseApplicationService) {
		this.accountBaseApplicationService = accountBaseApplicationService;
	} 
	
	@Override
	public ArrayList<AccountBean> findAccountList() throws AccountListNotFoundException {
		// TODO Auto-generated method stub
		ArrayList<AccountBean> accountList=accountBaseApplicationService.findAccountList();
		if(accountList.isEmpty()){				 
			 throw new AccountListNotFoundException("계정 목록을 조회하지 못했습니다.");
		 }
		 return accountList;
	}
	
	@Override
	public ArrayList<AccountBean> findDetailAccountList(String code) throws AccountListNotFoundException{
		// TODO Auto-generated method stub
		ArrayList<AccountBean> accountList=accountBaseApplicationService.findDetailAccountList(code);
		if(accountList.isEmpty()){				 
			 throw new AccountListNotFoundException("계정 상세 목록을 조회하지 못했습니다.");
		 }
		return accountList;
	}
	
	
	@Override
	public ArrayList<AccountControlBean> findAccountControlList(String accountInnerCode) {
		// TODO Auto-generated method stub
	    ArrayList<AccountControlBean> accountControlList=accountDetailBaseApplicationService.findAccountControlList(accountInnerCode);
		return accountControlList;
		}

	
	@Override
	public ArrayList<AccountBean> findSelectAccountList(String code) throws AccountListNotFoundException{
		// TODO Auto-generated method stub
		ArrayList<AccountBean> accountList=accountBaseApplicationService.findSelectAccountList(code);
		if(accountList.isEmpty()){				 
			 throw new AccountListNotFoundException("계정 목록을 조회하지 못했습니다.");
		 }
		return accountList;
	}
	
	
	@Override
	public ArrayList<AccountDetailBean> findAccountDetailList() {
		// TODO Auto-generated method stub
			ArrayList<AccountDetailBean> controlItemList=accountDetailBaseApplicationService.findAccountDetailList();
			return controlItemList;
	}
	@Override
	public void batchAccountControlListProcess(ArrayList<AccountControlBean> accountControlList) {
		// TODO Auto-generated method stub
	        	accountDetailBaseApplicationService.batchAccountControlListProcess(accountControlList);
	}

	
	@Override
	public ArrayList<AccountBean> editAccountList(AccountBean bean) throws AccountListNotFoundException {
		// TODO Auto-generated method stub
		ArrayList<AccountBean> accountList=accountBaseApplicationService.editAccountList(bean);
		if(accountList.isEmpty()){				 
			 throw new AccountListNotFoundException("수정할 항목을 조회하지 못했습니다.");
		 } 
		 return accountList;
	}
	
	@Override
	public ArrayList<AccountBean> findParentAccountList()  throws AccountListNotFoundException {
		// TODO Auto-generated method stub
		ArrayList<AccountBean> accountList=accountBaseApplicationService.findParentAccountList();
		if(accountList.isEmpty()){
			throw new AccountListNotFoundException("계정 목록을 조회하지 못했습니다.");
		 }
		return accountList;
	}
}
