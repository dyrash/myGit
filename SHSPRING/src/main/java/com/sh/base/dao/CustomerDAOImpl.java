package com.sh.base.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

import com.sh.base.dao.CustomerDAOImpl;
import com.sh.base.to.CustomerBean;
import com.sh.common.db.DataSourceTransactionManager;
import com.sh.common.exception.DataAccessException;

public class CustomerDAOImpl implements CustomerDAO{
	private DataSourceTransactionManager dataSourceTransactionManager;
	public void setDataSourceTransactionManager(DataSourceTransactionManager dataSourceTransactionManager) {
		this.dataSourceTransactionManager = dataSourceTransactionManager;
	}
	@Override
	public ArrayList<CustomerBean> selectCustomerCodeList(String code) {
		// TODO Auto-generated method stub
		
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
			return customerList;
		} catch (Exception sqle) {  
			// TODO Auto-generated catch block
			System.out.println("		@ 코드 조회 에러 발생");
			 throw new DataAccessException(sqle.getMessage());
		}finally{
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}	
}
