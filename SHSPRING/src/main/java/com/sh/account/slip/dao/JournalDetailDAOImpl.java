package com.sh.account.slip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sh.account.slip.dao.JournalDetailDAO;
import com.sh.account.slip.dao.JournalDetailDAOImpl;
import com.sh.account.slip.to.JournalDetailBean;
import com.sh.common.db.DataSourceTransactionManager;
import com.sh.common.exception.DataAccessException;

public class JournalDetailDAOImpl implements JournalDetailDAO{
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	private DataSourceTransactionManager dataSourceTransactionManager;
	
	public void setDataSourceTransactionManager(DataSourceTransactionManager dataSourceTransactionManager) {
		this.dataSourceTransactionManager = dataSourceTransactionManager;
	}

	@Override
	public JournalDetailBean selectJournalDetailList(String journalCode) {
		// TODO Auto-generated method stub
		 Connection con = null;
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         JournalDetailBean journalDetailBean=null;
         try {
             StringBuffer query = new StringBuffer();
             query.append("SELECT * FROM JOURNAL_DETAIL WHERE JOURNAL_NO = ?");
             con = dataSourceTransactionManager.getConnection();
          pstmt = con.prepareStatement(query.toString());
             pstmt.setString(1, journalCode);
             System.out.println("		@ 분개상세코드: "+journalCode);
             rs = pstmt.executeQuery();
             if(rs.next()) {
                 journalDetailBean = new JournalDetailBean();
                 journalDetailBean.setJournalDetailNo(rs.getString("JOURNAL_DETAIL_NO"));;
                 journalDetailBean.setJournalNo(rs.getString("JOURNAL_NO"));;
                 journalDetailBean.setSlipNo(rs.getString("SLIP_NO"));;
                 journalDetailBean.setItem1(rs.getString("ITEM1"));
                 journalDetailBean.setValue1(rs.getString("VALUE1"));
                 journalDetailBean.setItem2(rs.getString("ITEM2"));
                 journalDetailBean.setValue2(rs.getString("VALUE2"));
                 journalDetailBean.setItem3(rs.getString("ITEM3"));
                 journalDetailBean.setValue3(rs.getString("VALUE3"));
                 journalDetailBean.setItem4(rs.getString("ITEM4"));
                 journalDetailBean.setValue4(rs.getString("VALUE4"));
                 journalDetailBean.setItem5(rs.getString("ITEM5"));
                 journalDetailBean.setValue5(rs.getString("VALUE5"));
                 System.out.println("		@ 분개 세부사항 조회됨");
             }
            
             }catch(Exception sqle){
                throw new DataAccessException(sqle.getMessage());
             }finally{
                 dataSourceTransactionManager.close(pstmt, rs);
             }
         return journalDetailBean;
	}
	@Override
	public void deleteJournalDetail(JournalDetailBean journalDetailBean) {
		// TODO Auto-generated method stub
		  Connection conn = null;
	      PreparedStatement pstmt = null;
	      dataSourceTransactionManager.beginTransaction();
	      try{
	         StringBuffer query = new StringBuffer();
	         query.append("DELETE FROM JOURNAL_DETAIL WHERE SLIP_NO=?");
	         conn = dataSourceTransactionManager.getConnection();
	         pstmt = conn.prepareStatement(query.toString());
	         pstmt.setString(1, journalDetailBean.getJournalDetailNo());
             System.out.println("		@ 분개상세코드: "+journalDetailBean.getJournalDetailNo());
	         pstmt.executeUpdate();
	         dataSourceTransactionManager.commitTransaction();
	         System.out.println("		@ 분개 세부 사항 삭제됨");
	      }catch(Exception sqle){	
	          throw new DataAccessException(sqle.getMessage());
	      }finally{
	          dataSourceTransactionManager.close(pstmt);
	      }
	}
	@Override
	public void updateJournalDetail(JournalDetailBean journalDetailBean) {
		// TODO Auto-generated method stub
		Connection conn = null;
        PreparedStatement pstmt = null;
        try{
           StringBuffer query = new StringBuffer();
           query.append("UPDATE JOURNAL_DETAIL SET ");
           query.append(" ITEM1=?, VALUE1=?");
           query.append(" ITEM2=?, VALUE2=?,");
           query.append(" ITEM3=?, VALUE3=?,");
           query.append(" ITEM4=?, VALUE4=?,");
           query.append(" ITEM5=?, VALUE5=?");
           query.append(" WHERE JOURNAL_DETAIL_NO =? ");
           conn = dataSourceTransactionManager.getConnection();
           pstmt=conn.prepareStatement(query.toString());          
           pstmt.setString(1, journalDetailBean.getItem1());
           pstmt.setString(2, journalDetailBean.getValue1());
           pstmt.setString(3, journalDetailBean.getItem2());
           pstmt.setString(4, journalDetailBean.getValue2());
           pstmt.setString(5, journalDetailBean.getItem3());
           pstmt.setString(6, journalDetailBean.getValue3());
           pstmt.setString(7, journalDetailBean.getItem4());
           pstmt.setString(8, journalDetailBean.getValue4());
           pstmt.setString(9, journalDetailBean.getItem5());
           pstmt.setString(10, journalDetailBean.getValue5());
           pstmt.setString(11, journalDetailBean.getJournalNo());
           //System.out.println("		@ 전표 번호: "+journalDetailBean.getSlipNo());
           System.out.println("		@ 분개코드: "+journalDetailBean.getJournalNo());
           pstmt.executeUpdate();
           System.out.println("		@ 분개 세부 사항 수정됨");
        } catch(Exception sqle) { 
            throw new DataAccessException(sqle.getMessage());
         } finally {
            dataSourceTransactionManager.close(pstmt);
         }
		
	}
	@Override
	public void insertJournalDetail(JournalDetailBean journalDetailBean) {
		// TODO Auto-generated method stub
		 Connection conn = null;
	        PreparedStatement pstmt = null;
	        try{
	           StringBuffer query = new StringBuffer();
	           query.append("INSERT INTO JOURNAL_DETAIL VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
	           conn = dataSourceTransactionManager.getConnection();
	           pstmt=conn.prepareStatement(query.toString());
	           pstmt.setString(1, journalDetailBean.getJournalDetailNo());
	           pstmt.setString(2, journalDetailBean.getJournalNo());
	           pstmt.setString(3, journalDetailBean.getItem1());
	           pstmt.setString(4, journalDetailBean.getValue1());
	           pstmt.setString(5, journalDetailBean.getItem2());
	           pstmt.setString(6, journalDetailBean.getValue2());
	           pstmt.setString(7, journalDetailBean.getItem3());
	           pstmt.setString(8, journalDetailBean.getValue3());
	           pstmt.setString(9, journalDetailBean.getItem4());
	           pstmt.setString(10, journalDetailBean.getValue4());
	           pstmt.setString(11, journalDetailBean.getItem5());
	           pstmt.setString(12, journalDetailBean.getValue5());
	           pstmt.setString(13, journalDetailBean.getSlipNo());
	           //System.out.println("		@ 전표 번호: "+journalDetailBean.getSlipNo());
	           System.out.println("		@ 분개코드: "+journalDetailBean.getJournalNo());
	           pstmt.executeUpdate();
	           System.out.println("		@ 분개 세부 사항 추가됨");
	        } catch(Exception sqle) {	
	          throw new DataAccessException(sqle.getMessage());
	         } finally {
	            dataSourceTransactionManager.close(pstmt);
	         }
	}

}
