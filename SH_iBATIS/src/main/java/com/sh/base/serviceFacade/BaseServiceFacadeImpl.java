package com.sh.base.serviceFacade;

import java.util.ArrayList;
import java.util.HashMap;

import com.sh.base.exception.UserMenuListException;
import com.sh.base.exception.DetailCodeListException;
import com.sh.base.exception.CodeListException;
import com.sh.base.applicationService.BaseApplicationService;
import com.sh.base.applicationService.CodeListApplicationService;
import com.sh.base.exception.DeptCodeNotFoundException;
import com.sh.base.exception.IdNotFoundException;
import com.sh.base.exception.PwMissmatchException;
import com.sh.base.to.CodeBean;
import com.sh.base.to.DetailCodeBean;
import com.sh.base.to.MenuBean;
import com.sh.base.serviceFacade.BaseServiceFacade;
import com.sh.base.serviceFacade.BaseServiceFacadeImpl;



public class BaseServiceFacadeImpl implements BaseServiceFacade{
	
	private BaseApplicationService baseApplicationService;
	public void setBaseApplicationService(BaseApplicationService baseApplicationService) {
		this.baseApplicationService = baseApplicationService;
	}
	private CodeListApplicationService codeListApplicationService;
	public void setCodeListApplicationService(CodeListApplicationService codeListApplicationService) {
			this.codeListApplicationService = codeListApplicationService;
		}
	
	@Override						
	public HashMap<String, Object> accessToAuthority(String empCode, String userPw, String deptCode) throws IdNotFoundException,DeptCodeNotFoundException, PwMissmatchException {
		// TODO Auto-generated method stub
		HashMap<String,Object> result=baseApplicationService.accessToAuthority(empCode, userPw,deptCode);
		return result;
	}
	@Override
	public ArrayList<MenuBean> findUserMenuList(String empCode)  throws UserMenuListException{
		// TODO Auto-generated method stub
		ArrayList<MenuBean> menuList = baseApplicationService.findMenuCodeList(empCode);
		if(menuList==null){				 
			 throw new UserMenuListException("사용자 메뉴를 가져오지 못했습니다.");
		 }
		return menuList;
	}
	@Override
	public ArrayList<DetailCodeBean> findDetailCodeList(String code) throws DetailCodeListException{
		// TODO Auto-generated method stub
		ArrayList<DetailCodeBean> detailCodeList = codeListApplicationService.findDetailCodeList(code);
		if(detailCodeList==null){				 
			 throw new DetailCodeListException("코드 상세정보를 가져오지 못했습니다.");
		 }	
		return detailCodeList;
	}
	/*@Override
	public ArrayList<AddressBean> findRoadList(String sido, String sigunguname, String roadname) {
		// TODO Auto-generated method stub
		ArrayList<AddressBean> postRoadList=baseApplicationService.findRoadList(sido, sigunguname, roadname);
		return postRoadList;
	}
	@Override
	public ArrayList<AddressBean> findSigunguList(String parameter) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" BaseServiceFacadeImpl : findSigunguList 시작 "); }
				ArrayList<AddressBean> postSigunguList=null;

				try{
					postSigunguList=baseApplicationService.findSigunguList(parameter);
					System.out.println("		@ 시/군/구: "+postSigunguList.get(0).getSigungu());
					System.out.println("		@ DB 커밋");
				}catch(Exception e){
					logger.fatal(e.getMessage());
					System.out.println("		@ DB 롤백");
					//throw e;
				}
				if(logger.isDebugEnabled()){ logger.debug(" BaseServiceFacadeImpl : findSigunguList 종료 "); }
				return postSigunguList;
	}
	@Override
	public ArrayList<AddressBean> findSidoList() {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" BaseServiceFacadeImpl : findSidoList 시작 "); }
		System.out.println("		@ DB 접근");
		ArrayList<AddressBean> postSidoList=null;

		try{
			postSidoList=baseApplicationService.findSidoList();
			//System.out.println("		@ DB 시/도: "+postSidoList.get(0).getSido());
			System.out.println("		@ DB 커밋");

		}catch(Exception e){
			logger.fatal(e.getMessage());
			System.out.println("		@ DB 롤백");
			//throw e;
		}
		if(logger.isDebugEnabled()){ logger.debug(" BaseServiceFacadeImpl : findSidoList 종료 "); }
		return postSidoList;
	}
	@Override
	public ArrayList<AddressBean> findPostList(String dong) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" BaseServiceFacadeImpl : findPostList 시작 "); }
		System.out.println("		@ DB 접근 : findPostList");
		ArrayList<AddressBean> postList=null;
		try{
			postList=baseApplicationService.findPostList(dong);
			//System.out.println("		@ 우편번호: "+postList.get(0).getZipcode());
			System.out.println("		@ DB 커밋");
		}catch(Exception e){
			logger.fatal(e.getMessage());
			System.out.println("		@ DB 롤백");
			//throw e;
			}
		if(logger.isDebugEnabled()){ logger.debug(" BaseServiceFacadeImpl : findPostList 종료 "); }
			return postList;
	}*/
	
	@Override
	public ArrayList<CodeBean> findCodeList() throws CodeListException {
		// TODO Auto-generated method stub
		ArrayList<CodeBean> codeList = codeListApplicationService.findCodeList();
		if(codeList==null){				 
			 throw new CodeListException("코드 목록을 가져오지 못했습니다.");
		 }	
		return codeList;
	}
	@Override
	public void batchCodeProcess(ArrayList<CodeBean> codeList) {
		// TODO Auto-generated method stub
			codeListApplicationService.batchCodeProcess(codeList); 
	}
	

}
