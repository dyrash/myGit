package kr.co.seoulit.logi.business.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nexacro.xapi.data.PlatformData;

import kr.co.seoulit.common.mapper.DatasetBeanMapper;
import kr.co.seoulit.logi.business.exception.ContractNotFoundException;
import kr.co.seoulit.logi.business.serviceFacade.BusinessServiceFacade;
import kr.co.seoulit.logi.business.to.ContractDetailTO;
import kr.co.seoulit.logi.business.to.ContractTO;
import kr.co.seoulit.logi.business.to.EstimateDetailTO;
import kr.co.seoulit.logi.business.to.EstimateTO;

@Controller
public class ContractController {
   private static Gson gson = new GsonBuilder().serializeNulls().create();

   @Autowired
   private BusinessServiceFacade businessServiceFacade;

   @Autowired
   private DatasetBeanMapper datasetBeanMapper;

   @Autowired
   private DataSource dataSource;
   private ModelMap modelMap = new ModelMap();
   private ModelAndView modelAndView = null;

	@RequestMapping("logi/business/findContractList.do")
	public ModelAndView findContractList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, Object> searchDate = new HashMap<>();
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		searchDate.put("fromDate", fromDate);
		searchDate.put("toDate", toDate);
		List<ContractTO> contractList = businessServiceFacade.findContractList(searchDate);
		List<ContractDetailTO> contractDetailList=new ArrayList<>();
		for(ContractTO contractTO:contractList) {
			for(ContractDetailTO contractDetailTO:contractTO.getContractDetailList()) {
				contractDetailList.add(contractDetailTO);
			}
		}
		modelMap.put("contractList",contractList);
		modelAndView = new ModelAndView("jsonView",modelMap);
		return modelAndView;

	}

	 @RequestMapping("logi/business/findContractDetailList.do")
	   public ModelAndView findContractDetailList(HttpServletRequest request, HttpServletResponse response) throws Exception {
	      String contractNo=null;

	      if(request.getParameterMap().size()>0) {
	         contractNo = request.getParameter("contractNo");
	      }

	      List<ContractDetailTO> contractDetailList = businessServiceFacade.findContractDetailList(contractNo);

	      modelMap.put("contractDetailList",contractDetailList);
	      modelAndView = new ModelAndView("jsonView",modelMap);
	      return modelAndView;

	   }

	@RequestMapping("logi/business/findAllContractDetailList.do")
	public void findAllContractDetailList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		List<ContractDetailTO> contractDetailList = businessServiceFacade.findAllContractDetailList();

		datasetBeanMapper.beansToDataset(outData, contractDetailList, ContractDetailTO.class);

	}

	@RequestMapping("logi/business/findRangedContractDetailList.do")
	public ModelAndView findRangedContractDetailList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, Object> searchDate = new HashMap<>();

		HashMap<String,Object> value=new HashMap<>();

		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		value.put("fromDate", fromDate);
		value.put("toDate", toDate);

		System.out.println(value.get("fromDate"));

		if(value.get("fromDate").equals("mpsSelect")) {
			System.out.println(fromDate +" and "+toDate);
			searchDate.put("requestStatus", "mpsSelect");
			searchDate.put("value", "unDelivery");
		} else {
			System.out.println(" aaaaaaaaa ");
			searchDate.put("requestStatus", "date");
			searchDate.put("value", value);
		}
		List<ContractDetailTO> contractDetailList = businessServiceFacade.findRangedContractDetailList(searchDate);
		modelMap.put("contractDetailList",contractDetailList);
		modelAndView = new ModelAndView("jsonView",modelMap);
		return modelAndView;


	}

	  @RequestMapping("logi/business/registerContract.do")
	   public void registerContract(HttpServletRequest request, HttpServletResponse response) throws Exception {
	      String batchList=request.getParameter("inData");
	      System.out.println(batchList);

	      List<ContractTO> contractList = gson.fromJson(batchList, new TypeToken<List<ContractTO>>() {}.getType());

	      for(ContractTO cont:contractList) {
	         for(ContractDetailTO contd:cont.getContractDetailList()) {
	            System.out.println(contd.getContractNo());
	            System.out.println(contd.getContractDetailNo());
	            System.out.println(contd.getStatus());
	         }
	      }

	      List<ContractDetailTO> contractDetailList = gson.fromJson(batchList, new TypeToken<List<ContractDetailTO>>() {}.getType());

	      businessServiceFacade.registContract(contractList, contractDetailList);

	   }


	@RequestMapping("logi/business/pdfPrint.do")
	public ModelAndView pdfPrintEmp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 String contract_no = request.getParameter("contractNo");
		//response.getOutputStream().flush();

		ModelMap modelMap = new ModelMap();
		modelMap.put("format", "pdf");
		modelMap.put("contract_no", contract_no);
		modelMap.put("jdbcDataSource", dataSource);
		ModelAndView modelAndView = new ModelAndView("multiformat-view", modelMap);

		return modelAndView;
	}

	   @RequestMapping("logi/business/findContractTo.do")
	   public void findContractTo(HttpServletRequest request, HttpServletResponse response) throws Exception{
	      String contractNo=null;

	      if(request.getParameterMap().size()>0) {
	         contractNo = request.getParameter("contractNo");
	      }

	      List<ContractTO> contractTo = businessServiceFacade.findContractTo(contractNo);

	   }


}