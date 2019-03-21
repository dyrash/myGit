package com.sh.hr.serviceFacade;

import java.util.ArrayList;

import com.sh.hr.serviceFacade.HRServiceFacade;
import com.sh.hr.serviceFacade.HRServiceFacadeImpl;
import com.sh.hr.exception.EmployeeListException;
import com.sh.hr.applicationservice.HrApplicationService;
import com.sh.hr.to.EmployeeBean;

public class HRServiceFacadeImpl implements HRServiceFacade{
	
	HrApplicationService hrApplicationService;

	public void setHrApplicationService(HrApplicationService hrApplicationService) {
		this.hrApplicationService = hrApplicationService;
	}


	@Override
	public ArrayList<EmployeeBean> findEmployeeList(String deptCode) throws EmployeeListException{
		// TODO Auto-generated method stub
		ArrayList<EmployeeBean> empList = hrApplicationService.findEmployeeList(deptCode);
		if(empList==null){				 
			 throw new EmployeeListException("사원 목록을 조회하지 못했습니다.");
		 }
		return empList;
	}
	@Override
	public void batchEmployeeInfo(EmployeeBean employeeBean) {
		// TODO Auto-generated method stub
			hrApplicationService.batchEmployeeInfo(employeeBean);
	}
	@Override
	public void batchEmployee(ArrayList<EmployeeBean> empList) {
		// TODO Auto-generated method stub
			hrApplicationService.batchEmployee(empList);
			for(int a=0; a<empList.size(); a++) {
			System.out.println("		@ 사원리스트 : "+empList.get(a).getEmpName());
			}
	}
	@Override
	public void registerEmployee(EmployeeBean employeeBean) {
		// TODO Auto-generated method stub
			hrApplicationService.registerEmployee(employeeBean);
			System.out.println("		@ 등록된 사원명 : "+employeeBean.getEmpName());
	}
	@Override
	public ArrayList<EmployeeBean> findSYSList(String authority) {
		ArrayList<EmployeeBean> empList = hrApplicationService.findSYSList(authority);
		return empList;
	}
}
