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
import kr.co.seoulit.hr.attendance.to.DailyAttdRestTO;

@Controller
public class DailyAttdRestController {
   /* AttendanceServiceFacade setting */
   @Autowired
   private AttendanceServiceFacade attendanceServiceFacade;
   @Autowired
   private DatasetBeanMapper datasetBeanMapper;
   private ModelMap modelMap = new ModelMap();
   private ModelAndView modelAndView = null;
   private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환
   //근태외 목록을 가지고오는 메서드
   @RequestMapping("hr/attendance/findAttdRestList.do")
   public void findAttdRestList(HttpServletRequest request, HttpServletResponse response) throws Exception {

      PlatformData inData = (PlatformData) request.getAttribute("inData");
      PlatformData outData = (PlatformData) request.getAttribute("outData");
      String empCode = inData.getVariable("empCode").getString();
      String fromDate = inData.getVariable("fromDate").getString();
      String toDate = inData.getVariable("toDate").getString();

      HashMap<String,Object> map=new HashMap<String,Object>();
      map.put("empCode", empCode);
      map.put("fromDate", fromDate);
      map.put("toDate", toDate);

      List<DailyAttdRestTO> dailyAttdRestList=attendanceServiceFacade.findAttdRestList(map);
      datasetBeanMapper.beansToDataset(outData, dailyAttdRestList, DailyAttdRestTO.class);
    }

   // 근태외 승인부분에서 일괄적으로 처리하는 메서드
   @RequestMapping("hr/attendance/batchDailyAttdRest.do")
   public void batchDailyAttdRest(HttpServletRequest request, HttpServletResponse response) throws Exception {

      //PlatformData inData = (PlatformData) request.getAttribute("inData");
      String batchList=request.getParameter("inData");
      System.out.println(batchList);
      //List<DailyAttdRestTO> dailyAttdRestList=datasetBeanMapper.datasetToBeans(inData, DailyAttdRestTO.class);
      List<DailyAttdRestTO> dailyAttdRestList= gson.fromJson(batchList, new TypeToken<List<DailyAttdRestTO>>() {}.getType());
      //attendanceServiceFacade.batchDailyAttdRest(dailyAttdRestList);
      for(DailyAttdRestTO dt:dailyAttdRestList) {
         System.out.println(dt.getEmpName());
      }
      attendanceServiceFacade.batchDailyAttdRest(dailyAttdRestList);
    }
   // 근태외 승인, 일근태 관리부분에서 조건에 따라 조회하는 메서드
   @RequestMapping("hr/attendance/findAttdRestListByCondition.do")
   public ModelAndView findAttdRestListByCondition(HttpServletRequest request, HttpServletResponse response) throws Exception {


      String approvalStatus = request.getParameter("approvalStatus");
      String basicDay = request.getParameter("basicDay");
      String deptCode = request.getParameter("deptCode");

      HashMap<String,Object> map=new HashMap<String,Object>();
      map.put("approvalStatus", approvalStatus);
      map.put("basicDay", basicDay);
      map.put("deptCode", deptCode);

      List<DailyAttdRestTO> DailyAttdRestList=attendanceServiceFacade.findAttdRestListByCondition(map);
      //datasetBeanMapper.beansToDataset(outData, DailyAttdRestList, DailyAttdRestTO.class);
      modelMap.put("DailyAttdRestList",DailyAttdRestList);
      modelAndView = new ModelAndView("jsonView",modelMap);
      return modelAndView;
   }
}