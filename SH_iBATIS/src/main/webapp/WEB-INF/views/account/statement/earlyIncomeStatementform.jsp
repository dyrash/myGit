<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style type="text/css">
</style>
<script>
	$(document).ready(function() {
		getEarlyIncomeListDataFunc();
		getEarlyIncomeStatementDataFunc();				//전기분 손익계산서 프로시저 데이터 호출 
	});
	
	var earlyIncomeList={}
	var earlyIncomeStatementList={}
	
	/////전기분 손익목록리스트 데이터 호출 
	function getEarlyIncomeListDataFunc() {
		$.jgrid.gridUnload("#earlyIncomeList");
		$("#earlyIncomeList").jqGrid({
			url: "${pageContext.request.contextPath}/account/statement/earlyIncomeList.do?method=findEarlyIncomeList",
			datatype:'json',
			data : earlyIncomeList,
			jsonReader : {
				root : 'earlyIncomeList'
			},
			width : 600,
			height : 400,
			colNames : [ "계정코드","계정과목명","금액" ],
			colModel : [ 
				{name : "accountInnerCode", width : 200, align :"center", editable : false}, 
				{name : "accountName", width : 200, align :"center", editable : false}, 
				{name : "totalPrice", width : 200, align :"right",  editable : false, formatter:'currency', formatoptions:{thousandsSeparator:",", decimalPlaces:0}, summaryType:'sum'}
			],
			caption : "전기분 손익목록 리스트",
			loadtext : "데이터 조회중"
			/* grouping:true,
            groupingView : {
               groupField : ['status'],
               groupSummary : [true],
               groupColumnShow : [false],
               groupText : ['<b></b>'],
               groupCollapse : false
            } */
		});
		//var priceSum = $("#assets").jqGrid('getCol', 'price', false, 'sum');
		//$("#assets").jqGrid('footerData', 'set', { Rate: 'Summary', price: priceSum});
	}
	/////전기분 손익계산서 프로시저 데이터 호출 
	function getEarlyIncomeStatementDataFunc() {
		$.jgrid.gridUnload("#earlyIncomeStatement");
		$("#earlyIncomeStatement").jqGrid({
			url: "${pageContext.request.contextPath}/account/statement/earlyIncomeStatement.do?method=findEarlyIncomeStatementList",
			datatype:'json',
			data : earlyIncomeStatementList,
			jsonReader : {
				root : 'earlyIncomeStatementList'
			},
			width : 600,
			height : 400,
			colNames : [ "계정그룹명","금액" ],
			colModel : [ 
				{name : "accountName", width : 350, align :"left", editable : false}, 
				{name : "totalPrice", width : 150, align :"right",  editable : false, formatter:'currency', formatoptions:{thousandsSeparator:",", decimalPlaces:0}, summaryType:'sum'}
			],
			caption : "전기분 손익계산서",
			loadtext : "데이터 조회중"
			/* grouping:true,
            groupingView : {
               groupField : ['status'],
               groupSummary : [true],
               groupColumnShow : [false],
               groupText : ['<b></b>'],
               groupCollapse : false
            } */
		});
		//var priceSum = $("#assets").jqGrid('getCol', 'price', false, 'sum');
		//$("#assets").jqGrid('footerData', 'set', { Rate: 'Summary', price: priceSum});
	}


</script>
</head>
<body>
	<h1 align="center"><font color="white">전기분 손익계산서</font></h1><br><br><br>
	<table align="center" width="1200">
		<colgroup>
			<col width="40%">
			<col width="60%">
		</colgroup>
		<tr>
			<td><table id="earlyIncomeList"></table></td>
			<td><table id="earlyIncomeStatement" align="center"></table></td>
			
		</tr>
	</table>
	
	
</body>
</html>
