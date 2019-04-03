package com.sh.hr.dao;

import java.util.ArrayList;

import com.sh.hr.dao.EmployeeDAO;
import com.sh.hr.dao.EmployeeDAOImpl;
import com.sh.common.dao.IBatisDAOSupport;
import com.sh.hr.to.EmployeeBean;

public class EmployeeDAOImpl extends IBatisDAOSupport implements EmployeeDAO{
	 
	@Override
	@SuppressWarnings("deprecation")
	public EmployeeBean selectCompanyStaff(String empCode) {
		// TODO Auto-generated method stub
		return (EmployeeBean)getSqlMapClientTemplate().queryForObject("employee.selectCompanyStaff", empCode);
	   
	}
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override  
	public ArrayList<EmployeeBean> selectEmployeeList(String deptCode) {
		// TODO Auto-generated method stub
		return (ArrayList<EmployeeBean>)getSqlMapClientTemplate().queryForList("employee.selectEmployeeList", deptCode);
	} 

	@SuppressWarnings("deprecation")
	@Override
	public void updateEmployeeInfo(EmployeeBean employeeBean) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("employee.updateEmployeeInfo", employeeBean);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void updateEmployee(EmployeeBean bean) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("employee.updateEmployee", bean);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void deleteEmployee(String empCode) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("employee.deleteEmployee", empCode);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void insertEmployee(EmployeeBean employeeBean) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("employee.insertEmployee",employeeBean);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public EmployeeBean selectEmployee(String empCode) {
		// TODO Auto-generated method stub
		return (EmployeeBean)getSqlMapClientTemplate().queryForObject("employee.selectEmployee", empCode);
	}

}