package com.sh.account.statement.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.sh.account.statement.exception.StatementListException;
import com.sh.account.statement.serviceFacade.EarlyIncomeStatementServiceFacade;
import com.sh.account.statement.to.EarlyIncomeStatementBean;

public class EarlyIncomeStatementController extends MultiActionController{
	private EarlyIncomeStatementServiceFacade earlyIncomeStatementServiceFacade;
	private ModelMap modelMap = new ModelMap();
	private ModelAndView modelAndView = null;
	/*private MessageSource messageSource;

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}*/
	public void setEarlyIncomeStatementServiceFacade(EarlyIncomeStatementServiceFacade earlyIncomeStatementServiceFacade) {
		this.earlyIncomeStatementServiceFacade = earlyIncomeStatementServiceFacade;
	}
	
	@Override	
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws StatementListException {
		// TODO Auto-generated method stub
		response.setContentType("application/json; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		ArrayList<EarlyIncomeStatementBean> earlyIncomeStatementList = earlyIncomeStatementServiceFacade.findEarlyIncomeStatementList();
		modelMap.put("earlyIncomeStatementList",earlyIncomeStatementList);
		modelMap.put("errorCode", 1);
		modelMap.put("errorMsg", "데이터 조회 성공");

		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}
	/*@Override	
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		response.setContentType("application/json; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		try {
			ArrayList<EarlyIncomeStatementBean> earlyIncomeStatementList = earlyIncomeStatementServiceFacade.findEarlyIncomeStatementList();
			modelMap.put("earlyIncomeStatementList",earlyIncomeStatementList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "데이터 조회 성공");
		} catch (StatementListException e) {
			String errorMsg = messageSource.getMessage("StatementListException", new Object[] { e.getMessage() },
					(Locale) session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME));
			logger.fatal(e.getMessage());
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", errorMsg);
			e.printStackTrace();
		}

		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}*/
	
	
	
	
	/*public ModelAndView findEarlyIncomeList(HttpServletRequest request, HttpServletResponse response) throws StatementListException{
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		response.setContentType("application/json; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			ArrayList<EarlyIncomeStatementBean> earlyIncomeList = earlyIncomeStatementServiceFacade.findEarlyIncomeList();
			modelMap.put("earlyIncomeList",earlyIncomeList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "데이터 조회 성공");
		} catch (StatementListException e) {
			String errorMsg = messageSource.getMessage("StatementListException", new Object[] { e.getMessage() },
					(Locale) session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME));

			logger.fatal(e.getMessage());
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", errorMsg);
			e.printStackTrace();
		}
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}
	*/
	public ModelAndView findEarlyIncomeList(HttpServletRequest request, HttpServletResponse response) throws StatementListException{
		// TODO Auto-generated method stub
		response.setContentType("application/json; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		ArrayList<EarlyIncomeStatementBean> earlyIncomeList = earlyIncomeStatementServiceFacade.findEarlyIncomeList();
		modelMap.put("earlyIncomeList",earlyIncomeList);
		modelMap.put("errorCode", 1);
		modelMap.put("errorMsg", "데이터 조회 성공");
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}
	
}

