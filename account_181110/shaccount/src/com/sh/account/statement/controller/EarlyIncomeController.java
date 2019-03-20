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
import com.sh.common.servlet.controller.MultiActionController;

import net.sf.json.JSONObject;



public class EarlyIncomeController extends MultiActionController{
	protected final Log logger = LogFactory.getLog(this.getClass());
	private EarlyIncomeStatementServiceFacade earlyIncomeStatementServiceFacade = EarlyIncomeStatementServiceFacadeImpl.getInstance();
		
	public ArrayList<EarlyIncomeStatementBean> findEarlyIncomeList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" EarlyIncomeController : findEarlyIncomeList 시작 "); }
		response.setContentType("application/json; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		PrintWriter out = null;
		System.out.println("		@ EarlyIncomeStatementController에 접근");
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
		if(logger.isDebugEnabled()){ logger.debug(" EarlyIncomeController : findEarlyIncomeList 종료 "); }
		return null;
	}
	
}

