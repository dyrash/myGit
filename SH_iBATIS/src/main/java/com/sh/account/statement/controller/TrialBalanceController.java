package com.sh.account.statement.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.sh.account.statement.serviceFacade.StatementServiceFacade;

public class TrialBalanceController extends MultiActionController{

	private StatementServiceFacade statementServiceFacade;
	private ModelMap modelMap = new ModelMap();
	private ModelAndView modelAndView = null;
	private MessageSource messageSource;
	public void setMessageSource(MessageSource messageSource) {
			this.messageSource = messageSource;
		}
	public void setStatementServiceFacade(StatementServiceFacade statementServiceFacade) {
		this.statementServiceFacade = statementServiceFacade;
	}
	@Override	
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("application/json; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("		@ TrialBalanceController에 접근");
		try {
			String fromDate = request.getParameter("from");
			String toDate = request.getParameter("to");
			System.out.println("		@ 조회일자: "+toDate+" 까지");
			HashMap<String, Object> trialBalanceList = statementServiceFacade.findTrialBalanceList(fromDate, toDate);
			modelMap.put("trialBalanceList", trialBalanceList.get("trialBalanceList"));
			//TrialBalanceResultBean error = (TrialBalanceResultBean) trialBalanceList.get("result");
		/*	if (error.getErrorCode() != 0) {
				modelMap.put("errorCode", error.getErrorCode());
				modelMap.put("errorMsg", error.getErrorMsg());
			} else {
				modelMap.put("trialBalanceList", trialBalanceList.get("trialBalanceList"));
				modelMap.put("errorCode", error.getErrorCode());
				modelMap.put("errorMsg", error.getErrorMsg());
			}*/
		} catch (DataAccessException e) {
			logger.fatal(e.getMessage());
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e.getMessage());
			e.printStackTrace();
		} catch (Exception error) {
			logger.fatal(error.getMessage());
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", error.getMessage());
			error.printStackTrace();
		}
		modelAndView = new ModelAndView("jsonView",modelMap);
        return modelAndView;
	}
	
}
