package com.sh.account.slip.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.sh.account.slip.exception.SlipDataListException;
import com.sh.account.slip.serviceFacade.SlipServiceFacade;
import com.sh.account.slip.to.JournalBean;
import com.sh.account.slip.to.JournalDetailBean;
import com.sh.account.slip.to.SlipBean;


public class SlipController extends MultiActionController{
	protected final Log logger = LogFactory.getLog(this.getClass());
	private SlipServiceFacade slipServiceFacade;
	private ModelAndView modelAndView = null;
	private ModelMap modelMap = new ModelMap();
	private MessageSource messageSource;
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	   
	public void setSlipServiceFacade(SlipServiceFacade slipServiceFacade) {
		this.slipServiceFacade = slipServiceFacade;
	}

	
	
	public ModelAndView findSlipDataList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();  
		ArrayList<SlipBean> slipList=null;
	      try{
	         String slipDate1 = request.getParameter("slipDate1");
	         String slipDate2 = request.getParameter("slipDate2");
	         slipList=slipServiceFacade.findSlipDataList(slipDate1,slipDate2);
	         modelMap.put("slipList", slipList);  
	         modelMap.put("emptySlip", new SlipBean());	 
	         modelMap.put("emptyJournal", new JournalBean()); 
	         modelMap.put("emptyJournalDetail", new JournalDetailBean()); 
	         modelMap.put("errorCode", 1);
	         modelMap.put("errorMsg","데이터 조회 성공");
	      }catch (SlipDataListException e) {
	    	  String errorMsg=messageSource.getMessage("SlipDataListException",null,(Locale)session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME));
	          modelMap.put("errorCode", -2);
	          modelMap.put("errorMsg", errorMsg);
	          e.printStackTrace();
	      }
	      modelAndView = new ModelAndView("jsonView",modelMap);
          return modelAndView; 
	   }
	
    
     public ModelAndView findRangedSlipList(HttpServletRequest request, HttpServletResponse response){
    	 HttpSession session = request.getSession();
    	 try{
           String fromDate=request.getParameter("from");
           String toDate=request.getParameter("to");
           List<SlipBean> slipList=slipServiceFacade.findRangedSlipList(fromDate,toDate);
           modelMap.put("slipList",slipList);
           modelMap.put("errorCode", 1);
           modelMap.put("errorMsg", "데이터 조회 성공");
             }catch (DataAccessException e) {
            	 logger.fatal(e.getMessage());
                 modelMap.put("errorCode", -2);
                 modelMap.put("errorMsg", e.getMessage());
                 e.printStackTrace();
             }catch (SlipDataListException e) {
   	    	  String errorMsg=messageSource.getMessage("SlipDataListException",null,(Locale)session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME));
   	          modelMap.put("errorCode", -2);
   	          modelMap.put("errorMsg", errorMsg);
   	          e.printStackTrace();
   	      } 
             modelAndView = new ModelAndView("jsonView",modelMap);
             return modelAndView; 
         }
}
