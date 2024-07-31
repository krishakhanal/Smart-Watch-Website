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

.search-container {
	margin: 20px 0;
	text-align: center;
}

.search-container input[type="text"] {
	padding: 8px;
	width: 200px;
	border: 1px solid #ccc;
	border-radius: 4px;
	margin-right: 5px;
}

.search-container button {
	padding: 8px 16px;
	background-color: #4CAF50;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

.search-container button:hover {
	background-color: #45a049;
}
</style>
</head>
<body>
	<header>
		<nav>
			<h1>Your Cart Items</h1>
			<div class="nav-box">
				<a href="<%= request.getContextPath() %>/home">Home</a>
			</div>
			<div class="nav-box">
				<a href="<%= request.getContextPath() %>/userConfirmOrder">Confirmed
					Orders</a>
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
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${cartItems}">
				<tr>
					<td>${item.product_name}</td>
					<td>${item.description}</td>
					<td>${item.price}</td>
					<td>${item.quantity}</td>
					<td><img src="data:image/jpg;base64,${item.imageBase64}"
						width="100" height="100"></td>
					<td>
						<form action="confirmOrder" method="post">
							<input type="hidden" name="cart_id" value="${item.cart_id}" /> <input
								type="hidden" name="quantity" value="${item.quantity}" /> <input
								type="submit" value="Confirm Order" />
						</form>
						<form action="deleteCartItem" method="post">
							<input type="hidden" name="cart_id" value="${item.cart_id}" /> <input
								type="hidden" name="quantity" value="${item.quantity}" /> <input
								type="submit" value="Delete Order" />
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>