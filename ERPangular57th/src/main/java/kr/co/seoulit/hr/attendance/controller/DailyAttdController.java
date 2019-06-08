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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nexacro.xapi.data.PlatformData;

import kr.co.seoulit.common.mapper.DatasetBeanMapper;
import kr.co.seoulit.hr.attendance.serviceFacade.AttendanceServiceFacade;
import kr.co.seoulit.hr.attendance.to.DailyAttdTO;

@Controller
public class DailyAttdController {

   @Autowired
   private AttendanceServiceFacade attendanceServiceFacade;
   @Autowired
   private DatasetBeanMapper datasetBeanMapper;
   private ModelMap modelMap = new ModelMap();
   private ModelAndView modelAndView = null;
   private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환
 //일근태 목록을 가지고 오는 메서드
 	@RequestMapping("hr/attendance/findDailyAttdList.do")
 	public ModelAndView findDailyAttdList(HttpServletRequest request, HttpServletResponse response) throws Exception {

 		String empCode=request.getParameter("empCode");
 		String fromDate=request.getParameter("fromDate");
 		String toDate=request.getParameter("toDate");
 		System.out.println(empCode);

         HashMap<String,String> map=new HashMap<>();
         map.put("empCode",empCode);
         map.put("fromDate",fromDate);
         map.put("toDate",toDate);

 		List<DailyAttdTO> dailyAttdList=attendanceServiceFacade.findDailyAttdList(map);
 		modelMap.put("dailyAttdList",dailyAttdList);
 		modelAndView = new ModelAndView("jsonView",modelMap);
 		return modelAndView;
     }

   // 일근태 승인, 관리부분에서 조건에 따라 조회하는 메서드
   @RequestMapping("hr/attendance/findDailyAttdListByCondition.do")
   public ModelAndView findDailyAttdListByCondition(HttpServletRequest request, HttpServletResponse response) throws Exception {

      String approvalStatus = request.getParameter("approvalStatus");
      String basicDay = request.getParameter("basicDay");
      String deptCode = request.getParameter("deptCode");

      HashMap<String,Object> map=new HashMap<String,Object>();
      map.put("approvalStatus", approvalStatus);
      map.put("basicDay", basicDay);
      map.put("deptCode", deptCode);

      List<DailyAttdTO> dailyAttdList=attendanceServiceFacade.findDailyAttdListByCondition(map);
      //datasetBeanMapper.beansToDataset(outData, dailyAttdList, DailyAttdTO.class);

      modelMap.put("dailyAttdList",dailyAttdList);
      modelAndView = new ModelAndView("jsonView",modelMap);
      return modelAndView;
      }

   //일근태 등록,수정,삭제 메서드
   @RequestMapping("hr/attendance/batchDailyAttd.do")
   public void batchDailyAttd(HttpServletRequest request, HttpServletResponse response) throws Exception {

      String inData = request.getParameter("inData");

      //System.out.println(inData);

      ObjectMapper mapper = new ObjectMapper();

      List<DailyAttdTO> dailyAttdList = mapper.readValue(inData, new TypeReference<List<DailyAttdTO>>(){});

      //System.out.println(dailyAttdList);

      attendanceServiceFacade.batchDailyAttd(dailyAttdList);
    }

}