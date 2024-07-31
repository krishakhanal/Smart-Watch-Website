<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Cart</title>
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

img {
	max-width: 100px;
	max-height: 100px;
}

.nav-box {
	display: inline-block;
	border: 1px solid #ccc;
	padding: 10px;
	margin-right: 3px;
}
</style>
</head>
<body>
	<header>
		<nav>
			<h1>Admin Panel</h1>
			<h2>Order Check</h2>
			<div class="nav-box">
				<a href="<%= request.getContextPath() %>/list">Back to Main Page</a>
			</div>
			<ul>
				<%
                if (session.getAttribute("username") != null) {
                %>
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
	<table>
		<thead>
			<tr>
				<th>Product Name</th>
				<th>Description</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Image</th>
				<th>Ordered By</th>
				<th>Email</th>
				<th>Total Amount</th>
				<th>Order Status</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty cartItems}">
					<tr>
						<td colspan="9">No items to display</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="item" items="${cartItems}">
						<tr>
							<td>${item.product_name}</td>
							<td>${item.description}</td>
							<td>${item.price}</td>
							<td>${item.quantity}</td>
							<td><img src="data:image/jpg;base64,${item.imageBase64}"
								width="100" height="100"></td>
							<td>${item.username}</td>
							<td>${item.email}</td>
							<td>${item.price * item.quantity}</td>
							<td>${item.orderStatus == 1 ? 'Delivered' : 'Non-Delivered'}</td>
							<td>
								<form action="<%=request.getContextPath()%>/updateOrderStatus"
									method="post">
									<input type="hidden" name="cart_id" value="${item.cart_id}" />
									<input type="hidden" name="orderStatus"
										value="${item.orderStatus == 1 ? 0 : 1}" /> <input
										type="submit"
										value="${item.orderStatus == 1 ? 'Mark as Non-Delivered' : 'Mark as Delivered'}" />
								</form>
							</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>

</body>
</html>
