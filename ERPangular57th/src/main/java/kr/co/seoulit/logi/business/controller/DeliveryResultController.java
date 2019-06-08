package kr.co.seoulit.logi.business.controller;

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
import com.google.gson.reflect.TypeToken;
import com.nexacro.xapi.data.PlatformData;

import kr.co.seoulit.common.mapper.DatasetBeanMapper;
import kr.co.seoulit.logi.business.serviceFacade.BusinessServiceFacade;
import kr.co.seoulit.logi.business.to.CompleteDeliveryResultTO;
import kr.co.seoulit.logi.business.to.ContractDetailTO;
import kr.co.seoulit.logi.business.to.ContractTO;
import kr.co.seoulit.logi.business.to.DeliveryResultTO;
import kr.co.seoulit.logi.purchase.to.StockTO;
import kr.co.seoulit.sys.to.CustomerTO;

@Controller
public class DeliveryResultController{
	@Autowired
	private BusinessServiceFacade businessServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환
	private ModelMap modelMap = new ModelMap();
	private ModelAndView modelAndView = null;

	@RequestMapping("logi/business/findDeliveryResultList.do")
	public void findDeliveryResultList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PlatformData outData = (PlatformData) request.getAttribute("outData");

		List<DeliveryResultTO> deliveryResultList= businessServiceFacade.findDeliveryResultList();

        datasetBeanMapper.beansToDataset(outData, deliveryResultList, DeliveryResultTO.class);

	}
	@RequestMapping("logi/business/registDeliveryResult.do")
	public void registDeliveryResult(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String batch =request.getParameter("inData");
		System.out.println(batch);

		JsonObject batchJsonObject = new JsonParser().parse(batch).getAsJsonObject();

		String contractObj=gson.toJson(batchJsonObject.get("contractData"));

		String stockObj=gson.toJson(batchJsonObject.get("stockData"));

		String deliveryResultObj=gson.toJson(batchJsonObject.get("deliveryData"));


		List<ContractTO> contractList=gson.fromJson(contractObj, new TypeToken<List<ContractTO>>() {}.getType());

		List<StockTO> stockList = gson.fromJson(stockObj, new TypeToken<List<StockTO>>() {}.getType());

		List<DeliveryResultTO> deliveryResultList = gson.fromJson(deliveryResultObj, new TypeToken<List<DeliveryResultTO>>() {}.getType());

		for(StockTO stoc:stockList) {
			System.out.println("st "+stoc.getItemCode());
			System.out.println("st "+stoc.getItemName());
			System.out.println("st "+stoc.getWarehouseCode());
			System.out.println("st "+stoc.getStockAmount());
			System.out.println("st "+stoc.getUnitOfStock());

		}
		for(DeliveryResultTO drt:deliveryResultList) {
			System.out.println("drt "+drt.getItemCode());
			System.out.println("drt "+drt.getItemName());
			System.out.println("drt "+drt.getCustomerCode());
			System.out.println("drt "+drt.getWarehouseCode());
			System.out.println("drt "+drt.getContractDetailNo());
		}
		for(ContractTO cntl:contractList) {

			System.out.println("cntl "+cntl.getCustomerCode());
			System.out.println("cntl "+cntl.getContractNo());
			System.out.println("cntl "+cntl.getContractDate());
			for(ContractDetailTO cndtl:cntl.getContractDetailList()) {
				System.out.println("cndtl "+cndtl.getContractDetailNo());
			}

		}
		businessServiceFacade.registDeliveryResult(contractList,null,stockList,deliveryResultList);
	}

	@RequestMapping("logi/business/findCompleteDeliveryResultList.do")
	public void findCompleteDeliveryResultList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HashMap<String, Object> searchDate=new HashMap<>();
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		String fromDate = inData.getVariable("fromDate").getString();
		String toDate = inData.getVariable("toDate").getString();
		searchDate.put("fromDate", fromDate);
		searchDate.put("toDate", toDate);


		List<CompleteDeliveryResultTO> CompleteDeliveryResultList= businessServiceFacade.findCompleteDeliveryResultList(searchDate);

        datasetBeanMapper.beansToDataset(outData, CompleteDeliveryResultList, CompleteDeliveryResultTO.class);

	}
}
