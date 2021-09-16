<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Category</title>
</head>
<body>
	<c:import url="../header.jsp" />
	<c:choose>
		<c:when test="${sessionScope.authorized_user == null }" >
			<c:redirect url="../login.jsp?dest=Protected/listCategory" />
		</c:when>
		<c:when test="${sessionScope.authorized_user.authLevel < 1 }">
			<c:redirect url="../login.jsp?dest=Protected/listCategory"/>
		</c:when>
		<c:when test="${sessionScope.authorized_user.uid == null }">
			<c:redirect url="../login.jsp?dest=Protected/listCategory"/>
		</c:when>
		<c:when test="${sessionScope.categories == null}" >
			<c:redirect url="../getCategories.do?dest=Protected/listCategory" />
		</c:when>
	</c:choose>
	<table style="width: 100%; ">
	 	<tr>
	 		<td style="padding-top: 50px; width: 15%; height: 80%;" valign="top">
	 			<c:import url="../navbar.jsp" />
	 		</td>
	 		<td style="width: 85%; height: 80%;">
	 			<h3 style="text-align: center">LIST OF CATEGORY</h3>
				<table border="1" style="border-collapse: collapse; width: 100%">
					<tr style="text-align: center">
						<th>Name</th>
						<c:choose>
							<c:when test="${sessionScope.authorized_user.authLevel == 1 }">
								<th>Edit</th>
								<th>Delete</th>
							</c:when>
						</c:choose>
					</tr>
					<c:forEach var="tempCategory"  items="${sessionScope.categories }" varStatus="iterationCount"> 
						<tr style="text-align: center;">
							<td>${tempCategory.name}</td>
							<c:choose>
								<c:when test="${sessionScope.authorized_user.authLevel == 1 }">
									<td><a href="editCategory.jsp?id=${tempCategory.id }"><i class="fas fa-edit"></i>&nbsp;</a></td>
									<td><a href="../deleteCategory.do?id=${tempCategory.id }" onclick="return confirm('Are you sure you want to delete ${tempCategory.name}?');"><i class="far fa-trash-alt"></i>&nbsp;</a></td>
								</c:when>
							</c:choose>
						</tr>
					</c:forEach>
				</table>	 			
	 		</td>
	 	</tr>
	</table>
	<c:import url="../footer.jsp">
		<c:param name="copyrightYear" value="${initParam.copyright }"/>
		<c:param name="webLink" value="${initParam.webLink }"/>
	</c:import>
</body>
</html>