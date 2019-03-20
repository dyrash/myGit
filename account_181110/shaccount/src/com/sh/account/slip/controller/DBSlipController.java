package com.sh.account.slip.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sh.account.slip.serviceFacade.SlipServiceFacade;
import com.sh.account.slip.serviceFacade.SlipServiceFacadeImpl;
import com.sh.account.slip.to.SlipBean;
import com.sh.common.exception.DataAccessException;
import com.sh.common.servlet.ModelAndView;
import com.sh.common.servlet.controller.MultiActionController;

import net.sf.json.JSONObject;

public class DBSlipController extends MultiActionController{
	protected final Log logger = LogFactory.getLog(this.getClass());
	private SlipServiceFacade slipServiceFacade = SlipServiceFacadeImpl.getInstance();
	
	
	
    public ModelAndView batchListProcess(HttpServletRequest request, HttpServletResponse response){
    	 if(logger.isDebugEnabled()){ logger.debug(" SlipController : batchListProcess 시작 "); }
         JSONObject json = new JSONObject();
         PrintWriter out = null;
         try{
             out = response.getWriter();
             String BatchList = request.getParameter("batchList");
             System.out.println("		 @@@@@@@@@"+BatchList);
             ObjectMapper mapper = new ObjectMapper();
             mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
             ArrayList<SlipBean> slipList = mapper.readValue(BatchList, new TypeReference<ArrayList<SlipBean>>(){});	//readValue로 String형태의 BatchList를 읽어와 
             slipServiceFacade.batchListProcess(slipList);
             System.out.println("		 @ "+slipList);
             json.put("errorCode", 1);
             json.put("errorMsg", "데이터 조회 성공");
         }catch(IOException e){    
        	 logger.fatal(e.getMessage());
             json.put("errorCode", -1);
             json.put("errorMsg", "오류발생");
         }catch (DataAccessException e) {  
        	 logger.fatal(e.getMessage());
             json.put("errorCode", -2);
             json.put("errorMsg", e.getMessage());
             e.printStackTrace();
          }
         out.println(json);
         out.close();      
         if(logger.isDebugEnabled()){ logger.debug(" SlipController : batchListProcess 종료 "); }
         return null;
     }
    
     public ModelAndView findDisApprovalSlipList(HttpServletRequest request, HttpServletResponse response) {
    	 if(logger.isDebugEnabled()){ logger.debug(" SlipController : findDisApprovalSlipList 시작 "); }
 		JSONObject json = new JSONObject();
 		PrintWriter out = null;
 		try {
 			out = response.getWriter();
 			ArrayList<SlipBean> disApprovalSlipList = slipServiceFacade.findDisApprovalSlipList();
 			json.put("disApprovalSlipList", disApprovalSlipList);
 			json.put("errorCode", 1);
 			json.put("errorMsg", "데이터 조회 성공");
 			
 		} catch (Exception error) {
 			logger.fatal(error.getMessage());
 			json.put("errorCode", -1);
 			json.put("errorMsg", "오류발생");
 			error.printStackTrace();
 		}
 		out.println(json);
 		out.close();
 		System.out.println("		@ JSON 데이터: "+json);
 		if(logger.isDebugEnabled()){ logger.debug(" SlipController : findDisApprovalSlipList 종료 "); }
 		return null;
 	}


}
