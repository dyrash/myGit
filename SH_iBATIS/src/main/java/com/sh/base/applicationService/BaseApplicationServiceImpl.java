package com.sh.base.applicationService;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.dao.DataAccessException;

import com.sh.base.applicationService.BaseApplicationServiceImpl;
import com.sh.base.applicationService.BaseApplicationService;
import com.sh.base.dao.MenuDAO;
import com.sh.base.exception.DeptCodeNotFoundException;
import com.sh.base.exception.IdNotFoundException;
import com.sh.base.exception.PwMissmatchException;
import com.sh.base.to.MenuBean;
import com.sh.hr.dao.EmployeeDAO;
import com.sh.hr.to.EmployeeBean;

public class BaseApplicationServiceImpl implements BaseApplicationService{

	private EmployeeDAO employeeDAO;	
	private MenuDAO menuDAO;
	//private AddressDAO addressDAO;
	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}
	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}
	/*public void setAddressDAO(AddressDAO addressDAO) {
		this.addressDAO = addressDAO;
	}*/

	@Override
	public HashMap<String, Object> accessToAuthority(String empCode, String userPw,String deptCode) throws IdNotFoundException,DeptCodeNotFoundException, PwMissmatchException ,DataAccessException{
		// TODO Auto-generated method stub
		EmployeeBean employeeBean = null;
		ArrayList<MenuBean> menuList = null;
		ArrayList<MenuBean> masterMenuList = null;
		HashMap<String, Object> result = new HashMap<>();
			employeeBean = employeeDAO.selectCompanyStaff(empCode);
			if(employeeBean==null){
	        	throw new IdNotFoundException(" 존재 하지 않는 계정입니다. ");
			}else{
				
				if(!(employeeBean.getDeptCode().equals(deptCode))) {
					throw new DeptCodeNotFoundException("존재하지않는 부서입니다");
				}else {
				if(employeeBean.getUserPw().equals(userPw)){	
					
					
	        		menuList=menuDAO.selectMenuList(empCode);		
	        		//masterMenuList=menuDAO.selectAllMenuList();	  
	        		System.out.println("		@ 메뉴경로 : "+menuList.get(0).getUrl());
	        		//System.out.println("		@ 상위코드 : "+masterMenuList.get(0).getMenuCode());
	        		result.put("employeeBean", employeeBean);
	        		result.put("menuList", menuList);
	        		result.put("masterMenuList", masterMenuList);
	        		
	    		}else{
	    			
	    			throw new PwMissmatchException(" 비밀번호가 틀립니다. ");
	    		}
				
				}
				
			}
		return result;
				
	}
	
	@Override
	public ArrayList<MenuBean> findMenuCodeList(String empCode) {
		// TODO Auto-generated method stub
		ArrayList<MenuBean> menuList = menuDAO.selectMenuList(empCode);
		return menuList;
	}
/*	@Override
	public ArrayList<AddressBean> findRoadList(String sido, String sigunguname, String roadname) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" BaseApplicationServiceImpl : findRoadList 시작 "); }
		ArrayList<AddressBean> postRoadList=null;
		try{
		postRoadList = addressDAO.selectRoadList(sido,sigunguname,roadname);
		System.out.println("		@ 시/도: "+sido+", 시/군/구: "+sigunguname+", 도로명: "+roadname);
		
		}catch (Exception e){
			logger.fatal(e.getMessage());
			throw e;		
		}
		if(logger.isDebugEnabled()){ logger.debug(" BaseApplicationServiceImpl : findRoadList 종료 "); }
	return postRoadList;
	}
	@Override
	public ArrayList<AddressBean> findSigunguList(String parameter) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" BaseApplicationServiceImpl : findSigunguList 시작 "); }
		ArrayList<AddressBean> postSigunguList=null;
		try{
		postSigunguList = addressDAO.selectSigunguList(parameter);
		System.out.println("		@ 파라메터: "+parameter);
		}catch (Exception e){
			logger.fatal(e.getMessage());
			throw e;		
		}
		if(logger.isDebugEnabled()){ logger.debug(" BaseApplicationServiceImpl : findSigunguList 종료 "); }
	return postSigunguList;

	}
	@Override
	public ArrayList<AddressBean> findSidoList() {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" BaseApplicationServiceImpl : findSidoList 시작 "); }
		ArrayList<AddressBean> postSidoList=null;
		try{
			postSidoList = addressDAO.selectSidoList();
		}catch (Exception e){
			logger.fatal(e.getMessage());
			throw e;		
		}
		if(logger.isDebugEnabled()){ logger.debug(" BaseApplicationServiceImpl : findSidoList 종료 "); }
		return postSidoList;
	
	}
	@Override
	public ArrayList<AddressBean> findPostList(String dong) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" BaseApplicationServiceImpl : findPostList 시작 "); }
		ArrayList<AddressBean> postList=null;
		try{
		postList = addressDAO.selectPostList(dong);
		System.out.println("		@ 동이름: "+dong);
		}catch (Exception e){
			logger.fatal(e.getMessage());
			throw e;		
		}
		if(logger.isDebugEnabled()){ logger.debug(" BaseApplicationServiceImpl : findPostList 종료 "); }
	return postList;
	}
*/
	
}
