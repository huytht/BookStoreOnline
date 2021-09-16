<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Book</title>
</head>
<body>
	<c:import url="../header.jsp" /> 
	<%!String message; %>
	<c:choose>
		<c:when test="${sessionScope.categories == null}" >
			<c:redirect url="../getCategories.do?dest=Protected/addBook" />
		</c:when>
	</c:choose>
	<table style="width: 100%">
	 	<tr>
	 		<td style="padding-top: 50px; width: 15%; height: 80%;" valign="top">
	 			<c:import url="../navbar.jsp" />
	 		</td>
	 		<td style="padding-left: 250px; width: 85%; height: 80%;">
				<h3 style="padding-left: 100px">ADD BOOK</h3>
	 			<form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath }/addNewBook.do">
	 				<table>
						<tr>
							<td>Title</td>
							<td><input type="text" class="form-control" placeholder="Enter title" name="bookTitle" style="width: 100%"/></td>
						</tr>
						<tr>
							<td>Summary Content</td>
							<td><textarea style="width: 100%" class="form-control" placeholder="Enter summary content" name="bookSumContent"></textarea></td>
						</tr>
						<tr>
							<td>Price</td>
							<td><input type="number" class="form-control" placeholder="Enter price" name="bookPrice" style="width: 100%"/></td>
						</tr>	
						<tr>
							<td>Author</td>
							<td><input type="text" class="form-control" placeholder="Enter author name" name="bookAuthor" style="width: 100%"/></td>
						</tr> 				
						<tr>
							<td>Publication Date</td>
							<td><input type="date" class="form-control" placeholder="yyyy-MM-dd" name="bookPubDate" style="width: 100%"/></td>
						</tr>
						<tr>
							<td>Image</td>
							<td><input type="file" class="form-control" placeholder="Choose image" name="bookImage" /></td>
						</tr>
						<tr>
							<td>Category</td>
							<td>
								<select name="bookCategoryId" class="form-control" style="width: 100%">
									<option selected>---Choose category---</option>
									<c:forEach var="category" items="${sessionScope.categories }">
										<option value="${category.id }">${category.name }</option>
									</c:forEach>
								</select>
							</td>
						</tr>	 			
						<tr>
							<td colspan="2" align="right">
								<input type="submit" class="form-control btn btn-primary" name="addBook" value="Add Book" style="width: 100%" />
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
 