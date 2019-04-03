package com.sh.base.serviceFacade;

import java.util.ArrayList;
import java.util.HashMap;

import com.sh.base.exception.CodeListException;
import com.sh.base.exception.DeptCodeNotFoundException;
import com.sh.base.exception.DetailCodeListException;
import com.sh.base.exception.IdNotFoundException;
import com.sh.base.exception.PwMissmatchException;
import com.sh.base.exception.UserMenuListException;
import com.sh.base.to.CodeBean;
import com.sh.base.to.DetailCodeBean;
import com.sh.base.to.MenuBean;

public interface BaseServiceFacade {

	public HashMap<String, Object> accessToAuthority(String empCode, String userPw,String deptCode) throws IdNotFoundException, PwMissmatchException, DeptCodeNotFoundException;

	public ArrayList<MenuBean> findUserMenuList(String empCode) throws UserMenuListException;

	public ArrayList<DetailCodeBean> findDetailCodeList(String code) throws DetailCodeListException;

	public ArrayList<CodeBean> findCodeList() throws CodeListException;
	
	
	/*public ArrayList<AddressBean> findRoadList(String sido, String sigunguname, String roadname);

	public ArrayList<AddressBean> findSigunguList(String parameter);

	public ArrayList<AddressBean> findSidoList();

	public ArrayList<AddressBean> findPostList(String dong);*/

	public void batchCodeProcess(ArrayList<CodeBean> codeList);

	
	

}
