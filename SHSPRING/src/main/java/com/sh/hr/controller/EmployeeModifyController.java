package com.sh.hr.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.sh.common.exception.DataAccessException;
import com.sh.hr.serviceFacade.HRServiceFacade;
import com.sh.hr.to.EmployeeBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class EmployeeModifyController extends MultiActionController {
	
	private HRServiceFacade hrServiceFacade;
	private ModelAndView modelAndView = null;
	private ModelMap modelMap = new ModelMap();
	//private MessageSource messageSource;
	public  void setHrServiceFacade(HRServiceFacade hrServiceFacade) {
		this.hrServiceFacade = hrServiceFacade;
		
	}
	/*public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}*/

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response){
		if(logger.isDebugEnabled()){ logger.debug(" EmployeeModifyController : handleRequestInternal 시작 "); }
		// TODO Auto-generated method stub
		ArrayList<EmployeeBean> empList = new ArrayList<EmployeeBean>();
		try{
		
		JSONObject jsonObject = JSONObject.fromObject(request.getParameter("batchList"));
		JSONArray jsonEmployeeArray = jsonObject.getJSONArray("batchList");
	
		
		for (Object obj : jsonEmployeeArray) {
			
			EmployeeBean employeeBean = (EmployeeBean) JSONObject.toBean((JSONObject) obj, EmployeeBean.class);
			empList.add(employeeBean);
		}
		hrServiceFacade.batchEmployee(empList);
		
		modelMap.put("errorCode",1);
		modelMap.put("errorMsg","성공");
		}catch(DataAccessException e){
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", "DB오류"/*e.getMessage()*/);
		}
		modelAndView = new ModelAndView("jsonView",modelMap);
        return modelAndView;
	}
	
	

}
