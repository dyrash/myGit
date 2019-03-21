<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
font {
	color: white;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>전표입력</title>
<script>

var empCode = "${sessionScope.empCode}";
var empName = "${sessionScope.empName}";
var deptCode = "${sessionScope.deptCode}";
var empAuthority = "${sessionScope.authority}";
var slipList = [];
var emptySlip = {};
var emptyJournal = {};
var emptyJournalDetail = {};   	// 분개상세 빈 객체
var slipjour = {};				//extend(카피)를 하기위한 멤버 셋팅
var serchSlipDate;
var slipStatus;        			// 전표 상태
var slipNo;            			// 전표 번호
var slipRow;        			// 전표 row
var slipId;
var journalStatus;      		// 분개 상태
var journalNo;      			// 분개 코드
var journalRow;         		// 분개 row
var selJournalId;
var slipSelection;       		// 전표에서 선택한 row
var journalSelection;   		// 분개에서 선택한 row
var debitTotalPrice = 0;
var creditTotalPrice=0;
var debitList = [];
var creditList = [];

var checkapprovalState;

   $(document).ready(function(){
	   $.datepicker.setDefaults({
			dateFormat : 'yy-mm-dd',
			prevText : '이전 달',
			nextText : '다음 달',
			monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월',
					'8월', '9월', '10월', '11월', '12월' ],
			monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월',
					'7월', '8월', '9월', '10월', '11월', '12월' ],
			dayNames : [ '일', '월', '화', '수', '목', '금', '토' ],
			dayNamesShort : [ '일', '월', '화', '수', '목', '금', '토' ],
			dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
			showMonthAfterYear : true,
			changeMonth: true,
	        changeYear: true,
			yearSuffix : '년'
		}); /*한글로 달력 보이기 위해서 */

      today = $.datepicker.formatDate($.datepicker.ATOM, new Date()); //jQuery datepicker(달력)&오늘날짜입력
      $("#to").val(today);
      $("#from").val(today);

       /* 버튼이벤트  */
      $("input[type=button]").button();
      $("#slipDate").button().datepicker({dateFormat: "yy-mm-dd"});  // 오늘날짜 표시
      $("#from").button().datepicker({dateFormat: "yy-mm-dd"});
      $("#to").button().datepicker({dateFormat: "yy-mm-dd"});
      $("#slipSearchButton").click(slipSearchDataFunc);
      $("#slipUpdateButton").click(slipUpdateFunc);
      $("#slipInsertButton").click(slipInsertFunc);
      $("#journalInsertButton").click(journalInsertFunc);
      $("#journalUpdateButton").click(journalUpdateFunc);
      $("#journalDeleteButton").click(journalDeleteFunc);
      $("#slipDeleteButton").click(slipDeleteFunc);
      $("#batchSaveButton").click(batchSaveFunc);
      $("#pdfOpenButton").click(pdfOpenFunc);
      //$("#journalDetailSave").click(saveJournalDetail2);
      
      showSlipSearch();
      showJournalList();
      showCreditGrid(creditTotalPrice,creditList);
      showDebitGrid(debitTotalPrice,debitList);
      
   });
   
   function slipSearchDataFunc() {			// 조회하려는 시작날짜와 종료날짜에 대해서 전표 조회하는 버튼 이벤트
      serchSlipDate1=$("#from").val();
      serchSlipDate2=$("#to").val();
      findSlipDataList(serchSlipDate1,serchSlipDate2);
/*       $("#slipDate").hide();   전표조회하는 버튼 안숨길꺼라서 그냥 주석처리 
      $("#slipSearchButton").hide(); 
      $("#reStartButton").show();  */           
}
   
/*    function reOpenFunc(){
	   location.href="${pageContext.request.contextPath}/account/slipform.html";
   } */
   
   
   /* PDF 보기 */
   function pdfOpenFunc(){
        window.open("${pageContext.request.contextPath}/base/report.html?slip_no="+slipNo, "window", "width=1600, height=1000");
   }

   /* 데이타 가져오기  */
   function findSlipDataList(serchSlipDate1,serchSlipDate2) {
/* 		if(serchSlipDate1==''||serchSlipDate2=='')
			errorCheckDialog("기간을 설정해주세요","warning"); */
       $.ajax({
           url: "${pageContext.request.contextPath}/account/slip/slip.do",
           type: "post",
           data: {"method" : "findSlipDataList", slipDate1: serchSlipDate1, slipDate2: serchSlipDate2 },
           dataType: "json",
           success: function(data, textStatus, jqXHR) {

              console.log(data);
              console.log(data.slipList);
              console.log(data.emptySlip);
              console.log(data.emptyJournal);
              console.log(data.emptyJournalDetail);
              console.log(data.emptyJournalDetail);
              console.log(creditTotalPrice);
              console.log(creditList);
              console.log(debitTotalPrice);
              console.log(debitList);
              if(serchSlipDate1==''||serchSlipDate2=='')
      			errorCheckDialog("기간을 설정해주세요","warning");
              else if(data.errorCode < 0) {
                   alert(data.errorMsg);
               } else {
               if(data.slipList.length == 0){
            	   errorCheckDialog("조회된 전표정보가 없습니다","warning");
               }
              
        	           	   
                  slipList = data.slipList;   // 날짜에 존재하는 전표데이터 가져옴
                  emptySlip = data.emptySlip;
                  emptyJournal = data.emptyJournal;
                  emptyJournal.journalDetailBean = data.emptyJournalDetail;
                  emptyJournalDetail = data.emptyJournalDetail;//새로운 전표 or 분개 생성위해  빈객체 가져옴
                   // 조회후 빈 객체 내용 추가
              	  generateBase();
                   // 조회된 전표 출력
                  showSlipSearch();
                  showJournalList();
                  showCreditGrid(creditTotalPrice,creditList);
                  showDebitGrid(debitTotalPrice,debitList);
               }
           },
           error: function(jqXHR, textStatus, error) {
               alert("전표 조회 오류입니다");
           }
       });
   }

   /* 전표,분개,분개상세를 추가하기 위한 빈객체를 세팅하는 작업 */
   function generateBase() {
      emptySlip.status = "insert";
      emptySlip.reportingDate = $("#to").val();
      emptySlip.reportingEmpCode = empCode;
      emptySlip.reportingEmpName = empName;
      emptySlip.deptCode = "DPT-01";
      emptySlip.accountPeriodNo = "BRC-01_05";
      emptySlip.slipType = "선택";
      emptySlip.slipStatus = "미결";
      emptySlip.accountDifference = "0";
      //emptySlip.approval_seq = "0";
      
      
      emptyJournal.status = "insert";
      emptyJournal.balanceDiff = "선택";
      emptyJournal.price = "0";
      emptyJournal.descCode="코드찾기";
      emptyJournal.voucherCode="검색";
      emptyJournal.accountInnerCode="검색";
      //emptyJournal.customerCode="코드찾기";
   }

   /* 전표 그리드 */ /* 화면에 얻어온 전표목록을 띄우는 함수 */
   function showSlipSearch(){
      $.jgrid.gridUnload("#slipGrid");
      $("#slipGrid").jqGrid({
         data : slipList,
         datatype : "local",
         jsonReader :{
         root : "slipList"
         },
           colNames: ["전표번호","부서코드","결의일자","품의내역","전표구분","작성자코드","작성자","대차차액","승인자","승인상태","승인일자","상태","분개"],//"순번" 빼둠
           colModel: [
                  	{name :"slipNo",width :450,editable:false}, 
                    {name :"deptCode", width : 100 , align: "center", editable: false},
                    {name :"reportingDate",width :150 , align :"center",editable:false},
                    {name :"requestName",width :250 , align :"center",editable:true},
                    {name :"slipType",width :100,align: "center", editable: true, edittype: "select", editoptions: {value: "선택:선택;대체:대체;입금:입금;출금:출금"} },  //, edittype: "select", editoptions: {value: "선택:선택;일반:일반;매입:매입;매출:매출;수금:수금;반제:반제"}
                    {name :"reportingEmpCode",hidden: true,width :120 ,editable:false},
                    {name :"reportingEmpName",width :100 , align :"center",editable:false},
                    {name :"accountDifference",hidden:true,width :100 , align :"center",editable:false, formatter:'currency', formatoptions:{thousandsSeparator:",", decimalPlaces:0}},
                    {name :"approvalEmpName",width :100 , align :"center",editable:false},
                    {name :"slipStatus",width :100 , align :"center",editable:false},
                    {name :"approvalDate",width :170 , align :"center",editable:false},
                    {name :"status",width :100 , align :"center",editable:false},
                    {name :"journalBean",hidden: true ,width :500 , align :"center",editable:false}
           ],
         height:265,
         width:1100,
         multiselect : true,	
         multiboxonly : true,
         sortname: "slipNo",
         //sortorder: "desc",
         cmTemplate: { sortable: false },
         cache:false,
         rownumbers: true,
         viewrecords: true,
         //rowNum :30,
         caption:"전표 리스트",
         
         pager : $('#page'),
         emptyrecords : "레코드가 없습니다.",
         rowNum : 20,
         rowList : [20,40,60],
         viewrecords : true,
         
         onSelectRow: function(id) {
        	
            //saveJournalDetail(slipjour["slip"], slipjour["jour"]);
            
            if(id && id !== slipId) {
                   $(this).restoreRow(slipId);           
                   slipId = id;  
            }
            
            slipStatus = $(this).jqGrid ('getCell', id, 'status');
            slipNo=$(this).jqGrid ('getCell', id, 'slipNo');
            
            alert(slipNo); 
            slipRow= $(this).jqGrid('getGridParam', 'selrow');
            //alert(slipRow); 
            checkapprovalState = $(this).jqGrid ('getCell', id, 'slipStatus');
           
            if(checkapprovalState != '승인'){
            	$(this).editRow(id, true);
            }else{
            	
            	$(this).editRow(id, false);
            	
            	alert("이미 승인된 전표입니다");
            }
            //$(this).editRow(id, true);
            
            //jQuery('#slipGrid').jqGrid('editRow',id,true);    //퍼옴
           },
           
           afterEditCell : function(rowid,name,val,iRow,iCol){
    			  $(this).setColProp("slipType", { editable: false });
    			  $(this).setColProp("requestName", { editable: false });
    			  $(this).setColProp("reportingDate", { editable: false });
    			  
    		  },
           
           
           onCellSelect: function(rowid, iCol, cellcontent, e) {
              var status = $(this).getCell(rowid,"status");
              if(status == "normal"){
              }else if (status == "update" || status == "insert"){
                  /* if(iCol == 4) {
                     showCodeDialog(this ,rowid , iCol , "SH-57","품의내역");
                  } */
              }
            console.log(slipjour["slip"]);
            console.log(slipjour["jour"]);
            console.log(slipId);
            console.log(rowid);
            console.log(iCol);
            console.log(cellcontent);
            console.log(e);

            showJournalList(rowid);
            showBalanceSheet();
           }
           
       }).bind("jqGridInlineAfterSaveRow", function (e, rowid, orgClickEvent) {
           var status = $(this).getCell(rowid, "status");
           if(status != "insert")  {
               $(this).setCell(rowid, "status", "update");
               $("#slipGrid").setCell(rowid, "status", "update");
           }

       });
   }

   /* 분개리스트  그리드 */
   function showJournalList(slipId){
	  
      $.jgrid.gridUnload("#journalGrid");
      $("#journalGrid").jqGrid({
         data :  (isNaN(slipId)) ? [] : slipList[slipId-1].journalList,
         datatype : "local",
         jsonReader :{
         root : "journalBean"
         },//
           colNames: ["전표번호", "분개순번", "대차구분", "계정코드", "계정과목", "차변금액","대변금액", "거래처코드", "거래처", "상태","분개상세"],
           colModel: [
                  {name: "slipNo", hidden: true,width: 20, align :"center",editable: false},
                  {name :"journalNo", width :450,align :"center",editable: false},
                  {name :"balanceDivision",width :90 , align :"center",editable:true, edittype: "select", editoptions: {value: "선택:선택;대변:대변;차변:차변"}},
                  {name :"accountInnerCode",width :100 , align :"center",editable:false},
                  {name :"accountName",width :180 , align :"center",editable:false},
                  {name :"leftDebtorPrice",width :160 , align :"center",editable:false, formatter:'currency', formatoptions:{thousandsSeparator:",", decimalPlaces:0}},
                  {name :"rightCreditPrice",width :160 , align :"center",editable:false, formatter:'currency', formatoptions:{thousandsSeparator:",", decimalPlaces:0}},
                  {name :"customerCode",width : 90,align:"center",editable:false },  		//editoptions : {required: true, placeholder: "찾기" }
                  {name :"customerName",width :170 , align :"center",editable:true},
                  {name :"status",width :100 , align :"center",editable:false},
                  {name :"journalDetailBean",hidden: true,width :100 , align :"center",editable:false}
           ],
         height:250,
         width:1100,
         multiselect : true,
         multiboxonly : true,
         cmTemplate: { sortable: false },
         cache:false,
         rownumbers: true,
         viewrecords: true,
         rowNum :30,
         caption:"분개 리스트",
         onSelectRow: function(id){
        	
              //saveJournalDetail(slipjour["slip"], slipjour["jour"]); 
        	 if(id && id !== selJournalId) {
                      $(this).restoreRow(selJournalId);
                      selJournalId = id;
                  } 
                  //$(this).editRow(id, true);
            journalStatus = $(this).jqGrid ('getCell', id, 'status');
            journalNo = $(this).getRowData(id).journalNo;
            journalRow = $(this).jqGrid('getGridParam', 'selrow');
            var status = $("#slipGrid").getCell(slipId, "status");
            
            if(checkapprovalState != '승인'){
            	$(this).editRow(id, true);
            	 if(status != "insert"){
                     $("#slipGrid").jqGrid('setRowData',slipRow,{status:"update"});
                 }
            }else{
            	
            }
         },
           onCellSelect: function(rowid, iCol, cellcontent, e) {
        	   var balanceDivision = $(this).getCell(rowid, "balanceDivision");
              console.log("선택된 전표의 iCol:" + iCol);
              var status = $(this).getCell(rowid,"status");
              if(status == "select"){
              }else if (status == "update" || status == "insert"){
                  if(iCol == 5) {
                	  //alert(rowid);
                	 // alert(iCol);
                     showAccountCodeDialog(this,rowid,iCol);
                  }
                  if(iCol == 9)
                     showCodeDialog(this,rowid,iCol,"CL-01","거래처");
                  }
              
              	if(iCol == 7|| iCol==8) {
          	         if(balanceDivision=="차변"){
      	            	$("#journalGrid").setColProp("rightCreditPrice", { editable: false });
      	            	$("#journalGrid").setColProp("leftDebtorPrice", { editable: true });
      	            }else if(balanceDivision=="대변"){
      	            	$("#journalGrid").setColProp("leftDebtorPrice", { editable: false });
      	            	$("#journalGrid").setColProp("rightCreditPrice", { editable: true });
      	            }else{
      	             	alert("대차구분을 선택하세요");
      	            }
          	         
                   } 
              var accountInnerCode = $(this).getCell(rowid, "accountInnerCode");
              if(accountInnerCode != null && accountInnerCode != ""){
                 $.ajax({
                      url: "${pageContext.request.contextPath}/account/base/accountDetail.do",
                      type: "post",
                      data: {"method" : "findAccountControlList", accountInnerCode: accountInnerCode },
                      dataType: "json",
                       success: function(data) {
                           if(data.errorCode < 0) 
                        	   alert(data.errorMsg);
                         
                           showJournalDetailGrid(slipId, rowid, data.accountControlList);
                           
                       }
                   });
              }
           },
           afterEditCell : function(rowid,iCol){
          	// alert("ccc"+rowid);
          	 //alert("ddd"+iCol);
           	$("#journalGrid").setColProp("leftDebtorPrice", { editable: false });
           	$("#journalGrid").setColProp("rightCreditPrice", { editable: false });
           },
           
       }).bind("jqGridInlineAfterSaveRow", function (e, rowid, orgClickEvent) {
           var status = $(this).getCell(rowid, "status");
           if(status != "insert")  {
               $(this).setCell(rowid, "status", "update");
               $("#journalGrid").setCell(rowid, "status", "update");
           }
           // 대차차액 계산
           balanceDiffCalculator(slipId);
           // 대차 대조표 갱신
           showBalanceSheet()
       });
   }
   
   
   /* 분개 상세 보기 */
   function showJournalDetailGrid(slipId, journalId, accountControlList){
	   
       //saveJournalDetail(slipjour["slip"], slipjour["jour"]);
	   slipjour["slip"] = slipId; 
       slipjour["jour"] = journalId;
       
       var journalDetailData = slipList[slipId-1].journalList[journalId-1].journalDetailBean;
       var journalDetailvalueArray=[];
 
       if(journalDetailData!=null){
    	 
          journalDetailvalueArray.push(journalDetailData.value1);
          journalDetailvalueArray.push(journalDetailData.value2);
          journalDetailvalueArray.push(journalDetailData.value3);
          journalDetailvalueArray.push(journalDetailData.value4);
          journalDetailvalueArray.push(journalDetailData.value5);
        }else {
         for(var i=0; i<5; i++) {
        	 
            journalDetailvalueArray.push("");
         }
        }
       $("#journalDetailList").empty();
       
       $.each(accountControlList, function(index, element) {
   		   $("#journalDetailList").append("<font style='color:white'>"+element.detailName + " : </font><br/>");// &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           $("<input type='text' style='height:20px; width:500px;' id='" + element.detailName + "' value='" + journalDetailvalueArray[index] + "'>").appendTo("#journalDetailList").button();
    	   
           if(checkapprovalState != '승인'){
        	   if(element.detailType == "calendar") {
                   $("#"+element.detailName).datepicker({dateFormat : "yy-mm-dd"});
               } else if(element.detailType == "financial") {

                  $("#"+element.detailType).click(function(event) {
                      showCodeDialog(this, null, null, element.detailCode , element.detailType);
                   });
               } else if(element.inputType == "TEXT") {
               }
               
           }else{
        	   $("input[type=text]").attr("readonly",true);// readonly추가
           }
           $("#journalDetailList").append("&nbsp;&nbsp;&nbsp;<br/>");
          
           
       });
            
    	   saveJournalDetail(slipjour["slip"], slipjour["jour"]);
    	   $("#journalDetailList").children("input[type=text]").bind("change paste keyup", function() {

               var slipStatus = $("#slipGrid").getCell(slipId, "status");
               if(slipStatus != "insert")
                   $("#slipGrid").setCell(slipId, "status", "update");

               var journalStatus = $("#journalGrid").getCell(journalId, "status");
               if(journalStatus != "insert")
                   $("#journalGrid").setCell(journalId, "status", "update");
           });
   }
   
   
   
   
   
   //분개상세 저장
   
   function saveJournalDetail(slipId, journalId) {
	   /* var xxx=$("#journalGrid").find("td").children("input:text"); */
       if(slipId  === undefined) return;
       var saveData = $.extend(true, saveData, emptyJournalDetail);
       var inputs = $("#journalDetailList").children("input");
       
       
       $.each(inputs, function(index, element) { 
           saveData["item"+(index+1)] = $(element).attr("id");
           saveData["value"+(index+1)] = $(element).val();
           
       });
       
       var journalDetailBean = slipList[slipId-1].journalList[journalId-1].journalDetailBean;
        //saveData["journalNo"] = journalDetailBean.journalNo;
        //saveData["journalDeatailNo"] = journalDetailBean.journalDetailNo;
       $.extend(true, journalDetailBean, saveData);
       //alert("aaaaa"+JSON.stringify( journalDetailBean ));
      }
   
   
/*    function saveJournalDetail2() {
	   saveJournalDetail(slipjour["slip"], slipjour["jour"]);
      }
    */
   

   //일괄 저장하기
   
   function batchSaveFunc(){
	   saveJournalDetail(slipjour["slip"], slipjour["jour"]);
       var resultList = [];  //resultList에 저장할 값을 담음
       
       if(checkapprovalState != '승인'){

		  for(index = 0; index < slipList.length; index++) {
               if(slipList[index].status != "normal") {
                  // 수정이 되면 재승인을 받아야 한다
                  if(slipList[index].status =="update"){
                     slipList[index].slipStatus = "재승인";
                     slipList[index].approvalDate = "";
                     slipList[index].approvalEmpName = "";
                     slipList[index].approvalEmpCode = "";
                  }
                  if(empCode == "EMP-10"||empCode == "EMP-01" ){
                  //if(empName == "관리자" ){
                	  //작성자가 관리자면 바로 승인완료 된다
                	 slipList[index].slipStatus = "승인";
                	 slipList[index].approvalEmpCode = empCode;
                	 slipList[index].approvalEmpName = empName;
                	 slipList[index].approvalDate = $("#to").val();
                  }
                  resultList.push(slipList[index]);
               }
           }
		  
       //대변 합계와 차변 합계가 0이 될때 일괄저장

    for(index = 0; index < slipList.length; index++) { 	
    if(resultList[index].accountDifference != 0){
       console.log(resultList[index]); //내용물 확인차 콘솔 출력
       alert("대차차액이 0이 아닙니다.");
    }
    else{
    alert(JSON.stringify(resultList));	// 최종적으로 넘어갈 데이터 보기

        $.ajax({
           url: "${pageContext.request.contextPath}/base/savenapproval.do",
           type: "post",
           data: {"method" : "batchListProcess", batchList: JSON.stringify(resultList)},
           success: function(data, textStatus, jqXHR) {
        	   errorCheckDialog("정보가 일괄처리 되었습니다","success");
           }
           
       });
      }    
	}
       }else{ 
    	   errorCheckDialog("승인완료상태입니다.","warning");  //완료 상태에서는 수정불가능
       }
       
   }
   
   
   
   
   //전표,분개 추가
   //전표 추가
   function slipInsertFunc(){
	   var nextSeq = Number($("#slipGrid").getGridParam("records"))+1;
	   //alert(nextSeq);
	   var slipDate=$("#to").val();
	   var slipToDate=slipDate.replace(/\-/g,'')
	      
	      //var slipNoArray = serchSlipDate.split("-");
		//var slipCnt=1;
		var allSlipRows=$("#slipGrid").getDataIDs();
		emptySlip.slipNo ="(BRC-01_05/DPT-01)SLIP"+slipToDate+"-00"+nextSeq;
		$.each(allSlipRows,function(index,val){
		var slipNo=$("#slipGrid").jqGrid("getRowData", val).slipNo; 
		alert(slipNo);
		emptySlip.slipNo ="(BRC-01_05/DPT-01)SLIP"+slipToDate+"-00"+nextSeq;
		if(nextSeq>9)
		emptySlip.slipNo ="(BRC-01_05/DPT-01)SLIP"+slipToDate+"-0"+nextSeq;
	 	}); 
			emptySlip.reportingDate = $("#to").val();
	         $("#slipGrid").addRowData(nextSeq, emptySlip);
	         slipList[nextSeq-1]["journalList"]=[];
	      }  

   //분개 추가
   function journalInsertFunc(){
	   saveJournalDetail(slipjour["slip"], slipjour["jour"]);
	   if(checkapprovalState != '승인'){
			$("#journalGrid").setColProp("rightCreditPrice", { editable: false });
          	$("#journalGrid").setColProp("leftDebtorPrice", { editable: false });
		   var nextSeq = Number($("#journalGrid").getGridParam("records"))+1;
	         emptyJournal.slipNo = slipList[slipRow-1].slipNo;
	         alert(emptyJournal.slipNo);
	         emptyJournal.journalNo = emptyJournal.slipNo + '-0'+nextSeq;
	         if(nextSeq>9){
	        	 emptyJournal.journalNo = emptyJournal.slipNo + '-'+nextSeq;
	         }
	         alert(emptyJournal.journalNo);
	         
	         var tempJournal = {};
	         emptyJournal.journalDetailBean["slipNo"]= slipList[slipRow-1].slipNo;
	         emptyJournal.journalDetailBean["journalNo"]= emptyJournal.journalNo;
	         emptyJournal.journalDetailBean["journalDetailNo"]= emptyJournal.journalNo;
	         $.extend(true, tempJournal, emptyJournal);
	         tempJournal[""] = {};
	         $("#journalGrid").addRowData(nextSeq, tempJournal);
	         var status = $("#slipGrid").getCell(slipRow, "status");
	         if(status!="insert"){
	            $("#slipGrid").setCell(slipRow, "status", "update");
	         }
		   
	   }else{ 
    	   errorCheckDialog("승인완료상태입니다.","warning");
       }
   }
    
   /*
   계정과목 보기
*/
function showAccountCodeDialog(grid,rowid,iCol){
var rr=rowid;
var ii=iCol;
$("#codeDialog").dialog({
   title : "계정 과목 리스트",
   "width":"650",
   "height":"550",
     open: function(event, ui) {
     $(".ui-dialog-titlebar-close", $(this).parent()).show();
      }
    });
    
$.jgrid.gridUnload("#codeGrid"); /*리로드*/
$("#codeGrid")
		.jqGrid(
				{
					url : "${pageContext.request.contextPath}/account/base/account.do",
					datatype : "json",
					postData : {
						"method" : "findParentAccountList"
					},
					jsonReader : {
						root : "accountList"
					},
					colNames : [ "상위계정과목코드", "계정코드", "계정과목명" ],
					colModel : [ {
						name : "superAccountCode",
						width : 150,
						hidden : true

					}, {
						name : "accountCode",
						key : true,
						width : 150,
						align : "center",
						editable : true
					}, {
						name : "accountName",
						width : 200,

					} ],
					caption : "계정과목 리스트",
					width : 600,
					height : 500,
					hoverrows : true,
					rownumbers : true,
					//multiselect : true,
					multiboxonly : true,
					viewrecords : true,
					cache : false,
					gridview : true,
					rowNum : 100,
					onSelectRow : function(rowid) {
						var codeNo = $(this).jqGrid("getRowData", rowid).accountCode;
						showAccountControlGrid(codeNo,rr,ii);
					}
				});    
    
/*     
$.jgrid.gridUnload("#codeGrid");
$("#codeGrid").jqGrid({
   url:"${pageContext.request.contextPath}/account/account.do?method=findAccountList&type=slip",
   datatype: "json",
   jsonReader: { root:"accountList"},
   colNames:["계정코드", "계정과목명"],
    colModel:[
        {name:"accountCode",width:200, align :"center",editable:false},
         {name:"accountName",width:200, align :"center", editable:false},
     ],
       width: 605,
       height: 400,
       caption: "계정 과목 선택",
       align: "center",
       viewrecords:true,
       rownumbers: true,
       rowNum:300,
       onSelectRow: function(id) {

           var detailCode=$("#codeGrid").getCell(id, 1);
           var detailName=$("#codeGrid").getCell(id, 2);

             $(grid).setCell(rowid, iCol, detailCode);
             $(grid).setCell(rowid, iCol+1, detailName);

         $("#codeDialog").dialog("close");
   }
});
} */




}
function showAccountControlGrid(codeNo,rowid,iCol) {
	var rr=rowid;
	var ii=iCol;
	$.jgrid.gridUnload("#codeGrid"); /*리로드*/
	$("#codeGrid")
			.jqGrid(
					{
						url : "${pageContext.request.contextPath}/account/base/account.do",
						datatype : "json",
						postData : {
							"method" : "findDetailAccountList",
							"code" : codeNo
						},
						jsonReader : {
							root : "accountList"
						},
						colNames : [ "상위계정과목코드", "계정코드", "계정과목명" ],
						colModel : [ {
							name : "superAccountCode",
							width : 150,

						}, {
							name : "accountCode",
							key : true,
							width : 150,
							align : "center",
							editable : true
						}, {
							name : "accountName",
							width : 200,

						} ],
						caption : "계정과목 목록",
						width : 600,
						height : 500,
						hoverrows : true,
						rownumbers : true,
						//multiselect : true,
						multiboxonly : true,
						viewrecords : true,
						cache : false,
						gridview : true,
						rowNum : 100,
						onSelectRow : function(id) {

							 var detailCode=$("#codeGrid").getCell(id, 2);
					         var detailName=$("#codeGrid").getCell(id, 3);
					             $("#journalGrid").setCell(rr, ii, detailCode);
					             $("#journalGrid").setCell(rr, ii+1, detailName);

					         $("#codeDialog").dialog("close");
					   }

					});

}



/*
코드 상세 보기
*/

function showCodeDialog(grid, rowid, iCol, detailCodeNo, detailCodeName){
$("#codeDialog").dialog({
title : detailCodeName ,
"width":"650",
"height":"450",
open: function(event, ui) {
  $(".ui-dialog-titlebar-close", $(this).parent()).show();  } /*닫기 버튼 제거.. */
});
$.jgrid.gridUnload("#codeGrid");
$("#codeGrid").jqGrid({
url:"${pageContext.request.contextPath}/base/codeList.do?method=findDetailCodeList&code="+detailCodeNo,
datatype: "json",
jsonReader: { root:"detailCodeList"},
colNames:['거래처코드','거래처명'],
 colModel:[
     {name:'detailCode',width:200, align :"center",editable:false},
     {name:'detailCodeName',width:200, align :"center",editable:false},
  ],
    width: 605,
    height: 400,
    caption: detailCodeName,
    align: "center",
    viewrecords:true,
    rownumbers: true,
    onSelectRow: function(id) {

        var detailCode=$("#codeGrid").getCell(id, 1);
        var detailName=$("#codeGrid").getCell(id, 2);
     if(rowid ==null && iCol ==null){
         $("#"+detailCodeNo).val(detailName);
     }
        $(grid).setCell(rowid, iCol, detailCode);
        if(detailCode !="DEC03"){
           $(grid).setCell(rowid, iCol+1, detailName);
        }else{
           $("#journalGrid").jqGrid('setCell',rowid,'descName, ""', 'editable-cell');
        }


   $("#codeDialog").dialog("close");
  }
});
}



   
   //전표,분개 수정
   //전표 수정
   function slipUpdateFunc(){
	   if(checkapprovalState != '승인'){
		   $("#slipGrid").setCell(slipRow, "status", "update");
		   $("#slipGrid").editRow(slipRow, true);
       }else{ 
    	   errorCheckDialog("승인완료상태입니다.","warning");
       }
    
   }

   //분개 수정
   function journalUpdateFunc(){
	   
	   if(checkapprovalState != '승인'){
		  
		   if(slipNo == null){
		    	  errorCheckDialog("선택한 전표가 없습니다","warning");
		      }else{
		         var status = $("#journalGrid").getCell(journalRow, "status");
		         if(status =="insert"){
		            $("#journalGrid").setCell(journalRow, "status", "insert");
		         }else{
		          $("#journalGrid").setCell(journalRow, "status", "update");
		         }
		          $("#journalGrid").editRow(journalRow, true);
		      }
		   
       }else{ 
    	   errorCheckDialog("승인완료상태입니다.","warning");
       }
	   
     
   }

   /* 전표 , 분개 삭제하기 */

   function slipDeleteFunc(){

      if(slipNo == null){
    	  errorCheckDialog("삭제할 전표를 지정해주세요","warning");
      }else if(slipNo != null){
         $("#slipGrid").jqGrid('editRow',slipNo);
         $("#slipGrid").jqGrid('setRowData',slipRow,{status:"delete"});

         if(slipList[slipRow-1].journalList.length != 0){
        	 errorCheckDialog(" 해당 전표의 분개리스트를 삭제 하시겠습니까? ","slipDelete");
         }
      }
   }

   function journalDeleteFunc(){
      var selectDetailRow = $("#journalGrid").jqGrid('getGridParam', 'selrow');
      var deleteJournalNo = $("#journalGrid").getRowData(selectDetailRow).journalNo;
      if(selectDetailRow==null){
    	 
    	  errorCheckDialog("삭제 할 분개를 선택해 주세요","warning");
    	
      }else if(deleteJournalNo!=null){
         $("#journalGrid").jqGrid('editRow',deleteJournalNo);
         $("#journalGrid").jqGrid('setRowData',selectDetailRow,{status:"delete"});
         $("#slipGrid").jqGrid('setRowData',slipRow,{status:"update"});
      }
   }

   /* 대차 대조표 */
   function showBalanceSheet(){
      var debitTotalPrice=0;
      var creditTotalPrice=0;
      var debitList =[];
      var creditList =[];
      var codeList = [];

      function Summary(accountName , leftDebtorPrice,rightCreditPrice){
         this.accountName = accountName;
         this.leftDebtorPrice = leftDebtorPrice;
         this.rightCreditPrice = rightCreditPrice;
      }
      $.each($("#journalGrid").jqGrid("getDataIDs"),function(index,value){
         codeList.push(jQuery("#journalGrid").getRowData(index+1));
         });

      $.each(codeList, function(index, element) {
         var summary = new Summary(element.accountName, element.leftDebtorPrice, element.rightCreditPrice);

         if(element.balanceDivision == "차변") {
            debitTotalPrice += parseInt(element.leftDebtorPrice);
            debitList.push(summary);
         } else if(element.balanceDivision == "대변") {
            creditTotalPrice += parseInt(element.rightCreditPrice);
            creditList.push(summary);
         }
      });

      showDebitGrid(debitTotalPrice,debitList);
      showCreditGrid(creditTotalPrice,creditList);

   }


   /*
      대변 차변 목록
   */
   //차변 그리드
   function showDebitGrid(debitTotalPrice, debitList) {

       $.jgrid.gridUnload("#debitGrid");
       $("#debitGrid").jqGrid({
           datatype: "local",
           data: debitList,
           colNames: ["계정과목", "금액"],
           colModel: [
               {name: "accountName", editable: false, align: "center"},
               {name: "leftDebtorPrice", editable: false, align: "right", formatter:'currency', formatoptions:{thousandsSeparator:",", decimalPlaces:0}},
           ],
           width : 545,
           height : 130,
           caption : "차변",
           footerrow : true
       });
       $("#debitGrid").jqGrid("footerData", "set", {accountName: "합계", leftDebtorPrice: debitTotalPrice});
   }




   // 대변 그리드
   function showCreditGrid(creditTotalPrice, codeList) {

       $.jgrid.gridUnload("#creditGrid");
       $("#creditGrid").jqGrid({
           datatype: "local",
           data: codeList,
           colNames: ["계정과목", "금액"],
           colModel: [
               {name: "accountName", editable: false, align: "center"},
               {name: "rightCreditPrice", editable: false, align: "right", formatter:'currency', formatoptions:{thousandsSeparator:",", decimalPlaces:0}},
           ],
           width : 545,
           height : 130,
           caption : "대변",
           footerrow : true
       });
       $("#creditGrid").jqGrid("footerData", "set", {accountName: "합계", rightCreditPrice: creditTotalPrice});
}


   
   
   
   /*
      대차 차액 계산
   */
   function balanceDiffCalculator(slipId){

      var balance = 0;
       var rows = $("#journalGrid").getDataIDs();
       for(index = 0; index < rows.length; index++) {
          var data = $("#journalGrid").getRowData(rows[index]);
          if(data.balanceDivision == "차변")
              balance += parseInt(data.leftDebtorPrice);
          else
              balance -= parseInt(data.rightCreditPrice);
       }
       //총합계산을 전표쪽에 갱신
       $("#slipGrid").setCell(slipId, "accountDifference", balance);
       // 상태 변경
       var status = $("#slipGrid").getCell(slipId, "status");
       if(status == "select") $("#slipGrid").setCell(slipId, "status", "update");
   }


   /*
      에러 다이얼로그
   */
   function errorCheckDialog(errorMsg,alerts){

      if(alerts == "warning"){
         $("#errorDialog").html(errorMsg);
            $(function(){
             $("#errorDialog").dialog({
             title : "경고 메세지" ,
             modal : true,
              width : 500,
              heigth : 500,
              buttons : {
               ok : function(){
                $(this).dialog("close");
               }
              }
             });
          });
   }
      if(alerts == "success"){
         $("#errorDialog").html(errorMsg);
            $(function(){
             $("#errorDialog").dialog({
             title : "성공 메세지" ,
             modal : true,
              width : 500,
              heigth : 500,
              buttons : {
               ok : function(){
               $(location).attr("href","${pageContext.request.contextPath}/account/slip/slipform.html");
                $(this).dialog("close");
               }
              }
             });
          });
      }

      if(alerts == "slipDelete"){
         $("#errorDialog").html(errorMsg);
            $(function(){
             $("#errorDialog").dialog({
             title : "경고 메세지" ,
             modal : true,
              width : 500,
              heigth : 500,
              buttons : {
               ok : function(){
               var journalList = slipList[slipRow-1].journalList;
                for(var index = 0 ; index < journalList.length ; index++ ){
                  $("#journalGrid").setCell(index+1, "status", "delete");
                }
                $(this).dialog("close");
               },
                close : function(){
                $("#slipGrid").jqGrid('setRowData',slipRow,{status:"select"});
                $(this).dialog("close");
                }
              }
             });
          });
   }
   }

</script>
</head>
<body>
	<table id="layoutSlipForm">
		<tr>
			<td colspan="2">
				<!-- 전표 --> <!-- 달력 --> <!-- <input type="text" id="slipDate" size="20"> -->
				<input type="text" id="from" name="from"><B><font>
						~ </font></B> <input type="text" id="to" name="to">&nbsp;&nbsp;&nbsp;


				<!--         <input type="text" id="slipDate" size="20">
        <input type="text" id="from" size="10" name="from"><B><font> ~ </font></B>
    	<input type="text" id="to" size="10" name="to">&nbsp;&nbsp;&nbsp; -->

				<input type="button" id="slipSearchButton" value="조회"> <!-- <input type="button" id="reStartButton" value ="다른 날짜 선택 "> -->

				<input type="button" id="slipInsertButton" value="추가"> <input
				type="button" id="slipUpdateButton" value="수정"> <input
				type="button" id="slipDeleteButton" value="삭제"> <input
				type="button" id="batchSaveButton" value="일괄저장"> <input
				type="button" id="pdfOpenButton" value="PDF보기">

				<table id="slipGrid"></table>
				<div id="page" style="text-align: center;"></div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<!-- 분개 --> <input type="button" id="journalInsertButton"
				value="분개추가"> <input type="button" id="journalUpdateButton"
				value="분개수정"> <input type="button" id="journalDeleteButton"
				value="분개삭제">
				<!-- <input type="button" id="journalDetailSave" value="분개상세"> -->
				<table id="journalGrid" style="z-index: 3"></table>
			</td>
		</tr>
		<tr>
			<td><!--  colspan="2" -->
				<!-- 분개상세 -->
				<div id="journalDetailList"></div>
			</td>
		</tr>
		<tr>
			<!-- 대차대조표 -->
			<td>
				<table id="debitGrid"></table>
			</td>
			<td>
				<table id="creditGrid"></table>
			</td>
		</tr>
	</table>
	<div id="errorDialog"></div>
	<div id="codeDialog" title="Dialog Title">
		<table id="codeGrid"></table>
	</div>
</body>
</html>