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
		getEarlyAssetDataFunc();				//전기분 자산 데이터 호출 
		getEarlyLiabilitiseNequityDataFunc();	//전기분 부채&자본 데이터 호출
		getEarlyFinancialPositionDataFunc();	//전기분 재무상태표 호출
	});
	
	var assetList={}
	var liabNequiList={}
	var earlyFinacialList={}
	
	/////전기분 자산 데이터 호출 
	function getEarlyAssetDataFunc() {
		$.jgrid.gridUnload("#assets");
		$("#assets").jqGrid({
			url: "${pageContext.request.contextPath}/statement/earlyFinancialPosition.do?method=findEarlyAssets",
			datatype:'json',
			data : assetList,
			jsonReader : {
				root : 'assetList'
			},
			height : 500,
			colNames : [ "계정명","계정과목", "금액","상태" ],
			colModel : [ 
				{name : "groupCode", align :"left", editable : false}, 
				{name : "accountName", align :"left", editable : false}, 
				{name : "status", hidden: true },
				{name : "price", align :"right",  editable : false, formatter:'currency', formatoptions:{thousandsSeparator:",", decimalPlaces:0}, summaryType:'sum'}
			],
			caption : "전기분 자산",
			loadtext : "데이터 조회중",
			grouping:true,
            groupingView : {
               groupField : ['status'],
               groupSummary : [true],
               groupColumnShow : [false],
               groupText : ['<b></b>'],
               groupCollapse : false
            }
		});
		//var priceSum = $("#assets").jqGrid('getCol', 'price', false, 'sum');
		//$("#assets").jqGrid('footerData', 'set', { Rate: 'Summary', price: priceSum});
	}
	
	/////전기분 부채 및 자본 데이터 호출 
	function getEarlyLiabilitiseNequityDataFunc() {
		$.jgrid.gridUnload("#liabilitiesNequity");
		$("#liabilitiesNequity").jqGrid({
			url: "${pageContext.request.contextPath}/statement/earlyFinancialPosition.do?method=findEarlyLiabilitiseNequity",
			datatype:'json',
			//data : assetList,
			jsonReader : {
				root : 'liabNequiList'
			},
			height : 500,
			colNames : [ "계정명","계정과목", "금액","상태" ],
			colModel : [ 
				{name : "groupCode", align :"left", editable : false}, 
				{name : "accountName", align :"left", editable : false}, 
				{name : "totalCreditPrice", align :"right",  editable : false, formatter:'currency', formatoptions:{thousandsSeparator:",", decimalPlaces:0},summaryType:'sum'},
				{name : "status", hidden: true }
			],
			caption : "자본 및 부채",
			loadtext : "데이터 조회중",
			grouping:true,
            groupingView : {
               groupField : ['status'],
               groupSummary : [true],
               groupColumnShow : [false],
               groupText : ['<b></b>'],
               groupCollapse : false
            }
		});
	 	var priceSum = $("#liabilitiesNequity").jqGrid('getCol', 'totalCreditPrice', false, 'sum');
		$("#liabilitiesNequity").jqGrid('footerData', 'set', { Rate: 'Summary', totalCreditPrice: priceSum}); 
	}
	
	function getEarlyFinancialPositionDataFunc() {
		$.jgrid.gridUnload("#ealryFinancialPosition");
		$("#ealryFinancialPosition").jqGrid({
			url: "${pageContext.request.contextPath}/statement/earlyFinancialPosition.do?method=findEarlyFinancialPosition",
			datatype:'json',
			data : earlyFinacialList,
			jsonReader : {
				root : 'earlyFinacialList'
			},
			height : 500,
			colNames : [ "항목","금액","상태" ],
			colModel : [ 
				{name : "groupName", align :"left", editable : false}, 
				{name : "totalPrice", align :"right",  editable : false, formatter:'currency', formatoptions:{thousandsSeparator:",", decimalPlaces:0},summaryType:'sum'},
				{name : "status", hidden: true }
			],
			loadtext : "데이터 조회중",
			caption : "재무상태표"
		});
	}
	
	
	
	
	
/*
	/////전기분 자산 데이터 호출 
	function getEarlyAssetDataFunc() {
		$.ajax({
			url : "${pageContext.request.contextPath}/statement/earlyFinancialPosition.do",
			type : "post",
			data : {
				"method" : "findEarlyAssets"
			},
			dataType : "json",
			success : function(data, textStatus, jqXHR) {
				//alert(data.assetList[0].groupCode);
				showEarlyAssetDataFunc(data.assetList);
			},
		});
	}
	
	function showEarlyAssetDataFunc(assetList) {
		$.jgrid.gridUnload("#assets");
		$("#assets").jqGrid({
			data : assetList,
			datatype : "local",
			jsonReader : {
				root : 'assetList'
			},
			colNames : [ "계정명","계정과목", "금액" ],
			colModel : [ 
				{name : "groupCode", align :"center", resize:true, editable : false}, 
				{name : "accountName", align :"center", editable : false}, 
				{name : "price", align :"right",  editable : false, formatter:'currency', formatoptions:{thousandsSeparator:",", decimalPlaces:0}}
			],
			height : 600,
			width : 400,
			cache : false,
			caption : "전기분 자산",
			footerrow:true
		});
		var priceSum = $("#assets").jqGrid('getCol', 'price', false, 'sum');
		$("#assets").jqGrid('footerData', 'set', { Rate: 'Summary', price: priceSum});
	}
 */

</script>
</head>
<body>
<!-- 	<h2 align="center"><font color="white">아 썅</font></h2> -->
<table>
		<colgroup>
			<col width="35%">
			<col width="35%">
			<col width="30%">
		</colgroup>
		<tr>
			<td><table id="assets"></table>
			</td>
			<td><table id="liabilitiesNequity"></table>
			</td>
			<td><table id="ealryFinancialPosition"></table>
			</td>
		</tr>
	</table>


<!-- 
				<!-- 자산
		<table id="assets"></table>
				부채&자본
		<table id="liabilitiesNequity"></table>
				합계
		<table id="ealryFinancialPosition"></table> --> 
</body>
</html>
