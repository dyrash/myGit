package kr.co.seoulit.sys.controller;

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
import kr.co.seoulit.sys.serviceFacade.BaseServiceFacade;
import kr.co.seoulit.sys.to.CodeTO;
import kr.co.seoulit.sys.to.CustomerTO;
import kr.co.seoulit.sys.to.DepartmentTO;

@Controller
public class CustomerController {
	@Autowired
	private BaseServiceFacade baseServiceFacade;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환
	private ModelMap modelMap = new ModelMap();
	private ModelAndView modelAndView = null;

	@RequestMapping("sys/findCustomerList.do")
	public ModelAndView findCustomerList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<CustomerTO> customerList = baseServiceFacade.findCustomerList();
		modelMap.put("customerList",customerList);
		modelAndView = new ModelAndView("jsonView",modelMap);
		return modelAndView;

	}

	@RequestMapping("sys/batchCustomer.do")
	public void batchCustomer(HttpServletRequest request, HttpServletResponse response)throws Exception {
		String batch=request.getParameter("indata");

		List<CustomerTO> batchCustomerList= gson.fromJson(batch, new TypeToken<List<CustomerTO>>() {}.getType());

		baseServiceFacade.batchCustomer(batchCustomerList);



	}
}
