package com.sh.account.base.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sh.account.base.serviceFacade.AccountServiceFacade;
import com.sh.account.base.serviceFacade.AccountServiceFacadeImpl;
import com.sh.account.base.to.AccountBean;
import com.sh.account.base.to.AccountControlBean;
import com.sh.common.exception.DataAccessException;
import com.sh.common.servlet.ModelAndView;
import com.sh.common.servlet.controller.MultiActionController;

import net.sf.json.JSONObject;

public class AccountController extends MultiActionController{
	protected final Log logger = LogFactory.getLog(this.getClass());
	public AccountServiceFacade accountServiceFacade=AccountServiceFacadeImpl.getInstance();
    
	 public ModelAndView findAccountList(HttpServletRequest request, HttpServletResponse response){
		 if(logger.isDebugEnabled()){ logger.debug(" AccountController : findAccountList 시작 "); }
	        JSONObject json = new JSONObject();
	        PrintWriter out = null;
	        try{
	            out = response.getWriter();
	            ArrayList<AccountBean> accountList=accountServiceFacade.findAccountList();
	            json.put("accountList", accountList); 
	            json.put("emptyAccountControl", new AccountControlBean()); 
	            json.put("errorCode", 1);
	            json.put("errorMsg","오류발생");
	        }catch(IOException e){
	        	logger.fatal(e.getMessage());
	            json.put("errorCode", -1);
	            json.put("errorMsg", e.getMessage());
	            e.printStackTrace();
	        }catch (DataAccessException e) {	
	        	logger.fatal(e.getMessage());
	            json.put("errorCode", -2);
	            json.put("errorMsg", e.getMessage());
	            e.printStackTrace();
	         }
	        out.println(json);
	        out.close();
	        if(logger.isDebugEnabled()){ logger.debug(" AccountController : findAccountList 종료 "); }
	        return null;
	    }
	 
	 public ModelAndView editAccountList(HttpServletRequest request, HttpServletResponse response){
		 if(logger.isDebugEnabled()){ logger.debug(" AccountController : editAccountList 시작 "); }
	        JSONObject json = new JSONObject();
	        PrintWriter out = null;
	        try{
	            out = response.getWriter();
	            AccountBean bean=new AccountBean();
	            bean.setAccountCode(request.getParameter("accountInnerCode"));
	            bean.setAccountName(request.getParameter("accountName"));
	            bean.setAccountDescription(request.getParameter("accountDescription"));
	            bean.setAccountCharacter(request.getParameter("accountCharacter"));
	            ArrayList<AccountBean> accountList=accountServiceFacade.editAccountList(bean);
	            json.put("accountList", accountList); 
	            json.put("emptyAccountControl", new AccountControlBean()); 
	            json.put("errorCode", 1);
	            json.put("errorMsg","오류발생");
	        }catch(IOException e){
	        	logger.fatal(e.getMessage());
	            json.put("errorCode", -1);
	            json.put("errorMsg", e.getMessage());
	            e.printStackTrace();
	        }catch (DataAccessException e) {	
	        	logger.fatal(e.getMessage());
	            json.put("errorCode", -2);
	            json.put("errorMsg", e.getMessage());
	            e.printStackTrace();
	         }
	        out.println(json);
	        out.close();
	        if(logger.isDebugEnabled()){ logger.debug(" AccountController : editAccountList 종료 "); }
	        return null;
	    }    
	 public ModelAndView findParentAccountList(HttpServletRequest request, HttpServletResponse response){
		 if(logger.isDebugEnabled()){ logger.debug(" AccountController : findParentAccountList 시작 "); }
		 JSONObject json = new JSONObject();
		 PrintWriter out = null;
		 try{
			 out = response.getWriter();
			 ArrayList<AccountBean> accountList=accountServiceFacade.findParentAccountList();
			 json.put("accountList", accountList); 
			 json.put("emptyAccountControl", new AccountControlBean()); 
			 json.put("errorCode", 1);
			 json.put("errorMsg","오류발생");
		 }catch(IOException e){
			 logger.fatal(e.getMessage());
			 json.put("errorCode", -1);
			 json.put("errorMsg", e.getMessage());
			 e.printStackTrace();
		 }catch (DataAccessException e) {	
			 logger.fatal(e.getMessage());
			 json.put("errorCode", -2);
			 json.put("errorMsg", e.getMessage());
			 e.printStackTrace();
		 }
		 out.println(json);
		 out.close();
		 if(logger.isDebugEnabled()){ logger.debug(" AccountController : findParentAccountList 종료 "); }
		 return null;
	 }   
	 
	 
	 
	 public ModelAndView findDetailAccountList(HttpServletRequest request, HttpServletResponse response){
		 if(logger.isDebugEnabled()){ logger.debug(" AccountController : findDetailAccountList 시작 "); }
		 JSONObject json = new JSONObject();
		 PrintWriter out = null;
		 try{
			 String code=request.getParameter("code");
			 out = response.getWriter();
			 ArrayList<AccountBean> accountList=accountServiceFacade.findDetailAccountList(code);
			 json.put("accountList", accountList); 
			 json.put("emptyAccountControl", new AccountControlBean()); 
			 json.put("errorCode", 1);
			 json.put("errorMsg","오류발생");
		 }catch(IOException e){
			 logger.fatal(e.getMessage());
			 json.put("errorCode", -1);
			 json.put("errorMsg", e.getMessage());
			 e.printStackTrace();
		 }catch (DataAccessException e) {	
			 logger.fatal(e.getMessage());
			 json.put("errorCode", -2);
			 json.put("errorMsg", e.getMessage());
			 e.printStackTrace();
		 }
		 out.println(json);
		 out.close();
		 if(logger.isDebugEnabled()){ logger.debug(" AccountController : findDetailAccountList 종료 "); }
		 return null;
	 }    
	 
	 
	 public ModelAndView findSelectAccountList(HttpServletRequest request, HttpServletResponse response){
		 if(logger.isDebugEnabled()){ logger.debug(" AccountController : findSelectAccountList 시작 "); }
		 JSONObject json = new JSONObject();
		 PrintWriter out = null;
		 try{
			 String code=request.getParameter("accountCode");
			 //System.out.println(code);
			 out = response.getWriter();
			 ArrayList<AccountBean> accountList=accountServiceFacade.findSelectAccountList(code);
			 json.put("accountList", accountList); 
			 json.put("emptyAccountControl", new AccountControlBean()); 
			 json.put("errorCode", 1);
			 json.put("errorMsg","오류발생");
		 }catch(IOException e){
			 logger.fatal(e.getMessage());
			 json.put("errorCode", -1);
			 json.put("errorMsg", e.getMessage());
			 e.printStackTrace();
		 }catch (DataAccessException e) {	
			 logger.fatal(e.getMessage());
			 json.put("errorCode", -2);
			 json.put("errorMsg", e.getMessage());
			 e.printStackTrace();
		 }
		 out.println(json);
		 out.close();
		 if(logger.isDebugEnabled()){ logger.debug(" AccountController : findSelectAccountList 종료 "); }
		 return null;
	 }    

}
