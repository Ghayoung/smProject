<%@ page contentType="application/vnd.ms-excel; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="net.skhu.dto.Report"%>
<%@ page import="net.skhu.service.ReportDAO"%>
<%
	String s1 = request.getParameter("id");
	int id = Integer.parseInt(s1);

	Report list = ReportDAO.findOne(id);

	Date date = new Date();
	SimpleDateFormat dayformat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
	SimpleDateFormat hourformat = new SimpleDateFormat("hhmmss", Locale.KOREA);
	String day = dayformat.format(date);
	String hour = hourformat.format(date);
	String fileName = "보고서" + day + "_" + hour;

	response.setHeader("Content-Disposition",
			"attachment; filename=" + new String((fileName).getBytes("KSC5601"), "8859_1") + ".xls");
	response.setHeader("Content-Description", "JSP Generated Date");
%>

<!doctype html>
<html lang="ko" style="overflow: hidden">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title></title>
</head>

<body bgcolor="#ffffff" text="#000000" topmargin="0" leftmargin="0">

	<table>
		<thead>
			<tr align="center">
				<th scope="col" bgcolor="CDCDCD">스터디 주제</th>
				<th scope="col" bgcolor="CDCDCD">스터디 장소</th>
				<th scope="col" bgcolor="CDCDCD">모임요일</th>
				<th scope="col" bgcolor="CDCDCD">시작시간</th>
				<th scope="col" bgcolor="CDCDCD">종료시간</th>
				<th scope="col" bgcolor="CDCDCD">스터디 내용</th>
				<th scope="col" bgcolor="CDCDCD">작성자</th>
				<th scope="col" bgcolor="CDCDCD">작성일</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td style="text-align: left;"><%=list.getSubject()%></td>
				<td style="text-align: left;"><%=list.getPlace()%></td>
				<td style="text-align: left;"><%=list.getDay()%></td>
				<td style="text-align: left;"><%=list.getStart_time()%></td>
				<td style="text-align: left;"><%=list.getEnd_time()%></td>
				<td style="text-align: left;"><%=list.getStudy_content()%></td>
				<td style="text-align: left;"><%=list.getName()%></td>
				<td style="text-align: left;"><%=list.getCreate_date()%></td>
			</tr>
		</tbody>
	</table>
</body>
</html>

