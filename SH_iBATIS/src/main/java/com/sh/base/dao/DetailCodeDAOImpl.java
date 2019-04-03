package com.sh.base.dao;


import java.util.ArrayList;

import com.sh.base.dao.DetailCodeDAO;
import com.sh.base.dao.DetailCodeDAOImpl;
import com.sh.base.to.DetailCodeBean;
import com.sh.common.dao.IBatisDAOSupport;

public class DetailCodeDAOImpl extends IBatisDAOSupport implements DetailCodeDAO{
	@SuppressWarnings({ "unchecked", "deprecation" })	
	@Override
	public ArrayList<DetailCodeBean> selectDetailCodeList(String code) {
		// TODO Auto-generated method stub
		return (ArrayList<DetailCodeBean>)getSqlMapClientTemplate().queryForList("detailCode.selectDetailCodeList", code);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void insertDetailCode(DetailCodeBean codeDetailBean) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("detailCode.insertDetailCode", codeDetailBean);
	}
	@SuppressWarnings("deprecation")
	@Override
	public void updateDetailCode(DetailCodeBean codeDetailBean) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("detailCode.updateDetailCode", codeDetailBean);
	}
	@SuppressWarnings("deprecation")
	@Override
	public void deleteDetailCode(String codeNo) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("detailCode.deleteDetailCode", codeNo);
	}
}
