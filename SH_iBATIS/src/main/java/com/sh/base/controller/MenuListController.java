package com.sh.base.controller;

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

import com.sh.base.exception.UserMenuListException;
import com.sh.base.serviceFacade.BaseServiceFacade;
import com.sh.base.to.MenuBean;

public class MenuListController extends MultiActionController {  // 하나의 컨트롤러에서 여러개의 요청처리 지원
	
	private ModelAndView modelAndView = null;	
    private ModelMap modelMap = new ModelMap();
    
    private BaseServiceFacade baseServiceFacade;
    private MessageSource messageSource;
    
	public void setBaseServiceFacade(BaseServiceFacade baseServiceFacade) {
		this.baseServiceFacade = baseServiceFacade;
	}
    public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	public ModelAndView findUserMenuList(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		try{
			String empCode = request.getParameter("empCode");   
			ArrayList<MenuBean> menuList = baseServiceFacade.findUserMenuList(empCode);
			modelMap.put("userMenuList", menuList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "메뉴를 가져 왔습니다");
		}catch(UserMenuListException e){ 
			String errorMsg=messageSource.getMessage("UserMenuListException",null,(Locale)session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME));
			modelMap.put("errorCode", -2);
            modelMap.put("errorMsg", errorMsg);
            e.printStackTrace();			
		}
		modelAndView = new ModelAndView("jsonView",modelMap);
        return modelAndView;	
	}
}
