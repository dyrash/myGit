package com.sh.base.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.sh.base.exception.DeptCodeNotFoundException;
import com.sh.base.exception.IdNotFoundException;
import com.sh.base.exception.PwMissmatchException;
import com.sh.base.serviceFacade.BaseServiceFacade;
import com.sh.base.to.MenuBean;
import com.sh.hr.to.EmployeeBean;

public class MemberLoginController extends MultiActionController{

	protected final Log logger = LogFactory.getLog(this.getClass());
	private BaseServiceFacade baseServiceFacade;
	private MessageSource messageSource;
	private ModelMap modelMap = new ModelMap();
	
	public void setBaseServiceFacade(BaseServiceFacade baseServiceFacade) {
		this.baseServiceFacade = baseServiceFacade;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}	
	@SuppressWarnings("unchecked")
	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response){
		// TODO Auto-generated method stub
		String viewName = null;
		HttpSession session = request.getSession();
		String empCode = request.getParameter("empCode").toUpperCase();
		String userPw = request.getParameter("userPw");
		String deptCode = request.getParameter("deptCode").toUpperCase();
			
		try {
			HashMap<String,Object> result = baseServiceFacade.accessToAuthority(empCode, userPw ,deptCode);
			if (result != null) {			
				session.setAttribute("empCode", ((EmployeeBean)result.get("employeeBean")).getEmpCode());			
				session.setAttribute("empName", ((EmployeeBean)result.get("employeeBean")).getEmpName());
				session.setAttribute("deptCode", ((EmployeeBean)result.get("employeeBean")).getDeptCode());
				session.setAttribute("detailCodeName", ((EmployeeBean)result.get("employeeBean")).getPositionName());
			
				session.setAttribute("authority", ((ArrayList<MenuBean>)result.get("menuList")).get(0).getAuthorityName()); 
				session.setAttribute("positionCode", ((EmployeeBean)result.get("employeeBean")).getPositionCode()); 
				//session.setAttribute("masterMenuList", result.get("masterMenuList"));
				viewName = "redirect:hello.html"; 
			}
			System.out.println("		@ 뷰네임: "+viewName);
		} catch (IdNotFoundException e) {
			String errorMsg = messageSource.getMessage("IdNotFoundException", new Object[] { empCode }, (Locale)session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME));
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", errorMsg);
			viewName = "loginform";
		}catch(DeptCodeNotFoundException e){
			String errorMsg=messageSource.getMessage("DeptCodeNotFoundException", new Object[] { deptCode }, (Locale)session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME));
			modelMap.put("errorCode", -2);			
			modelMap.put("errorMsg",errorMsg);
			viewName = "loginform";
		} catch (PwMissmatchException e) {	
			String errorMsg = messageSource.getMessage("PwMissmatchException", null, (Locale)session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME));
			modelMap.put("errorCode", -3);			
			modelMap.put("errorMsg",errorMsg);
			viewName = "loginform";
		} catch (Exception e) {
			modelMap.put("errorCode", -4);
			modelMap.put("errorMsg", /*e.getMessage()*/ "오류발생");
			viewName = "loginform"; 
		}
		System.out.println("		@ 로그인 되었습니다");
		ModelAndView modelAndView = new ModelAndView(viewName, modelMap);
		return modelAndView;
	}

}
