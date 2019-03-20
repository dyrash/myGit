package com.sh.base.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sh.base.dao.DetailCodeDAO;
import com.sh.base.dao.DetailCodeDAOImpl;
import com.sh.base.to.DetailCodeBean;
import com.sh.common.db.DataSourceTransactionManager;
import com.sh.common.exception.DataAccessException;

public class DetailCodeDAOImpl implements DetailCodeDAO{

	protected final Log logger = LogFactory.getLog(this.getClass());
	private DataSourceTransactionManager dataSourceTransactionManager = DataSourceTransactionManager.getInstance();
	
	private static DetailCodeDAO instance;
	private DetailCodeDAOImpl(){};
	public static DetailCodeDAO getinstance() {
		// TODO Auto-generated method stub
		if(instance ==null){
			instance = new DetailCodeDAOImpl();
			System.out.println("		@ DetailCodeDAOImpl에 접근");
		}
		return instance;
	}
	
	@Override
	public ArrayList<DetailCodeBean> selectDetailCodeList(String code) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" DetailCodeDAOImpl : selectDetailCodeList 시작 "); }
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<DetailCodeBean> detailCodeList = new ArrayList<DetailCodeBean>();
		//System.out.println("		@ selectDetailCodeList에서 받은 코드: '"+code+"'");
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT * FROM CODE_DETAIL WHERE DIVISION_CODE_NO= ?");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			while(rs.next()){
				 DetailCodeBean detailcodeBean = new DetailCodeBean();
				 detailcodeBean.setDivisionCodeNo(rs.getString("DIVISION_CODE_NO"));
	             detailcodeBean.setDetailCode(rs.getString("DETAIL_CODE"));
	             detailcodeBean.setDetailCodeName(rs.getString("DETAIL_CODE_NAME"));
	             detailcodeBean.setCodeUseCheck(rs.getString("CODE_USE_CHECK"));
	             
	             detailCodeList.add(detailcodeBean);
	            
			}
			//System.out.println("		@ 코드명: '"+DetailCodeList.get(0).getDetailCodeName()+"' 조회됨");
			if(logger.isDebugEnabled()){ logger.debug(" DetailCodeDAOImpl : selectDetailCodeList 종료 "); }
			return detailCodeList;
		} catch (Exception sqle) {  
			// TODO Auto-generated catch block
			System.out.println("		@ 코드 조회 에러 발생");
			logger.fatal(sqle.getMessage());
			 throw new DataAccessException(sqle.getMessage());
		}finally{
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}
	@Override
	public void insertDetailCode(DetailCodeBean codeDetailBean) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" DetailCodeDAOImpl : insertDetailCode 시작 "); }
		 Connection con = null;
	        PreparedStatement pstmt = null;
	        //System.out.println("@ 입력할 코드객체: "+codeDetailBean.getCodeName());
	        try {
	            StringBuffer query = new StringBuffer();
	            query.append("INSERT INTO CODE_DETAIL VALUES(?, ?, ?, ?,?)");
	            con = dataSourceTransactionManager.getConnection();
	            pstmt = con.prepareStatement(query.toString());

	            pstmt.setString(1, codeDetailBean.getDivisionCode());
	            pstmt.setString(2, codeDetailBean.getCodeNo());
	            pstmt.setString(3, codeDetailBean.getCodeName());
	            pstmt.setString(4, codeDetailBean.getCodeUseCheck());
	            pstmt.setString(5, "");

	            pstmt.executeUpdate();
	            //System.out.println("		@ 코드 '"+codeDetailBean.getCodeName()+"' 입력됨");
	        }catch(Exception sqle){
	        	logger.fatal(sqle.getMessage());
	         throw new DataAccessException(sqle.getMessage());
	      }finally{
	         dataSourceTransactionManager.close(pstmt);
	      }
	        if(logger.isDebugEnabled()){ logger.debug(" DetailCodeDAOImpl : insertDetailCode 종료 "); }
		
	}
	@Override
	public void updateDetailCode(DetailCodeBean codeDetailBean) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" DetailCodeDAOImpl : updateDetailCode 시작 "); }

		  Connection con = null;
	        PreparedStatement pstmt = null;
	        //System.out.println("		@ 수정될 코드: '"+codeDetailBean.getCodeName()+"'");
	        try{
	            StringBuffer query = new StringBuffer();
	            query.append("UPDATE CODE_DETAIL SET DETAIL_CODE_NAME = ?, CODE_USE_CHECK=? WHERE DIVISION_CODE_NO = ? AND DETAIL_CODE = ?");
	            con = dataSourceTransactionManager.getConnection();
	            pstmt = con.prepareStatement(query.toString());
	            pstmt.setString(1, codeDetailBean.getCodeName());
	            pstmt.setString(2, codeDetailBean.getCodeUseCheck());
	            pstmt.setString(3, codeDetailBean.getDivisionCode());
	            pstmt.setString(4, codeDetailBean.getCodeNo());
	            pstmt.executeUpdate();
	            //System.out.println("@ 코드 '"+codeDetailBean.getCodeName()+"' 수정됨");
	        }catch(Exception sqle){
	        	logger.fatal(sqle.getMessage());
	         throw new DataAccessException(sqle.getMessage());
	      }finally{
	         dataSourceTransactionManager.close(pstmt);
	      }
	        if(logger.isDebugEnabled()){ logger.debug(" DetailCodeDAOImpl : updateDetailCode 종료 "); }
	}
	@Override
	public void deleteDetailCode(String codeNo) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" DetailCodeDAOImpl : deleteDetailCode 시작 "); }
		 Connection con = null;
	        PreparedStatement pstmt = null;
	        //System.out.println("		@ 삭제할 코드: '"+codeNo+"'");
	        try {
	            StringBuffer query = new StringBuffer();
	            query.append("DELETE FROM CODE_DETAIL WHERE DIVISION_CODE_NO = ?");
	            con = dataSourceTransactionManager.getConnection();
	            pstmt = con.prepareStatement(query.toString());
	            pstmt.setString(1, codeNo);
	            pstmt.executeUpdate();
	            //System.out.println("		@ 코드: '"+codeNo+"' 삭제됨");
	        }catch(Exception sqle){
	        	logger.fatal(sqle.getMessage());
	         throw new DataAccessException(sqle.getMessage());
	      }finally{
	         dataSourceTransactionManager.close(pstmt);
	      }
	        if(logger.isDebugEnabled()){ logger.debug(" DetailCodeDAOImpl : deleteDetailCode 종료 "); }
	}
	
	

}
