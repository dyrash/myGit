package kr.co.seoulit.acc.slip.controller;

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
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nexacro.xapi.data.PlatformData;
import com.google.gson.reflect.TypeToken;

import kr.co.seoulit.acc.slip.serviceFacade.SlipServiceFacade;
import kr.co.seoulit.acc.slip.to.JournalDetailTO;
import kr.co.seoulit.acc.slip.to.JournalTO;
import kr.co.seoulit.acc.slip.to.SlipTO;
import kr.co.seoulit.common.mapper.DatasetBeanMapper;


@Controller
public class SlipController{

   @Autowired
   private SlipServiceFacade slipServiceFacade;

   @Autowired
   private DatasetBeanMapper datasetBeanMapper;
   private ModelMap modelMap = new ModelMap();
   private ModelAndView modelAndView = null;
   private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환

   //승인,미결에따른 전표조회 메서드
   @RequestMapping("acc/slip/findSlipListByCondition.do")
   public ModelAndView findSlipListByCondition(HttpServletRequest request, HttpServletResponse response) throws Exception{
      // TODO Auto-generated method stub
      System.out.println(" findSlipListByCondition 시작 ");

      HashMap<String, Object> slipMap = new HashMap<>();

      String startDate=request.getParameter("startDate");
      String endDate=request.getParameter("endDate");
      String slipStatus=request.getParameter("slipStatus");

      slipMap.put("startDate", startDate);
      slipMap.put("endDate", endDate);
      slipMap.put("slipStatus", slipStatus);

      List<SlipTO> sliplist=slipServiceFacade.findSlipListByCondition(slipMap);

//        List<JournalTO> journalList=new ArrayList<>();
//      List<JournalDetailTO> journalDetailList=new ArrayList<>();
//
//      for(SlipTO slipTo:sliplist) {
//         List<JournalTO> journalBeanList=slipTo.getJournalToList();
//                for(JournalTO journalTO:journalBeanList)
//                {
//                 List<JournalDetailTO> journalDetailBeanList=journalTO.getJournalDetailToList();
//                 journalDetailList.addAll(journalDetailBeanList);
//                }
//         journalList.addAll(journalBeanList);
//      }
//

      modelMap.put("sliplist",sliplist);
//      modelMap.put("journalList",journalList);
//      modelMap.put("journalDetailList",journalDetailList);
      modelAndView = new ModelAndView("jsonView",modelMap);
      System.out.println(modelMap.toString());

      return modelAndView;


   }




   /*//전표 추가할때 전표 있는지 당일작성날짜꺼 조회해서 유효성 검사 메서드
   @RequestMapping("acc/slip/findSlipByReportingDate.do")
   public void findSlipByReportingDate(HttpServletRequest request, HttpServletResponse response) throws Exception{
      // TODO Auto-generated method stub
      System.out.println(" getSlipRowCount 시작 ");

      PlatformData inData = (PlatformData) request.getAttribute("inData");
      PlatformData outData = (PlatformData) request.getAttribute("outData");

      String reportingDate=inData.getVariable("reportingDate").getString();
      System.out.println("reportingDate : " + reportingDate);

      List<SlipTO> slipRowCount=slipServiceFacade.findSlipByReportingDate(reportingDate);
      System.out.println(" sf -> findSlipByReportingDate ");

      datasetBeanMapper.beansToDataset(outData, slipRowCount, SlipTO.class);
   }
*/
   //전표일괄저장
   @RequestMapping("acc/slip/batchSlipAndJournalAndJournalDetailInfo.do")
   public void batchSlipAndJournalAndJournalDetailInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
      // TODO Auto-generated method stub
      System.out.println(" batchSlipAndJournalAndJournalDetailInfo 시작 ");

      String batchList=request.getParameter("inData");

      JsonObject batchListJsonObject = new JsonParser().parse(batchList).getAsJsonObject();

      String slipList=gson.toJson(batchListJsonObject.get("slipInfo"));
      String journalList=gson.toJson(batchListJsonObject.get("journalInfo"));
      String journalDetailList=gson.toJson(batchListJsonObject.get("journalDetailInfo"));

      ArrayList<SlipTO> slipInfo = gson.fromJson(slipList,
            new TypeToken<ArrayList<SlipTO>>() {
            }.getType());


      ArrayList<JournalTO> journalInfo = gson.fromJson(journalList,
            new TypeToken<ArrayList<JournalTO>>() {
            }.getType());


      ArrayList<JournalDetailTO> journalDetailInfo = gson.fromJson(journalDetailList,
            new TypeToken<ArrayList<JournalDetailTO>>() {
            }.getType());



      slipServiceFacade.batchSlipAndJournalAndJournalDetailInfo((ArrayList)slipInfo,(ArrayList)journalInfo,(ArrayList)journalDetailInfo);
      System.out.println(" batchSlipAndJournalAndJournalDetailInfo 종료 ");
   }


   //승인으로 업데이트
   @RequestMapping("acc/slip/modifyApprovalSlipList.do")
   public void modifyApprovalSlipList(HttpServletRequest request, HttpServletResponse response) throws Exception {
      // TODO Auto-generated method stub
      System.out.println(" modifyApprovalSlipList 시작 ");

      PlatformData inData = (PlatformData) request.getAttribute("inData");
      PlatformData outData = (PlatformData) request.getAttribute("outData");

      List<SlipTO> slipInfo = datasetBeanMapper.datasetToBeans(inData, SlipTO.class);

      slipServiceFacade.modifyApprovalSlipList(slipInfo);

      System.out.println(" modifyApprovalSlipList 종료 ");

      datasetBeanMapper.beansToDataset(outData, slipInfo, SlipTO.class);
   }


}