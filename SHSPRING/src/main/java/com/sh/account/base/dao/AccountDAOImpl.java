package com.sh.account.base.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.sh.account.base.dao.AccountDAO;
import com.sh.account.base.dao.AccountDAOImpl;
import com.sh.account.base.to.AccountBean;
import com.sh.common.db.DataSourceTransactionManager;
import com.sh.common.exception.DataAccessException;

public class AccountDAOImpl implements AccountDAO{
	private DataSourceTransactionManager dataSourceTransactionManager;
	
	public void setDataSourceTransactionManager(DataSourceTransactionManager dataSourceTransactionManager) {
		this.dataSourceTransactionManager = dataSourceTransactionManager;
	}

	@Override
	public ArrayList<AccountBean> selectAccountList() {
		// TODO Auto-generated method stub
		 	Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        ArrayList<AccountBean> accountList = new ArrayList<>();
	        try {
	            StringBuffer query = new StringBuffer();
	            
	            query.append("SELECT * FROM ACCOUNT ");
	            query.append("ORDER BY ACCOUNT_INNER_CODE");

	            con = dataSourceTransactionManager.getConnection();
	            pstmt = con.prepareStatement(query.toString());
	            rs = pstmt.executeQuery();
	            while(rs.next()){
	            	AccountBean accountBean=new AccountBean();
	            	accountBean.setAccountCode(rs.getString("ACCOUNT_INNER_CODE"));
	            	accountBean.setSuperAccountCode(rs.getString("PARENT_ACCOUNT_INNER_CODE"));
	                accountBean.setAccountName(rs.getString("ACCOUNT_NAME"));
	                accountBean.setAccountUseCheck(rs.getString("ACCOUNT_USE_CHECK"));
	                accountBean.setLoaded("true");
	                accountBean.setExpanded("true");
	                accountList.add(accountBean);
	            }
	            return accountList;
	        }catch(Exception sqle){
	            throw new DataAccessException(sqle.getMessage());
	        }finally{
	        	 dataSourceTransactionManager.close(pstmt, rs);
	        }
	}
	@Override
	public ArrayList<AccountBean> findSelectAccountList(String code) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<AccountBean> accountList = new ArrayList<>();
		try {
			StringBuffer query = new StringBuffer();
			
			query.append("SELECT * ");
			query.append("FROM ACCOUNT WHERE ACCOUNT_INNER_CODE= ?  ");
			
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			while(rs.next()){
				AccountBean accountBean=new AccountBean();
				accountBean.setAccountCode(rs.getString("ACCOUNT_INNER_CODE"));
				accountBean.setSuperAccountCode(rs.getString("PARENT_ACCOUNT_INNER_CODE"));
				accountBean.setAccountName(rs.getString("ACCOUNT_NAME"));
				accountBean.setAccountUseCheck(rs.getString("ACCOUNT_USE_CHECK"));
				accountBean.setLoaded("true");
				accountBean.setExpanded("true");
				accountList.add(accountBean);
			}
			return accountList;
		}catch(Exception sqle){
			throw new DataAccessException(sqle.getMessage());
		}finally{
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}
	@Override
	public ArrayList<AccountBean> findAccountList() {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<AccountBean> accountList = new ArrayList<>();
		try {
			StringBuffer query = new StringBuffer();
			
			query.append("SELECT * ");
			query.append("FROM ACCOUNT  ");
			query.append("ORDER BY ACCOUNT_INNER_CODE");
			
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while(rs.next()){
				AccountBean accountBean=new AccountBean();
				accountBean.setAccountCode(rs.getString("ACCOUNT_INNER_CODE"));
				accountBean.setSuperAccountCode(rs.getString("PARENT_ACCOUNT_INNER_CODE"));
				accountBean.setAccountName(rs.getString("ACCOUNT_NAME"));
				accountBean.setAccountUseCheck(rs.getString("ACCOUNT_USE_CHECK"));
				accountBean.setLoaded("true");
				accountBean.setExpanded("true");
				accountList.add(accountBean);
			}
			return accountList;
		}catch(Exception sqle){
			throw new DataAccessException(sqle.getMessage());
		}finally{
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}

	
	
	   @Override
	   public ArrayList<AccountBean> findParentAccountList() {
	      // TODO Auto-generated method stub
	          Connection con = null;
	           PreparedStatement pstmt = null;
	           ResultSet rs = null;
	           ArrayList<AccountBean> accountList = new ArrayList<>();
	           try {
	               StringBuffer query = new StringBuffer();
	               
	            query.append("SELECT * ");
	            query.append("FROM ACCOUNT  ");
	            query.append("WHERE ACCOUNT_INNER_CODE LIKE '%-%'   ");
	            query.append("AND PARENT_ACCOUNT_INNER_CODE IS NOT NULL   ");
	            query.append("ORDER BY ACCOUNT_INNER_CODE" );
	               
	               con = dataSourceTransactionManager.getConnection();
	               pstmt = con.prepareStatement(query.toString());
	               rs = pstmt.executeQuery();
	               while(rs.next()){
	                  AccountBean accountBean=new AccountBean();
	                  accountBean.setAccountCode(rs.getString("ACCOUNT_INNER_CODE"));
	                  accountBean.setSuperAccountCode(rs.getString("PARENT_ACCOUNT_INNER_CODE"));
	                   accountBean.setAccountName(rs.getString("ACCOUNT_NAME"));
	                   accountBean.setAccountUseCheck(rs.getString("ACCOUNT_USE_CHECK"));
	                   accountBean.setLoaded("true");
	                   accountBean.setExpanded("true");
	                   accountList.add(accountBean);
	               }
	               return accountList;
	           }catch(Exception sqle){
	               throw new DataAccessException(sqle.getMessage());
	           }finally{
	               dataSourceTransactionManager.close(pstmt, rs);
	           }
	   }
	   
	   @Override
	   public ArrayList<AccountBean> editAccountList(AccountBean bean) {
		   // TODO Auto-generated method stub
		   Connection con = null;
		   PreparedStatement pstmt = null;
		   ResultSet rs = null;
		   ArrayList<AccountBean> accountList = new ArrayList<>();
		   try {
			   StringBuffer query = new StringBuffer();
	            query.append("UPDATE ACCOUNT SET ACCOUNT_NAME = ?, ACCOUNT_CHARACTER=?, ACCOUNT_DESCRIPTION=? WHERE ACCOUNT_INNER_CODE = ?");
	            con = dataSourceTransactionManager.getConnection();
	            pstmt = con.prepareStatement(query.toString());
	            pstmt.setString(1, bean.getAccountName());		 
	            pstmt.setString(2, bean.getAccountCharacter());
	            pstmt.setString(3, bean.getAccountDescription());
	            pstmt.setString(4, bean.getAccountCode());
	            pstmt.executeUpdate();

		   }catch(Exception sqle){
			   throw new DataAccessException(sqle.getMessage());
		   }finally{
			   dataSourceTransactionManager.close(pstmt, rs);
		   }
		   return accountList;
	   }
	   
	   
	   
	   @Override
	   public ArrayList<AccountBean> findDetailAccountList(String code) {
	      // TODO Auto-generated method stub
	          Connection con = null;
	           PreparedStatement pstmt = null;
	           ResultSet rs = null;
	           ArrayList<AccountBean> accountList = new ArrayList<>();
	           try {
	        	   StringBuffer query = new StringBuffer();
	            query.append("SELECT * FROM ACCOUNT WHERE PARENT_ACCOUNT_INNER_CODE=? ");
	            query.append("AND ACCOUNT_INNER_CODE NOT LIKE '%-%'  ");
	            query.append("AND PARENT_ACCOUNT_INNER_CODE IS NOT NULL ");         
	            query.append("ORDER BY ACCOUNT_INNER_CODE");
	               
	               con = dataSourceTransactionManager.getConnection();
	               pstmt = con.prepareStatement(query.toString());
	               pstmt.setString(1,code);
	               rs = pstmt.executeQuery();
	               
	               while(rs.next()){
	                  AccountBean accountBean=new AccountBean();
	                  accountBean.setAccountCode(rs.getString("ACCOUNT_INNER_CODE"));
	                  accountBean.setSuperAccountCode(rs.getString("PARENT_ACCOUNT_INNER_CODE"));
	                   accountBean.setAccountName(rs.getString("ACCOUNT_NAME"));
	                   accountBean.setAccountUseCheck(rs.getString("ACCOUNT_USE_CHECK"));
	                  // accountBean.setLevel(rs.getInt("LEVEL"));
	                  // accountBean.setLeaf(rs.getString("LEAF"));
	                   accountBean.setLoaded("true");
	                   accountBean.setExpanded("true");
	                   accountList.add(accountBean);
	               }
	               return accountList;
	           }catch(Exception sqle){
	               throw new DataAccessException(sqle.getMessage());
	           }finally{
	               dataSourceTransactionManager.close(pstmt, rs);
	           }
	   }
}
