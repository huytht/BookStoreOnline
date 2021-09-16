<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Book</title>
</head>
<body>
	<c:import url="../header.jsp" /> 
	<c:choose>
		<c:when test="${sessionScope.bookDataEdit.id != param.id }" >
			<c:redirect url="../getBookDataEdit.do?id=${param.id }" />
		</c:when>
		<c:when test="${sessionScope.categories == null}" >
			<c:redirect url="../getCategories.do?dest=Protected/editBook" />
		</c:when>
	</c:choose>
	<table style="width: 100%">
	 	<tr>
	 		<td style="padding-top: 50px; width: 15%; height: 80%;" valign="top">
	 			<c:import url="../navbar.jsp" />
	 		</td>
	 		<td style="padding-left: 250px; width: 85%; height: 80%;">
	 			<form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath }/editBook.do?id=${param.id}">
					<h3 style="padding-left: 100px">EDIT BOOK</h3>
	 				<table>
						<tr>
							<td>Title</td>
							<td><input type="text" class="form-control" placeholder="Enter title" name="bookTitle" value="${sessionScope.bookDataEdit.title }" style="width: 100%"/></td>
						</tr>
						<tr>
							<td>Summary Content</td>
							<td><textarea style="width: 100%" class="form-control" placeholder="Enter summary content" name="bookSumContent">${sessionScope.bookDataEdit.summaryContent }</textarea></td>
						</tr>
						<tr>
							<td>Price</td>
							<td><input type="number" class="form-control" name="bookPrice" placeholder="Enter price" value="${sessionScope.bookDataEdit.price }" style="width: 100%"/></td>
						</tr>	
						<tr>
							<td>Author</td>
							<td><input type="text" class="form-control" name="bookAuthor" placeholder="Enter author name" value="${sessionScope.bookDataEdit.author }" style="width: 100%"/></td>
						</tr> 				
						<tr>
							<td>Publication Date</td>
							<td><input type="date" class="form-control" name="bookPubDate" placeholder="yyyy-MM-dd" value="${sessionScope.bookDataEdit.publicationDate }" style="width: 100%"/></td>
						</tr>
						<tr>
							<td>Image (Current)</td>
							<td><img src="<c:url value='../image/${sessionScope.bookDataEdit.image }' />" width="100px" height="100px" /></td>
						</tr>
						<tr>
							<td>Image</td>
							<td><input type="file" class="form-control" name="bookImage" /></td>
						</tr>
						<tr>
							<td>Category</td>
							<td>
								<select name="bookCategoryId" class="form-control" style="width: 100%">
									<option>---Choose category---</option>
									<c:forEach var="category" items="${sessionScope.categories }">
										<option value="${category.id }"
											<c:if test="${category.id == sessionScope.bookDataEdit.categoryId}">
												selected
											</c:if>	
										>${category.name }
										</option>
									</c:forEach>
								</select>
							</td>
						</tr>	 			
						<tr>
							<td colspan="2" align="right">
								<input type="submit" class="form-control btn btn-primary" onclick="return confirm('Are you sure you want to edit this book?');" name="editBook" value="Edit Book" style="width: 100%" />
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
