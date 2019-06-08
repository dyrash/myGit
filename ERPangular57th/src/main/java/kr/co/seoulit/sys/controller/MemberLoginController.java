package kr.co.seoulit.sys.controller;

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
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nexacro.xapi.data.PlatformData;
import kr.co.seoulit.hr.emp.to.EmployeeTO;

import kr.co.seoulit.common.mapper.DatasetBeanMapper;
import kr.co.seoulit.sys.serviceFacade.BaseServiceFacade;
import kr.co.seoulit.sys.to.MenuTO;

@Controller
public class MemberLoginController {

	@Autowired
	private BaseServiceFacade baseServiceFacade;

	@Autowired
   	private DatasetBeanMapper datasetBeanMapper;
   	private ModelMap modelMap = new ModelMap();
   	private ModelAndView modelAndView = null;
   	private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환


	@RequestMapping("sys/checkLogin.do")
	public ModelAndView checkLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, Object> loginInfo=new HashMap<>();

		String batchList=request.getParameter("inData");

	    JsonObject batchListJsonObject = new JsonParser().parse(batchList).getAsJsonObject();


		String businessPlaceCode = gson.toJson(batchListJsonObject.get("placeCode"));
		String deptCode = gson.toJson(batchListJsonObject.get("deptCode")).toUpperCase().replaceAll("\"","").trim();
		String empCode = gson.toJson(batchListJsonObject.get("employeeNo")).toUpperCase().replaceAll("\"","").trim();
		String password = gson.toJson(batchListJsonObject.get("password")).replaceAll("\"","").trim();

		loginInfo.put("businessPlaceCode", businessPlaceCode);

		loginInfo.put("deptCode", deptCode);
		loginInfo.put("empCode", empCode);
		loginInfo.put("password", password);
		//HashMap<String, Object> result = baseServiceFacade.accessToAuthority(loginInfo);
		EmployeeTO employee=baseServiceFacade.checkLogin(loginInfo);
		modelMap.put("employee",employee);
		modelAndView = new ModelAndView("jsonView",modelMap);
		return modelAndView;

	}
}
