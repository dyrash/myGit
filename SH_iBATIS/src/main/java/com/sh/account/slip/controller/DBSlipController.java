package com.sh.account.slip.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sh.account.slip.serviceFacade.SlipServiceFacade;
import com.sh.account.slip.to.SlipBean;


public class DBSlipController extends MultiActionController{
	protected final Log logger = LogFactory.getLog(this.getClass());
	private SlipServiceFacade slipServiceFacade;
	private ModelAndView modelAndView = null;
	private ModelMap modelMap = new ModelMap();
	//private MessageSource messageSource;

	public void setSlipServiceFacade(SlipServiceFacade slipServiceFacade) {
		this.slipServiceFacade = slipServiceFacade;
	}
	
	
    public ModelAndView batchListProcess(HttpServletRequest request, HttpServletResponse response){
         try{
             String BatchList = request.getParameter("batchList");
             System.out.println("		 @@@@@@@@@"+BatchList);
             ObjectMapper mapper = new ObjectMapper();
             mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
             ArrayList<SlipBean> slipList = mapper.readValue(BatchList, new TypeReference<ArrayList<SlipBean>>(){});	//readValue로 String형태의 BatchList를 읽어와 
             slipServiceFacade.batchListProcess(slipList);
             System.out.println("		 @ "+slipList);
             modelMap.put("errorCode", 1);
             modelMap.put("errorMsg", "데이터 조회 성공");
         }catch(IOException e){    
        	 logger.fatal(e.getMessage());
        	 modelMap.put("errorCode", -1);
        	 modelMap.put("errorMsg", "오류발생");
         }catch (DataAccessException e) {  
        	 logger.fatal(e.getMessage());
        	 modelMap.put("errorCode", -2);
        	 modelMap.put("errorMsg", e.getMessage());
             e.printStackTrace();
          }
         modelAndView = new ModelAndView("jsonView",modelMap);
         return modelAndView;
     }
    
     public ModelAndView findDisApprovalSlipList(HttpServletRequest request, HttpServletResponse response) {
 		try {
 			ArrayList<SlipBean> disApprovalSlipList = slipServiceFacade.findDisApprovalSlipList();
 			modelMap.put("disApprovalSlipList", disApprovalSlipList);
 			modelMap.put("errorCode", 1);
 			modelMap.put("errorMsg", "데이터 조회 성공");
 			
 		} catch (Exception error) {
 			logger.fatal(error.getMessage());
 			modelMap.put("errorCode", -1);
 			modelMap.put("errorMsg", "오류발생");
 			error.printStackTrace();
 		}
 		System.out.println("		@ modelMap 데이터: "+modelMap);
	      modelAndView = new ModelAndView("jsonView",modelMap);
	      return modelAndView;
 	}


}
