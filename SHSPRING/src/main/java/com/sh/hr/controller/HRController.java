package com.sh.hr.controller;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.sh.hr.exception.EmployeeListException;
import com.sh.common.exception.DataAccessException;
import com.sh.hr.serviceFacade.HRServiceFacade;
import com.sh.hr.to.EmployeeBean;

import net.sf.json.JSONObject;

public class HRController extends MultiActionController{
	private ModelAndView modelAndView = null;
	private ModelMap modelMap = new ModelMap();
	private MessageSource messageSource;	
	private HRServiceFacade hrServiceFacade;
	
	public void setMessageSource(MessageSource messageSource) {
			this.messageSource = messageSource;
		}
	public  void setHrServiceFacade(HRServiceFacade hrServiceFacade) {
		this.hrServiceFacade = hrServiceFacade;
		
	}

	public ModelAndView findEmployeeList(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		try{	
		String deptCode = request.getParameter("deptCode");
		ArrayList<EmployeeBean> empList = hrServiceFacade.findEmployeeList(deptCode);
		modelMap.put("empList", empList);
		modelMap.put("errorCode", 1);  
		modelMap.put("errorMsg", "성공");		
		}catch(EmployeeListException e){ 
			String errorMsg=messageSource.getMessage("EmployeeListException",null,(Locale)session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME));
			modelMap.put("errorCode", -2);
			//modelMap.put("errorMes",e.getMessage());
			modelMap.put("errorMes", errorMsg);
			e.printStackTrace();
		}

	    modelAndView = new ModelAndView("jsonView",modelMap);
        return modelAndView;
	}
	
	
	 public ModelAndView batchEmpInfo(HttpServletRequest request, HttpServletResponse response){
		 try {
	            JSONObject jsonObject = JSONObject.fromObject(request.getParameter("emp"));
	            EmployeeBean employeeBean = (EmployeeBean) JSONObject.toBean(jsonObject.getJSONObject("EmpInfo"), EmployeeBean.class);
	           
	            hrServiceFacade.batchEmployeeInfo(employeeBean);
	            
	            modelMap.put("errorCode", 1);
	            modelMap.put("errorMsg", "성공");
		 }catch (DataAccessException e) {	        	
	        	modelMap.put("errorCode", -2);
	            //modelMap.put("errorMsg", e.getMessage());
	        	modelMap.put("errorMsg", "DB오류");
	            e.printStackTrace();
	        }

	        modelAndView = new ModelAndView("jsonView",modelMap);
	        return modelAndView;
	    }
	
	 public ModelAndView EmptyEmpBean(HttpServletRequest request, HttpServletResponse response){
	        try {
	        	modelMap.put("EmptyEmpBean",new EmployeeBean());
	        	modelMap.put("errorCode", 1);
	        	modelMap.put("errorMsg", "성공");
	        }catch (DataAccessException e) {	        	
	        	modelMap.put("errorCode", -2);
	            //modelMap.put("errorMsg", e.getMessage());
	        	modelMap.put("errorMsg", "DB오류");
	            e.printStackTrace();
	        }
	        modelAndView = new ModelAndView("jsonView",modelMap);
	        return modelAndView;
	    }
	
	 public ModelAndView batchEmp(HttpServletRequest request, HttpServletResponse response){
	        try {
	            JSONObject jsonObject = JSONObject.fromObject(request.getParameter("JoinEmployee"));
	            EmployeeBean employeeBean = (EmployeeBean) JSONObject.toBean(jsonObject.getJSONObject("empBean"), EmployeeBean.class);
	           
	            hrServiceFacade.registerEmployee(employeeBean);
	            System.out.println("		@ Emp: "+employeeBean.getEmpCode());

	            modelMap.put("errorCode", 1);
	            modelMap.put("errorMsg", "성공");
	        }catch (DataAccessException e) {	        	
	        	modelMap.put("errorCode", -2);
	           // modelMap.put("errorMsg", e.getMessage());
	        	modelMap.put("errorMsg", "DB오류");
	            e.printStackTrace();
	         }

	        modelAndView = new ModelAndView("jsonView",modelMap);
	        return modelAndView;
	    }

}
