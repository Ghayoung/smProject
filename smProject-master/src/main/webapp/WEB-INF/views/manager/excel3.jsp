<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%

  response.setHeader("Content-Disposition", "attachment; filename=excel.xls");

  response.setHeader("Content-Description", "JSP Generated Data");

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
