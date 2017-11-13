<%@ page language="java"
	contentType="application/vnd.ms-excel;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//중요한 사항 : "attachment; filename=excel.xls" 로 적으면 excel.xls 파일이 생성되고 다운로드된다. 
	//의심하지 말고 아래줄은 그냥 적어요 
	//모든 HTML은 Excel 파일형식으로 변환됨 (편하지 않나요?) 

	response.setHeader("Content-Disposition", "attachment; filename=excel.xls");
	response.setHeader("Content-Description", "JSP Generated Data");
	response.setHeader("Content-Disposition", "attachment; filename=excel.xls");  
	response.setHeader("Content-Description", "JSP Generated Data");  
	response.setContentType("application/vnd.ms-excel");  

%>

<html>
<head>
<title>HTML코드가 엑셀파일변환</title>

</head>
<body>

	<table border=1>
		<tr bgcolor=#CACACA>
			
			<td colspan=3><H3>제목을 적어줍니다</H3></td>
		</tr>
		<tr bgcolor=yellow>
			<td>삼</td>
			<td>육</td>
			<td>구</td>
		</tr>
		<tr>
			<td>1</td>
			<td>2</td>
			<td>3</td>
		</tr>
		<tr>
			<td>4</td>
			<td>5</td>
			<td>6</td>
		</tr>
		<tr>
			<td>7</td>
			<td>8</td>
			<td>9</td>
		</tr>
	</table>

</body>
</html>
