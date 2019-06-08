package kr.co.seoulit.logi.production.dao;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.logi.production.to.PrmTO;

public interface PrmDAO {

	public List<PrmTO> selectPrmList(HashMap<String,Object> dateMap);

	public void insertPrm(PrmTO prmTO);

}
