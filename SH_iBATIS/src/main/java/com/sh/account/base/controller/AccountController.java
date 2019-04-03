package com.sh.account.base.controller;

import java.util.ArrayList;
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

import com.sh.account.base.exception.AccountListNotFoundException;
import com.sh.account.base.serviceFacade.AccountServiceFacade;
import com.sh.account.base.to.AccountBean;
import com.sh.account.base.to.AccountControlBean;

public class AccountController extends MultiActionController{
	protected final Log logger = LogFactory.getLog(this.getClass());
	private AccountServiceFacade accountServiceFacade;
	private ModelAndView modelAndView = null;
    private ModelMap modelMap = new ModelMap();
    private MessageSource messageSource;
    public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	public void setAccountServiceFacade(AccountServiceFacade accountServiceFacade) {
		this.accountServiceFacade = accountServiceFacade;
	}
    
	 public ModelAndView findAccountList(HttpServletRequest request, HttpServletResponse response){
		 HttpSession session = request.getSession();       
		 try {
		 	ArrayList<AccountBean> accountList=accountServiceFacade.findAccountList();
		 	modelMap.put("accountList", accountList);
		 	modelMap.put("emptyAccountControl", new AccountControlBean());
		 	modelMap.put("errorCode", 1);
		 	modelMap.put("errorMsg","성공");
		 }catch (DataAccessException e) {	
        	logger.fatal(e.getMessage());
            modelMap.put("errorCode", -2);
            modelMap.put("errorMsg", e.getMessage());
            e.printStackTrace();
		 }catch (AccountListNotFoundException e) {
				String errorMsg = messageSource.getMessage("AccountListNotFoundException", null,
						(Locale) session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME));
				modelMap.put("errorCode", -2);
				modelMap.put("errorMsg", errorMsg);
				e.printStackTrace();	
		}
        modelAndView = new ModelAndView("jsonView",modelMap);
        return modelAndView;
	    }
	 
	 public ModelAndView editAccountList(HttpServletRequest request, HttpServletResponse response){
		 HttpSession session = request.getSession();
		 try{
	            AccountBean bean=new AccountBean();
	            bean.setAccountCode(request.getParameter("accountInnerCode"));
	            bean.setAccountName(request.getParameter("accountName"));
	            bean.setAccountDescription(request.getParameter("accountDescription"));
	            bean.setAccountCharacter(request.getParameter("accountCharacter"));
	            ArrayList<AccountBean> accountList=accountServiceFacade.editAccountList(bean);
	            modelMap.put("accountList", accountList); 
	            modelMap.put("emptyAccountControl", new AccountControlBean()); 
	            modelMap.put("errorCode", 1);
	            modelMap.put("errorMsg","성공");
	        }catch (DataAccessException e) {	
	        	logger.fatal(e.getMessage());
	            modelMap.put("errorCode", -2);
	            modelMap.put("errorMsg", e.getMessage());
	            e.printStackTrace();
	        }catch (AccountListNotFoundException e) {
				String errorMsg = messageSource.getMessage("AccountListNotFoundException", null,
						(Locale) session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME));
				modelMap.put("errorCode", -2);
				modelMap.put("errorMsg", errorMsg);
				e.printStackTrace();	
			}
	        modelAndView = new ModelAndView("jsonView",modelMap);
	        return modelAndView;
	    }    
	 public ModelAndView findParentAccountList(HttpServletRequest request, HttpServletResponse response){
		 HttpSession session = request.getSession();
		 try{
			 ArrayList<AccountBean> accountList=accountServiceFacade.findParentAccountList();
			 modelMap.put("accountList", accountList); 
			 modelMap.put("emptyAccountControl", new AccountControlBean()); 
			 modelMap.put("errorCode", 1);
			 modelMap.put("errorMsg","성공");
		 }catch (DataAccessException e) {	
			 logger.fatal(e.getMessage());
			 modelMap.put("errorCode", -2);
			 modelMap.put("errorMsg", e.getMessage());
			 e.printStackTrace();
		 } catch (AccountListNotFoundException e) {
				String errorMsg = messageSource.getMessage("AccountListNotFoundException", null,
						(Locale) session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME));
				modelMap.put("errorCode", -2);
				modelMap.put("errorMsg", errorMsg);
				e.printStackTrace();
		 }
		 modelAndView = new ModelAndView("jsonView",modelMap);
	        return modelAndView;
	 }   
	 
	 
	 
	 public ModelAndView findDetailAccountList(HttpServletRequest request, HttpServletResponse response){
		 HttpSession session = request.getSession();
		 String code=request.getParameter("code");
		 try{
			 ArrayList<AccountBean> accountList=accountServiceFacade.findDetailAccountList(code);
			 modelMap.put("accountList", accountList); 
			 modelMap.put("emptyAccountControl", new AccountControlBean()); 
			 modelMap.put("errorCode", 1);
			 modelMap.put("errorMsg","성공");
		 }catch (DataAccessException e) {	
			 logger.fatal(e.getMessage());
			 modelMap.put("errorCode", -2);
			 modelMap.put("errorMsg", e.getMessage());
			 e.printStackTrace();
		 } catch (AccountListNotFoundException e) {
				String errorMsg = messageSource.getMessage("AccountListNotFoundException", null,
						(Locale) session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME));
				modelMap.put("errorCode", -2);
				modelMap.put("errorMsg", errorMsg);
				e.printStackTrace();	
		 }
		 modelAndView = new ModelAndView("jsonView",modelMap);
	        return modelAndView;
	 }    
	 
	 
	 public ModelAndView findSelectAccountList(HttpServletRequest request, HttpServletResponse response){
		 HttpSession session = request.getSession();
		 String code=request.getParameter("accountCode");
		 try{
			 ArrayList<AccountBean> accountList=accountServiceFacade.findSelectAccountList(code);
			 modelMap.put("accountList", accountList); 
			 modelMap.put("emptyAccountControl", new AccountControlBean()); 
			 modelMap.put("errorCode", 1);
			 modelMap.put("errorMsg","성공");
		 }catch (DataAccessException e) {	
			 logger.fatal(e.getMessage());
			 modelMap.put("errorCode", -2);
			 modelMap.put("errorMsg", e.getMessage());
			 e.printStackTrace();
		 } catch (AccountListNotFoundException e) {
				String errorMsg = messageSource.getMessage("AccountListNotFoundException", null,
						(Locale) session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME));
				modelMap.put("errorCode", -2);
				modelMap.put("errorMsg", errorMsg);
				e.printStackTrace();
		 }
		 modelAndView = new ModelAndView("jsonView",modelMap);
	        return modelAndView;
	 }    
}
