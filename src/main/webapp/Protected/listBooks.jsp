<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Book</title>
</head>
<body>
	<c:import url="../header.jsp" />
	<c:choose>
		<c:when test="${sessionScope.authorized_user == null }" >
			<c:redirect url="../login.jsp?dest=Protected/listBooks" />
		</c:when>
		<c:when test="${sessionScope.authorized_user.authLevel < 1 }">
			<c:redirect url="../login.jsp?dest=Protected/listBooks"/>
		</c:when>
		<c:when test="${sessionScope.authorized_user.uid == null }">
			<c:redirect url="../login.jsp?dest=Protected/listBooks"/>
		</c:when>
		<c:when test="${sessionScope.bookData == null}" >
			<c:redirect url="../getbookdata.do" />
		</c:when>
		<c:when test="${sessionScope.categories == null}" >
			<c:redirect url="../getCategories.do?dest=Protected/listBooks" />
		</c:when>
	</c:choose>
	<table style="width: 100%;">
	 	<tr>
	 		<td style="padding-top: 50px; width: 15%; height: 80%;" valign="top">
	 			<c:import url="../navbar.jsp" />
	 		</td>
	 		<td style="width: 85%; height: 80%;">
	 			<h3 style="text-align: center">LIST OF BOOK</h3>
				<table border="1" style="border-collapse: collapse; width: 100%">
					<tr style="text-align: center">
						<th>Title</th>
						<th>Summary Content</th>
						<th>Price</th>
						<th>Author</th>
						<th>Publication Date</th>
						<th>Image</th>
						<th>Category</th>
						<c:choose>
							<c:when test="${sessionScope.authorized_user.authLevel == 1 }">
								<th>Edit</th>
								<th>Delete</th>
							</c:when>
						</c:choose>
					</tr>
					<c:forEach var="tempBook"  items="${sessionScope.bookData }" varStatus="iterationCount"> 
						<tr style="text-align: center;">
							<td>${tempBook.title}</td>
							<td>${tempBook.summaryContent}</td>
							<td>${tempBook.price}</td>
							<td>${tempBook.author}</td>
							<td><fmt:formatDate  pattern="dd/MM/yyyy" value="${tempBook.publicationDate}" /></td>
							<td><img src="<c:url value='../image/${tempBook.image}' />" width="100px" height="100px" /></td>
							<td>
							<c:forEach var="category" items="${sessionScope.categories }">
								<c:if test="${category.id == tempBook.categoryId}">
									${category.name}
								</c:if>
							</c:forEach>
							</td>
							<c:choose>
								<c:when test="${sessionScope.authorized_user.authLevel == 1 }">
									<td><a href="editBook.jsp?id=${tempBook.id }"><i class="fas fa-edit"></i>&nbsp;</a></td>
									<td><a href="../deleteBook.do?id=${tempBook.id }" onclick="return confirm('Are you sure you want to delete ${tempBook.title}?');"><i class="far fa-trash-alt"></i>&nbsp;</a></td>
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