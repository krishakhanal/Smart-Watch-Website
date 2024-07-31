<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title>Product Management Application</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f8f9fa;
	margin: 0;
	padding: 0;
}

header {
	background-color: #378da0;
	color: white;
	padding: 10px 0;
	text-align: center;
}

nav {
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 0 20px;
}

nav a {
	color: white;
	text-decoration: none;
}

.container {
	max-width: 1200px;
	margin: 0 auto;
	padding: 20px;
}

.btn {
	background-color: #F65A83;
	color: white;
	padding: 8px 16px;
	border: none;
	border-radius: 4px;
	text-decoration: none;
}

table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

tr:hover {
	background-color: #f9f9f9;
}

.nav-box {
	display: inline-block;
	border: 1px solid #ccc;
	padding: 10px;
	margin-right: 3px;
}

img {
	max-width: 100px;
	max-height: 100px;
}

/* Responsive */
@media ( max-width : 768px) {
	nav {
		flex-direction: column;
		align-items: flex-start;
	}
	nav a {
		margin: 5px 0;
	}
}
</style>
</head>

<body>
	<header>
		<nav>
			<h1>Admin Panel</h1>
			<div class="nav-box">
				<a href="<%= request.getContextPath()%>/list">Product Management</a>
			</div>

			<ul>
				<%--  <li><a href="<%=request.getContextPath()%>/list">Products</a></li>
                <li><a href="<%=request.getContextPath()%>/login">Login</a></li> --%>

				<%
				if (session.getAttribute("username") != null) {
				%>
				<div class="nav-box">
					<a href="<%= request.getContextPath() %>/allConfirmOrder">Check
						All Confirmed Orders</a>
				</div>
				<p>
					Welcome,
					<%=session.getAttribute("username")%></p>
				<a href="<%=request.getContextPath()%>/logout">Logout</a>
				<%
				} else {
				%>
				<a href="<%=request.getContextPath()%>/login">Login</a>
				<%
				}
				%>

			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<div class="container">
			<h3 class="text-center">List of Products For Admin</h3>
			<hr>
			<div class="container text-left">
				<%
				if (session.getAttribute("username") != null) {
				%>
				<a href="<%=request.getContextPath()%>/new" class="btn">Add New
					Product</a>
				<%
				} else {
				%>
				<%
				}
				%>

			</div>
			<br>
			<table>
				<thead>
					<tr>
						<th>Product Name</th>
						<th>Description</th>
						<th>Price</th>
						<th>Stock</th>
						<th>Image</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="product" items="${listProduct}">
						<tr>
							<td><c:out value="${product.product_name}" /></td>
							<td><c:out value="${product.description}" /></td>
							<td><c:out value="${product.price}" /></td>
							<td><c:out value="${product.stock}" /></td>
							<td><img src="data:image/jpg;base64,${product.imageBase64}"
								width="100" height="100" /></td>
							<%
							if (session.getAttribute("username") != null) {
							%>
							<td><a
								href="edit?product_id=<c:out value='${product.product_id}' />">Edit</a>
								<a
								href="delete?product_id=<c:out value='${product.product_id}' />">Delete</a></td>
							<%
							} else {
							%>
							<td></td>
							<%
							}
							%>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>

</html>
