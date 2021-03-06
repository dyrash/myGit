package com.sh.account.slip.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class SlipReportController extends MultiActionController {

	private ModelAndView modelAndView = null;

	private ModelMap modelMap = new ModelMap();

	public ModelAndView findReport(HttpServletRequest request, HttpServletResponse response) {

			String slipNo = request.getParameter("slipNo");
			modelMap.put("slip_no", slipNo);
			modelMap.put("format", "pdf");
			modelAndView = new ModelAndView("pdfView",modelMap);
	        return modelAndView;
	}
}