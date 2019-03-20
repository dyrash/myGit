package com.sh.base.dao;

import java.util.ArrayList;

import com.sh.base.to.MenuBean;

public interface MenuDAO {
	ArrayList<MenuBean> selectMenuList(String empCode);

	ArrayList<MenuBean> selectAllMenuList();
	
}
