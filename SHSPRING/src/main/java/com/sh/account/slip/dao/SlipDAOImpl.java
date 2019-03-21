package com.sh.account.slip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sh.account.slip.dao.SlipDAO;
import com.sh.account.slip.dao.SlipDAOImpl;
import com.sh.account.slip.to.SlipBean;
import com.sh.common.db.DataSourceTransactionManager;
import com.sh.common.exception.DataAccessException;

public class SlipDAOImpl implements SlipDAO{
	protected final Log logger = LogFactory.getLog(this.getClass());
	private DataSourceTransactionManager dataSourceTransactionManager;
	public void setDataSourceTransactionManager(DataSourceTransactionManager dataSourceTransactionManager) {
		this.dataSourceTransactionManager = dataSourceTransactionManager;
	}

	@Override
	public ArrayList<SlipBean> selectSlipDataList(String slipDate1,String slipDate2) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SlipBean> slipList = new ArrayList<>();
		try {
			StringBuffer query = new StringBuffer();
			query.append(" SELECT * FROM SLIP WHERE REPORTING_DATE BETWEEN TO_DATE(?,'yyyy-mm-dd') AND TO_DATE(?,'yyyy-mm-dd') ");
			conn = dataSourceTransactionManager.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, slipDate1);
			pstmt.setString(2, slipDate2);
			System.out.println("		@ 조회할 일자: "+slipDate1+"~"+slipDate2);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SlipBean slipBean = new SlipBean();
				slipBean.setSlipNo(rs.getString("SLIP_NO"));
				slipBean.setDeptCode(rs.getString("DEPT_CODE"));
				slipBean.setReportingEmpCode(rs.getString("REPORTING_EMP_CODE"));
				slipBean.setApprovalEmpCode(rs.getString("APPROVAL_EMP_CODE"));
				slipBean.setRequestName(rs.getString("EXPENSE_REPORT"));
				slipBean.setSlipType(rs.getString("SLIP_TYPE"));
				slipBean.setAccountDifference(rs.getString("ACCOUNT_DIFFERENCE"));
				slipBean.setApprovalSeq(rs.getInt("APPROVAL_SEQ"));
				slipBean.setSlipStatus(rs.getString("SLIP_STATUS"));
				slipBean.setApprovalDate(rs.getString("APPROVAL_DATE"));
				slipBean.setSlipSeq(rs.getInt("SLIP_SEQ"));
				slipBean.setReportingDate(rs.getString("REPORTING_DATE"));
				slipBean.setAccountPeriodNo(rs.getString("ACCOUNT_PERIOD_NO"));
				slipList.add(slipBean);
				System.out.println("		@ 전표 조회됨");
			}
			return slipList;
		} catch (Exception sqle) {
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}
	@Override
	public void deleteSlip(SlipBean slipBean) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
	    dataSourceTransactionManager.beginTransaction();

		try {
			StringBuffer query = new StringBuffer();
			query.append("DELETE FROM SLIP WHERE SLIP_NO = ?");
			conn = dataSourceTransactionManager.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, slipBean.getSlipNo());
			System.out.println("		@ 전표번호: "+slipBean.getSlipNo());
			pstmt.executeUpdate();
			dataSourceTransactionManager.commitTransaction();
			System.out.println("		@ 전표 삭제됨");
		} catch (Exception sqle) {
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt);
		}
	}
		
	
	@Override
	public void updateSlip(SlipBean slipBean) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append(" UPDATE SLIP SET ");
			query.append("REPORTING_EMP_CODE=?, APPROVAL_EMP_CODE=?, EXPENSE_REPORT=?, SLIP_TYPE=?, ");
			query.append("ACCOUNT_DIFFERENCE=?, APPROVAL_SEQ=?, SLIP_STATUS=?, APPROVAL_DATE=?, ");
			query.append("SLIP_SEQ=?, REPORTING_DATE=? ");
			query.append("WHERE SLIP_NO=? ");
			conn = dataSourceTransactionManager.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, slipBean.getReportingEmpCode());
			pstmt.setString(2, slipBean.getApprovalEmpCode());
			pstmt.setString(3, slipBean.getRequestName());
			//pstmt.setString(4, slipBean.getRequestCode());
			pstmt.setString(4, slipBean.getSlipType());
			pstmt.setString(5, slipBean.getAccountDifference());
			pstmt.setInt(6, slipBean.getApprovalSeq());
			pstmt.setString(7, slipBean.getSlipStatus());
			pstmt.setString(8, slipBean.getApprovalDate());
			pstmt.setInt(9, slipBean.getSlipSeq());
			pstmt.setString(10, slipBean.getReportingDate());
			pstmt.setString(11, slipBean.getSlipNo());
			pstmt.executeUpdate();
			System.out.println("		@ 전표번호: "+slipBean.getSlipNo());
			System.out.println("		@ 전표 수정됨");
		} catch (Exception sqle) {
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt);
		}
	}
	@Override
	public void insertSlip(SlipBean slipBean) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append(" INSERT INTO SLIP(SLIP_NO, ACCOUNT_PERIOD_NO, DEPT_CODE, REPORTING_EMP_CODE, APPROVAL_EMP_CODE, EXPENSE_REPORT, ");
			query.append(" SLIP_TYPE, ACCOUNT_DIFFERENCE, APPROVAL_SEQ,SLIP_STATUS, APPROVAL_DATE, ");
			query.append(" SLIP_SEQ, REPORTING_DATE ) VALUES(?,?,?,?,?,?,?,?,APPROVAL_NO_SEQ.NEXTVAL,?,?,SLIP_NO_SEQ.NEXTVAL,?) ");
			conn = dataSourceTransactionManager.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, slipBean.getSlipNo());			
			pstmt.setString(2, slipBean.getAccountPeriodNo());			
			pstmt.setString(3, slipBean.getDeptCode());			
			pstmt.setString(4, slipBean.getReportingEmpCode());
			pstmt.setString(5, slipBean.getApprovalEmpCode());
			pstmt.setString(6, slipBean.getRequestName());
			pstmt.setString(7, slipBean.getSlipType());
			pstmt.setString(8, slipBean.getAccountDifference());			
			pstmt.setString(9, slipBean.getSlipStatus());
			pstmt.setString(10, slipBean.getApprovalDate());
			pstmt.setString(11, slipBean.getReportingDate()); 
			
			pstmt.executeUpdate();
			System.out.println("		@ 전표번호: "+slipBean.getSlipNo());
			System.out.println("		@ 전표 추가됨");
		} catch (Exception sqle) {
				throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt);
		}
	}
	@Override
	public ArrayList<SlipBean> selectRangedSlipList(String fromDate, String toDate) {	//분개장 보기
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SlipBean> slipList = new ArrayList<>();
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT * FROM SLIP WHERE REPORTING_DATE BETWEEN ? AND ? ");
			query.append("AND SLIP_STATUS = '승인' ");
			query.append("ORDER BY REPORTING_DATE ");
			conn = dataSourceTransactionManager.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			System.out.println("		@ 조회 일자");
			System.out.println("		==========" + fromDate + "부터");
			System.out.println("		==========" + toDate + "까지");
			
			pstmt.setString(1, fromDate);
			pstmt.setString(2, toDate);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SlipBean slipBean = new SlipBean();
				slipBean.setSlipNo(rs.getString("SLIP_NO"));
				slipBean.setReportingEmpCode(rs.getString("REPORTING_EMP_CODE"));
				slipBean.setApprovalEmpCode(rs.getString("APPROVAL_EMP_CODE"));
				slipBean.setRequestName(rs.getString("EXPENSE_REPORT"));
				//slipBean.setRequestCode(rs.getString("REQUEST_CODE"));
				slipBean.setSlipType(rs.getString("SLIP_TYPE"));
				slipBean.setAccountDifference(rs.getString("ACCOUNT_DIFFERENCE"));
				slipBean.setApprovalSeq(rs.getInt("APPROVAL_SEQ"));
				slipBean.setSlipStatus(rs.getString("SLIP_STATUS"));
				slipBean.setApprovalDate(rs.getString("APPROVAL_DATE"));
				slipBean.setSlipSeq(rs.getInt("SLIP_SEQ"));
				slipBean.setReportingDate(rs.getString("REPORTING_DATE"));
				slipList.add(slipBean);
			}
		} catch (Exception sqle) {
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
		return slipList;
	}
	@Override
	public ArrayList<SlipBean> selectDisApprovalSlipList() {	//미승인,승인 전표 조회
		// TODO Auto-generated method stub
		 Connection conn = null;
	     PreparedStatement pstmt = null;
	     ResultSet rs = null;
	     ArrayList<SlipBean> disApprovalSlipList = new ArrayList<>();
	     try{
	    	 StringBuffer query = new StringBuffer();
	         query.append(" SELECT * FROM SLIP WHERE SLIP_STATUS='미결' OR APPROVAL_DATE IS NULL ORDER BY REPORTING_DATE ");
	         conn = dataSourceTransactionManager.getConnection();
	         pstmt=conn.prepareStatement(query.toString());
	         rs = pstmt.executeQuery();
	         while(rs.next()) {
	        	 SlipBean slipBean = new SlipBean();
	                slipBean.setSlipNo(rs.getString("SLIP_NO"));
					slipBean.setReportingDate(rs.getString("REPORTING_DATE"));
					slipBean.setSlipSeq(rs.getInt("SLIP_SEQ"));
					//slipBean.setRequestCode(rs.getString("REQUEST_CODE"));
					slipBean.setRequestName(rs.getString("EXPENSE_REPORT"));
					slipBean.setSlipType(rs.getString("SLIP_TYPE"));
					slipBean.setSlipStatus(rs.getString("SLIP_STATUS"));
					slipBean.setApprovalSeq(rs.getInt("APPROVAL_SEQ"));
					slipBean.setAccountDifference(rs.getString("ACCOUNT_DIFFERENCE"));
					slipBean.setReportingEmpCode(rs.getString("REPORTING_EMP_CODE"));
					slipBean.setApprovalEmpCode(rs.getString("APPROVAL_EMP_CODE"));
					slipBean.setApprovalDate(rs.getString("APPROVAL_DATE"));
					disApprovalSlipList.add(slipBean);
					System.out.println("		@ 승인/미승인 전표 조회");
	         }
	     }catch(Exception sqle){
	    	 throw new DataAccessException(sqle.getMessage());
	     }finally{
	    	 dataSourceTransactionManager.close(pstmt, rs);
	     }
		return disApprovalSlipList;
	}

}
