package com.sh.account.statement.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sh.account.statement.to.EarlyIncomeStatementBean;
import com.sh.common.db.DataSourceTransactionManager;
import com.sh.common.exception.DataAccessException;

import oracle.jdbc.internal.OracleTypes;



public class EarlyIncomeStatementDAOImpl implements EarlyIncomeStatementDAO{
	protected final Log logger = LogFactory.getLog(this.getClass());
	 private DataSourceTransactionManager dataSourceTransactionManager;
		
	 public void setDataSourceTransactionManager(DataSourceTransactionManager dataSourceTransactionManager) {
		this.dataSourceTransactionManager = dataSourceTransactionManager;
	 }	
	@Override
	public ArrayList<EarlyIncomeStatementBean> findEarlyIncomeStatementList() {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" earlyIncomeStatementDAOImpl : findEarlyIncomeStatementList 시작 "); }
		Connection conn = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		ArrayList<EarlyIncomeStatementBean> earlyIncomeStatementList = new ArrayList<>();
		try {
			conn = dataSourceTransactionManager.getConnection();
			cstmt = conn.prepareCall("{call EARLY_INCOME_STATEMENT(?,?,?)}");
			System.out.println("		@ 프로시저 호출");
			cstmt.registerOutParameter(1, OracleTypes.NUMBER);  /*에러코드*/
			cstmt.registerOutParameter(2, OracleTypes.VARCHAR);  /*에러메세지*/
			cstmt.registerOutParameter(3, OracleTypes.CURSOR);   /*프로시저에서 result였던것! 즉, 재무상태표 결과 */
			cstmt.execute();
			rs = (ResultSet) cstmt.getObject(3);    /*커서 매개변수에 대해서만 CallableStatement.getObject() 또는 CallableStatement.getString()을 호출할 수 있음 */

			while (rs.next()) {
				EarlyIncomeStatementBean earlyIncomeStatementBean = new EarlyIncomeStatementBean();
				earlyIncomeStatementBean.setAccountName(rs.getString("ACCOUNT_NAME"));
				earlyIncomeStatementBean.setTotalPrice(rs.getString("TOTAL_PRICE"));
				earlyIncomeStatementList.add(earlyIncomeStatementBean);
			}
			
			//System.out.println("		@ 에러코드: " + cstmt.getInt(1));
			//System.out.println("		@ 에러메시지: " + cstmt.getString(2));
		} catch (Exception sqle) {
			logger.fatal(sqle.getMessage());
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(cstmt, rs);
		}
		if(logger.isDebugEnabled()){ logger.debug(" earlyIncomeStatementDAOImpl : findEarlyIncomeStatementList 종료 "); }
		return earlyIncomeStatementList; 
	}

	public ArrayList<EarlyIncomeStatementBean> findEarlyIncomeList() {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" earlyIncomeStatementDAOImpl : findEarlyIncomeList 시작 "); }
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<EarlyIncomeStatementBean> earlyIncomeList = new ArrayList<>();
		try {
			StringBuffer query = new StringBuffer();
			query.append(" SELECT ACCOUNT_INNER_CODE, ACCOUNT_NAME, (NVL(LEFT_DEBTOR_PRICE,0)+NVL(RIGHT_CREDITS_PRICE,0)) AS TOTAL_PRICE ");
			query.append(" FROM EARLY_STATEMENTS WHERE STATEMENTS_DIVISION= '손익' ORDER BY ACCOUNT_INNER_CODE ");
			conn = dataSourceTransactionManager.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EarlyIncomeStatementBean earlyIncomeStatementBean = new EarlyIncomeStatementBean();
				earlyIncomeStatementBean.setAccountInnerCode(rs.getString("ACCOUNT_INNER_CODE"));
				earlyIncomeStatementBean.setAccountName(rs.getString("ACCOUNT_NAME"));
				earlyIncomeStatementBean.setTotalPrice(rs.getString("TOTAL_PRICE"));
				earlyIncomeList.add(earlyIncomeStatementBean);
				//System.out.println("		@ 전기 자산 조회됨");
				//System.out.println("		@ "+assetList.get(0).getGroupCode());
			}
			if(logger.isDebugEnabled()){ logger.debug(" earlyIncomeStatementDAOImpl : findEarlyIncomeList 종료 "); }
			return earlyIncomeList;
		} catch (Exception sqle) {
			logger.fatal(sqle.getMessage());
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}
}
