package kr.co.seoulit.logi.production.applicationService;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.logi.production.to.PrmTO;
import kr.co.seoulit.logi.production.to.WorkInstructionTO;
import kr.co.seoulit.logi.purchase.to.StockTO;
import kr.co.seoulit.logi.purchase.to.WarehousingTO;

public interface PrmApplicationService {

	List<PrmTO> findPrmList(HashMap<String,Object> dateMap);

	void registPrm(HashMap<String, Object> paramMap, List<WorkInstructionTO> workInstructionList, List<PrmTO> prmList,
			List<StockTO> stockList, List<WarehousingTO> warehousingList);

}
