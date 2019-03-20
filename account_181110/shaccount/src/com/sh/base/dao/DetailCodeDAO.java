package com.sh.base.dao;

import java.util.ArrayList;

import com.sh.base.to.DetailCodeBean;

public interface DetailCodeDAO {

	ArrayList<DetailCodeBean> selectDetailCodeList(String code);

	void insertDetailCode(DetailCodeBean codeDetailBean);

	void updateDetailCode(DetailCodeBean codeDetailBean);

	void deleteDetailCode(String codeNo);

}
