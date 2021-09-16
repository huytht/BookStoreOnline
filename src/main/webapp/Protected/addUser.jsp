<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add User</title>
</head>
<body>
	<c:import url="../header.jsp" /> 
	<%!String message; %>
	<c:if test="${sessionScope.roles == null}" >
		<c:redirect url="../getRoles.do?dest=Protected/addUser" />
	</c:if>
	<table style="width: 100%">
	 	<tr>
	 		<td style="padding-top: 50px; width: 15%; height: 80%;" valign="top">
	 			<c:import url="../navbar.jsp" />
	 		</td>
	 		<td style="padding-left: 250px; width: 85%; height: 80%;">
				<h3>ADD USER</h3>
	 			<form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath }/addNewUser.do">
	 				<table>
						<tr>
							<td>Username</td>
							<td><input type="text" class="form-control" placeholder="Enter username" name="uid" style="width: 100%"/></td>
						</tr>
						<tr>
							<td>Password</td>
							<td><input type="password" class="form-control" placeholder="Enter password" name="pwd" style="width: 100%"/></td>
						</tr>
						<tr>
							<td>Role</td>
							<td>
								<select name="authLevel" class="form-control" style="width: 100%">
									<option selected>---Choose role---</option>
									<c:forEach var="role" items="${sessionScope.roles }">
										<option value="${role.id }">${role.name }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="right">
								<input type="submit" class="form-control btn btn-primary" name="addUser" value="Add User" style="width: 100%" />
							</td>
						</tr>
	 				</table>
	 				<c:if test='${sessionScope.message != null && sessionScope.message != ""}'>
						<c:out value="${sessionScope.message }"/>
					</c:if>
	 			</form>
	 		</td>
	 	</tr>
	</table>
	<c:import url="../footer.jsp">
		<c:param name="copyrightYear" value="${initParam.copyright }"/>
		<c:param name="webLink" value="${initParam.webLink }"/>
	</c:import>
</body>
</html>
 