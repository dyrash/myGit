package com.sh.account.statement.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.sh.account.statement.serviceFacade.EarlyIncomeStatementServiceFacade;
import com.sh.account.statement.to.EarlyIncomeStatementBean;
import com.sh.common.exception.DataAccessException;


public class EarlyIncomeController extends MultiActionController{
	protected final Log logger = LogFactory.getLog(this.getClass());
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

	
	public ModelAndView findEarlyIncomeList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// TODO Auto-generated method stub
		response.setContentType("application/json; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("		@ EarlyIncomeStatementController에 접근");
		try {
			ArrayList<EarlyIncomeStatementBean> earlyIncomeList = earlyIncomeStatementServiceFacade.findEarlyIncomeList();
			modelMap.put("earlyIncomeList",earlyIncomeList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "데이터 조회 성공");
		}catch (DataAccessException e) {	        	
            modelMap.put("errorCode", -2);
            modelMap.put("errorMsg", e.getMessage());
            e.printStackTrace();
         }

		modelAndView = new ModelAndView("jsonView",modelMap);
        return modelAndView;
	}
}

