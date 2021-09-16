<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit User</title>
</head>
<body>
	<c:import url="../header.jsp" /> 
	<c:choose>
		<c:when test="${sessionScope.userDataEdit.uid != param.uid }" >
			<c:redirect url="../getUserDataEdit.do?uid=${param.uid }" />
		</c:when>
		<c:when test="${sessionScope.roles == null}" >
			<c:redirect url="../getRoles.do?dest=Protected/editUser" />
		</c:when>
	</c:choose>
	<table style="width: 100%">
	 	<tr>
	 		<td style="padding-top: 50px; width: 15%; height: 80%;" valign="top">
	 			<c:import url="../navbar.jsp" />
	 		</td>
	 		<td style="padding-left: 250px; width: 85%; height: 80%;">
	 			<form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath }/editUser.do?uid=${param.uid}">
					<h3 style="padding-left: 100px">EDIT USER</h3>
	 				<table>
						<tr>
							<td>Username</td>
							<td><input type="text" class="form-control" disabled="disabled" placeholder="Enter username" value="${sessionScope.userDataEdit.uid }" name="uid" style="width: 100%"/></td>
						</tr>
						<tr>
							<td>Password</td>
							<td><input type="password" class="form-control" placeholder="Enter password" value="${sessionScope.userDataEdit.pwd }" name="pwd" style="width: 100%"/></td>
						</tr>
						<tr>
							<td>Role</td>
							<td>
								<select name="authLevel" class="form-control" style="width: 100%">
									<option>---Choose role---</option>
									<c:forEach var="role" items="${sessionScope.roles }">
										<option value="${role.id }"
											<c:if test="${role.id == sessionScope.userDataEdit.authLevel}">
												selected
											</c:if>	
										>${role.name }
										</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="right">
								<input type="submit" class="form-control btn btn-primary" onclick="return confirm('Are you sure you want to edit this user?');" name="editUser" value="Edit User" style="width: 100%" />
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
