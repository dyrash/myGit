package com.sh.base.dao;

import java.util.ArrayList;

import com.sh.base.to.DepartmentCodeBean;

public interface DepartmentCodeDAO {

	ArrayList<DepartmentCodeBean> selectDeptCodeList(String code);

}
