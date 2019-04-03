package com.sh.hr.applicationservice;

import java.util.ArrayList;

import com.sh.hr.applicationservice.HrApplicationService;
import com.sh.hr.applicationservice.HrApplicationServiceImpl;
import com.sh.hr.dao.EmployeeDAO;
import com.sh.hr.to.EmployeeBean;

public class HrApplicationServiceImpl implements HrApplicationService{

	private EmployeeDAO employeeDAO;
	
	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}
	
	
	@Override
	public ArrayList<EmployeeBean> findEmployeeList(String deptCode) {
		// TODO Auto-generated method stub
		ArrayList<EmployeeBean> empList = employeeDAO.selectEmployeeList(deptCode);
		return empList;
	}

	@Override
	public void batchEmployeeInfo(EmployeeBean employeeBean) {
		// TODO Auto-generated method stub
			employeeDAO.updateEmployeeInfo(employeeBean);
	}

	@Override
	public void batchEmployee(ArrayList<EmployeeBean> empList) {
		// TODO Auto-generated method stub
			for(EmployeeBean employBean : empList){
				String empStatus = employBean.getStatus();
				switch (empStatus) {
				case "update":
					modifyEmployee(employBean);
					break;
				case "delete":
					removeEmployee(employBean);
					break;
				}
			}
	}

	private void modifyEmployee(EmployeeBean employBean) {
		// TODO Auto-generated method stub
		employeeDAO.updateEmployee(employBean);
	}

	private void removeEmployee(EmployeeBean employBean) {
		// TODO Auto-generated method stub
		employeeDAO.deleteEmployee(employBean.getEmpCode());
	}

	@Override
	public void registerEmployee(EmployeeBean employeeBean) {
		// TODO Auto-generated method stub
			employeeDAO.insertEmployee(employeeBean);
	}
}
