package com.sh.base.applicationService;

import java.util.ArrayList;

import com.sh.base.applicationService.CodeListApplicationService;
import com.sh.base.applicationService.CodeListApplicationServiceImpl;
import com.sh.base.dao.CodeDAO;
import com.sh.base.dao.DepartmentCodeDAO;
import com.sh.base.dao.DetailCodeDAO;
import com.sh.base.to.CodeBean;
import com.sh.base.to.DepartmentCodeBean;
import com.sh.base.to.DetailCodeBean;

public class CodeListApplicationServiceImpl implements CodeListApplicationService{

	private CodeDAO codeDAO;
	private DetailCodeDAO detailCodeDAO;
	private DepartmentCodeDAO departmentCodeDAO;
	
	public void setCodeDAO(CodeDAO codeDAO) {
		this.codeDAO = codeDAO;
	}
	public void setCodeDetailDAO(DetailCodeDAO detailCodeDAO) {
		this.detailCodeDAO = detailCodeDAO;
	}
	public void setDepartmentCodeDAO(DepartmentCodeDAO departmentCodeDAO) {
		this.departmentCodeDAO = departmentCodeDAO;
	}
	@Override
	public ArrayList<DetailCodeBean> findDetailCodeList(String code) {
		// TODO Auto-generated method stub
		ArrayList<DetailCodeBean> detailCodeList = detailCodeDAO.selectDetailCodeList(code);
		return detailCodeList;
	}
	@Override
	public ArrayList<CodeBean> findCodeList() {
		// TODO Auto-generated method stub
		ArrayList<CodeBean> codeList = codeDAO.selectCodeList(); 
			for(CodeBean codeBean : codeList){						
				codeBean.setCodeDetailList(detailCodeDAO.selectDetailCodeList(codeBean.getDivisionCode()));
			}		
		return codeList;
	}
	
	@Override
	public void batchCodeProcess(ArrayList<CodeBean> codeList) {
		// TODO Auto-generated method stub
			for (CodeBean code : codeList) {
				switch (code.getStatus()) {
				case "insert":
					codeDAO.insertCode(code); 
					break;
				case "update":
					codeDAO.updateCode(code); 
					break;
				case "normal":
					break;
				case "delete":
					codeDAO.deleteCode(code.getDivisionCode());	
				}
				ArrayList<DetailCodeBean> DetailcodeList = code.getCodeDetailList();
				for (DetailCodeBean codeDetailBean : DetailcodeList) {
					switch (codeDetailBean.getStatus()) {
					case "insert":
						detailCodeDAO.insertDetailCode(codeDetailBean);
						break;
					case "update":
						detailCodeDAO.updateDetailCode(codeDetailBean);
						break;
					case "delete":
						detailCodeDAO.deleteDetailCode(codeDetailBean.getCodeNo());
					}
				}

			}
	}
	@Override
	public ArrayList<DepartmentCodeBean> selectDeptCodeList(String code) {
		// TODO Auto-generated method stub
		ArrayList<DepartmentCodeBean> departmentCodeList = departmentCodeDAO.selectDeptCodeList(code);
		return departmentCodeList;		
			
	}
}
