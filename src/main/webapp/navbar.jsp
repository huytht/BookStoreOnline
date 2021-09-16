<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<style>
	a {
		text-decoration: none;
	}
	a:hover {
		color: black;
	}
	* {
		font-family: 'Time New Roman';
		font-size: 20px;
	}
</style>

</head>
<body>
	<a href="${pageContext.request.contextPath }/index.jsp"><i class="fas fa-home"></i>&nbsp;Home</a><br>
	<a href="${pageContext.request.contextPath }/Protected/listBooks.jsp"><i class="far fa-list-alt"></i>&nbsp;List Book</a><br>
	<a href="${pageContext.request.contextPath }/Protected/listCategory.jsp"><i class="far fa-list-alt"></i>&nbsp;List Category</a><br>
	<a href="${pageContext.request.contextPath }/Protected/listUser.jsp"><i class="fas fa-users"></i>&nbsp;List User</a><br>
	<c:if test="${sessionScope.authorized_user.authLevel == 1 }">
		<a href="${pageContext.request.contextPath }/Protected/addBook.jsp"><i class="fas fa-book-medical"></i>&nbsp;Add New Book</a><br>
		<a href="${pageContext.request.contextPath }/Protected/addCategory.jsp"><i class="fas fa-book-medical"></i>&nbsp;Add New Category</a><br>
		<a href="${pageContext.request.contextPath }/Protected/addUser.jsp"><i class="fas fa-user-plus"></i>&nbsp;Add New User</a><br>
	</c:if>
	<c:choose>
		<c:when test="${sessionScope.authorized_user == null }">
			<a href="${pageContext.request.contextPath }/login.jsp"><i class="fas fa-sign-in-alt"></i>&nbsp;Sign in</a><br>	
		</c:when>
		<c:otherwise>
			<a href="${pageContext.request.contextPath }/signOut.do"><i class="fas fa-sign-out-alt"></i>&nbsp;Sign out</a><br>	
		</c:otherwise>
	</c:choose>
</body>
</html>



