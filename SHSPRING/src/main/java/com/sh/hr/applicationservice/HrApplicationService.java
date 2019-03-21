package com.sh.hr.applicationservice;

import java.util.ArrayList;

import com.sh.hr.to.EmployeeBean;

public interface HrApplicationService {
	public 	ArrayList<EmployeeBean> findEmployeeList(String deptCode);

	public void batchEmployeeInfo(EmployeeBean employeeBean);

	public void batchEmployee(ArrayList<EmployeeBean> empList);

	public void registerEmployee(EmployeeBean employeeBean);

	public ArrayList<EmployeeBean> findSYSList(String authority);

}
