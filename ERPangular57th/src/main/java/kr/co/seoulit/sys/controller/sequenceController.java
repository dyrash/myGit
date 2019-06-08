package kr.co.seoulit.sys.controller;

import java.util.List;

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
import kr.co.seoulit.sys.to.BusinessPlaceTO;
import kr.co.seoulit.sys.to.CodeTO;
import kr.co.seoulit.sys.to.SequenceTo;

@Controller
public class sequenceController {

   @Autowired
   private BaseServiceFacade baseServiceFacade;

   @Autowired
   private DatasetBeanMapper datasetBeanMapper;
   private ModelMap modelMap = new ModelMap();
   private ModelAndView modelAndView = null;


   @RequestMapping("sys/findSequenceNo.do")
   public ModelAndView findSequenceNo(HttpServletRequest request, HttpServletResponse response) throws Exception {
      SequenceTo seqTo=new SequenceTo();

      String findSeq = request.getParameter("findSeq");
      // System.out.println("@@@"+findSeq);
      String squenceNo = baseServiceFacade.findSequenceNo(findSeq);


      modelMap.put("squenceNo", squenceNo);
      modelAndView=new ModelAndView("jsonView",modelMap);

      return modelAndView;

   }

}