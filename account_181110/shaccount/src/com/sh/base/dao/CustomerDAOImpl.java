package com.sh.base.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sh.base.dao.CustomerDAOImpl;
import com.sh.base.to.CustomerBean;
import com.sh.common.db.DataSourceTransactionManager;
import com.sh.common.exception.DataAccessException;

public class CustomerDAOImpl implements CustomerDAO{

	protected final Log logger = LogFactory.getLog(this.getClass());
	private DataSourceTransactionManager dataSourceTransactionManager = DataSourceTransactionManager.getInstance();
	
	private static CustomerDAO instance;
	private CustomerDAOImpl(){};
	public static CustomerDAO getinstance() {
		// TODO Auto-generated method stub
		if(instance ==null){
			instance = new CustomerDAOImpl();
			System.out.println("		@ CustomerDAOImpl에 접근");
		}
		return instance;
	}

	@Override
	public ArrayList<CustomerBean> selectCustomerCodeList(String code) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" CustomerDAOImpl : selectCustomerCodeList 시작 "); }
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CustomerBean> customerList = new ArrayList<CustomerBean>();
		//System.out.println("		@ selectCustomerCodeList에서 받은 코드: '"+code+"'");
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT * FROM CUSTOMER WHERE WORKPLACE_CODE= ?");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			while(rs.next()){
				 CustomerBean customerBean = new CustomerBean();
				 customerBean.setCustomerCode(rs.getString("CUSTOMER_CODE"));
				 customerBean.setCustomerName(rs.getString("CUSTOMER_NAME"));
	             
				 customerList.add(customerBean);
	            
			}
			//System.out.println("		@ 코드명: '"+customerList.get(0).getCustomerName()+"' 조회됨");
			if(logger.isDebugEnabled()){ logger.debug(" CustomerDAOImpl : selectCustomerCodeList 종료 "); }
			return customerList;
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
