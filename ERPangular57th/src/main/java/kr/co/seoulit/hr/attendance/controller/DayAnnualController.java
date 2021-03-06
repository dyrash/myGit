package kr.co.seoulit.hr.attendance.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nexacro.xapi.data.PlatformData;

import kr.co.seoulit.common.mapper.DatasetBeanMapper;
import kr.co.seoulit.hr.attendance.serviceFacade.AttendanceServiceFacade;
import kr.co.seoulit.hr.attendance.to.DayAnnualTO;

@Controller
public class DayAnnualController {
	/* AttendanceServiceFacade setting */
	@Autowired
	private AttendanceServiceFacade attendanceServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	private ModelMap modelMap = new ModelMap();
	private ModelAndView modelAndView = null;
	private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환
	/* 해당년도, 해당사원의 연차정보, 사원정보를 조회하는 메서드 */

	@RequestMapping("hr/attendance/findAnnualMgt.do")
	private void findAnnualMgt(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		String empCode = inData.getVariable("empCode").getString();
		String standardYear = inData.getVariable("standardYear").getString();
		HashMap<String, String> map = new HashMap<>();
		map.put("empCode", empCode);
		map.put("standardYear", standardYear);
		List<DayAnnualTO> dayAnnualList = attendanceServiceFacade.findAnnualMgt(map);
		datasetBeanMapper.beansToDataset(outData, dayAnnualList, DayAnnualTO.class);
	}

	// 연차를 신청하는 메서드
	@RequestMapping("hr/attendance/addDayAnnual.do")
	public void addDayAnnual(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		DayAnnualTO dayAnnualTO = datasetBeanMapper.datasetToBean(inData, DayAnnualTO.class);
		List<DayAnnualTO> dayAnnualList = attendanceServiceFacade.addDayAnnual(dayAnnualTO);
		datasetBeanMapper.beansToDataset(outData, dayAnnualList, DayAnnualTO.class);
	}

	// 연차 승인, 관리부분에서 조건에 따라 조회하는 메서드
	@RequestMapping("hr/attendance/findDayAnnualListByCondition.do")
	public ModelAndView findDayAnnualListByCondition(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String deptCode = request.getParameter("deptCode");
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String approvalStatus = request.getParameter("approvalStatus");

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("deptCode", deptCode);
		map.put("fromDate", fromDate);
		map.put("toDate", toDate);
		map.put("approvalStatus", approvalStatus);

		List<DayAnnualTO> dayAnnualList = attendanceServiceFacade.findDayAnnualListByCondition(map);
		// datasetBeanMapper.beansToDataset(outData, dayAnnualList, DayAnnualTO.class);
		modelMap.put("dayAnnualList", dayAnnualList);
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}

	// 연차를 일괄적으로 승인처리 하는 메서드
	@RequestMapping("hr/attendance/batchDayAnnual.do")
	public void batchDayAnnual(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String batchList = request.getParameter("inData");
		System.out.println(batchList);
		// List<DayAnnualTO> dayAnnualList=datasetBeanMapper.datasetToBeans(inData,
		// DayAnnualTO.class);
		List<DayAnnualTO> dayAnnualList = gson.fromJson(batchList, new TypeToken<List<DayAnnualTO>>() {
		}.getType());
		// attendanceServiceFacade.batchDayAnnual(dayAnnualList);
		for (DayAnnualTO dat : dayAnnualList) {
			System.out.println(dat.getEmpName());
		}
		attendanceServiceFacade.batchDayAnnual(dayAnnualList);
	}
}