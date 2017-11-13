<%@ page language="java" contentType="application/vnd.ms-excel;charset=UTF-8" %>
<%  
  String file_name ="download";
  String ExcelName  = new String(file_name.getBytes(),"UTF-8")+".xls";
  response.setContentType("application/vnd.ms-excel");
  response.setHeader("Content-Disposition", "attachment; filename="+ExcelName);
  response.setHeader("Content-Description", "JSP Generated Data");
  response.setHeader("Pragma", "no-cache");
  
  String table = request.getParameter("table");
  
%>
<html>
<head>
</head>
<body>
<%=table%>
</body>
</html>
