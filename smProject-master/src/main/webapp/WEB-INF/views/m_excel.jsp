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

</div>

