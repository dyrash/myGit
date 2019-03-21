package com.sh.account.base.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.sh.account.base.dao.AccountDetailDAO;
import com.sh.account.base.dao.AccountDetailDAOImpl;
import com.sh.account.base.to.AccountDetailBean;
import com.sh.common.db.DataSourceTransactionManager;
import com.sh.common.exception.DataAccessException;

public class AccountDetailDAOImpl implements AccountDetailDAO{
	
	private DataSourceTransactionManager dataSourceTransactionManager;
	public void setDataSourceTransactionManager(DataSourceTransactionManager dataSourceTransactionManager) {
		this.dataSourceTransactionManager = dataSourceTransactionManager;
	}
	
	@Override
	public ArrayList<AccountDetailBean> findControlItemList() {
		// TODO Auto-generated method stub
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<AccountDetailBean> controlItemList = new ArrayList<>();
        
        try{
            StringBuffer query = new StringBuffer();
            query.append("SELECT * FROM ACC_DETAIL");
            con = dataSourceTransactionManager.getConnection();
            pstmt = con.prepareStatement(query.toString());
            rs = pstmt.executeQuery();
            while(rs.next()){
            	AccountDetailBean controlItemBean = new AccountDetailBean();
            	controlItemBean.setAccountControlCode(rs.getString("ACCOUNT_CONTROL_CODE"));
            	controlItemBean.setAccountControlName(rs.getString("ACCOUNT_CONTROL_NAME"));
            	controlItemBean.setAccountType(rs.getString("ACCOUNT_TYPE"));            	
                controlItemList.add(controlItemBean);
            }
            return controlItemList;
        }catch(Exception sqle){
            throw new DataAccessException(sqle.getMessage());
         }finally{
        	 dataSourceTransactionManager.close(pstmt, rs);
         }
	}
	

}
