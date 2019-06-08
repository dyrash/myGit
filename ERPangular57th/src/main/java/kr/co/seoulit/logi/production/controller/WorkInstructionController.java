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
import com.nexacro.xapi.data.PlatformData;
import kr.co.seoulit.common.mapper.DatasetBeanMapper;
import kr.co.seoulit.logi.production.serviceFacade.ProductionServiceFacade;
import kr.co.seoulit.logi.production.to.MaterialCheckTempTO;
import kr.co.seoulit.logi.production.to.WorkInstructionTO;

/************************************************************************
@Package      kr.co.seoulit.logi.production.controller
@Class         WorkInstructionController.java
@Author         wonminlee
@Description   작업지시 컨트롤러
@Create         2019.02.11
@Last Update    2019.02.22 : 작업지시관련 메서드생성
************************************************************************/


@Controller
public class WorkInstructionController {


   private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환

   @Autowired
   private ProductionServiceFacade productionServiceFacade;

   @Autowired
   private DatasetBeanMapper datasetBeanMapper;
   private ModelMap modelMap = new ModelMap();
   private ModelAndView modelAndView = null;

   //1작업지시 추가버튼 메서드: PKG_WORK_INSTRUCTION.P_WORK_INSTRUCTION_OPEN 프로시저호출
   @RequestMapping("logi/production/findMaterialCheckTempList.do")
   public ModelAndView findMaterialCheckTempList(HttpServletRequest request, HttpServletResponse response) throws Exception {


      HashMap<String, Object> paramMap = new HashMap<>();
      String mrpGno = request.getParameter("mrpGno");
      paramMap.put("mrpGno", mrpGno);
      System.out.println(mrpGno);
       productionServiceFacade.findMaterialCheckTempList(paramMap);

      List<MaterialCheckTempTO> materialCheckList =(List<MaterialCheckTempTO>)paramMap.get("result");
   for(MaterialCheckTempTO mt:materialCheckList) {
      System.out.println(mt.getItemCode());
   }
      modelMap.put("materialCheckList",materialCheckList);
      modelAndView = new ModelAndView("jsonView",modelMap);
      return modelAndView;


   }
   //2프로시저 호출 실행후 콜백에서 워크인스트럭션 펑션 호출하면 실행되는 트랜잭션에서 아래메서드호출
   @RequestMapping("logi/production/findWorkInstructionList.do")
   public ModelAndView findWorkInstructionList(HttpServletRequest request, HttpServletResponse response) throws Exception {
      //애가 작업지끝나고 밑에 리스트 뿌리는애
      HashMap<String, Object> paramMap = new HashMap<>();
      String mrpGno = request.getParameter("mrpGno");
      paramMap.put("mrpGno", mrpGno);

      List<WorkInstructionTO> workInstructionList = productionServiceFacade.findWorkInstructionList(paramMap);
      modelMap.put("workInstructionList",workInstructionList);
      modelAndView = new ModelAndView("jsonView",modelMap);
      return modelAndView;

   }

   //작업지시에서
   @RequestMapping("logi/production/findWorkInstructionList2.do")
   public ModelAndView findWorkInstructionList2(HttpServletRequest request, HttpServletResponse response) throws Exception {

      List<WorkInstructionTO> workInstructionList = productionServiceFacade.findWorkInstructionList2();
      modelMap.put("workInstructionList",workInstructionList);
      modelAndView = new ModelAndView("jsonView",modelMap);
      return modelAndView;
   }

}