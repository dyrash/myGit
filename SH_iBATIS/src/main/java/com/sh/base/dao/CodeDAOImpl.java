package com.sh.base.dao;

import java.util.ArrayList;

import com.sh.base.dao.CodeDAO;
import com.sh.base.dao.CodeDAOImpl;
import com.sh.base.to.CodeBean;
import com.sh.common.dao.IBatisDAOSupport;


public class CodeDAOImpl extends IBatisDAOSupport implements CodeDAO{
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override		
	public ArrayList<CodeBean> selectCodeList() {
		// TODO Auto-generated method stub
		return (ArrayList<CodeBean>)getSqlMapClientTemplate().queryForList("code.selectCodeList");
	}
	@SuppressWarnings("deprecation")
	@Override
	public void insertCode(CodeBean codeBean) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("code.insertCode", codeBean);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void updateCode(CodeBean codeBean) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("code.updateCode", codeBean);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void deleteCode(String divisionCode) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("code.deleteCode",divisionCode);
	}
}
