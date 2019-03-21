package com.sh.account.statement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sh.account.statement.to.EarlyStatementBean;
import com.sh.common.db.DataSourceTransactionManager;
import com.sh.common.exception.DataAccessException;

public class EarlyAssetDAOImpl implements EarlyAssetDAO{
	protected final Log logger = LogFactory.getLog(this.getClass());
	 private DataSourceTransactionManager dataSourceTransactionManager;
		
	 public void setDataSourceTransactionManager(DataSourceTransactionManager dataSourceTransactionManager) {
		this.dataSourceTransactionManager = dataSourceTransactionManager;
	 }	
	

	public ArrayList<EarlyStatementBean> findEarlyAssets() {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" EarlyAssetDAOImpl : findEarlyAssets 시작 "); }
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<EarlyStatementBean> assetList = new ArrayList<>();
		try {
			StringBuffer query = new StringBuffer();
			query.append(" SELECT GROUP_CODE, ACCOUNT_INNER_CODE, ACCOUNT_NAME, ");
			query.append(" CASE WHEN ACCOUNT_NAME IN ('대손충당금','감가상각누계액') THEN (PRICE*(-1)) ");
			query.append(" ELSE PRICE END AS PRICE  FROM EARLY_ASSETS ");
			conn = dataSourceTransactionManager.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EarlyStatementBean earlyStatementBean = new EarlyStatementBean();
				earlyStatementBean.setGroupCode(rs.getString("GROUP_CODE"));
				earlyStatementBean.setAccountInnerCode(rs.getString("ACCOUNT_INNER_CODE"));
				earlyStatementBean.setAccountName(rs.getString("ACCOUNT_NAME"));
				earlyStatementBean.setPrice(rs.getString("PRICE"));
				assetList.add(earlyStatementBean);
				//System.out.println("		@ 전기 자산 조회됨");
			}
			if(logger.isDebugEnabled()){ logger.debug(" EarlyAssetDAOImpl : findEarlyAssets 종료 "); }
			return assetList;
		} catch (Exception sqle) {
			logger.fatal(sqle.getMessage());
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}
	
	
	public ArrayList<EarlyStatementBean> findEarlyLiabilitiseNequity() {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" EarlyAssetDAOImpl : findEarlyLiabilitiseNequity 시작 "); }
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<EarlyStatementBean> liabNequiList = new ArrayList<>();
		try {
			StringBuffer query = new StringBuffer();
			query.append(" SELECT es.STATEMENTS_DIVISION, ES.ACCOUNT_INNER_CODE, es.ACCOUNT_NAME, ac.GROUP_CODE, ");
			query.append(" SUM(NVL(es.RIGHT_CREDITS_PRICE,0)-NVL(es.LEFT_DEBTOR_PRICE,0))  AS TOTAL_CREDIT_PRICE ");
			query.append(" FROM EARLY_STATEMENTS es, ACCOUNT ac WHERE  ac.ACCOUNT_INNER_CODE=es.ACCOUNT_INNER_CODE ");
			query.append(" AND es.STATEMENTS_DIVISION='재무' AND ac.GROUP_CODE IN('3.유동부채','4.비유동부채','5.자본') ");
			query.append(" AND ac.ACCOUNT_NAME NOT IN ('감가상각누계액','대손충당금') GROUP BY  es.STATEMENTS_DIVISION, ES.ACCOUNT_INNER_CODE, es.ACCOUNT_NAME ");
			query.append(" , ac.GROUP_CODE ORDER BY es.ACCOUNT_INNER_CODE ");
			conn = dataSourceTransactionManager.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EarlyStatementBean earlyStatementBean = new EarlyStatementBean();
				earlyStatementBean.setGroupCode(rs.getString("GROUP_CODE"));
				earlyStatementBean.setAccountInnerCode(rs.getString("ACCOUNT_INNER_CODE"));
				earlyStatementBean.setAccountName(rs.getString("ACCOUNT_NAME"));
				earlyStatementBean.setTotalCreditPrice(rs.getString("TOTAL_CREDIT_PRICE"));
				liabNequiList.add(earlyStatementBean);
				//System.out.println("		@ 전기 자산 조회됨");
			}
			if(logger.isDebugEnabled()){ logger.debug(" EarlyAssetDAOImpl : findEarlyLiabilitiseNequity 종료 "); }
			return liabNequiList;
		} catch (Exception sqle) {
			logger.fatal(sqle.getMessage());
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}
	
	public ArrayList<EarlyStatementBean> findEarlyFinancialPosition() {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" EarlyAssetDAOImpl : findEarlyFinancialPosition 시작 "); }
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<EarlyStatementBean> earlyFinacialList = new ArrayList<>();
		try {
			StringBuffer query = new StringBuffer();
			query.append(" SELECT NVL(ac.GROUP_CODE,'자산합계') AS GROUPING, SUM(NVL(es.LEFT_DEBTOR_PRICE,0)-NVL(es.RIGHT_CREDITS_PRICE,0)) AS TOT_PRICE ");
			query.append(" FROM EARLY_STATEMENTS es, ACCOUNT ac WHERE ac.ACCOUNT_INNER_CODE=es.ACCOUNT_INNER_CODE ");
			query.append(" AND ac.GROUP_CODE IN('1.유동자산','2.비유동자산') GROUP BY ROLLUP (ac.GROUP_CODE) ");
			query.append(" UNION ALL SELECT NVL(ac.GROUP_CODE,'부채합계'), SUM(NVL(es.RIGHT_CREDITS_PRICE,0)-NVL(es.LEFT_DEBTOR_PRICE,0)) AS TOTAL_PRICE ");
			query.append(" FROM EARLY_STATEMENTS es, ACCOUNT ac WHERE ac.ACCOUNT_INNER_CODE=es.ACCOUNT_INNER_CODE ");
			query.append(" AND ac.GROUP_CODE IN('3.유동부채','4.비유동부채') AND es.ACCOUNT_NAME NOT IN ('감가상각누계액','대손충당금') GROUP BY ROLLUP (ac.GROUP_CODE) ");
			query.append(" UNION ALL SELECT NVL(ac.GROUP_CODE,'자본합계'), SUM(NVL(es.RIGHT_CREDITS_PRICE,0)-NVL(es.LEFT_DEBTOR_PRICE,0)) AS TOTAL_PRICE ");
			query.append(" FROM EARLY_STATEMENTS es, ACCOUNT ac WHERE ac.ACCOUNT_INNER_CODE=es.ACCOUNT_INNER_CODE ");
			query.append(" AND ac.GROUP_CODE='5.자본' GROUP BY ROLLUP (ac.GROUP_CODE) ");
			conn = dataSourceTransactionManager.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EarlyStatementBean earlyStatementBean = new EarlyStatementBean();
				earlyStatementBean.setGroupName(rs.getString("GROUPING"));
				earlyStatementBean.setTotalPrice(rs.getString("TOT_PRICE"));
				earlyFinacialList.add(earlyStatementBean);
				//System.out.println("		@ 전기 자산 조회됨");
			}
			if(logger.isDebugEnabled()){ logger.debug(" EarlyAssetDAOImpl : findEarlyFinancialPosition 종료 "); }
			return earlyFinacialList;
		} catch (Exception sqle) {
			logger.fatal(sqle.getMessage());
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}
}
