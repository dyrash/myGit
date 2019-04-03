package com.sh.hr.serviceFacade;

import java.util.ArrayList;

import com.sh.hr.exception.EmployeeListException;
import com.sh.hr.to.EmployeeBean;

public interface HRServiceFacade {

	public ArrayList<EmployeeBean> findEmployeeList(String deptCode) throws EmployeeListException;

	public void batchEmployeeInfo(EmployeeBean employeeBean);

	public void batchEmployee(ArrayList<EmployeeBean> empList);

	public void registerEmployee(EmployeeBean employeeBean);

}
