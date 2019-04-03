package com.sh.base.dao;

import java.util.ArrayList;

import com.sh.base.dao.MenuDAO;
import com.sh.base.dao.MenuDAOImpl;
import com.sh.base.to.MenuBean;
import com.sh.common.dao.IBatisDAOSupport;

public class MenuDAOImpl extends IBatisDAOSupport implements MenuDAO{

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public ArrayList<MenuBean> selectMenuList(String empCode) {
		// TODO Auto-generated method stub
		return (ArrayList<MenuBean>)getSqlMapClientTemplate().queryForList("menu.selectMenuList", empCode);
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public ArrayList<MenuBean> selectAllMenuList() {
		// TODO Auto-generated method stub
		return (ArrayList<MenuBean>)getSqlMapClientTemplate().queryForList("menu.selectAllMenuList");
	}
}
