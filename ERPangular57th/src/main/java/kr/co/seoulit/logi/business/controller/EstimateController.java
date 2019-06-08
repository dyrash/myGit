package kr.co.seoulit.logi.business.controller;

import java.util.ArrayList;
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
import com.google.gson.reflect.TypeToken;
import com.google.gson.reflect.TypeToken;
import com.nexacro.xapi.data.PlatformData;

import kr.co.seoulit.common.mapper.DatasetBeanMapper;
import kr.co.seoulit.logi.business.serviceFacade.BusinessServiceFacade;
import kr.co.seoulit.logi.business.to.ContractDetailTO;
import kr.co.seoulit.logi.business.to.ContractTO;
import kr.co.seoulit.logi.business.to.EstimateDetailTO;
import kr.co.seoulit.logi.business.to.EstimateTO;
import kr.co.seoulit.sys.to.CodeTO;

@Controller
public class EstimateController{
	private static Gson gson = new GsonBuilder().serializeNulls().create();

	@Autowired
	private BusinessServiceFacade businessServiceFacade;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	private ModelMap modelMap = new ModelMap();
	private ModelAndView modelAndView = null;

	@RequestMapping("logi/business/findEstimateList.do")
	public ModelAndView findEstimateList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HashMap<String, Object> searchDate=new HashMap<>();
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		searchDate.put("fromDate", fromDate);
		searchDate.put("toDate", toDate);
		List<EstimateTO> estimateList = businessServiceFacade.findEstimateList(searchDate);
		List<EstimateDetailTO> estimateDetailList=new ArrayList<>();
		for(EstimateTO estimateTO:estimateList) {
			for(EstimateDetailTO estimateDetailTO:estimateTO.getEstimateDetailList()) {
				estimateDetailList.add(estimateDetailTO);
			}
		}
		modelMap.put("estimateList",estimateList);
		modelAndView = new ModelAndView("jsonView",modelMap);
		return modelAndView;

	}
	@RequestMapping("logi/business/findEstimateDialog.do")
	public ModelAndView findEstimateDialog(HttpServletRequest request, HttpServletResponse response) throws Exception{

		List<EstimateTO> estimateList = businessServiceFacade.findEstimateDialog();
		modelMap.put("estimateList",estimateList);
		modelAndView = new ModelAndView("jsonView",modelMap);
		return modelAndView;

	}

	@RequestMapping("logi/business/findEstimateDetailList.do")
	public ModelAndView findEstimateDetailList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String estimateNo=null;
		if(request.getParameterMap().size()>0) {
			estimateNo = request.getParameter("estimateNo");
		}
		List<EstimateDetailTO> estimateDetailList = businessServiceFacade.findEstimateDetailList(estimateNo);

		modelMap.put("estimateDetailList",estimateDetailList);
		modelAndView = new ModelAndView("jsonView",modelMap);
		return modelAndView;

	}

	@RequestMapping("logi/business/registerEstimateDetail.do")
	public void registerEstimateDetail(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String batchList=request.getParameter("inData");

		List<EstimateTO> estimateList = gson.fromJson(batchList, new TypeToken<List<EstimateTO>>() {}.getType());

		for(EstimateTO est:estimateList) {
			for(EstimateDetailTO edt:est.getEstimateDetailList()) {
				System.out.println(edt.getEstimateNo());
				System.out.println(edt.getEstimateDetailNo());
				System.out.println(edt.getStatus());
			}
		}


		List<EstimateDetailTO> estimateDetailList = gson.fromJson(batchList, new TypeToken<List<EstimateDetailTO>>() {}.getType());

		businessServiceFacade.registEstimateDetail(estimateList,estimateDetailList);

	}

	@RequestMapping("logi/business/findEstimateTo.do")
	public void findEstimateTo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String estimateNo=null;

		if(request.getParameterMap().size()>0) {
			estimateNo = request.getParameter("estimateNo");
		}
		List<EstimateTO> estimateTo = businessServiceFacade.findEstimateTo(estimateNo);
	}

}