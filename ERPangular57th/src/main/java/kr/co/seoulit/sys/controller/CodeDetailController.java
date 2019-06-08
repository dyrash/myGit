package kr.co.seoulit.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nexacro.xapi.data.PlatformData;

import kr.co.seoulit.common.mapper.DatasetBeanMapper;
import kr.co.seoulit.hr.circumstance.to.PayStepTO;
import kr.co.seoulit.sys.serviceFacade.BaseServiceFacade;
import kr.co.seoulit.sys.to.CodeDetailTO;
import kr.co.seoulit.sys.to.CodeTO;



@Controller
public class CodeDetailController {

	@Autowired
	private BaseServiceFacade baseServiceFacade;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환

	private ModelMap modelMap = new ModelMap();
	private ModelAndView modelAndView = null;

	@RequestMapping("sys/findCodeDetailList.do")
	public ModelAndView findCodeDetailList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<CodeDetailTO> codeDetailList = baseServiceFacade.findCodeDetailList();
		modelMap.put("codeDetailList",codeDetailList);
		modelAndView = new ModelAndView("jsonView",modelMap);
		return modelAndView;
	}

	@RequestMapping("sys/batchDetailCode.do")
	public void batchDetailCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//PlatformData inData = (PlatformData) request.getAttribute("inData");
		String batchList=request.getParameter("inData");
		System.out.println(batchList);
		//baseServiceFacade.batchDetailCodeProcess(codeDetailList);
	}
}
