package kr.co.seoulit.hr.circumstance.controller;

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
import kr.co.seoulit.hr.circumstance.serviceFacade.CircumstanceServiceFacade;
import kr.co.seoulit.hr.circumstance.to.SalPaymentDateTO;

@Controller
public class SalPaymentDateController {

	@Autowired
	private CircumstanceServiceFacade circumstanceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	   private ModelMap modelMap = new ModelMap();
	   private ModelAndView modelAndView = null;

	@RequestMapping("hr/circumstance/findSalPaymentDateList.do")
	public ModelAndView findSalPaymentDateList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String inputedYearMonth = request.getParameter("inputedYearMonth");
		List<SalPaymentDateTO> salPaymentsDateList = circumstanceFacade.findSalPaymentDateList(inputedYearMonth);
		modelMap.put("salPaymentsDateList",salPaymentsDateList);
	    modelAndView = new ModelAndView("jsonView",modelMap);
	    return modelAndView;
    }


	// 귀속년월의 급/상여 지급일 및 관련정보를 등록및 수정,삭제한 내용을 일괄처리하는 메서드
	@RequestMapping("hr/circumstance/batchSalPaymentDate.do")
	public void batchSalPaymentDate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		List<SalPaymentDateTO> salPaymentDateList = datasetBeanMapper.datasetToBeans(inData, SalPaymentDateTO.class);
		circumstanceFacade.batchSalPaymentDate(salPaymentDateList);
    }

}
