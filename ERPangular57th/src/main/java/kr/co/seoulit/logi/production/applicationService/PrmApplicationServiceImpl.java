package kr.co.seoulit.logi.production.applicationService;

import java.util.ArrayList;
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
import kr.co.seoulit.sys.applicationService.BaseApplicationService;

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
	@Autowired
	private BaseApplicationService baseApplicationService;

	@Override
	public List<PrmTO> findPrmList(HashMap<String,Object> dateMap){
		// TODO Auto-generated method stub
		return prmDAO.selectPrmList(dateMap);
	}


	@Override
	public void registPrm(HashMap<String, Object> logiSlipMap,List<WorkInstructionTO> workInstructionList, List<PrmTO> prm, List<StockTO> stoist , List<WarehousingTO> wh) {
		// TODO Auto-generated method stub

		List<WarehousingTO> warehousingList=new ArrayList<>();
		List<PrmTO> prmList=new ArrayList<>();
		List<StockTO> stockList=purchaseApplicationService.findStockList();
		for(WorkInstructionTO workInstruction:workInstructionList) {
			PrmTO prmTO=new PrmTO();
			WarehousingTO whTO=new WarehousingTO();
			StockTO stockTO=new StockTO();
			System.out.println("지시수량"+workInstruction.getWorkInstructionAmount());
			//Sysout
			String workInstructionNo=workInstruction.getWorkInstructionNo();
			String productionAmount=workInstruction.getWorkInstructionAmount();
			String itemCode=workInstruction.getItemCode();
			String itemName=workInstruction.getItemName();
			String unit=workInstruction.getUnitOfWorkInstruction();
			//
			String productionResultNo=baseApplicationService.findSequenceNo("PRM_SEQ");
			prmTO.setProductionResultNo("PRM"+productionResultNo);
			prmTO.setWorkInstructionNo(workInstructionNo);
			prmTO.setProductionAmount(productionAmount);
			prmTO.setItemCode(itemCode);
			prmTO.setItemName(itemName);
			prmTO.setStatus("inserted");
			prmList.add(prmTO);
			//

			String warehousingNo=baseApplicationService.findSequenceNo("WH_SEQ");
			whTO.setCustomer("BRC-01");
			whTO.setItemCode(itemCode);
			whTO.setItemName(itemName);
			whTO.setOrderAmount(productionAmount);
			whTO.setStatus("updated");
			whTO.setDescription("Y");
			whTO.setWarehousingAmount(productionAmount);
			whTO.setUnitWarehousing(unit);
			//whTO.setWarehousingDate();
			whTO.setWarehousingNo("PMH"+warehousingNo);
			warehousingList.add(whTO);
			//

			for(StockTO stock:stockList) {
				if(stock.getItemCode().equals(itemCode)) {
					String stockAmount=
							Integer.toString(
							Integer.parseInt(productionAmount)+Integer.parseInt(stock.getStockAmount())
							);
					System.out.println("창고수량"+stockAmount);
					stockTO.setStatus("updated");
					stockTO.setStockAmount(stockAmount);
				}

			}
			//
			workInstruction.setProductionStatus("Y");
			workInstruction.setStatus("updated");
		}

		String req="생산실적";
		logiSlipMap.put("req", req);
		logiSlipMap.put("prmList", prmList);
		workInstructionApplicationService.registWorkInstruction(workInstructionList,null,null,null);
		purchaseApplicationService.batchStock(stockList);//스톡어플리케이션 서비스
		purchaseApplicationService.registWarehousing(null,null,stockList,warehousingList);//웨얼하우징어플리케이션서비스로변경
		otherDeptslipApplicationService.registLogiSlipList(logiSlipMap);
		for(PrmTO prmTO : prmList) {
			switch(prmTO.getStatus()) {
			case "inserted" : prmDAO.insertPrm(prmTO); break;
			//case "update" : prmDAO.updatePrm(prmTO); break;
			//case "delete" : prmDAO.deletePrm(prmTO); break;
			}
		}
	}
}
