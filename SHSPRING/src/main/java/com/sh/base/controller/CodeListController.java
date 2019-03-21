package com.sh.base.controller;

import java.io.IOException;
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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sh.base.exception.CodeListException;
import com.sh.base.exception.DetailCodeListException;
import com.sh.base.serviceFacade.BaseServiceFacade;
import com.sh.base.to.CodeBean;
import com.sh.base.to.CustomerBean;
import com.sh.base.to.DepartmentCodeBean;
import com.sh.base.to.DetailCodeBean;
import com.sh.common.exception.DataAccessException;


public class CodeListController extends MultiActionController {
	
	private BaseServiceFacade baseServiceFacade;
	private ModelAndView modelAndView = null;
    private ModelMap modelMap = new ModelMap();
    private MessageSource messageSource;
    
    public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	public void setBaseServiceFacade(BaseServiceFacade baseServiceFacade) {
		this.baseServiceFacade = baseServiceFacade;
	}
	public ModelAndView findDetailCodeList(HttpServletRequest request, HttpServletResponse response) throws DetailCodeListException{
		try {
			String code = request.getParameter("code"); 
			ArrayList<DetailCodeBean> detailCodeList = baseServiceFacade.findDetailCodeList(code);
			modelMap.put("detailCodeList", detailCodeList); 
			modelMap.put("errorCode", 1);
			
		} catch (DataAccessException e) {
			modelMap.put("errorCode", -2);
            modelMap.put("errorMsg", e.getMessage());
            e.printStackTrace();
         }
		modelAndView = new ModelAndView("jsonView",modelMap);
        return modelAndView;
		
	}
	
	public ModelAndView findCodeList(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
			try {
				ArrayList<CodeBean> codeList = baseServiceFacade.findCodeList();
				modelMap.put("emptyCode", new CodeBean()); 
				modelMap.put("emptyDetailCode", new DetailCodeBean()); 
				modelMap.put("codeList", codeList); 
				modelMap.put("errorCode", 1);
				modelMap.put("errorMsg", "코드를 가져 왔습니다");
			}catch (CodeListException e) {
				String errorMsg=messageSource.getMessage("CodeListException",null,(Locale)session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME));
	            modelMap.put("errorCode", -2);
	            modelMap.put("errorMsg",errorMsg);
	            e.printStackTrace();
	         }
	        modelAndView = new ModelAndView("jsonView",modelMap);
	        return modelAndView;		
	}
	
	public ModelAndView batchCodeProcess(HttpServletRequest request, HttpServletResponse response){
		try{
            String list = request.getParameter("batchList");
            ObjectMapper mapper = new ObjectMapper();
            ArrayList<CodeBean> codeList = mapper.readValue(list, new TypeReference<ArrayList<CodeBean>>(){});
            baseServiceFacade.batchCodeProcess(codeList);
          
            modelMap.put("errorCode", 1);
            modelMap.put("errorMsg", "데이터를 가져 왔습니다");
        }catch (IOException e){
        	modelMap.put("errorCode", -1);
        	modelMap.put("errorMsg", "출력오류");
            e.printStackTrace();
        }catch (DataAccessException e) {
        	modelMap.put("errorCode", -2);
        	modelMap.put("errorMsg", e.getMessage());
            e.printStackTrace();
         }
        modelAndView = new ModelAndView("jsonView",modelMap);
        return modelAndView;
    }
	
	public ModelAndView selectDeptCodeList(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		try {
			String code = request.getParameter("code"); 
			ArrayList<DepartmentCodeBean> departmentCodeList = baseServiceFacade.selectDeptCodeList(code);
			modelMap.put("departmentCodeList", departmentCodeList); 
			modelMap.put("errorCode", 1);
		}catch (CodeListException e) {
			String errorMsg=messageSource.getMessage("CodeListException",null,(Locale)session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME));
            modelMap.put("errorCode", -2);
            modelMap.put("errorMsg",errorMsg);
            e.printStackTrace();
         }
		modelAndView = new ModelAndView("jsonView",modelMap);
        return modelAndView;
		
	}	

	public ModelAndView selectCustomerCodeList(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		try {
			String code = request.getParameter("code"); 
			ArrayList<CustomerBean> customerList = baseServiceFacade.selectCustomerCodeList(code);
			modelMap.put("customerList", customerList); 
			modelMap.put("errorCode", 1);
		}catch (CodeListException e) {
			String errorMsg=messageSource.getMessage("CodeListException",null,(Locale)session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME));
            modelMap.put("errorCode", -2);
            modelMap.put("errorMsg",errorMsg);
            e.printStackTrace();
         }
		modelAndView = new ModelAndView("jsonView",modelMap);
        return modelAndView;
	}
}
