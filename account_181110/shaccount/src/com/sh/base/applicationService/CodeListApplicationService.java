package com.sh.base.applicationService;

import java.util.ArrayList;

import com.sh.base.to.CodeBean;
import com.sh.base.to.DepartmentCodeBean;
import com.sh.base.to.DetailCodeBean;

public interface CodeListApplicationService {

	public ArrayList<DetailCodeBean> findDetailCodeList(String code);

	public ArrayList<CodeBean> findCodeList();

	public void batchCodeProcess(ArrayList<CodeBean> codeList);

	public ArrayList<DepartmentCodeBean> selectDeptCodeList(String code);
	

}
