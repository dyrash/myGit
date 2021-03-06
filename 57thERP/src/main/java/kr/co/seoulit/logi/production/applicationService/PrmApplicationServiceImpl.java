package kr.co.seoulit.logi.production.applicationService;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.acc.slip.applicationService.OtherDeptSlipApplicationService;
import kr.co.seoulit.logi.production.dao.PrmDAO;
import kr.co.seoulit.logi.production.to.PrmTO;
import kr.co.seoulit.logi.production.to.WorkInstructionTO;
import kr.co.seoulit.logi.purchase.applicationService.StockApplicationService;
import kr.co.seoulit.logi.purchase.to.StockTO;
import kr.co.seoulit.logi.purchase.to.WarehousingTO;

/************************************************************************
@Package		kr.co.seoulit.logi.production.PrmApplicationServiceImpl
@Class			PrmApplicationServiceImpl.java
@Author			wonminlee, kong
@Description	상품 AS
@Create			2019.03.07
@Last Update    2019.03.07 새로만들고 이전
************************************************************************/

@Component
public class PrmApplicationServiceImpl implements PrmApplicationService{

	@Autowired
	private StockApplicationService purchaseApplicationService;
	@Autowired
	private WorkInstructionApplicationService workInstructionApplicationService;
	@Autowired
	private PrmDAO prmDAO;
	@Autowired
	private OtherDeptSlipApplicationService otherDeptslipApplicationService;

	@Override
	public List<PrmTO> findPrmList(){
		// TODO Auto-generated method stub
		return prmDAO.selectPrmList();
	}


	@Override
	public void registPrm(HashMap<String, Object> logiSlipMap,List<WorkInstructionTO> workInstructionList, List<PrmTO> prmList, List<StockTO> stockList , List<WarehousingTO> warehousingList) {
		// TODO Auto-generated method stub
		String req="생산실적";
		logiSlipMap.put("req", req);
		logiSlipMap.put("prmList", prmList);
		/*
		 * 	String deptCode
			String empCode
			String reportingDate
			String businessCode 사업장코드(유니물류 BRC-02)
		 *
		 *
		 *
		 *
		 * */
		for(PrmTO prmTO : prmList) {

			switch(prmTO.getStatus()) {
			case "insert" : prmDAO.insertPrm(prmTO); break;
			//case "update" : prmDAO.updatePrm(prmTO); break;
			//case "delete" : prmDAO.deletePrm(prmTO); break;

			}

		}

		workInstructionApplicationService.registWorkInstruction(workInstructionList,null,null,null);
		purchaseApplicationService.batchStock(stockList);//스톡어플리케이션 서비스
		purchaseApplicationService.registWarehousing(null,null,stockList,warehousingList);//웨얼하우징어플리케이션서비스로변경
		otherDeptslipApplicationService.registLogiSlipList(logiSlipMap);
	}
}
