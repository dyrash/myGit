package com.sh.account.base.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sh.account.base.serviceFacade.AccountServiceFacade;
import com.sh.account.base.to.AccountControlBean;
import com.sh.account.base.to.AccountDetailBean;
import com.sh.common.exception.DataAccessException;


public class AccountDetailController extends MultiActionController{
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	private AccountServiceFacade accountServiceFacade;
	private ModelAndView modelAndView = null;
    private ModelMap modelMap = new ModelMap();
    //private MessageSource messageSource;
    //public void setMessageSource(MessageSource messageSource) {
	//	this.messageSource = messageSource;
	//}
    public void setAccountServiceFacade(AccountServiceFacade accountServiceFacade) {
		this.accountServiceFacade = accountServiceFacade;
	}
	
	
	public ModelAndView findAccountControlList(HttpServletRequest request, HttpServletResponse response){
		if(logger.isDebugEnabled()){ logger.debug(" AccountDetailController : findAccountControlList 시작 "); }
        try{
            String accountInnerCode=request.getParameter("accountInnerCode");
            System.out.println("		@ 계정코드: "+accountInnerCode);
            ArrayList<AccountControlBean> accountControlList=accountServiceFacade.findAccountControlList(accountInnerCode);
            modelMap.put("accountControlList", accountControlList);
            modelMap.put("errorCode", 1);
            modelMap.put("errorMsg", "findAccountControlList에서 오류 발생");
           // System.out.println(json);
        }catch (DataAccessException e) {
        	logger.fatal(e.getMessage());
        	modelMap.put("errorCode", -2);
        	modelMap.put("errorMsg", e.getMessage());
            e.printStackTrace();
         }
        if(logger.isDebugEnabled()){ logger.debug(" AccountDetailController : findAccountControlList 종료 "); }
        modelAndView = new ModelAndView("jsonView",modelMap);        
        return modelAndView;
    }
	
	public ModelAndView findAccountDetailList(HttpServletRequest request, HttpServletResponse response){
		if(logger.isDebugEnabled()){ logger.debug(" AccountDetailController : findAccountDetailList 시작 "); }
        try{
            ArrayList<AccountDetailBean> controlItemList = accountServiceFacade.findAccountDetailList();
            modelMap.put("controlItemList", controlItemList);
            modelMap.put("errorCode", 1);
            modelMap.put("errorMsg","데이터를 가져 왔습니다");
           // System.out.println(json);
        }catch (DataAccessException e) {
        	logger.fatal(e.getMessage());
        	modelMap.put("errorCode", -2);
        	modelMap.put("errorMsg", e.getMessage());
            e.printStackTrace();
         }
        if(logger.isDebugEnabled()){ logger.debug(" AccountDetailController : findAccountDetailList 종료 "); }
        modelAndView = new ModelAndView("jsonView",modelMap);        
        return modelAndView;
    }
	
		
	public ModelAndView batchAccountControlListProcess(HttpServletRequest request, HttpServletResponse response){
		if(logger.isDebugEnabled()){ logger.debug(" AccountDetailController : batchAccountControlListProcess 시작 "); }
        try{
            String jsonList = request.getParameter("controlList");
            //System.out.println("		@ 컨트롤리스트: "+jsonList);
            ObjectMapper mapper = new ObjectMapper();
           
            ArrayList<AccountControlBean> accountControlList = mapper.readValue(jsonList, new TypeReference<ArrayList<AccountControlBean>>(){});

            accountServiceFacade.batchAccountControlListProcess(accountControlList);
            modelMap.put("errorCode", 1);
            modelMap.put("errorMsg", "데이터를 가져 왔습니다");
        }catch(IOException e){
        	logger.fatal(e.getMessage());
        	modelMap.put("errorCode", -1);
        	modelMap.put("errorMsg", e.getMessage());
            e.printStackTrace();
        }catch (DataAccessException e) {
        	logger.fatal(e.getMessage());
        	modelMap.put("errorCode", -2);
        	modelMap.put("errorMsg", e.getMessage());
            e.printStackTrace();
         }
        if(logger.isDebugEnabled()){ logger.debug(" AccountDetailController : batchAccountControlListProcess 종료 "); }
        modelAndView = new ModelAndView("jsonView",modelMap);        
        return modelAndView;
    }    

}
