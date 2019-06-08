package kr.co.seoulit.logi.purchase.controller;

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
import kr.co.seoulit.logi.purchase.serviceFacade.PurchaseServiceFacade;
import kr.co.seoulit.logi.purchase.to.ItemTO;

@Controller
public class ItemController {

   @Autowired
   private PurchaseServiceFacade purchaseServiceFacade;

   @Autowired
   private DatasetBeanMapper datasetBeanMapper;

   private ModelMap modelMap = new ModelMap();
   private ModelAndView modelAndView = null;

   @RequestMapping("logi/purchase/findItemList.do")
   public ModelAndView findItemList(HttpServletRequest request, HttpServletResponse response) throws Exception {
      List<ItemTO> itemList = purchaseServiceFacade.findItemList();

      modelMap.put("itemList",itemList);
      modelAndView = new ModelAndView("jsonView",modelMap);
      return modelAndView;

   }

   @RequestMapping("logi/purchase/batchItem.do")
   public void batchItem(HttpServletRequest request, HttpServletResponse response) throws Exception{
      PlatformData inData = (PlatformData) request.getAttribute("inData");

      List<ItemTO> itemList = datasetBeanMapper.datasetToBeans(inData, ItemTO.class);

      purchaseServiceFacade.batchItem(itemList);
   }
}