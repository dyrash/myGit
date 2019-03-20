<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/scripts/menu/menu.css" />
<script 	src="${pageContext.request.contextPath}/scripts/menu/menu.js"></script>

<style>
/* 	.ui-jqgrid .ui-jqgrid-htable {
			table-layout : auto;
		}  
 구글링에서 찾은 헤더 높이 조절 소스, 적용 안됨...*/ 
 

/*jqGrid 칼럼높이 조절하는 소스*/

	.ui-jqgrid .ui-jqgrid-hdiv {  
		font-size: 0.8em;
		height: auto;
	}
	
/*jqGrid 제목 헤더 높이 조절하는 소스*/

	.ui-jqgrid .ui-widget-header {
		height: 40px;
		font-size: 1em;
	}
	.ui-jqgrid .ui-jqgrid-bdiv {
		overflow-x: auto;
		overflow-y: scroll;
	}


h1{
	text-shadow: 1px 1px 1px #FFB2F5;
	color: #ffffff;
	font-weight: bold;
}

h4 {
	/* text-shadow: 5px 5px 2px #4C4C4C;  */
	color : #ffffff;
	font-weight: bold;
}




</style>
<script>
    
function alertError(title, message) {
	
	// error-dialog 보이게 하기
	$("#error-dialog").attr("style", "display:block");

	$("#error-dialog").dialog({   // jqueryUI dialog 위젯 적용
		autoOpen : true,  // 자동으로 열리도록
		modal : true,     // 외부 클릭 못하게
		title : title,   // error-dialog 폼 제목
		width : 'auto',
		height : 'auto',
		position : {    // 폼 열릴 때 위치
			my : "center center",  
			at : "center-120 center-30"   // 폼 열릴 때, 대강 화면 중앙에 오도록
		},
		buttons : {  // 버튼 이벤트 적용
			"확인" : function() {
				$("#error-dialog").attr("style", "display:none");
				$("#error-dialog").dialog("close");
			}
		}
	});
	$("#error-dialog #errorMsg").html(message);

}

	var userMenuList;
	
	function setUserMenuList(){
		
		$.ajax({
			 url:"${pageContext.request.contextPath}/base/menuList.do",  
	         data:{
	            method:"findUserMenuList",   
	            empCode:"${sessionScope.empCode}"
	         },
	         dataType:"json",
	         success : function(data){
	      	   if(data.errorCode<0){
	      		   errorDialog(data.errorMsg,"warning");     		   
	      	   }else{
	            userMenuList = data.userMenuList;
	            
	      	   }
	         }
	      });
		}

	function permitted(menuCode,url){

		   alert(menuCode);	  
		   var permitted = false;    
		   
		   for(var index in userMenuList){
		      if(userMenuList[index].menuCode==menuCode&&userMenuList[index].isAccessDenied=='y'){
		         var permitted = true;   
		      }
		   }
		   if(permitted)
			  
			 location.href=url;
		  	
		   else{
			  //alert("해당 권한이 없습니다.\n관리자에게 문의하세요","warning");
		      errorDialog("해당 권한이 없습니다.\n관리자에게 문의하세요","warning");
		   }
		}


	function errorDialog(errorMsg,alerts){ 
		if(alerts == "warning"){
	     $("#errorDialog").html(errorMsg);
	        $(function(){
	         $("#errorDialog").dialog({
	         title : "경고 메세지" ,
	         modal : true,
	          width : 500,
	          heigth : 500,
	          buttons : {
	           	닫기 : function(){
	            $(this).dialog("close");
	           }
	          }
	         
	         });
	      });
		}
	}

	
		$(document).ready(function(){
			 <%if (session.getAttribute("empCode") == null) {%>location.href="<%=request.getContextPath()%>/loginform.html"
				    <%}else{%>setUserMenuList();<%}%>
			
		$("#main").click(function(){ location.href="<%=request.getContextPath()%>/hello.html"; });
		<%-- $("#logout").click(function(){ location.href="<%=request.getContextPath()%>/logout.do"; }); --%>
		$("#logout").click(function(){ location.href="<%=request.getContextPath()%>/loginform.html"; });
		
		//setUserMenuList();
		
		$("button").button();
		
		});

		
</script>
<body>

<h1 align="center">
ACCOUNT 
<%-- <img src="${pageContext.request.contextPath}/resources/image/s_2.png"> --%>
</h1>
<h4 align="right">
	직원번호  : ${sessionScope.empCode}  &emsp; &emsp; &emsp; &emsp; &emsp;&emsp;&emsp; &emsp; &emsp;<br>
	${sessionScope.empName}  ${sessionScope.positionName}님  환영합니다!&emsp; &emsp; &emsp; &emsp; &emsp;&emsp;&emsp; &emsp; &emsp;<br>
	<%-- <%-- 권한 : ${sessionScope.authority} &emsp;
	직원번호  : ${sessionScope.empCode}  &emsp; &emsp; &emsp; &emsp; &emsp;&emsp;&emsp; &emsp; &emsp;<br>
	직급 : ${positionCode} &emsp;
	직   급 :  ${sessionScope.positionName} &emsp; &emsp; &emsp; &emsp; &emsp;&emsp;&emsp; &emsp; &emsp;<br>
	이   름 :  ${sessionScope.empName} 님 &emsp; &emsp; &emsp; &emsp; &emsp;&emsp;&emsp; &emsp; &emsp; --%>
</h4>
	<div align="right"><button id="logout" class='button'>로그아웃</button>&emsp; &emsp;&emsp; &emsp; &emsp;&emsp; &emsp;&emsp;&emsp; </div><br> 
<div id="cssmenu" align="center">
   <ul>
   
     <li class='active'><a href='${pageContext.request.contextPath}/hello.html'>Home</a></li>
     
     
     <li class='has-sub'><a href='#'><span>인사 등록 관리</span></a>
       <ul> 
           
           <li onclick="permitted('ME006_1','${pageContext.request.contextPath}/hr/empinsertform.html')">
           <a href='#'><span>사원 등록</span></a>
           </li>
				
           <li onclick="permitted('ME006_2','${pageContext.request.contextPath}/hr/employeeform.html')">
           <a href='#'><span>사용자 권한 설정</span></a>
           </li>
           
           </ul>
           </li>
           
        <li class='has-sub'><a href='#'><span>계정/코드 관리</span></a>
          <ul>
          
           <li onclick="permitted('ME001_2','${pageContext.request.contextPath}/account/accountform.html')">
           <a href='#'><span>계정과목</span></a>
           </li>
           
           <li onclick="permitted('ME001_3','${pageContext.request.contextPath}/base/codemanageform.html')">
           <a href='#'><span>코드 관리</span></a>
           </li>
           
          </ul>
          </li>
        
      <li class='has-sub'><a href="#"><span>전표 관리</span></a>
         <ul>
          <li onclick="permitted('ME002_1','${pageContext.request.contextPath}/account/slipform.html')">
          <a href='#'><span>일반전표입력</span></a>
          </li>
          <li onclick="permitted('ME002_2','${pageContext.request.contextPath}/base/approvalmanager.html')">
          <a href='#'><span>전표승인/해제</span></a></li>
         </ul>
         </li>
      <li class='has-sub'><a href="#"><span>장부 관리</span></a>
         <ul>
            <li onclick="permitted('ME003_1','${pageContext.request.contextPath}/account/journalform.html')">
            <a href='#'><span>분개장</span></a>
            </li>
            
         </ul>
             
         <li class='has-sub'><a href='#'><span>전기분재무제표</span></a>
          <ul> 
          <li onclick="permitted('ME004_1','${pageContext.request.contextPath}/statement/earlyFinancialPositionform.html')">
           <a href='#'><span>전기분재무상태표</span></a>
           </li>
           <li onclick="permitted('ME004_3','${pageContext.request.contextPath}/statement/earlyIncomeStatementform.html')">
           <a href='#'><span>전기분손익계산서</span></a>
           </li> 
           </ul>
          
         <li class='has-sub'><a href='#'><span>결산/재무제표</span></a>
          <ul> 
          <li onclick="permitted('ME004_1','${pageContext.request.contextPath}/statement/totaltrialbalanceform.html')">
           <a href='#'><span>합계잔액시산표</span></a>
           </li>
           <li onclick="permitted('ME004_2','${pageContext.request.contextPath}/statement/financialPositionform.html')">
           <a href='#'><span>재무상태표</span></a>
           </li> 
           <li onclick="permitted('ME004_3','${pageContext.request.contextPath}/statement/incomeStatementform.html')">
           <a href='#'><span>손익계산서</span></a>
           </li> 
           </ul>
          
          
         </li>
   </ul>
</div>
<div id="error-dialog">
	<p id="errorMsg"></p>
</div> 
</body>