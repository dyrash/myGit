<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>재무상태표</title>  
<script >
var financialPositionList=[];     //담아둘 배열

$(document).ready(function(){

	$("input[type=text] , input[type=button]").button();
	
	$.datepicker.setDefaults({
	    closeText:"닫기",
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
		yearSuffix : '년'
	}); /* 한글로 달력 보이기 위해서 기본값 설정 */
	
	$("font").css("color" ,"white");
	$("#from").val('2018-01-01');

	$( "#to" ).datepicker({
	    //defaultDate: "+1w",
	    changeYear: true,
	    changeMonth: true,
	    numberOfMonths: 1,
	    dateFormat : "yy-mm-dd",
	    onClose: function( selectedDate ) {
	      $( "#from" ).datepicker( "option", "maxDate", selectedDate );
	    }
	});


	$("#FinancialPositionBtn").click(FinanciialPositionListFunc);

	//IncomeStatementGrid(); 
});


function FinanciialPositionListFunc() {  //db 가는 함수 
	$.jgrid.gridUnload("#FinancialPositionGrid");
	$("#FinancialPositionGrid").jqGrid({
		url: "${pageContext.request.contextPath}/account/statement/financialPositionList.do",  
		postData :{from: $("#from").val(), to: $("#to").val()},      // 날짜 전달 
		datatype: "json",
		data: financialPositionList,
		jsonReader : {
			root : 'financialPositionList'
		},
		colNames:["계정과목","당기금액","당기합계","전기금액","전기합계","그룹"], 
        colModel:[
        	{name: "accountName", width: 200, editable: false, align: "left"},
        	{name: "clPrice", width: 250, align: "right", editable: false, formatter:'currency', formatoptions:{thousandsSeparator:",", decimalPlaces:0}},
        	{name: "crPrice", width: 250, align: "right", editable: false, formatter:'currency', formatoptions:{thousandsSeparator:",", decimalPlaces:0}},
        	{name: "elPrice", width: 250, align: "right", editable: false, formatter:'currency', formatoptions:{thousandsSeparator:",", decimalPlaces:0}},
        	{name: "erPrice", width: 250, align: "right", editable: false, formatter:'currency', formatoptions:{thousandsSeparator:",", decimalPlaces:0}},
        	{name: "gId", width: 50, hidden:true}
        ],
        width: '1100',
        height: 'auto',  //값에 따라 자동으로 늘어나게
        scrollrows : true,
        rowNum: 200,
        //footerrow : true,
        hoverrows : false,
        loadtext : "데이터 조회중",
        caption : "재무상태표"
    });
    $("#FinancialPositionGrid").setGroupHeaders({ useColSpanStyle: true,
    	groupHeaders: [ {startColumnName: "clPrice", numberOfColumns: 2, titleText: "<em>당기</em>"},
    	                {startColumnName: "elPrice", numberOfColumns: 2, titleText: "<em>전기</em>"} ]
    });
}		

function AddComma(data_value) {

	return Number(data_value).toLocaleString('en');

}

   /*  에러 다이얼로그 */
	function IncomeStateErrorDialog(errorMsg,alerts){  

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
	<input type="hidden" type="text" id="from" name="from">
	<!-- <input type="hidden" type="text" id="from" name="from">&nbsp;&nbsp;&nbsp;<B><font>부터</font></B>&nbsp;&nbsp;&nbsp; -->
    <input type="text" id="to" name="to">
    <!-- <input type="text" id="to" name="to">&nbsp;&nbsp;&nbsp;<B><font>까지</font></B>&nbsp;&nbsp;&nbsp; -->
    <input type="button" id="FinancialPositionBtn" value="재무상태표 보기"><br><br>
    <table id="FinancialPositionGrid"></table> 
</body>
</html>