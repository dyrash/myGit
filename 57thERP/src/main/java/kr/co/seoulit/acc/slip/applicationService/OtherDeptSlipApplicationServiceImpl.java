package kr.co.seoulit.acc.slip.applicationService;
/**
 * @Package  com.seoul.erp.common.OtherDeptSlipApplicationServiceImpl
 * @Class    LogiSlipController.java
 * @Create   2019. 03. 2.
 * @Author   허용석
 * @Description 물류전표관련 OtherDeptSlipApplicationServiceImpl 클래스
 *
 */
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.acc.slip.dao.JournalDAO;
import kr.co.seoulit.acc.slip.dao.SlipDAO;
import kr.co.seoulit.acc.slip.to.AccountControlDetailTO;
import kr.co.seoulit.acc.slip.to.JournalDetailTO;
import kr.co.seoulit.acc.slip.to.JournalTO;
import kr.co.seoulit.acc.slip.to.SlipTO;
import kr.co.seoulit.common.exception.ProcedureException;
import kr.co.seoulit.hr.circumstance.applicationService.SalPaymentDateApplicationService;
import kr.co.seoulit.hr.circumstance.exception.CircumstanceException;
import kr.co.seoulit.hr.circumstance.to.SalPaymentDateTO;
import kr.co.seoulit.hr.pay.applicationService.PayApplicationService;
import kr.co.seoulit.logi.business.applicationService.ContractApplicationService;
import kr.co.seoulit.logi.business.applicationService.DeliveryApplicationService;
import kr.co.seoulit.logi.business.to.ContractDetailTO;
import kr.co.seoulit.logi.business.to.ContractTO;
import kr.co.seoulit.logi.production.to.PrmTO;
import kr.co.seoulit.logi.purchase.applicationService.ItemApplicationService;
import kr.co.seoulit.logi.purchase.applicationService.OrderApplicationService;
import kr.co.seoulit.logi.purchase.applicationService.StockApplicationService;
import kr.co.seoulit.logi.purchase.to.ItemTO;
import kr.co.seoulit.logi.purchase.to.OrderDetailTO;
import kr.co.seoulit.logi.purchase.to.OrderInfoTO;
import kr.co.seoulit.sys.applicationService.BaseApplicationService;

@Component
public class OtherDeptSlipApplicationServiceImpl implements OtherDeptSlipApplicationService {

	@Autowired
	private SlipDAO slipDAO;
	@Autowired
	private JournalDAO journalDAO;
	@Autowired
	private ContractApplicationService contractApplicationService;
	@Autowired
	private BaseApplicationService baseApplicationService;
	@Autowired
	private OrderApplicationService orderApplicationService;
	@Autowired
	PayApplicationService payAppService;
	@Autowired
	private SalPaymentDateApplicationService salPaymentDateApplicationService;
	@Autowired
	private ItemApplicationService itemApplicationService;

	protected final Log logger = LogFactory.getLog(this.getClass());

	@Override
	public List<ContractDetailTO> findContractSlipDetailList(HashMap<String, Object> logiSlipReq) {
		// TODO Auto-generated method stub
		return contractApplicationService.findRangedContractDetailList(logiSlipReq);
	}

	@Override
	public List<OrderDetailTO> findOrderSlipDetailList(HashMap<String, Object> logiSlipReq) {
		// TODO Auto-generated method stub
		return orderApplicationService.findOrderDetailList(logiSlipReq);
	}

	@Override
	public void registLogiSlipList(HashMap<String, Object> logiSlipMap) {
		// TODO Auto-generated method stub
	/*	if (logger.isDebugEnabled()) {
			logger.debug("(registLogiSlipList) 시작");
		}*/
		String req = (String) logiSlipMap.get("req");
			switch(req){
			case "수주선수" : registContractSlipList(logiSlipMap); break;
			case "납품완료" : registContractSlipList(logiSlipMap); break;
			case "발주선급" : registOrderSlipList(logiSlipMap); break;
			case "입고완료" : registOrderSlipList(logiSlipMap); break;
			case "생산실적" : registProductionSlipList(logiSlipMap); break;
			}
		}
	public void registContractSlipList(HashMap<String, Object> logiSlipMap) {
		String deptCode = (String) logiSlipMap.get("deptCode");
		String empCode = (String) logiSlipMap.get("empCode");
		String reportingDate = (String) logiSlipMap.get("reportingDate");
		String businessCode = (String) logiSlipMap.get("businessCode");
		String req = (String) logiSlipMap.get("req");
		String period ="BRC-01_02"; //(String) logiSlipMap.get("period");

		List<ContractDetailTO> logiSlipList=(List<ContractDetailTO>) logiSlipMap.get("logiSlipList");
		List<ContractTO> contractTOList=(List<ContractTO>) logiSlipMap.get("contractTOList");
		List<ContractDetailTO>  contractDetailList=new ArrayList<>();

		for (ContractDetailTO logiSlip : logiSlipList) {
			for(ContractTO contractTO:contractTOList){
				if(contractTO.getContractNo().equals(logiSlip.getContractNo())){
					contractTO.setStatus("updated");
				}
			}
			HashMap<String, Object> variables = new HashMap<>();

			String slipseq = baseApplicationService.findSequenceNo("SLIP_SEQ");

			SlipTO slipTo = new SlipTO();
			slipTo.setSlipDescription(logiSlip.getContractNo());
			slipTo.setStatus("inserted");
			slipTo.setSlipStatus("미결");
			slipTo.setSlipNo(slipseq);
			slipTo.setSlipType("대체");
			slipTo.setApprovalDate("미결");
			slipTo.setApprovalEmpCode("미결");
			slipTo.setDeptCode(deptCode);
			slipTo.setReportingDate(reportingDate);
			slipTo.setReportingEmpCode(empCode);
			slipTo.setExpenseReport(req);
			slipTo.setAccountPeriodNo(period);
			List<JournalTO> journalToList = new ArrayList<>();

			variables.put("contractNo", logiSlip.getContractNo());
			ContractTO contractTO =(ContractTO) contractApplicationService.findContractTo((String) logiSlip.getContractNo()).get(0);
			contractDetailList=contractTO.getContractDetailList();

			for(ContractDetailTO contractDetailTO:contractDetailList){
				contractDetailTO.setStatus("updated");
				contractDetailTO.setDescription(logiSlip.getDescription());
			}

			for (int i = 0; i < 2; i++) {
				JournalTO journalTO = new JournalTO();
				journalTO.setStatus("inserted");
				journalTO.setJournalDescription(logiSlip.getContractDetailNo());


				String journalSeq = baseApplicationService.findSequenceNo("JOURNAL_SEQ");
				journalTO.setJournalNo(journalSeq);
				journalTO.setSlipNo(slipseq);
				journalTO.setSummaryComment(logiSlip.getItemCode() + "/" + logiSlip.getItemName());
				journalTO.setCustomerCode(contractTO.getCustomerCode());
				journalTO.setCustomerName(contractTO.getCustomerCode());

				List<JournalDetailTO> journaldetailTOList = new ArrayList<>();
				if (i == 0) {
					journalTO.setRightCreditsPrice(logiSlip.getSumPriceOfContract());
					journalTO.setLeftDebtorPrice("0");
					journalTO.setBalanceDivision("대변");
					journalTO.setAccountInnerCode("0401");
					journalTO.setAccountName("상품매출");

					List<AccountControlDetailTO> accountControlDetailTOList = journalDAO.selectAccountControlDetailList("0146");

					for (AccountControlDetailTO accountControlDetailTO : accountControlDetailTOList) {
						String journalDetailSeq = baseApplicationService.findSequenceNo("JOURNAL_DETAIL_SEQ");
						JournalDetailTO journaldetailTO = new JournalDetailTO();
						journaldetailTO.setStatus("inserted");
						journaldetailTO.setJournalNo(journalSeq);
						journaldetailTO.setAccountInnerCode("0401");
						journaldetailTO.setJournaldetailNo(journalDetailSeq);
						journaldetailTO.setItem(accountControlDetailTO.getAccountcontrolName());
						switch (accountControlDetailTO.getAccountcontrolName()) {
						case "수량":
							journaldetailTO.setValue(logiSlip.getContractAmount());
							break;
						case "사업장":
							journaldetailTO.setValue(businessCode);
							break;
						case "선급율":
							journaldetailTO.setValue(logiSlip.getDescription());
							break;
						default:
							journaldetailTO.setValue("검증필요");
							break;
						}

						journalDAO.insertJournalDetailInfo(journaldetailTO);
					}
				} else {
					journalTO.setBalanceDivision("차변");
					journalTO.setLeftDebtorPrice(logiSlip.getSumPriceOfContract());
					journalTO.setRightCreditsPrice("0");

					List<AccountControlDetailTO> accountControlDetailTOList = journalDAO.selectAccountControlDetailList("0401");
					journalTO.setAccountInnerCode("0102");
					journalTO.setAccountName("당좌예금");

					for (AccountControlDetailTO accountControlDetailTO : accountControlDetailTOList) {

						String journalDetailSeq = baseApplicationService.findSequenceNo("JOURNAL_DETAIL_SEQ");

						JournalDetailTO journaldetailTO = new JournalDetailTO();
						journaldetailTO.setStatus("inserted");
						journaldetailTO.setJournalNo(journalSeq);
						journaldetailTO.setAccountInnerCode("0102");
						journaldetailTO.setJournaldetailNo(journalDetailSeq);
						journaldetailTO.setItem(accountControlDetailTO.getAccountcontrolName());
						switch (accountControlDetailTO.getAccountcontrolName()) {
						case "금융기관":
							journaldetailTO.setValue("NH농협은행");
							break;
						case "선급율":
							journaldetailTO.setValue(logiSlip.getDescription());
							break;
						default:
							journaldetailTO.setValue("검증필요");
							break;
						}

						journalDAO.insertJournalDetailInfo(journaldetailTO);
					}

				}

				journalDAO.insertJournalInfo(journalTO);
			}

			slipDAO.insertSlipInfo(slipTo);
			}

		contractApplicationService.batchContract(contractTOList,contractDetailList);
		registOriginCostSlipList(logiSlipMap);
	}
	//////////////////////


	public void registOriginCostSlipList(HashMap<String, Object> logiSlipMap){
		String deptCode = (String) logiSlipMap.get("deptCode");
		String empCode = (String) logiSlipMap.get("empCode");
		String reportingDate = (String) logiSlipMap.get("reportingDate");
		String businessCode = (String) logiSlipMap.get("businessCode");
		String req = (String) logiSlipMap.get("req");
		String period ="BRC-01_02"; //(String) logiSlipMap.get("period");

		List<ContractDetailTO> logiSlipList=(List<ContractDetailTO>) logiSlipMap.get("logiSlipList");
		List<ContractTO> contractTOList=(List<ContractTO>) logiSlipMap.get("contractTOList");
		List<ContractDetailTO>  contractDetailList=new ArrayList<>();

		for (ContractDetailTO logiSlip : logiSlipList) {

			HashMap<String, Object> variables = new HashMap<>();

			String slipseq = baseApplicationService.findSequenceNo("SLIP_SEQ");

			SlipTO slipTo = new SlipTO();
			slipTo.setSlipDescription(logiSlip.getContractNo());
			slipTo.setStatus("inserted");
			slipTo.setSlipStatus("미결");
			slipTo.setSlipNo(slipseq);
			slipTo.setSlipType("대체");
			slipTo.setApprovalDate("미결");
			slipTo.setApprovalEmpCode("미결");
			slipTo.setDeptCode(deptCode);
			slipTo.setReportingDate(reportingDate);
			slipTo.setReportingEmpCode(empCode);
			slipTo.setExpenseReport(req);
			slipTo.setAccountPeriodNo(period);
			List<JournalTO> journalToList = new ArrayList<>();

			variables.put("contractNo", logiSlip.getContractNo());
			ContractTO contractTO =(ContractTO) contractApplicationService.findContractTo((String) logiSlip.getContractNo()).get(0);
			contractDetailList=contractTO.getContractDetailList();

			for (int i = 0; i < 2; i++) {
				JournalTO journalTO = new JournalTO();
				journalTO.setStatus("inserted");
				journalTO.setJournalDescription(logiSlip.getContractDetailNo());


				String journalSeq = baseApplicationService.findSequenceNo("JOURNAL_SEQ");
				journalTO.setJournalNo(journalSeq);
				journalTO.setSlipNo(slipseq);
				journalTO.setSummaryComment(logiSlip.getItemCode() + "/" + logiSlip.getItemName());
				journalTO.setCustomerCode(contractTO.getCustomerCode());
				journalTO.setCustomerName(contractTO.getCustomerCode());
				String cost=itemApplicationService.findItemCost(logiSlip.getItemCode());
				String originCost=Integer.toString
						(Integer.parseInt(logiSlip.getSumPriceOfContract())-
								(Integer.parseInt(cost)*Integer.parseInt(logiSlip.getContractAmount())));
				List<JournalDetailTO> journaldetailTOList = new ArrayList<>();
				if (i == 0) {

					journalTO.setRightCreditsPrice("0");
					journalTO.setBalanceDivision("차변");
					journalTO.setAccountInnerCode("0451");
					journalTO.setAccountName("매출원가");

					journalTO.setLeftDebtorPrice(originCost);

					List<AccountControlDetailTO> accountControlDetailTOList = journalDAO.selectAccountControlDetailList("0451");

					for (AccountControlDetailTO accountControlDetailTO : accountControlDetailTOList) {
						String journalDetailSeq = baseApplicationService.findSequenceNo("JOURNAL_DETAIL_SEQ");
						JournalDetailTO journaldetailTO = new JournalDetailTO();
						journaldetailTO.setStatus("inserted");
						journaldetailTO.setJournalNo(journalSeq);
						journaldetailTO.setAccountInnerCode("0451");
						journaldetailTO.setJournaldetailNo(journalDetailSeq);
						journaldetailTO.setItem(accountControlDetailTO.getAccountcontrolName());
						switch (accountControlDetailTO.getAccountcontrolName()) {
						case "수량":
							journaldetailTO.setValue(logiSlip.getContractAmount());
							break;
						case "사업장":
							journaldetailTO.setValue(businessCode);
							break;
						case "선급율":
							journaldetailTO.setValue(logiSlip.getDescription());
							break;
						default:
							journaldetailTO.setValue("검증필요");
							break;
						}

						journalDAO.insertJournalDetailInfo(journaldetailTO);
					}
				} else {
					journalTO.setBalanceDivision("대변");
					journalTO.setLeftDebtorPrice("0");
					journalTO.setRightCreditsPrice(originCost);

					List<AccountControlDetailTO> accountControlDetailTOList = journalDAO.selectAccountControlDetailList("0401");
					journalTO.setAccountInnerCode("0146");
					journalTO.setAccountName("상품");

					for (AccountControlDetailTO accountControlDetailTO : accountControlDetailTOList) {

						String journalDetailSeq = baseApplicationService.findSequenceNo("JOURNAL_DETAIL_SEQ");

						JournalDetailTO journaldetailTO = new JournalDetailTO();
						journaldetailTO.setStatus("inserted");
						journaldetailTO.setJournalNo(journalSeq);
						journaldetailTO.setAccountInnerCode("0146");
						journaldetailTO.setJournaldetailNo(journalDetailSeq);
						journaldetailTO.setItem(accountControlDetailTO.getAccountcontrolName());
						switch (accountControlDetailTO.getAccountcontrolName()) {
						case "창고":
							journaldetailTO.setValue("진주창고");
							break;
						case "수량":
							journaldetailTO.setValue(logiSlip.getContractAmount());
							break;
						default:
							journaldetailTO.setValue("검증필요");
							break;
						}

						journalDAO.insertJournalDetailInfo(journaldetailTO);
					}

				}

				journalDAO.insertJournalInfo(journalTO);
			}

			slipDAO.insertSlipInfo(slipTo);
			}

	}

	public void registOrderSlipList(HashMap<String, Object> logiSlipMap) {
		String deptCode = (String) logiSlipMap.get("deptCode");
		String empCode = (String) logiSlipMap.get("empCode");
		String reportingDate = (String) logiSlipMap.get("reportingDate");
		String businessCode = (String) logiSlipMap.get("businessCode");
		String req = (String) logiSlipMap.get("req");
		String period ="BRC-01_02"; //(String) logiSlipMap.get("period");

		List<OrderDetailTO> logiSlipList=(List<OrderDetailTO>) logiSlipMap.get("logiSlipList");
		List<OrderInfoTO> orderInfoList=(List<OrderInfoTO>) logiSlipMap.get("orderInfoList");

		for (OrderDetailTO logiSlip : logiSlipList) {

			HashMap<String, Object> variables = new HashMap<>();

			String slipseq = baseApplicationService.findSequenceNo("SLIP_SEQ");

			SlipTO slipTo = new SlipTO();
			slipTo.setSlipDescription(logiSlip.getOrderDetailNo());
			slipTo.setStatus("inserted");
			slipTo.setSlipStatus("미결");
			slipTo.setSlipNo(slipseq);
			slipTo.setSlipType("대체");
			slipTo.setApprovalDate("미결");
			slipTo.setApprovalEmpCode("미결");
			slipTo.setDeptCode(deptCode);
			slipTo.setReportingDate(reportingDate);
			slipTo.setReportingEmpCode(empCode);
			slipTo.setExpenseReport(req);
			slipTo.setAccountPeriodNo(period);
			List<JournalTO> journalToList = new ArrayList<>();


			for (int i = 0; i < 2; i++) {
				JournalTO journalTO = new JournalTO();
				journalTO.setJournalDescription(logiSlip.getOrderDetailNo());
				journalTO.setStatus("inserted");
				String journalSeq = baseApplicationService.findSequenceNo("JOURNAL_SEQ");
				journalTO.setJournalNo(journalSeq);
				journalTO.setSlipNo(slipseq);
				journalTO.setSummaryComment(logiSlip.getItemCode() + "/" + logiSlip.getItemName());

				for(OrderInfoTO orderInfoTO:orderInfoList){
					if(orderInfoTO.getOrderNo().equals(logiSlip.getOrderNo())){
						orderInfoTO.setStatus("updated");
						journalTO.setCustomerCode(orderInfoTO.getCustomerCode());
						journalTO.setCustomerName(orderInfoTO.getCustomerCode());
					}
				}


				List<JournalDetailTO> journaldetailTOList = new ArrayList<>();
				if (i == 0) {
					journalTO.setRightCreditsPrice(logiSlip.getSumPriceOfOrder());
					journalTO.setLeftDebtorPrice("0");
					journalTO.setBalanceDivision("대변");
					List<AccountControlDetailTO> accountControlDetailTOList = journalDAO.selectAccountControlDetailList("0501");
					journalTO.setAccountInnerCode("0102");
					journalTO.setAccountName("당좌예금");

					for (AccountControlDetailTO accountControlDetailTO : accountControlDetailTOList) {
						String journalDetailSeq = baseApplicationService.findSequenceNo("JOURNAL_DETAIL_SEQ");
						JournalDetailTO journaldetailTO = new JournalDetailTO();
						journaldetailTO.setStatus("inserted");
						journaldetailTO.setJournalNo(journalSeq);
						journaldetailTO.setAccountInnerCode("0102");
						journaldetailTO.setJournaldetailNo(journalDetailSeq);
						journaldetailTO.setItem(accountControlDetailTO.getAccountcontrolName());
						switch (accountControlDetailTO.getAccountcontrolName()) {
						case "금융기관":
							journaldetailTO.setValue("NH농협은행");
							break;
						case "비고":
							journaldetailTO.setValue("선급율 "+logiSlip.getDescription());
							break;
						default:
							journaldetailTO.setValue("검증필요");
							break;
						}

						journalDAO.insertJournalDetailInfo(journaldetailTO);
					}
				} else {
					List<AccountControlDetailTO> accountControlDetailTOList = journalDAO.selectAccountControlDetailList("0153");
					journalTO.setBalanceDivision("차변");
					journalTO.setRightCreditsPrice("0");
					journalTO.setLeftDebtorPrice(logiSlip.getSumPriceOfOrder());
					journalTO.setAccountInnerCode("0153");
					journalTO.setAccountName("원재료");
					for (AccountControlDetailTO accountControlDetailTO : accountControlDetailTOList) {

						String journalDetailSeq = baseApplicationService.findSequenceNo("JOURNAL_DETAIL_SEQ");

						JournalDetailTO journaldetailTO = new JournalDetailTO();
						journaldetailTO.setStatus("inserted");
						journaldetailTO.setJournalNo(journalSeq);
						journaldetailTO.setAccountInnerCode("0153");
						journaldetailTO.setJournaldetailNo(journalDetailSeq);
						journaldetailTO.setItem(accountControlDetailTO.getAccountcontrolName());
						switch (accountControlDetailTO.getAccountcontrolName()) {
						case "수량":
							journaldetailTO.setValue(logiSlip.getOrderAmount());
							break;
						case "사업장":
							journaldetailTO.setValue(businessCode);
							break;
						case "선급율":
							journaldetailTO.setValue(logiSlip.getDescription());
							break;
						default:
							journaldetailTO.setValue("검증필요");
							break;
						}

						journalDAO.insertJournalDetailInfo(journaldetailTO);
					}

				}

				journalDAO.insertJournalInfo(journalTO);
			}

			slipDAO.insertSlipInfo(slipTo);
			}
		orderApplicationService.registOrder(orderInfoList, logiSlipList, null);
	}

	@Override
	public void registProductionSlipList(HashMap<String, Object> logiSlipMap) {
		// TODO Auto-generated method stub
		String deptCode = (String) logiSlipMap.get("deptCode");
		String empCode = (String) logiSlipMap.get("empCode");
		String reportingDate = (String) logiSlipMap.get("reportingDate");
		String businessCode = (String) logiSlipMap.get("businessCode");
		String req = (String) logiSlipMap.get("req");
		List<PrmTO> logiSlipList=( List<PrmTO>)logiSlipMap.get("prmList");
		String period ="BRC-01_02"; //(String) logiSlipMap.get("period");
		for (PrmTO logiSlip : logiSlipList) {

			HashMap<String, Object> variables = new HashMap<>();

			String slipseq = baseApplicationService.findSequenceNo("SLIP_SEQ");
			String price="0";
			SlipTO slipTo = new SlipTO();
			slipTo.setSlipDescription(logiSlip.getWorkInstructionNo());
			slipTo.setStatus("inserted");
			slipTo.setSlipStatus("미결");
			slipTo.setSlipNo(slipseq);
			slipTo.setSlipType("대체");
			slipTo.setApprovalDate("미결");
			slipTo.setApprovalEmpCode("미결");
			slipTo.setAuthorizationStatus("미결");
			slipTo.setDeptCode(deptCode);
			slipTo.setReportingDate(reportingDate);
			slipTo.setReportingEmpCode(empCode);
			slipTo.setExpenseReport(req);
			slipTo.setAccountPeriodNo(period);
			List<JournalTO> journalToList = new ArrayList<>();

			List<ItemTO> itemList=(List<ItemTO>)itemApplicationService.findItemList();

			for(ItemTO item:itemList){
				if(item.getItemCode().equals(logiSlip.getItemCode())){
				price=item.getStandardUnitPrice();
				System.out.println("getStandardUnitPrice"+price);
				}
			}
			price=""+Integer.parseInt(logiSlip.getProductionAmount())*Integer.parseInt(price);
			for (int i = 0; i < 2; i++) {
				JournalTO journalTO = new JournalTO();
				journalTO.setStatus("inserted");
				journalTO.setJournalDescription(logiSlip.getProductionResultNo());

				System.out.println("getStandardUnitPrice*getProductionAmount"+price);

				String journalSeq = baseApplicationService.findSequenceNo("JOURNAL_SEQ");
				journalTO.setJournalNo(journalSeq);
				journalTO.setSlipNo(slipseq);
				journalTO.setSummaryComment(logiSlip.getItemCode() + "/" + logiSlip.getItemName());
				journalTO.setCustomerCode(businessCode);
				journalTO.setCustomerName(businessCode);

				List<JournalDetailTO> journaldetailTOList = new ArrayList<>();
				if (i == 0) {
					journalTO.setRightCreditsPrice("0");
					journalTO.setLeftDebtorPrice(price);
					journalTO.setBalanceDivision("차변");
					journalTO.setAccountInnerCode("0146");
					journalTO.setAccountName("상품");

					List<AccountControlDetailTO> accountControlDetailTOList = journalDAO.selectAccountControlDetailList("0146");

					for (AccountControlDetailTO accountControlDetailTO : accountControlDetailTOList) {
						String journalDetailSeq = baseApplicationService.findSequenceNo("JOURNAL_DETAIL_SEQ");
						JournalDetailTO journaldetailTO = new JournalDetailTO();
						journaldetailTO.setStatus("inserted");
						journaldetailTO.setJournalNo(journalSeq);
						journaldetailTO.setAccountInnerCode("0146");
						journaldetailTO.setJournaldetailNo(journalDetailSeq);
						journaldetailTO.setItem(accountControlDetailTO.getAccountcontrolName());
						switch (accountControlDetailTO.getAccountcontrolName()) {
						case "수량":
							journaldetailTO.setValue(logiSlip.getProductionAmount());
							break;
						case "사업장":
							journaldetailTO.setValue(businessCode);
							break;
						case "선급율":
							journaldetailTO.setValue(req);
							break;
						default:
							journaldetailTO.setValue("검증필요");
							break;
						}

						journalDAO.insertJournalDetailInfo(journaldetailTO);
					}
				} else {
					journalTO.setBalanceDivision("대변");
					journalTO.setLeftDebtorPrice("0");
					journalTO.setRightCreditsPrice(price);

					List<AccountControlDetailTO> accountControlDetailTOList = journalDAO.selectAccountControlDetailList("0169");
					journalTO.setAccountInnerCode("0169");
					journalTO.setAccountName("재공품");

					for (AccountControlDetailTO accountControlDetailTO : accountControlDetailTOList) {

						String journalDetailSeq = baseApplicationService.findSequenceNo("JOURNAL_DETAIL_SEQ");

						JournalDetailTO journaldetailTO = new JournalDetailTO();
						journaldetailTO.setStatus("inserted");
						journaldetailTO.setJournalNo(journalSeq);
						journaldetailTO.setAccountInnerCode("0169");
						journaldetailTO.setJournaldetailNo(journalDetailSeq);
						journaldetailTO.setItem(accountControlDetailTO.getAccountcontrolName());
						switch (accountControlDetailTO.getAccountcontrolName()) {
						case "창고":
							journaldetailTO.setValue("진주창고");
							break;
						case "수량":
							journaldetailTO.setValue(logiSlip.getProductionAmount());
							break;
						default:
							journaldetailTO.setValue("검증필요");
							break;
						}

						journalDAO.insertJournalDetailInfo(journaldetailTO);
					}
				}

				journalDAO.insertJournalInfo(journalTO);
			}
			slipDAO.insertSlipInfo(slipTo);
			}
	}






	@Override
	public HashMap<String, Object> registHrSlipList(HashMap<String, Object> hrSlipMap) throws CircumstanceException {
		// TODO Auto-generated method stub
		String inputedYearMonth=(String)hrSlipMap.get("paymentDate");
		inputedYearMonth=inputedYearMonth.substring(0, 6);
		System.out.println(inputedYearMonth);

		List<SalPaymentDateTO> salPaymentDateList=salPaymentDateApplicationService.findSalPaymentDateList(inputedYearMonth);
		for(SalPaymentDateTO salPaymentDateTO:salPaymentDateList){
			if(salPaymentDateTO.getSlipRegistStatus()!=null){
				if(salPaymentDateTO.getSlipRegistStatus().equals("Y")){
					throw new CircumstanceException("이미 지급된 월급입니다");
				}
			}
		}
		return slipDAO.registHrSlipProcess(hrSlipMap);
	}

}



