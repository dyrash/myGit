package com.sh.base.serviceFacade;

import java.util.ArrayList;
import java.util.HashMap;

import com.sh.base.exception.DeptCodeNotFoundException;
import com.sh.base.exception.IdNotFoundException;
import com.sh.base.exception.PwMissmatchException;
import com.sh.base.to.AddressBean;
import com.sh.base.to.CodeBean;
import com.sh.base.to.CustomerBean;
import com.sh.base.to.DepartmentCodeBean;
import com.sh.base.to.DetailCodeBean;
import com.sh.base.to.MenuBean;

public interface BaseServiceFacade {

	public HashMap<String, Object> accessToAuthority(String empCode, String empPassword,String deptCode) throws IdNotFoundException, PwMissmatchException, DeptCodeNotFoundException;

	public ArrayList<MenuBean> findUserMenuList(String empCode);

	public ArrayList<DetailCodeBean> findDetailCodeList(String code);

	public ArrayList<CodeBean> findCodeList();
	
	public ArrayList<DepartmentCodeBean> selectDeptCodeList(String code);
	
	public ArrayList<CustomerBean> selectCustomerCodeList(String code);
	
	public ArrayList<AddressBean> findRoadList(String sido, String sigunguname, String roadname);

	public ArrayList<AddressBean> findSigunguList(String parameter);

	public ArrayList<AddressBean> findSidoList();

	public ArrayList<AddressBean> findPostList(String dong);

	public void batchCodeProcess(ArrayList<CodeBean> codeList);

	
	

}
