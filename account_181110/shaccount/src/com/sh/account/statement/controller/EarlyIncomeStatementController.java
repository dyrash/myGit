package com.sh.account.statement.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sh.account.statement.serviceFacade.EarlyIncomeStatementServiceFacade;
import com.sh.account.statement.serviceFacade.EarlyIncomeStatementServiceFacadeImpl;
import com.sh.account.statement.to.EarlyIncomeStatementBean;
import com.sh.common.exception.DataAccessException;
import com.sh.common.servlet.ModelAndView;
import com.sh.common.servlet.controller.MultiActionController;

import net.sf.json.JSONObject;



public class EarlyIncomeStatementController extends MultiActionController{
	protected final Log logger = LogFactory.getLog(this.getClass());
	private EarlyIncomeStatementServiceFacade earlyIncomeStatementServiceFacade = EarlyIncomeStatementServiceFacadeImpl.getInstance();
	@Override	
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" EarlyIncomeStatementController : handleRequestInternal 시작 "); }

		response.setContentType("application/json; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		PrintWriter out = null;
		System.out.println("		@ EarlyIncomeStatementController에 접근");
		try {
			//String fromDate = request.getParameter("from");
			//String toDate = request.getParameter("to");
			out = response.getWriter();
			//System.out.println("		@ 조회일자: "+fromDate+"부터 "+toDate+" 까지");
			ArrayList<EarlyIncomeStatementBean> earlyIncomeStatementList = earlyIncomeStatementServiceFacade.findEarlyIncomeStatementList();
			//System.out.println("		@ 얼리컨트롤러"+earlyIncomeStatementList.get(0));
	        json.put("earlyIncomeStatementList",earlyIncomeStatementList);
	        json.put("errorCode", 1);
	        json.put("errorMsg", "데이터 조회 성공");
		} catch (DataAccessException e) {
			logger.fatal(e.getMessage());
			json.put("errorCode", -2);
			json.put("errorMsg", e.getMessage());
			e.printStackTrace();
		} catch (Exception error) {
			logger.fatal(error.getMessage());
			json.put("errorCode", -1);
			json.put("errorMsg", error.getMessage());
			error.printStackTrace();
		}
		out.println(json);
		out.close();
		if(logger.isDebugEnabled()){ logger.debug(" EarlyIncomeStatementController : handleRequestInternal 종료 "); }
		return null;
	}
	
	
	public ArrayList<EarlyIncomeStatementBean> findEarlyIncomeList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" EarlyFinancialPositionController : findEarlyIncomeList 시작 "); }
		response.setContentType("application/json; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			ArrayList<EarlyIncomeStatementBean> earlyIncomeList = earlyIncomeStatementServiceFacade.findEarlyIncomeList();
			json.put("earlyIncomeList",earlyIncomeList);
			json.put("errorCode", 1);
			json.put("errorMsg", "데이터 조회 성공");
		} catch (DataAccessException e) {
			logger.fatal(e.getMessage());
			json.put("errorCode", -2);
			json.put("errorMsg", e.getMessage());
			e.printStackTrace();
		} catch (Exception error) {
			logger.fatal(error.getMessage());
			json.put("errorCode", -1);
			json.put("errorMsg", error.getMessage());
			error.printStackTrace();
		}
		out.println(json);
		out.close();
		if(logger.isDebugEnabled()){ logger.debug(" EarlyIncomeStatementController : findEarlyIncomeList 종료 "); }
		return null;
	}
	
}

