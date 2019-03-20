package com.sh.account.statement.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sh.account.statement.dao.TrialBalanceDAO;
import com.sh.account.statement.dao.TrialBalanceDAOImpl;
import com.sh.account.statement.to.TrialBalanceBean;
import com.sh.base.to.TrialBalanceResultBean;
import com.sh.common.db.DataSourceTransactionManager;
import com.sh.common.exception.DataAccessException;

import oracle.jdbc.internal.OracleTypes;



public class TrialBalanceDAOImpl implements TrialBalanceDAO{
	protected final Log logger = LogFactory.getLog(this.getClass());
	private DataSourceTransactionManager dataSourceTransactionManager = DataSourceTransactionManager.getInstance();
	
	private static TrialBalanceDAO instacne;
	private TrialBalanceDAOImpl(){}
	public static TrialBalanceDAO getInstance() {
		// TODO Auto-generated method stub
		if(instacne == null){
			instacne = new TrialBalanceDAOImpl();
			System.out.println("		@ TrialBalanceDAOImpl에 접근");
		}
		return instacne;
		
	}
	@Override
	public HashMap<String, Object> findTrialBalanceList(String fromDate, String toDate) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" TrialBalanceDAOImpl : findTrialBalanceList 시작 "); }
		Connection conn = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		HashMap<String, Object> resultList = new HashMap<String, Object>(); 
		ArrayList<TrialBalanceBean> trialBalanceList = new ArrayList<>();
		System.out.println("		@ "+fromDate+" 부터 "+toDate+" 까지 ");
		
		try {
			conn = dataSourceTransactionManager.getConnection();
			cstmt = conn.prepareCall("{call P_Total_Balance_OPEN(?,?,?,?)}");
			System.out.println("		@ 프로시저 호출");
			//cstmt.setString(1, fromDate); 
			cstmt.setString(1, toDate);
			cstmt.registerOutParameter(2, OracleTypes.NUMBER);  /*에러코드*/
			cstmt.registerOutParameter(3, OracleTypes.VARCHAR);  /*에러메세지*/
			cstmt.registerOutParameter(4, OracleTypes.CURSOR);   /*프로시저에서 result였던것! 즉, 합계잔액시산표 결과 */
			cstmt.execute();
			rs = (ResultSet) cstmt.getObject(4);    /*커서 매개변수에 대해서만 CallableStatement.getObject 또는 CallableStatement.getString을 호출할 수 있음 */

			while (rs.next()) {
				TrialBalanceBean trialBalanceBean = new TrialBalanceBean();
				trialBalanceBean.setLeftDebtorBalance(rs.getString("LEFT_DEBTOR_BALANCE"));
				trialBalanceBean.setLeftDebtorSum(rs.getString("LEFT_DEBTOR_SUM")); 
				trialBalanceBean.setAccountName(rs.getString("ACCOUNT_NAME"));
				trialBalanceBean.setRightCreditSum(rs.getString("RIGHT_CREDITS_SUM"));
				trialBalanceBean.setRightCreditBalance(rs.getString("RIGHT_CREDITS_BALANCE"));
				trialBalanceList.add(trialBalanceBean);
				//System.out.println(trialBalanceList.get(0).getAccountName());
			}
			
			//System.out.println("		@ 에러코드: " + cstmt.getInt(2));
			//System.out.println("		@ 에러메시지: " + cstmt.getString(3));

			TrialBalanceResultBean resultBean = new TrialBalanceResultBean(); 
			resultBean.setErrorCode(cstmt.getInt(2)); 
			resultBean.setErrorMsg(cstmt.getString(3));
			resultList.put("trialBalanceList", trialBalanceList);  
			resultList.put("result", resultBean);
			
			
		} catch (Exception sqle) {
			logger.fatal(sqle.getMessage());
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(cstmt, rs);
		}
		if(logger.isDebugEnabled()){ logger.debug(" TrialBalanceDAOImpl : findTrialBalanceList 종료 "); }
		return resultList; 
	}

}
