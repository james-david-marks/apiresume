<%@ page import="com.jimmarks.apiresume.HtmlFormatter"%>
<%
	HtmlFormatter htmlFormatter = new HtmlFormatter();
	out.println(htmlFormatter.getLandingPage());
%>
