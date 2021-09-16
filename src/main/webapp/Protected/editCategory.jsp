<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Category</title>
</head>
<body>
	<c:import url="../header.jsp" /> 
	<c:if test="${sessionScope.categoryDataEdit == null}" >
		<c:redirect url="../getCategoryDataEdit.do?id=${param.id }" />
	</c:if>
	<table style="width: 100%">
	 	<tr>
	 		<td style="padding-top: 50px; width: 15%; height: 80%;" valign="top">
	 			<c:import url="../navbar.jsp" />
	 		</td>
	 		<td style="padding-left: 250px; width: 85%; height: 80%;">
	 			<form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath }/editCategory.do?id=${param.id}">
					<h3 style="padding-left: 50px">EDIT CATEGORY</h3>
	 				<table>
						<tr>
							<td>Name</td>
							<td><input type="text" class="form-control" placeholder="Enter name category" name="categoryName" value="${sessionScope.categoryDataEdit.name }" style="width: 100%"/></td>
						</tr>
						<tr>
							<td colspan="2" align="right">
								<input type="submit" class="form-control btn btn-primary" onclick="return confirm('Are you sure you want to edit this category?');" name="editCategory" value="Edit category" style="width: 100%" />
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
