package com.sh.base.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.sh.base.dao.MenuDAO;
import com.sh.base.dao.MenuDAOImpl;
import com.sh.base.to.MenuBean;
import com.sh.common.db.DataSourceTransactionManager;
import com.sh.common.exception.DataAccessException;

public class MenuDAOImpl implements MenuDAO{
	 private DataSourceTransactionManager dataSourceTransactionManager;
		
	 public void setDataSourceTransactionManager(DataSourceTransactionManager dataSourceTransactionManager) {
		this.dataSourceTransactionManager = dataSourceTransactionManager;
	 }
	@Override
	public ArrayList<MenuBean> selectMenuList(String empCode) {
		// TODO Auto-generated method stub
		//System.out.println("		@ 넘겨받은 empCode: "+empCode);
		ArrayList<MenuBean> menuList = new ArrayList<MenuBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
	         StringBuffer query = new StringBuffer();
	         query.append(" SELECT MA.MENU_CODE, M.MENU_NAME, M.PARENT_MENU_CODE, M.URL, P.POSITION_NAME, ");
	         query.append(" MA.IS_ACCESS_DENIED,MA.IS_ACCESS_DENIED_NOT_PRINTED ");
	         query.append(" FROM EMPLOYEE E, MENU_AVAILABLE_BY_POSITION MA, MENU M, POSITION P ");
	         query.append(" WHERE MA.DEPT_CODE=E.DEPT_CODE ");
	         query.append(" AND MA.POSITION_CODE=E.POSITION_CODE ");
	         query.append(" AND MA.MENU_CODE = M.MENU_CODE ");
	         query.append(" AND E.POSITION_CODE=P.POSITION_CODE ");
	         query.append(" AND E.EMP_CODE=? ORDER BY M.MENU_CODE ");
	         con = dataSourceTransactionManager.getConnection();
	         pstmt = con.prepareStatement(query.toString());
	         pstmt.setString(1, empCode);
	         rs = pstmt.executeQuery();
	         while (rs.next()) {
	            MenuBean menuBean = new MenuBean();
	            menuBean.setIsAccessDenied(rs.getString("IS_ACCESS_DENIED"));
	            menuBean.setMenuCode(rs.getString("MENU_CODE"));
	            menuBean.setMenuName(rs.getString("MENU_NAME"));
	            menuBean.setSuperMenuCode(rs.getString("PARENT_MENU_CODE"));
	            menuBean.setMenuUrl(rs.getString("URL"));
	            menuBean.setAuthorityName(rs.getString("MENU_CODE"));
	            menuList.add(menuBean);
	         
	         }
	             return menuList;
	      } catch (Exception sqle) { 
	          throw new DataAccessException(sqle.getMessage());
	      } finally {
	         dataSourceTransactionManager.close(pstmt, rs);
	      }
	}
	@Override
	public ArrayList<MenuBean> selectAllMenuList() {
		// TODO Auto-generated method stub
		 ArrayList<MenuBean> menuList = new ArrayList<MenuBean>();
		      
		      Connection con = null;
		      PreparedStatement pstmt = null;
		      ResultSet rs = null;
		      try {
		         StringBuffer query = new StringBuffer();
		         query.append(" SELECT * FROM MENU ORDER BY MENU_CODE");	 
		         con = dataSourceTransactionManager.getConnection();
		         pstmt = con.prepareStatement(query.toString());	         
		         rs = pstmt.executeQuery();
		         while (rs.next()) {
		            MenuBean menuBean = new MenuBean();
		            menuBean.setMenuCode(rs.getString("MENU_CODE"));
		            menuBean.setMenuName(rs.getString("MENU_NAME"));
		            menuBean.setSuperMenuCode(rs.getString("PARENT_MENU_CODE"));
		            menuBean.setMenuUrl(rs.getString("URL"));	            
		            menuList.add(menuBean);
		         }
		         //System.out.println("		@ 메뉴경로 : "+menuList.get(0).getMenuUrl());
		        return menuList;
		      } catch (Exception sqle) {
		         throw new DataAccessException(sqle.getMessage());
		      } finally {
		         dataSourceTransactionManager.close(pstmt, rs);
		      }
	   }

}
