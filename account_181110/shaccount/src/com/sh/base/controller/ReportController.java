package com.sh.base.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sh.common.servlet.ModelAndView;
import com.sh.common.servlet.controller.MultiActionController;
import com.sh.common.sl.ServiceLocator;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class ReportController extends MultiActionController{
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	 private ModelAndView modelAndView = null;
	 
	 public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
	    
		 if(logger.isDebugEnabled()){ logger.debug(" ReportController : handleRequestInternal 시작 "); }
	        HashMap<String, Object> parameters = new HashMap<>();
	        response.setContentType("application/json; charset=UTF-8");
	        response.setCharacterEncoding("utf-8");
	        try {
	            String slipNo = request.getParameter("slip_no");
	            parameters.put("slip_no", slipNo);
	            
	            DataSource dataSource = ServiceLocator.getInstance().getDataSource("jdbc/ac2");
	            Connection conn = dataSource.getConnection();
	            
	            InputStream inputStream = new FileInputStream("C:\\Users\\hyun3\\workspace\\shaccount\\WebContent\\resources\\reportform\\report11.jrxml");
	            
	            JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
	            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
	            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
	            
	            ServletOutputStream out = response.getOutputStream();
	            response.setContentType("application/pdf");
	            JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	            
	            out.println();
	            out.flush();
	            
	        } catch (Exception error) {
	        	logger.fatal(error.getMessage());
	        	error.printStackTrace();
	        }
	        if(logger.isDebugEnabled()){ logger.debug(" ReportController : handleRequestInternal 종료 "); }
	        return modelAndView;
	    }
}
