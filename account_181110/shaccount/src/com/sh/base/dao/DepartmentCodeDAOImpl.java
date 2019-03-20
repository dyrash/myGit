package com.sh.base.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sh.base.dao.DepartmentCodeDAOImpl;
import com.sh.base.to.DepartmentCodeBean;
import com.sh.common.db.DataSourceTransactionManager;
import com.sh.common.exception.DataAccessException;

public class DepartmentCodeDAOImpl implements DepartmentCodeDAO{

	protected final Log logger = LogFactory.getLog(this.getClass());
	private DataSourceTransactionManager dataSourceTransactionManager = DataSourceTransactionManager.getInstance();
	
	private static DepartmentCodeDAO instance;
	private DepartmentCodeDAOImpl(){};
	public static DepartmentCodeDAO getinstance() {
		// TODO Auto-generated method stub
		if(instance ==null){
			instance = new DepartmentCodeDAOImpl();
			System.out.println("		@ DepartmentCodeDAOImpl에 접근");
		}
		return instance;
	}
	
	@Override
	public ArrayList<DepartmentCodeBean> selectDeptCodeList(String code) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" DepartmentCodeDAOImpl : selectDeptCodeList 시작 "); }
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<DepartmentCodeBean> departmentCodeList = new ArrayList<DepartmentCodeBean>();
		System.out.println("		@ selectDeptCodeList에서 받은 코드: '"+code+"'");
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT * FROM CODE_DETAIL WHERE DIVISION_CODE_NO=?");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			while(rs.next()){
				DepartmentCodeBean departmentCodeBean = new DepartmentCodeBean();
				departmentCodeBean.setDetailCode(rs.getString("DETAIL_CODE"));
				departmentCodeBean.setDetailCodeName(rs.getString("DETAIL_CODE_NAME"));
	             
				departmentCodeList.add(departmentCodeBean);
	            
			}
			if(logger.isDebugEnabled()){ logger.debug(" DepartmentCodeDAOImpl : selectDeptCodeList 종료 "); }
			return departmentCodeList;
		} catch (Exception sqle) {  
			// TODO Auto-generated catch block
			System.out.println("		@ 코드 조회 에러 발생");
			logger.fatal(sqle.getMessage());
			 throw new DataAccessException(sqle.getMessage());
		}finally{
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}
}
