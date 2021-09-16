<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<c:import url="header.jsp" />
	<table style="width: 100%">
	 	<tr>
	 		<td style="padding-top: 50px; width: 15%; height: 80%;" valign="top">
	 			<c:import url="navbar.jsp" />
	 		</td>
	 		<td style="padding-left: 250px; width: 85%; height: 80%;">
	 			<form id="login" method="post" action="loginUser.do">
					<h3 style="padding-left: 100px">SIGN IN</h3>
	 				<table>
	 					<tr> 
	 						<td>Username</td>
	 						<td><input class="form-control" type="text" name="uid" style="width: 250px" value="${cookie.credentials_uid.value }"/></td>
	 					</tr>
	 					<tr> 
	 						<td>Password</td>
	 						<td><input class="form-control" type="password" name="pwd" style="width: 250px" value="${cookie.credentials_pwd.value }"/></td>
	 					</tr>
	 					<tr>
	 						<td colspan="2" align="right">
	 							<input type="checkbox" name="rememberMe" />&nbsp;Remember Me
	 						</td>
	 					</tr>
	 					<tr>
	 						<td colspan="2" align="right">
	 							<input class="form-control btn btn-primary" type="submit" value="Sign in" style="width: 100%" />
	 						</td>
	 					</tr>
	 				</table>
	 				<input type="hidden" name="dest" value="${param.dest}" />
	 			</form>
	 		</td>
	 	</tr>
	</table>
	<c:import url="footer.jsp">
		<c:param name="copyrightYear" value="${initParam.copyright }"/>
		<c:param name="webLink" value="${initParam.webLink }"/>
	</c:import>
</body>
</html>