<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Category</title>
</head>
<body>
	<c:import url="../header.jsp" /> 
	<%!String message; %>
	<table style="width: 100%">
	 	<tr>
	 		<td style="padding-top: 50px; width: 15%; height: 80%;" valign="top">
	 			<c:import url="../navbar.jsp" />
	 		</td>
	 		<td style="padding-left: 250px; width: 85%; height: 80%;">
				<h3>ADD CATEGORY</h3>
	 			<form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath }/addNewCategory.do">
	 				<table>
						<tr>
							<td>Name</td>
							<td><input type="text" class="form-control" placeholder="Enter name category" name="categoryName" style="width: 100%"/></td>
						</tr>
						<tr>
							<td colspan="2" align="right">
								<input type="submit" class="form-control btn btn-primary" name="addCategory" value="Add Category" style="width: 100%" />
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
 