<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="n be" class="w test.SangpumBe" />
<jsp:setProperty property="*" name="bean" />
<jsp:useBean id="g con" class="w test.ConnPooli" scope="page" />

<%
//boolean b = connP.updateDataok(bean);
//if(b) ...application

if(connP.updateDataOk(bean))
	response.sendRedirect("jsp17dbcp.jsp"); // 수정 후 목록보기
else
	response.sendRedirect("jsp17fail.html"); 
%>