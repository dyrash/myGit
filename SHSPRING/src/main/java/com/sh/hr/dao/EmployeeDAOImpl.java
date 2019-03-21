package com.sh.hr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.sh.hr.dao.EmployeeDAO;
import com.sh.hr.dao.EmployeeDAOImpl;
import com.sh.common.db.DataSourceTransactionManager;
import com.sh.common.exception.DataAccessException;
import com.sh.hr.to.EmployeeBean;

public class EmployeeDAOImpl implements EmployeeDAO{
	private DataSourceTransactionManager dataSourceTransactionManager ;
	public void setDataSourceTransactionManager(DataSourceTransactionManager dataSourceTransactionManager) {
		this.dataSourceTransactionManager = dataSourceTransactionManager;
	}
	 
	@Override
	public EmployeeBean selectCompanyStaff(String empCode) {
		// TODO Auto-generated method stub
		
		Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
	         StringBuffer query = new StringBuffer();
	         query.append("SELECT * FROM EMPLOYEE E, CODE_DETAIL CD WHERE E.POSITION_CODE=CD.DETAIL_CODE AND E.EMP_CODE=?");
	         con = dataSourceTransactionManager.getConnection();
	         pstmt = con.prepareStatement(query.toString());
	         pstmt.setString(1, empCode);
	         rs = pstmt.executeQuery();
	         EmployeeBean employeeBean = null;
	         if (rs.next()) {
	        	 
	            employeeBean = new EmployeeBean();
	            employeeBean.setEmpCode(rs.getString("EMP_CODE"));
	            employeeBean.setEmpName(rs.getString("EMP_NAME"));
	            employeeBean.setEmpPassword(rs.getString("USER_PW"));
	            employeeBean.setPositionCode(rs.getString("POSITION_CODE"));
	            employeeBean.setPositionName(rs.getString("DETAIL_CODE_NAME"));
	            employeeBean.setDeptCode(rs.getString("DEPT_CODE"));
	            employeeBean.setEmail(rs.getString("EMAIL"));
	            employeeBean.setGender(rs.getString("GENDER"));
	            employeeBean.setResidentNumber(rs.getString("SOCIAL_SECURITY_NUMBER"));
	            employeeBean.setTelephone(rs.getString("PHONE_NUMBER"));
	            employeeBean.setBirthday(rs.getString("BIRTH_DATE"));
	            employeeBean.setEducation(rs.getString("LEVEL_OF_EDUCATION"));
	            employeeBean.setZipCode(rs.getString("ZIP_CODE"));
	            employeeBean.setBasicAddress(rs.getString("BASIC_ADDRESS"));
	            employeeBean.setDetailAddress(rs.getString("DETAIL_ADDRESS"));
	            employeeBean.setImgSrc(rs.getString("IMAGE"));
	            System.out.println("		@ 선택된 사원: "+empCode);
	         }
	          return employeeBean;
	        
	      } catch (Exception sqle) {
	             throw new DataAccessException(sqle.getMessage());
	      } finally {
	         dataSourceTransactionManager.close(pstmt, rs);
	      }
	   
	}
	
	@Override  
	public ArrayList<EmployeeBean> selectEmployeeList(String deptCode) {
		// TODO Auto-generated method stub
		Connection con=null;
	    PreparedStatement pstmt=null;
	    ResultSet rs=null;
	    ArrayList<EmployeeBean> empList=new ArrayList<EmployeeBean>();
	    try {
		StringBuffer query=new StringBuffer();
        query.append(" SELECT * FROM EMPLOYEE WHERE DEPT_CODE = ? ");
        con = dataSourceTransactionManager.getConnection();
		pstmt = con.prepareStatement(query.toString());
		pstmt.setString(1, deptCode);
        rs = pstmt.executeQuery();
		System.out.println("		@ 선택된 부서: "+deptCode);
        while (rs.next()){
           	EmployeeBean employeeBean=new EmployeeBean();
            employeeBean.setEmpCode(rs.getString("EMP_CODE"));
            employeeBean.setEmpName(rs.getString("EMP_NAME"));
            employeeBean.setEmpPassword(rs.getString("USER_PW"));
            employeeBean.setPositionCode(rs.getString("POSITION_CODE"));
            //employeeBean.setAuthority(rs.getString("AUTHORITY"));
            employeeBean.setDeptCode(rs.getString("DEPT_CODE"));
            employeeBean.setEmail(rs.getString("EMAIL"));            
            employeeBean.setGender(rs.getString("GENDER"));
            employeeBean.setResidentNumber(rs.getString("SOCIAL_SECURITY_NUMBER"));
            employeeBean.setTelephone(rs.getString("PHONE_NUMBER"));
            employeeBean.setBirthday(rs.getString("BIRTH_DATE"));
            employeeBean.setEducation(rs.getString("LEVEL_OF_EDUCATION"));
            employeeBean.setZipCode(rs.getString("ZIP_CODE"));
            employeeBean.setBasicAddress(rs.getString("BASIC_ADDRESS"));
            employeeBean.setDetailAddress(rs.getString("DETAIL_ADDRESS"));
            employeeBean.setImgSrc(rs.getString("IMAGE"));
           	empList.add(employeeBean);
           }
        	return empList;
	    } catch (Exception sqle) { 
				// TODO Auto-generated catch block
	    	throw new DataAccessException(sqle.getMessage());
		}finally{
			dataSourceTransactionManager.close(pstmt, rs);
		}
			  
	} 
	
	@Override
	public void updateEmployeeInfo(EmployeeBean employeeBean) {
		// TODO Auto-generated method stub
		 Connection conn = null;
	        PreparedStatement pstmt = null;
	        System.out.println("		@ 수정할 사원코드: "+employeeBean.getEmpCode());
	        System.out.println("		@ 수정할 사원이름: "+employeeBean.getEmpName());
	        try {
	            StringBuffer query = new StringBuffer();
	            query.append("UPDATE EMPLOYEE SET ");
	            query.append("GENDER =?, ");
	            query.append("PHONE_NUMBER= ?, ZIP_CODE = ?, BASIC_ADDRESS = ?, ");
	            query.append("DETAIL_ADDRESS = ?, EMAIL = ?, IMAGE = ?, ");
	            query.append("SOCIAL_SECURITY_NUMBER = ? ");
	            query.append("WHERE EMP_CODE = ?");
	            conn = dataSourceTransactionManager.getConnection();
	            pstmt = conn.prepareStatement(query.toString());

	            pstmt.setString(1, employeeBean.getGender());
	            pstmt.setString(2, employeeBean.getTelephone());
	            pstmt.setString(3, employeeBean.getZipCode());
	            pstmt.setString(4, employeeBean.getBasicAddress());
	            pstmt.setString(5, employeeBean.getDetailAddress());
	            pstmt.setString(6, employeeBean.getEmail());
	            pstmt.setString(7, employeeBean.getImgSrc());
	            pstmt.setString(8, employeeBean.getResidentNumber());
	            pstmt.setString(9, employeeBean.getEmpCode());

	            pstmt.executeUpdate();
	        }catch(Exception sqle){
	            throw new DataAccessException(sqle.getMessage());
	        }finally{
	           dataSourceTransactionManager.close(pstmt);
	        }
	}
	
	
	@Override
	public void updateEmployee(EmployeeBean bean) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		System.out.println("		@ 수정할 사원코드: "+bean.getEmpCode());
        System.out.println("		@ 수정할 사원이름: "+bean.getEmpName());
		try{
			StringBuffer query = new StringBuffer();
			query.append("UPDATE EMPLOYEE ");
			query.append("SET DEPT_CODE=? "); 
			query.append("WHERE EMP_CODE=? ");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, bean.getDeptCode());
			pstmt.setString(2, bean.getEmpCode());
			pstmt.executeUpdate();
		}catch(Exception sqle){
			throw new DataAccessException(sqle.getMessage()); 
		}finally{
			dataSourceTransactionManager.close(pstmt);
		}
	}
	
	@Override
	public void deleteEmployee(String empCode) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		System.out.println("		@ 삭제할 사원코드: "+empCode);
        
		try{
			StringBuffer query = new StringBuffer();
			query.append(" DELETE FROM EMPLOYEE ");
			query.append(" WHERE EMP_CODE=? ");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, empCode);
			pstmt.executeUpdate();
		}catch(Exception sqle){
			throw new DataAccessException(sqle.getMessage());
		}finally{
			dataSourceTransactionManager.close(pstmt);
		}
	}
	@Override
	public void insertEmployee(EmployeeBean employeeBean) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			StringBuffer query = new StringBuffer();
			query.append(" INSERT INTO EMPLOYEE (EMP_CODE, EMP_NAME, USER_PW, ");
			query.append("POSITION_CODE, DEPT_CODE, EMAIL, "); 			//AUTHORITY, 
			query.append("GENDER, SOCIAL_SECURITY_NUMBER, PHONE_NUMBER,  ");  		//ENTERING_DATE,
			query.append("BIRTH_DATE, LEVEL_OF_EDUCATION, ZIP_CODE, BASIC_ADDRESS, ");
			query.append("DETAIL_ADDRESS, IMAGE,COMPANY_CODE, WORKPLACE_CODE) ");
			query.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");			 			
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, employeeBean.getEmpCode());
			pstmt.setString(2, employeeBean.getEmpName());
			pstmt.setString(3, employeeBean.getEmpPassword());
			pstmt.setString(4, employeeBean.getPositionCode());
			pstmt.setString(5, employeeBean.getDeptCode());
			pstmt.setString(6, employeeBean.getEmail());
			pstmt.setString(7, employeeBean.getGender());
			pstmt.setString(8, employeeBean.getResidentNumber());
			pstmt.setString(9, employeeBean.getTelephone());
			pstmt.setString(10, employeeBean.getBirthday());
			pstmt.setString(11, employeeBean.getEducation());
			pstmt.setString(12, employeeBean.getZipCode());
			pstmt.setString(13, employeeBean.getBasicAddress());
			pstmt.setString(14, employeeBean.getDetailAddress());
			pstmt.setString(15, employeeBean.getImgSrc());
			pstmt.setString(16, employeeBean.getCompanyCode());
			pstmt.setString(17, employeeBean.getWorkplaceCode());
			System.out.println("		@ 가입할 사원코드"+employeeBean.getEmpCode());
			System.out.println("		@ 가입할 사원이름"+employeeBean.getEmpName());
			pstmt.executeUpdate();
		}catch(Exception sqle){
			throw new DataAccessException(sqle.getMessage());
		}finally{
			dataSourceTransactionManager.close(pstmt);
		}
	}
	
	@Override
	public EmployeeBean selectEmployee(String empCode) {
		// TODO Auto-generated method stub
		 Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      EmployeeBean employeeBean = null;
	      try{
	         StringBuffer query = new StringBuffer(); 
	         query.append("SELECT * FROM EMPLOYEE WHERE EMP_CODE=?");
	         conn = dataSourceTransactionManager.getConnection();
	         pstmt = conn.prepareStatement(query.toString());
	         pstmt.setString(1, empCode);
	         rs = pstmt.executeQuery();
	         if(rs.next()){
	       	  employeeBean = new EmployeeBean();
	       	  employeeBean.setEmpCode(rs.getString("EMP_CODE"));
	       	  employeeBean.setEmpName(rs.getString("EMP_NAME"));
	       	  employeeBean.setEmpPassword(rs.getString("USER_PW"));
	       	  employeeBean.setDeptCode(rs.getString("DEPT_CODE"));
	       	System.out.println("		@ 조회한 사원코드 : "+empCode);
	         }
	      }catch(Exception sqle){
	         throw new DataAccessException(sqle.getMessage());
	      }
	      return employeeBean;
	}
	@Override
	public ArrayList<EmployeeBean> findSYSList(String authority) {
		// TODO Auto-generated method stub
		Connection con=null;
	    PreparedStatement pstmt=null;
	    ResultSet rs=null;
	    ArrayList<EmployeeBean> empList=new ArrayList<EmployeeBean>();
	    try {
		StringBuffer query=new StringBuffer();
        query.append(" SELECT * FROM EMPLOYEE where AUTHORITY = ? ");
        con = dataSourceTransactionManager.getConnection();
		pstmt = con.prepareStatement(query.toString());
		pstmt.setString(1, authority);
        rs = pstmt.executeQuery();
		
        while (rs.next()){
           	EmployeeBean employeeBean=new EmployeeBean();
            employeeBean.setEmpCode(rs.getString("EMP_CODE"));
            employeeBean.setEmpName(rs.getString("EMP_NAME"));
            employeeBean.setAuthority(rs.getString("AUTHORITY"));
           	empList.add(employeeBean);
           }
        	return empList;
	    } catch (Exception sqle) { 
				// TODO Auto-generated catch block
	    	throw new DataAccessException(sqle.getMessage());
	    		
		}finally{
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}

}