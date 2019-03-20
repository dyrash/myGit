package com.sh.account.statement.controller;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sh.account.statement.serviceFacade.StatementServiceFacade;
import com.sh.account.statement.serviceFacade.StatementServiceFacadeImpl;
import com.sh.base.to.IncomeStatementResultBean;
import com.sh.common.exception.DataAccessException;
import com.sh.common.servlet.ModelAndView;
import com.sh.common.servlet.controller.MultiActionController;

import net.sf.json.JSONObject;



public class incomeStatementController extends MultiActionController{

	protected final Log logger = LogFactory.getLog(this.getClass());
	private StatementServiceFacade statementServiceFacade = StatementServiceFacadeImpl.getInstance();
	@Override	
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" statementPositionController : handleRequestInternal 시작 "); }

		response.setContentType("application/json; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		PrintWriter out = null;
		System.out.println("		@ incomeStatementController에 접근");
		try {
			String fromDate = request.getParameter("from");
			String toDate = request.getParameter("to");
			out = response.getWriter();
			System.out.println("		@ 조회일자: "+fromDate+"부터 "+toDate+" 까지");
			HashMap<String, Object> incomeStatementList = statementServiceFacade.findincomeStatementList(fromDate, toDate);

			IncomeStatementResultBean error = ( IncomeStatementResultBean) incomeStatementList.get("result");
			if (error.getErrorCode() != 0) {
				json.put("errorCode", error.getErrorCode());
				json.put("errorMsg", error.getErrorMsg());
			} else {
				json.put("incomeStatementList", incomeStatementList.get("incomeStatementList"));
				json.put("errorCode", error.getErrorCode());
				json.put("errorMsg", error.getErrorMsg());
			}
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
		if(logger.isDebugEnabled()){ logger.debug(" incomeStatementController : handleRequestInternal 종료 "); }
		return null;
	}
	
}

