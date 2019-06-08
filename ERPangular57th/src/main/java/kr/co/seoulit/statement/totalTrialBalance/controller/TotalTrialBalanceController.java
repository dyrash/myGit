package kr.co.seoulit.statement.totalTrialBalance.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nexacro.xapi.data.PlatformData;

import kr.co.seoulit.statement.totalTrialBalance.serviceFacade.TotalTrialBalanceServiceFacade;
import kr.co.seoulit.statement.totalTrialBalance.to.TotalTrialBalanceTO;
import kr.co.seoulit.common.mapper.DatasetBeanMapper;

@Controller
public class TotalTrialBalanceController {

	@Autowired
	private TotalTrialBalanceServiceFacade statementServiceFacade;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환
	private ModelMap modelMap = new ModelMap();
	private ModelAndView modelAndView = null;


	@RequestMapping("statement/totalTrialBalance/findTotalTrialBalanceList.do")
	public ModelAndView findTotalTrialBalanceList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub

		String approvalDate = request.getParameter("approvalDate");
		if(approvalDate.equals("")) {

		} else {
	    	String a1=approvalDate.substring(0,4);
	    	String a2=approvalDate.substring(4,6);
	    	String a3=approvalDate.substring(6,8);
	    	approvalDate=a1+"-"+a2+"-"+a3;
	    	System.out.println("날짜확인"+approvalDate);
		}

		System.out.println(" approvalDate : " + approvalDate);

		HashMap<String,String> approvalDateMap= new HashMap<>();
		approvalDateMap.put("approvalDate", approvalDate);
		List<TotalTrialBalanceTO> totalTrialBalanceList = statementServiceFacade.findTotalTrialBalanceList(approvalDateMap);
		System.out.println(" sf -> findTotalTrialBalanceList ");

		modelMap.put("totalTrialBalanceList",totalTrialBalanceList);
		modelAndView = new ModelAndView("jsonView",modelMap);
		return modelAndView;
		}

}
