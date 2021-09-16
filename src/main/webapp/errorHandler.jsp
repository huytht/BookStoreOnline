<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    isErrorPage="true"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error Handler</title>
</head>
<body>
	<c:import url="header.jsp" />
	<table style="width: 100%">
	 	<tr>
	 		<td style="padding-top: 50px; width: 25%; height: 80%;" valign="top">
	 			<c:import url="navbar.jsp"/>
	 		</td>
	 		<td style="width: 75%; height: 80%;">
	 			<h1>Error 404</h1>
	 			${pageContext.exception.message}
	 			${param.ex}
	 		</td>
	 	</tr>
	</table>
	<c:import url="footer.jsp" />
</body>
</html>