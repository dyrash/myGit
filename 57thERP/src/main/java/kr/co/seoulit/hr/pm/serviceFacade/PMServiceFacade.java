package kr.co.seoulit.hr.pm.serviceFacade;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.hr.pm.to.EmpInfoTO;
import kr.co.seoulit.hr.pm.to.EmployeeInfoTO;
import kr.co.seoulit.hr.pm.to.EmployeeTO;
import kr.co.seoulit.hr.pm.to.ReportTO;
import kr.co.seoulit.hr.pm.to.WorkInfoTO;

public interface PMServiceFacade {

	public EmployeeInfoTO findEmployeeInfoAll();

	public void batchEmployeeInfoAll(EmployeeInfoTO employeeInfoTO);

	public List<ReportTO> findEmpInfoReport(String empCode);

	public List<EmpInfoTO> findEmpInfoList();

	public void batchEmpInfoList(List<EmpInfoTO> batchEmpInfo);

	public List<EmpInfoTO> findEmpCode(HashMap<String, String> map);

    public List<WorkInfoTO> selectWorkInfoAll();

    public List<WorkInfoTO> findWorkInfoCode();
	public List<EmployeeTO> findEmployeeList();

	public void batchEmployeeList(List<EmployeeTO> employeeList);
}

