<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>JSTL - Hello World</title>
</head>
<body>
	<c:import url="header.jsp" /> 
	<table style="width: 100%">
	 	<tr>
	 		<td style="padding-top: 50px; width: 15%; height: 80%;" valign="top">
	 			<c:import url="navbar.jsp" />
	 		</td>
	 		<td style="text-align: center; width: 85%; height: 80%;">
	 			<h1>WELCOME TO BOOK STORE</h1>
	 			<%
	 				if (session.getAttribute("authorized_user") != null) {
				%>
	 					<h2>Hello ${sessionScope.authorized_user.uid }</h2>		
				<%
					}
				%>
	 		</td>
	 	</tr>
	</table>
	<c:import url="footer.jsp">
		<c:param name="copyrightYear" value="${initParam.copyright }"/>
		<c:param name="webLink" value="${initParam.webLink }"/>
	</c:import>
</body>
</html>