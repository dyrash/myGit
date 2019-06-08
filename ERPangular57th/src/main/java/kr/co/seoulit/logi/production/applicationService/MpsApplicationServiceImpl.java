package kr.co.seoulit.logi.production.applicationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.logi.business.applicationService.ContractApplicationService;
import kr.co.seoulit.logi.business.to.ContractDetailTO;
import kr.co.seoulit.logi.production.dao.MpsDAO;
import kr.co.seoulit.logi.production.to.MpsTO;
import kr.co.seoulit.sys.applicationService.BaseApplicationService;

/************************************************************************
@Package		kr.co.seoulit.logi.production.applicationService
@Class			ProductionApplicationServiceImpl.java
@Author			wonminlee, kong
@Description	상품 AS
@Create			2019.02.11
@Last Update    2019.03.06 : AS분리
************************************************************************/

@Component
public class MpsApplicationServiceImpl implements MpsApplicationService {

	@Autowired
	private MpsDAO mpsDAO;
	@Autowired
	private ContractApplicationService contractApplicationService;
	@Autowired
	private BaseApplicationService baseApplicationService;


	//@@@@@@@@@@@@@@@@@@@@@  mps  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@Override
	public List<MpsTO> findMpsList(HashMap<String, Object> searchDate) {
		// TODO Auto-generated method stub
		return mpsDAO.selectMpsList(searchDate);
	}

	@Override
	public void registMps(List<MpsTO> mpst, List<ContractDetailTO> contractDetailList) {
		// TODO Auto-generated method stub
		List<MpsTO> mpsList=new ArrayList<>();
		for(ContractDetailTO contractDetailTO:contractDetailList) {
			String itemCode=contractDetailTO.getItemCode();
			String itemName=contractDetailTO.getItemName();
			String contractDetailNo=contractDetailTO.getContractDetailNo();
			String mpsPlanAmount=contractDetailTO.getContractAmount();
			String scheduledEndDate=Integer.toString(Integer.parseInt(contractDetailTO.getDueDateOfContract())-9);// -9 -2
			String dueDateOfMps=Integer.toString(Integer.parseInt(contractDetailTO.getDueDateOfContract())-2);// -9 -2
			String mpsNo=baseApplicationService.findSequenceNo("SERIAL_SEQ");
			contractDetailTO.setMpsApplyStatus("Y");
			contractDetailTO.setStatus("updated");

			MpsTO mpsTO=new MpsTO();
			mpsTO.setMpsNo("MPS"+mpsNo);
			mpsTO.setContractDetailNo(contractDetailNo);
			mpsTO.setItemCode(itemCode);
			mpsTO.setItemName(itemName);
			mpsTO.setMpsPlanAmount(mpsPlanAmount);
			mpsTO.setMpsPlanClassification("수주");
			mpsTO.setScheduledEndDate(scheduledEndDate);
			mpsTO.setDueDateOfMps(dueDateOfMps);
			mpsTO.setStatus("inserted");
			mpsList.add(mpsTO);


		}

		if(mpsList!=null) {
			for(MpsTO mpsTO : mpsList) {
				switch(mpsTO.getStatus()) {
				case "inserted" : mpsDAO.insertMps(mpsTO);	break;
				//case "updated" : contractDAO.updateContract(contractTO); break;
				//case "deleted" : contractDAO.deleteContract(contractTO); break;
				}
			}

		}

		if(contractDetailList!=null) {
			contractApplicationService.batchContract(null, contractDetailList);
		}
	}

}
