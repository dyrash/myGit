package com.sh.base.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sh.base.dao.CodeDAO;
import com.sh.base.dao.CodeDAOImpl;
import com.sh.base.to.CodeBean;
import com.sh.common.db.DataSourceTransactionManager;
import com.sh.common.exception.DataAccessException;


public class CodeDAOImpl implements CodeDAO{
	protected final Log logger = LogFactory.getLog(this.getClass());
	private DataSourceTransactionManager dataSourceTransactionManager = DataSourceTransactionManager.getInstance();
	
	private static CodeDAO instance;
	
	private CodeDAOImpl(){};
	
	public static CodeDAO getInstance() {
		// TODO Auto-generated method stub
		if(instance == null){
			instance = new CodeDAOImpl(); 
		}
		return instance;
	}
	@Override		
	public ArrayList<CodeBean> selectCodeList() {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" CodeDAOImpl : selectCodeList 시작 "); }
		ArrayList<CodeBean> codeList = new ArrayList<CodeBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		StringBuffer query = new StringBuffer();
		query.append("SELECT C.DIVISION_CODE_NO, C.DIVISION_CODE_NAME, C.CODE_CHANGE_AVAILABLE, CD.CODE_USE_CHECK ");
		query.append("FROM CODE C, CODE_DETAIL CD WHERE C.DIVISION_CODE_NO=CD.DIVISION_CODE_NO ");
		query.append("GROUP BY C.DIVISION_CODE_NO, C.DIVISION_CODE_NAME, C.CODE_CHANGE_AVAILABLE, CD.CODE_USE_CHECK, CD.CODE_USE_CHECK ");
		query.append("ORDER BY C.DIVISION_CODE_NO ");
		con = dataSourceTransactionManager.getConnection();
		
		pstmt = con.prepareStatement(query.toString()); 
		rs = pstmt.executeQuery();
		while(rs.next()){
			CodeBean codeBean = new CodeBean();
			codeBean.setDivisionCode(rs.getString("DIVISION_CODE_NO"));
			codeBean.setCodeName(rs.getString("DIVISION_CODE_NAME"));
			codeBean.setCodeUseCheck(rs.getString("CODE_USE_CHECK"));
			codeList.add(codeBean);
		}
		if(logger.isDebugEnabled()){ logger.debug(" CodeDAOImpl : selectCodeList 종료 "); }
		return codeList;
		} catch (SQLException e) {			
			// TODO Auto-generated catch block
			logger.fatal(e.getMessage());
			throw new DataAccessException(e.getMessage());
		}finally{
			
			dataSourceTransactionManager.close(pstmt, rs);
		}		
	}

	@Override
	public void insertCode(CodeBean codeBean) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" CodeDAOImpl : insertCode 시작 "); }
		Connection con = null;
        PreparedStatement pstmt = null;
        try {
        	
            StringBuffer query = new StringBuffer();
            query.append("INSERT INTO CODE VALUES(?, ?, ?,?,?)");
            con = dataSourceTransactionManager.getConnection();
            pstmt = con.prepareStatement(query.toString());
            pstmt.setString(1, codeBean.getDivisionCode());
            pstmt.setString(2, codeBean.getCodeName());
            pstmt.setString(3, codeBean.getCodeUseCheck());
            pstmt.setString(4, codeBean.getCodeChangeAvailable());
            pstmt.setString(5, "");
            pstmt.executeUpdate();
            
        }catch(Exception sqle) {
        	logger.fatal(sqle.getMessage());
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt);
		}
        if(logger.isDebugEnabled()){ logger.debug(" CodeDAOImpl : insertCode 종료 "); }
	}

	@Override
	public void updateCode(CodeBean codeBean) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" CodeDAOImpl : updateCode 시작 "); }
		  Connection con = null;
	        PreparedStatement pstmt = null;
	        try{
	            StringBuffer query = new StringBuffer();
	            query.append("UPDATE CODE SET DIVISION_CODE_NAME = ? WHERE DIVISION_CODE_NO = ?");
	            con = dataSourceTransactionManager.getConnection();
	            pstmt = con.prepareStatement(query.toString());
	            pstmt.setString(1, codeBean.getCodeName());
	            pstmt.setString(2, codeBean.getDivisionCode());
	            pstmt.executeUpdate();
	        }catch(Exception sqle){
	        	logger.fatal(sqle.getMessage());
	        	throw new DataAccessException(sqle.getMessage());
			} finally {
				dataSourceTransactionManager.close(pstmt);
			}
	        if(logger.isDebugEnabled()){ logger.debug(" CodeDAOImpl : updateCode 종료 "); }
	}

	@Override
	public void deleteCode(String divisionCode) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" CodeDAOImpl : deleteCode 시작 "); }
		  Connection con = null;
	        PreparedStatement pstmt = null;
	        try {
	            StringBuffer query = new StringBuffer();
	            query.append("DELETE CODE WHERE DIVISION_CODE_NO = ?");
	            con = dataSourceTransactionManager.getConnection();
	            pstmt = con.prepareStatement(query.toString());
	            pstmt.setString(1, divisionCode);
	            pstmt.executeUpdate();
	       } catch(Exception sqle) {
	    	   logger.fatal(sqle.getMessage());
	    	   throw new DataAccessException(sqle.getMessage());
			} finally {
				dataSourceTransactionManager.close(pstmt);
			}
	        if(logger.isDebugEnabled()){ logger.debug(" CodeDAOImpl : deleteCode 종료 "); }
	}
	
	

}
