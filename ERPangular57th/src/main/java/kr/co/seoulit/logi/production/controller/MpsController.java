package kr.co.seoulit.logi.production.controller;

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
import com.nexacro.xapi.data.PlatformData;
import kr.co.seoulit.common.mapper.DatasetBeanMapper;
import kr.co.seoulit.logi.business.to.ContractDetailTO;
import kr.co.seoulit.logi.production.serviceFacade.ProductionServiceFacade;
import kr.co.seoulit.logi.production.to.MpsTO;
import kr.co.seoulit.sys.to.CodeTO;

/************************************************************************
@Package		kr.co.seoulit.logi.production.controller
@Class			MpsController.java
@Author			wonminlee, kong
@Description	주생산계획 관련 컨트롤러
@Create			2019.02.11
@Last Update    2019.02.15 : 주생산계획 조회 메서드생성 (wonminlee)
				2019.02.20 : MPS적용메서드 추가(kong)
************************************************************************/
@Controller
public class MpsController{
	@Autowired
	private ProductionServiceFacade productionServiceFacade;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환
	private ModelMap modelMap = new ModelMap();
	private ModelAndView modelAndView = null;

	//주생산계획 조회메서드
	@RequestMapping("logi/production/findMpsList.do")
	public ModelAndView findMpsList (HttpServletRequest request, HttpServletResponse response) throws Exception{
		HashMap<String, Object> searchDate=new HashMap<>();

		String fromDate = request.getParameter("fromDate");  //mrpSelect
		String toDate = request.getParameter("toDate"); //오늘날짜

		searchDate.put("fromDate", fromDate);
		searchDate.put("toDate", toDate);

		List<MpsTO> mpsList = productionServiceFacade.findMpsList(searchDate);
		modelMap.put("mpsList",mpsList);
		modelAndView = new ModelAndView("jsonView",modelMap);
		return modelAndView;

	}

	@RequestMapping("logi/production/registerMps.do")
	public void registerMps(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String batchData=request.getParameter("inData");

		List<ContractDetailTO> contractDetailList = gson.fromJson(batchData, new TypeToken<List<ContractDetailTO>>() {}.getType());

		productionServiceFacade.registMps(null,contractDetailList);
	}
}
