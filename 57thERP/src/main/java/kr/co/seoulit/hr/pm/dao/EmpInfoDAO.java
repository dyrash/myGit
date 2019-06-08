package kr.co.seoulit.hr.pm.dao;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.hr.pm.to.EmpInfoTO;
import kr.co.seoulit.hr.pm.to.EmployeeTO;


public interface EmpInfoDAO {
	
	public List<EmployeeTO> selectEmployeeList();
	
	public List<EmpInfoTO> selectEmpInfoAll();

	public List<EmpInfoTO> selectEmpInfoList();	
	
    public void updateEmpInfo(EmpInfoTO empInfoTO);

	public void insertEmpinfo(EmpInfoTO empInfoTO);

	public void deleteEmpinfo(EmpInfoTO empInfoTO);

	public List<EmpInfoTO> selectEmpInfoList(HashMap<String, String> map);

}
