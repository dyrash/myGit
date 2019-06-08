package kr.co.seoulit.hr.pm.applicationService;

import java.util.List;

import kr.co.seoulit.hr.pm.to.EmployeeTO;

public interface EmpApplicationService {

	public List<EmployeeTO> findEmployeeList();
	
	public EmployeeTO findEmployee(String empCode);
	
	public void batchEmployeeList(List<EmployeeTO> employeeList);
	
}
