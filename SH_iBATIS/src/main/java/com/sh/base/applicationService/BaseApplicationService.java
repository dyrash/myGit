package com.sh.base.applicationService;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.dao.DataAccessException;

import com.sh.base.exception.DeptCodeNotFoundException;
import com.sh.base.exception.IdNotFoundException;
import com.sh.base.exception.PwMissmatchException;
import com.sh.base.to.MenuBean;

public interface BaseApplicationService {

	public HashMap<String, Object> accessToAuthority(String empCode, String userPw,String deptCode) throws IdNotFoundException,DeptCodeNotFoundException,PwMissmatchException, DataAccessException;

	public ArrayList<MenuBean> findMenuCodeList(String empCode);

	/*
	
	public ArrayList<AddressBean> findRoadList(String sido, String sigunguname, String roadname);

	public ArrayList<AddressBean> findSigunguList(String parameter);

	public ArrayList<AddressBean> findSidoList();

	public ArrayList<AddressBean> findPostList(String dong);*/

	

}
