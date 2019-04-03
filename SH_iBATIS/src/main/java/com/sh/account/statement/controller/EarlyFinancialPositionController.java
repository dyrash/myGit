package com.sh.account.statement.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.sh.account.statement.exception.StatementListException;
import com.sh.account.statement.serviceFacade.StatementServiceFacade;
import com.sh.account.statement.to.EarlyStatementBean;


public class EarlyFinancialPositionController extends MultiActionController{
	private StatementServiceFacade statementServiceFacade;
	private ModelMap modelMap = new ModelMap();
	private ModelAndView modelAndView = null;
	//private MessageSource messageSource;
	public void setStatementServiceFacade(StatementServiceFacade statementServiceFacade) {
		this.statementServiceFacade = statementServiceFacade;
	}
	
	public ModelAndView findEarlyAssets(HttpServletRequest request, HttpServletResponse response) throws StatementListException{
		// TODO Auto-generated method stub
		//HttpSession session = request.getSession();
		response.setContentType("application/json; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		ArrayList<EarlyStatementBean> assetList = statementServiceFacade.findEarlyAssets();
		modelMap.put("assetList",assetList);
		modelMap.put("errorCode", 1);
		modelMap.put("errorMsg", "데이터 조회 성공");
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}
	/*public ModelAndView findEarlyAssets(HttpServletRequest request, HttpServletResponse response) throws StatementListException{
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		response.setContentType("application/json; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			ArrayList<EarlyStatementBean> assetList = earlyStatementServiceFacade.findEarlyAssets();
			modelMap.put("assetList",assetList);
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
	

	public ModelAndView findEarlyLiabilitiseNequity(HttpServletRequest request, HttpServletResponse response) throws StatementListException{
		// TODO Auto-generated method stub
		//HttpSession session = request.getSession();
		response.setContentType("application/json; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		ArrayList<EarlyStatementBean> liabNequiList = statementServiceFacade.findEarlyLiabilitiseNequity();
		modelMap.put("liabNequiList",liabNequiList);
		modelMap.put("errorCode", 1);
		modelMap.put("errorMsg", "데이터 조회 성공");

		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}
	/*public ModelAndView findEarlyLiabilitiseNequity(HttpServletRequest request, HttpServletResponse response) throws StatementListException{
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		response.setContentType("application/json; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			ArrayList<EarlyStatementBean> liabNequiList = earlyStatementServiceFacade.findEarlyLiabilitiseNequity();
			modelMap.put("liabNequiList",liabNequiList);
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
	
	public ModelAndView findEarlyFinancialPosition(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// TODO Auto-generated method stub
		response.setContentType("application/json; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			ArrayList<EarlyStatementBean> earlyFinacialList = statementServiceFacade.findEarlyFinancialPosition();
			modelMap.put("earlyFinacialList",earlyFinacialList);
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
