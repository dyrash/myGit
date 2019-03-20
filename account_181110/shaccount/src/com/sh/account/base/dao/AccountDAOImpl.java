package com.sh.account.base.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sh.account.base.dao.AccountDAO;
import com.sh.account.base.dao.AccountDAOImpl;
import com.sh.account.base.to.AccountBean;
import com.sh.common.db.DataSourceTransactionManager;
import com.sh.common.exception.DataAccessException;

public class AccountDAOImpl implements AccountDAO{
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	private DataSourceTransactionManager dataSourceTransactionManager=DataSourceTransactionManager.getInstance();

	
	private static AccountDAO instance;
	private AccountDAOImpl(){}
	public static AccountDAO getInstance() {
		// TODO Auto-generated method stub
		if(instance == null){
			instance = new AccountDAOImpl();
		}
		return instance;
	}

	@Override
	public ArrayList<AccountBean> selectAccountList() {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" AccountDAOImpl : selectAccountList 시작 "); }
		 	Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        ArrayList<AccountBean> accountList = new ArrayList<>();
	        try {
	            StringBuffer query = new StringBuffer();
	            
	            query.append("SELECT * ");//LEVEL, ");
	            //query.append("(CASE WHEN CONNECT_BY_ISLEAF = 0 OR LEVEL = 1 THEN 'FALSE' ELSE 'TRUE' END) LEAF ");
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
	                //accountBean.setLevel(rs.getInt("LEVEL"));
	                //accountBean.setLeaf(rs.getString("LEAF"));
	                accountBean.setLoaded("true");
	                accountBean.setExpanded("true");
	                accountList.add(accountBean);
	            }
	            if(logger.isDebugEnabled()){ logger.debug(" AccountDAOImpl : selectAccountList 종료 "); }
	            return accountList;
	        }catch(Exception sqle){
	        	logger.fatal(sqle.getMessage());
	            throw new DataAccessException(sqle.getMessage());
	        }finally{
	        	 dataSourceTransactionManager.close(pstmt, rs);
	        }
	}
	@Override
	public ArrayList<AccountBean> findSelectAccountList(String code) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" AccountDAOImpl : selectAccountList 시작 "); }
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<AccountBean> accountList = new ArrayList<>();
		//System.out.println(code);
		try {
			StringBuffer query = new StringBuffer();
			
			query.append("SELECT * ");
			query.append("FROM ACCOUNT WHERE ACCOUNT_INNER_CODE= ?  ");
			//query.append("ORDER BY ACCOUNT_INNER_CODE");
			
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
				//accountBean.setLevel(rs.getInt("LEVEL"));
				//accountBean.setLeaf(rs.getString("LEAF"));
				accountBean.setLoaded("true");
				accountBean.setExpanded("true");
				accountList.add(accountBean);
			}
			if(logger.isDebugEnabled()){ logger.debug(" AccountDAOImpl : selectAccountList 종료 "); }
			return accountList;
		}catch(Exception sqle){
			logger.fatal(sqle.getMessage());
			throw new DataAccessException(sqle.getMessage());
		}finally{
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}
	@Override
	public ArrayList<AccountBean> findAccountList() {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" AccountDAOImpl : findAccountList 시작 "); }
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<AccountBean> accountList = new ArrayList<>();
		try {
			StringBuffer query = new StringBuffer();
			
			query.append("SELECT * ");//LEVEL, ");
			//query.append("(CASE WHEN CONNECT_BY_ISLEAF = 0 OR LEVEL = 1 THEN 'FALSE' ELSE 'TRUE' END) LEAF ");
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
				//accountBean.setLevel(rs.getInt("LEVEL"));
				//accountBean.setLeaf(rs.getString("LEAF"));
				accountBean.setLoaded("true");
				accountBean.setExpanded("true");
				accountList.add(accountBean);
			}
			if(logger.isDebugEnabled()){ logger.debug(" AccountDAOImpl : findAccountList 종료 "); }
			return accountList;
		}catch(Exception sqle){
			logger.fatal(sqle.getMessage());
			throw new DataAccessException(sqle.getMessage());
		}finally{
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}

	
	
	
	   @Override
	   public ArrayList<AccountBean> findParentAccountList() {
	      // TODO Auto-generated method stub
	      if(logger.isDebugEnabled()){ logger.debug(" AccountDAOImpl : findParentAccountList 시작 "); }
	          Connection con = null;
	           PreparedStatement pstmt = null;
	           ResultSet rs = null;
	           ArrayList<AccountBean> accountList = new ArrayList<>();
	           try {
	               StringBuffer query = new StringBuffer();
	               
	            query.append("SELECT * ");//LEVEL, ");
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
	                  // accountBean.setLevel(rs.getInt("LEVEL"));
	                  // accountBean.setLeaf(rs.getString("LEAF"));
	                   accountBean.setLoaded("true");
	                   accountBean.setExpanded("true");
	                   accountList.add(accountBean);
	               }
	               if(logger.isDebugEnabled()){ logger.debug(" AccountDAOImpl : findParentAccountList 종료 "); }
	               return accountList;
	           }catch(Exception sqle){
	              logger.fatal(sqle.getMessage());
	               throw new DataAccessException(sqle.getMessage());
	           }finally{
	               dataSourceTransactionManager.close(pstmt, rs);
	           }
	   }
	   
	   @Override
	   public ArrayList<AccountBean> editAccountList(AccountBean bean) {
		   // TODO Auto-generated method stub
		   if(logger.isDebugEnabled()){ logger.debug(" AccountDAOImpl : editAccountList 시작 "); }
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
			   logger.fatal(sqle.getMessage());
			   throw new DataAccessException(sqle.getMessage());
		   }finally{
			   dataSourceTransactionManager.close(pstmt, rs);
		   }
		   if(logger.isDebugEnabled()){ logger.debug(" AccountDAOImpl : editAccountList 종료 "); }
		   return accountList;
	   }
	   
	   
	   
	   @Override
	   public ArrayList<AccountBean> findDetailAccountList(String code) {
	      // TODO Auto-generated method stub
	      if(logger.isDebugEnabled()){ logger.debug(" AccountDAOImpl : findDetailAccountList 시작 "); }
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
	               if(logger.isDebugEnabled()){ logger.debug(" AccountDAOImpl : findDetailAccountList 종료 "); }
	               return accountList;
	           }catch(Exception sqle){
	              logger.fatal(sqle.getMessage());
	               throw new DataAccessException(sqle.getMessage());
	           }finally{
	               dataSourceTransactionManager.close(pstmt, rs);
	           }
	   }
	
	
	
}
