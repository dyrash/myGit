package com.sh.account.slip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sh.account.slip.dao.JournalDAO;
import com.sh.account.slip.dao.JournalDAOImpl;
import com.sh.account.slip.to.JournalBean;
import com.sh.common.db.DataSourceTransactionManager;
import com.sh.common.exception.DataAccessException;

public class JournalDAOImpl implements JournalDAO{
	protected final Log logger = LogFactory.getLog(this.getClass());

	private DataSourceTransactionManager dataSourceTransactionManager
	   = DataSourceTransactionManager.getInstance();
	
	private static JournalDAO instance;
	private JournalDAOImpl(){}
	public static JournalDAO getInstance() {
		// TODO Auto-generated method stub
		if(instance == null){
			instance = new JournalDAOImpl();
			System.out.println("		@ JournalDAOImpl에 접근");
		}
		return instance;
	}
	

	@Override
	public ArrayList<JournalBean> selectJournalList(String slipNo) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" JournalDAOImpl : selectJournalList 시작 "); }
			Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        ArrayList<JournalBean> journalList = new ArrayList<>();
	         try {
	            StringBuffer query = new StringBuffer();
	            query.append("SELECT * FROM JOURNAL WHERE SLIP_NO=?");
	            conn = dataSourceTransactionManager.getConnection();
	            pstmt = conn.prepareStatement(query.toString());
	            pstmt.setString(1, slipNo);
	            rs = pstmt.executeQuery();
	            while(rs.next()){
	               JournalBean journalBean=new JournalBean();
	               journalBean.setJournalNo(rs.getString("JOURNAL_NO"));
	               journalBean.setSlipNo(rs.getString("SLIP_NO"));
	               journalBean.setBalanceDivision(rs.getString("BALANCE_DIVISION"));
	               journalBean.setAccountInnerCode(rs.getString("ACCOUNT_INNER_CODE"));
	               journalBean.setAccountName(rs.getString("ACCOUNT_NAME"));
	               journalBean.setCustomerCode(rs.getString("CUSTOMER_CODE"));
	               journalBean.setCustomerName(rs.getString("CUSTOMER_NAME"));
	               journalBean.setPrice(rs.getString("PRICE"));	
	               journalBean.setLeftDebtorPrice(rs.getString("LEFT_DEBTOR_PRICE"));	//차변 대변은 새로 만든내용
	               journalBean.setRightCreditPrice(rs.getString("RIGHT_CREDITS_PRICE"));	//차변 대변은 새로 만든내용
	               journalBean.setSummaryNumber(rs.getString("SUMMARY_NUMBER"));
	               journalBean.setSummaryComment(rs.getString("SUMMARY_COMMENT"));
	               journalList.add(journalBean);
	               System.out.println("		@ 분개 조회됨");
	            }
	    		if(logger.isDebugEnabled()){ logger.debug(" JournalDAOImpl : selectJournalList 종료 "); }
	            return journalList;
	         } catch(Exception sqle) {
	        	 logger.fatal(sqle.getMessage());
	            throw new DataAccessException(sqle.getMessage());
	         } finally {
	            dataSourceTransactionManager.close(pstmt, rs);
	         }
	}
	@Override
	public void insertJournal(JournalBean journalBean) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" JournalDAOImpl : insertJournal 시작 "); }
		 Connection conn = null;
	        PreparedStatement pstmt = null;
	        try{
	           StringBuffer query = new StringBuffer();				//섬머리 번호 코멘트 -> 적요임      계정명이랑 계정코드 칼럼에 값 넣어야함
  	           query.append("INSERT INTO JOURNAL(JOURNAL_NO, SLIP_NO, BALANCE_DIVISION, ACCOUNT_INNER_CODE, ACCOUNT_NAME,CUSTOMER_CODE, CUSTOMER_NAME, ");
	           query.append(" LEFT_DEBTOR_PRICE, RIGHT_CREDITS_PRICE,PRICE,SUMMARY_NUMBER, SUMMARY_COMMENT ) ");
	           query.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?,?) ");
	           conn = dataSourceTransactionManager.getConnection();
	           pstmt=conn.prepareStatement(query.toString());
	           pstmt.setString(1,journalBean.getJournalNo());
	           pstmt.setString(2,journalBean.getSlipNo());
	           pstmt.setString(3,journalBean.getBalanceDivision());
	           pstmt.setString(4,journalBean.getAccountInnerCode());
	           pstmt.setString(5,journalBean.getAccountName());
	           pstmt.setString(6,journalBean.getCustomerCode());
	           pstmt.setString(7,journalBean.getCustomerName());
	           pstmt.setString(8,journalBean.getLeftDebtorPrice());
	           pstmt.setString(9,journalBean.getRightCreditPrice());
	           //pstmt.setString(10,journalBean.getDescCode());
	           pstmt.setString(10,journalBean.getPrice());
	           pstmt.setString(11,journalBean.getSummaryNumber());
	           pstmt.setString(12,journalBean.getSummaryComment());
	           pstmt.executeUpdate();
	           System.out.println("		@ 분개 추가됨");
	        } catch(Exception sqle) {
	        	logger.fatal(sqle.getMessage());
	           throw new DataAccessException(sqle.getMessage());
	         } finally {
	            dataSourceTransactionManager.close(pstmt);
	         }
			if(logger.isDebugEnabled()){ logger.debug(" JournalDAOImpl : insertJournal 종료 "); }
	}
	@Override
	public void deleteJournal(JournalBean journalBean) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" JournalDAOImpl : deleteJournal 시작 "); }

		Connection conn = null;
		PreparedStatement pstmt = null;
		dataSourceTransactionManager.beginTransaction();
      try{
         StringBuffer query = new StringBuffer();
         query.append("DELETE FROM JOURNAL WHERE SLIP_NO =? AND JOURNAL_NO=?");
         conn = dataSourceTransactionManager.getConnection();
         pstmt = conn.prepareStatement(query.toString());
         pstmt.setString(1, journalBean.getSlipNo());
         pstmt.setString(2, journalBean.getJournalNo());
         //System.out.println(journalBean.getSlipNo());
         pstmt.executeUpdate();
         dataSourceTransactionManager.commitTransaction();
         System.out.println("		@ 분개 삭제됨");
      }catch(Exception sqle){
    	  logger.fatal(sqle.getMessage());
          throw new DataAccessException(sqle.getMessage());
      }finally{
          dataSourceTransactionManager.close(pstmt);
      }
      if(logger.isDebugEnabled()){ logger.debug(" JournalDAOImpl : deleteJournal 종료 "); }
		
	}
	@Override
	public void updateJournal(JournalBean journalBean) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" JournalDAOImpl : updateJournal 시작 "); }
		 Connection conn = null;
	        PreparedStatement pstmt = null;
	        try{
	           StringBuffer query = new StringBuffer();
	           query.append("UPDATE JOURNAL SET ");
	           query.append("BALANCE_DIVISION=?, ACCOUNT_INNER_CODE=?, ACCOUNT_NAME=?, CUSTOMER_CODE=?,");
	           query.append("CUSTOMER_NAME=?, LEFT_DEBTOR_PRICE=?, RIGHT_CREDITS_PRICE=?, ");			//VOUCHER_CODE=?, VOUCHER_NAME=?,
	           query.append("SUMMARY_NUMBER=?, SUMMARY_COMMENT=?, SLIP_APPROVAL_DATE=?");
	           query.append("WHERE SLIP_NO =? AND JOURNAL_NO =?");
	           conn = dataSourceTransactionManager.getConnection();
	           pstmt=conn.prepareStatement(query.toString());
	           pstmt.setString(1,journalBean.getBalanceDivision());
	           pstmt.setString(2,journalBean.getAccountInnerCode());
	           pstmt.setString(3,journalBean.getAccountName());
	           pstmt.setString(4,journalBean.getCustomerCode());
	           pstmt.setString(5,journalBean.getCustomerName());
	           pstmt.setString(6,journalBean.getLeftDebtorPrice());		//테이블에 맞춰서 데이터 ?번호 다시 확인
	           pstmt.setString(7,journalBean.getRightCreditPrice());
	           //pstmt.setString(7,journalBean.getVoucherCode());
	           //pstmt.setString(8,journalBean.getVoucherName());
	           pstmt.setString(8,journalBean.getDescCode());
	           pstmt.setString(9,journalBean.getDescName());
	           pstmt.setString(10,journalBean.getSlipApprovalDate());
	           pstmt.setString(11,journalBean.getSlipNo());
	           pstmt.setString(12,journalBean.getJournalNo());
	           pstmt.executeUpdate();
	           System.out.println("		@ 분개 수정됨");
	        } catch(Exception sqle) {
	        	logger.fatal(sqle.getMessage());
	           throw new DataAccessException(sqle.getMessage());
	         } finally {
	            dataSourceTransactionManager.close(pstmt);
	         }
	        if(logger.isDebugEnabled()){ logger.debug(" JournalDAOImpl : updateJournal 종료 "); }
		
	}

}
