package com.sh.account.statement.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sh.account.statement.serviceFacade.EarlyStatementServiceFacade;
import com.sh.account.statement.serviceFacade.EarlyStatementServiceFacadeImpl;
import com.sh.account.statement.to.EarlyStatementBean;
import com.sh.common.exception.DataAccessException;
import com.sh.common.servlet.controller.MultiActionController;

import net.sf.json.JSONObject;

public class EarlyFinancialPositionController extends MultiActionController{
	protected final Log logger = LogFactory.getLog(this.getClass());
	private EarlyStatementServiceFacade earlyStatementServiceFacade = EarlyStatementServiceFacadeImpl.getInstance();

	public ArrayList<EarlyStatementBean> findEarlyAssets(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" EarlyFinancialPositionController : findEarlyAssets 시작 "); }
		response.setContentType("application/json; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			ArrayList<EarlyStatementBean> assetList = earlyStatementServiceFacade.findEarlyAssets();
			//System.out.println("		@ 얼리컨트롤러"+assetList.get(0).getGroupCode());
	        json.put("assetList",assetList);
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
		if(logger.isDebugEnabled()){ logger.debug(" EarlyFinancialPositionController : findEarlyAssets 종료 "); }
		return null;
	}

	public ArrayList<EarlyStatementBean> findEarlyLiabilitiseNequity(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" EarlyFinancialPositionController : findEarlyLiabilitiseNequity 시작 "); }
		response.setContentType("application/json; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			ArrayList<EarlyStatementBean> liabNequiList = earlyStatementServiceFacade.findEarlyLiabilitiseNequity();
	        json.put("liabNequiList",liabNequiList);
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
		if(logger.isDebugEnabled()){ logger.debug(" EarlyFinancialPositionController : findEarlyLiabilitiseNequity 종료 "); }
		return null;
	}
	
	public ArrayList<EarlyStatementBean> findEarlyFinancialPosition(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()){ logger.debug(" EarlyFinancialPositionController : findEarlyFinancialPosition 시작 "); }
		response.setContentType("application/json; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			ArrayList<EarlyStatementBean> earlyFinacialList = earlyStatementServiceFacade.findEarlyFinancialPosition();
			json.put("earlyFinacialList",earlyFinacialList);
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
		if(logger.isDebugEnabled()){ logger.debug(" EarlyFinancialPositionController : findEarlyFinancialPosition 종료 "); }
		return null;
	}
}
