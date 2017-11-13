<%@ page language="java" contentType="application/vnd.ms-excel;charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="net.skhu.dto.Report"%>
<%@ page import="net.skhu.service.ReportDAO"%>
<%
	String s1 = request.getParameter("id");
	int id = Integer.parseInt(s1);

	Report list = ReportDAO.findOne(id);

	response.setHeader("Content-Disposition","attachment;filename=member.xls");
    response.setHeader("Content-Description", "JSP Generated Data");

%>
<div id="fh5co-main">


	<h3>회원 목록</h3>

	<form name="frm" method="post" onsubmit="excel();">
		<input type="hidden" name="excel_data" /> <input type="submit"
			value="전송">
	</form>

	<table id="excel_body">
		<caption>list</caption>
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