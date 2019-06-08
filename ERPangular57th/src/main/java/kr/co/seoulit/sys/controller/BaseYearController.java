package kr.co.seoulit.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nexacro.xapi.data.PlatformData;

import kr.co.seoulit.common.mapper.DatasetBeanMapper;
import kr.co.seoulit.sys.serviceFacade.BaseServiceFacade;
import kr.co.seoulit.sys.to.BaseYearTO;
import kr.co.seoulit.sys.to.BusinessPlaceTO;
import kr.co.seoulit.sys.to.CodeTO;
import kr.co.seoulit.sys.to.DepartmentTO;
import kr.co.seoulit.sys.to.SequenceTo;

@Controller
public class BaseYearController {

	@Autowired
	private BaseServiceFacade baseServiceFacade;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	private ModelMap modelMap = new ModelMap();
	private ModelAndView modelAndView = null;



	@RequestMapping("sys/findBaseYearList.do")
	public ModelAndView findBaseYearList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");

		Map<String,Object> vList=new HashMap<>();

		String businessPlaceCode=request.getParameter("businessPlaceCode").toUpperCase();

		vList.put("businessPlaceCode", businessPlaceCode);

		List<BaseYearTO> baseYearList = baseServiceFacade.findBaseYearList(vList);
		modelMap.put("baseYearList",baseYearList);
		modelAndView = new ModelAndView("jsonView",modelMap);
		return modelAndView;
	}

}