package com.sh.account.slip.applicationService;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sh.account.slip.applicationService.SlipApplicationService;
import com.sh.account.slip.applicationService.SlipApplicationServiceImpl;
import com.sh.account.slip.dao.JournalDAO;
import com.sh.account.slip.dao.JournalDetailDAO;
import com.sh.account.slip.dao.SlipDAO;
import com.sh.account.slip.to.JournalBean;
import com.sh.account.slip.to.JournalDetailBean;
import com.sh.account.slip.to.SlipBean;
import com.sh.hr.dao.EmployeeDAO;

public class SlipApplicationServiceImpl implements SlipApplicationService{
	protected final Log logger = LogFactory.getLog(this.getClass());
	private  SlipDAO slipDAO;
	private  EmployeeDAO employeeDAO;
	private  JournalDAO journalDAO;
	private  JournalDetailDAO journalDetailDAO;
	
	public  void setSlipDAO(SlipDAO slipDAO) {
		this.slipDAO = slipDAO;
	}

	public  void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	public  void setJournalDAO(JournalDAO journalDAO) {
		this.journalDAO = journalDAO;
	}

	public  void setJournalDetailDAO(JournalDetailDAO journalDetailDAO) {
		this.journalDetailDAO = journalDetailDAO;
	}

	@Override
	public ArrayList<SlipBean> findSlipDataList(String slipDate1,String slipDate2) {
		// TODO Auto-generated method stub
		ArrayList<SlipBean> slipList = slipDAO.selectSlipDataList(slipDate1,slipDate2);  
			for (SlipBean slip : slipList) { 
				slip.setReportingEmpName(employeeDAO.selectEmployee(slip.getReportingEmpCode()).getEmpName()); 
				if (slip.getApprovalEmpCode() != null) {
					slip.setApprovalEmpName(employeeDAO.selectEmployee(slip.getApprovalEmpCode()).getEmpName());
				}
				ArrayList<JournalBean> journalList = journalDAO.selectJournalList(slip.getSlipNo());

				for (JournalBean journal : journalList) {
										
					JournalDetailBean journalDetail = journalDetailDAO.selectJournalDetailList(journal.getJournalNo());
					journal.setJournalDetailBean(journalDetail);
				}
				slip.setJournalList(journalList);
			}
		return slipList;
	}

	@Override
	public void batchListProcess(ArrayList<SlipBean> slipList) {
		// TODO Auto-generated method stub
		String totalStatus=null;
			for (SlipBean slipBean : slipList) {
				String slipStatus = slipBean.getStatus();	 			
				if(slipBean.getJournalList().isEmpty()){ 
					
				totalStatus=slipStatus;
				
					switch (totalStatus) {
					case "insert":
						registerSlip(slipBean);
						break;
					case "update":
						modifySlip(slipBean);
						break;
					case "delete" : 
						removeSlip(slipBean);
						break;
					}
				}else{
					if(slipStatus.equals("insert")){
						registerSlip(slipBean);
					}
					
					ArrayList<JournalBean> journalList = slipBean.getJournalList(); 
					for (JournalBean journalBean : journalList) {
						JournalDetailBean journalDetailBean = journalBean.getJournalDetailBean(); 
						String journalStatus = journalBean.getStatus();
						totalStatus=slipStatus+journalStatus;
						switch (totalStatus) {
						case "insertinsert":			  			
							registerJournal(journalBean);
							registerJournalDetail(journalDetailBean);
							break;
						case "updateinsert": 			  
							modifySlip(slipBean);
							registerJournal(journalBean);
							registerJournalDetail(journalDetailBean);
							break;
						case "updateupdate": 				
							modifySlip(slipBean);
							modifyJournal(journalBean);
							modifyJournalDetail(journalDetailBean);
							break;
						case "updatenormal": 			  
							modifySlip(slipBean);
							modifyJournal(journalBean);
							modifyJournalDetail(journalDetailBean);
							break;
						case "updatedelete":			  
							modifySlip(slipBean);
							removeJournalDetail(journalDetailBean);
							removeJournal(journalBean);	
							break;	
						case "deletedelete": 			 	
							removeJournalDetail(journalDetailBean);
							removeJournal(journalBean);							
							break;
						}
						
					}
					if(slipStatus.equals("delete")){
						removeSlip(slipBean);
					}
				}
			}
	}



	private void removeSlip(SlipBean slipBean) {
		// TODO Auto-generated method stub
			slipDAO.deleteSlip(slipBean);
	}

	private void modifySlip(SlipBean slipBean) {
		// TODO Auto-generated method stub
			slipDAO.updateSlip(slipBean);
	}

	private void registerSlip(SlipBean slipBean) {
		// TODO Auto-generated method stub
			slipDAO.insertSlip(slipBean);
	}
		
	private void registerJournal(JournalBean journalBean) {
		// TODO Auto-generated method stub
			journalDAO.insertJournal(journalBean);
	}
	
	private void removeJournal(JournalBean journalBean) {
		// TODO Auto-generated method stub
			journalDAO.deleteJournal(journalBean);
	}
	
	private void modifyJournal(JournalBean journalBean) {
		// TODO Auto-generated method stub
			journalDAO.updateJournal(journalBean);
	}

	private void removeJournalDetail(JournalDetailBean journalDetailBean) {
		// TODO Auto-generated method stub
			journalDetailDAO.deleteJournalDetail(journalDetailBean);
	}

	private void modifyJournalDetail(JournalDetailBean journalDetailBean) {
		// TODO Auto-generated method stub
			journalDetailDAO.updateJournalDetail(journalDetailBean);
	}

	private void registerJournalDetail(JournalDetailBean journalDetailBean) {
		// TODO Auto-generated method stub
			journalDetailDAO.insertJournalDetail(journalDetailBean);
	}

	@Override
	public ArrayList<SlipBean> findRangedSlipList(String fromDate, String toDate) {
		// TODO Auto-generated method stub
		ArrayList<SlipBean> slipList = slipDAO.selectRangedSlipList(fromDate, toDate);
			for (SlipBean slip : slipList) {
				/* if (slip.getApprovalEmpCode() != null) {
					slip.setApprovalEmpName(employeeDAO.selectEmployee(slip.getApprovalEmpCode()).getEmpName());
				}*/
				ArrayList<JournalBean> journalList = journalDAO.selectJournalList(slip.getSlipNo());
				slip.setJournalList(journalList);
			}
		return slipList;
	}

	@Override
	public ArrayList<SlipBean> findDisApprovalSlipList() {
		// TODO Auto-generated method stub
		ArrayList<SlipBean> disApprovalSlipList = null;
		disApprovalSlipList=slipDAO.selectDisApprovalSlipList();
		for(SlipBean slip : disApprovalSlipList){
			slip.setReportingEmpName(employeeDAO.selectEmployee(slip.getReportingEmpCode()).getEmpName());
			 ArrayList<JournalBean> journalList = journalDAO.selectJournalList(slip.getSlipNo());
			 for(JournalBean journal : journalList) {
				 JournalDetailBean journalDetail = journalDetailDAO.selectJournalDetailList(journal.getJournalNo());
				 journal.setJournalDetailBean(journalDetail);
			}
			 slip.setJournalList(journalList);
		}
		return disApprovalSlipList;	
	}
}
