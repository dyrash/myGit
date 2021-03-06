package com.sh.account.statement.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.sh.account.statement.serviceFacade.StatementServiceFacade;
import com.sh.base.to.IncomeStatementResultBean;
import com.sh.common.exception.DataAccessException;




public class incomeStatementController extends MultiActionController{

	private StatementServiceFacade statementServiceFacade;
	private ModelMap modelMap = new ModelMap();
	private ModelAndView modelAndView = null;
	/*private MessageSource messageSource;
	   public void setMessageSource(MessageSource messageSource) {
			this.messageSource = messageSource;
		}
*/
	public void setStatementServiceFacade(StatementServiceFacade statementServiceFacade) {
		this.statementServiceFacade = statementServiceFacade;
	}
	@Override	
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("application/json; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		try {
			String fromDate = request.getParameter("from");
			String toDate = request.getParameter("to");
			System.out.println("		@ 조회일자: "+fromDate+"부터 "+toDate+" 까지");
			HashMap<String, Object> incomeStatementList = statementServiceFacade.findincomeStatementList(fromDate, toDate);
			IncomeStatementResultBean error = ( IncomeStatementResultBean) incomeStatementList.get("result");
				modelMap.put("incomeStatementList", incomeStatementList.get("incomeStatementList"));
				modelMap.put("errorCode", error.getErrorCode());
				modelMap.put("errorMsg", error.getErrorMsg());
		} catch (DataAccessException e) {
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e.getMessage());
			e.printStackTrace();
		} catch (Exception error) {
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", error.getMessage());
			error.printStackTrace();
		}
		modelAndView = new ModelAndView("jsonView",modelMap);
        return modelAndView;
	}
	
}

