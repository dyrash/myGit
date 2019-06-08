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
import kr.co.seoulit.logi.production.serviceFacade.ProductionServiceFacade;
import kr.co.seoulit.logi.production.to.PrmTO;
import kr.co.seoulit.logi.production.to.WorkInstructionTO;
import kr.co.seoulit.logi.purchase.to.StockTO;
import kr.co.seoulit.logi.purchase.to.WarehousingTO;
import kr.co.seoulit.sys.to.CodeTO;

/************************************************************************
@Package		kr.co.seoulit.logi.production.controller
@Class			PrmController.java
@Author			wonminlee
@Description	소요량 전개 취합 관련 컨트롤러
@Create			2019.02.11
@Last Update    2019.02.26 메서드추가
************************************************************************/

@Controller
public class PrmController {
	@Autowired
	private ProductionServiceFacade productionServiceFacade;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환

	private ModelMap modelMap = new ModelMap();
	private ModelAndView modelAndView = null;


	@RequestMapping("logi/production/findPrmList.do")
	public ModelAndView findPrmList (HttpServletRequest request, HttpServletResponse response) throws Exception{
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("toDate");
		HashMap<String,Object> dateMap=new HashMap<>();
		dateMap.put("fromDate", fromDate);
		dateMap.put("toDate", toDate);
		List<PrmTO> prmList = productionServiceFacade.findPrmList(dateMap);

		modelMap.put("prmList",prmList);
		modelAndView = new ModelAndView("jsonView",modelMap);
		return modelAndView;


	}
	@RequestMapping("logi/production/registPrm.do")
	public void registPrm(HttpServletRequest request, HttpServletResponse response) throws Exception{
//창고업무
		HashMap<String, Object> paramMap = new HashMap<>();
		String workInstruction = request.getParameter("inData");
		List<WorkInstructionTO> prmList = gson.fromJson(workInstruction, new TypeToken<List<WorkInstructionTO>>() {}.getType());

		String deptCode = request.getParameter("deptCode");
		String empCode = request.getParameter("empCode");
		String reportingDate = request.getParameter("reportingDate");
		String businessCode = request.getParameter("businessCode");
		paramMap.put("deptCode", deptCode);
		paramMap.put("empCode", empCode);
		paramMap.put("reportingDate", reportingDate);
		paramMap.put("businessCode", businessCode);

		productionServiceFacade.registPrm(paramMap,prmList, null, null, null);

	}




}

