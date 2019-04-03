package com.sh.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class MemberLogoutController extends MultiActionController {
	protected final Log logger = LogFactory.getLog(this.getClass());
	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response){
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" MemberLogoutController : handleRequestInternal 시작 "); }
		ModelAndView modelAndView = new ModelAndView("loginform",null);
		HttpSession session = request.getSession();
		session.invalidate();
		System.out.println("		@ :session 종료: 사용자가 로그 아웃 하였습니다");
		if(logger.isDebugEnabled()){ logger.debug(" MemberLogoutController : handleRequestInternal 종료 "); }
		return modelAndView;
	}

}
