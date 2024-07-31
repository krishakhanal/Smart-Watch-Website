<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title>Time Treasures</title>
<link rel="stylesheet" type=text/css
	href="${pageContext.request.contextPath}/Css/home.css" />
<style>
@import
	url("https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700;900&display=swap")
	;

* {
	padding: 0;
	margin: 0;
	box-sizing: border-box;
}

body {
	margin: 0;
}

nav {
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 5px 50px;
	background-color: #378da0;
	box-sizing: content-box;
}

.nav-left img {
	padding: 10px 0px;
	border-radius: 50%;
}

.nav-center {
	display: flex;
	margin-left: 550px;
}

.nav-center a {
	text-decoration: none;
	color: #000;
	margin-right: 3rem;
	font-size: 20px;
	font-family: "Helvetica", Arial, sans-serif;
	/* font-weight: bold; */
	padding: 5px;
	position: relative;
	transition: all 0.2s ease-in-out;
}

/* .nav-center a.active {
    color: #fff;
    background-color: #000;/*#f21f07/
    border-radius: 50px;
} */
.nav-center a:hover {
	color: #fff;
	background-color: #000; /*#f21f07*/
	border-radius: 50px;
}

.nav-right {
	display: flex;
	align-items: center;
}

.nav-right img {
	padding-right: 10px;
	cursor: pointer;
}

.search { /*search text and icon*/
	display: flex;
	border: none;
	border-radius: 40px;
	align-items: center;
	background-color: #E3E5E7;
	margin-right: 2rem;
	padding-right: 20px;
	height: 35px;
}

.nav-box a {
	text-decoration: none;
	margin-right: 10px;
	color: black;
	font-size: 20px;
}

.search-input { /*search text*/
	outline: none;
	border: none;
	border-radius: 40px;
	height: 20px;
	padding: 10px;
	margin-right: 1rem;
	font-size: 16px;
	background-color: #E3E5E7;
}

.separate {
	margin-right: 2rem;
}

/* Popular products  */
h2 {
	display: block;
	text-align: center;
	margin: 20px;
}

.product-list {
	display: flex;
	flex-wrap: wrap;
	/* justify-content: center; */
	justify-content: space-between;
	padding: 20px;
	margin: 0px 20px; /* Center the product list horizontally */
}

.product-item {
	width: 30%;
	text-align: center;
	margin-bottom: 20px;
	border: 1px solid #ddd;
	padding: 10px;
	position: relative;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	border-radius: 20px;
	box-shadow: 5px black;
}

.product-item img {
	width: 200px; /* Set desired width */
	height: 200px; /* Set desired height */
	margin-bottom: 10px;
}

.product-info {
	padding-top: 10px;
	flex-direction: column;
}

.product-info h3, .product-info p {
	margin: 0; /* Remove default margins */
}

/* Hide the quick buy button by default */
.product-item .quick-buy {
	background-color: #2471a3;
	color: #fff;
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	margin-right: 10px;
}

/* Show the quick buy button on hover for ALL product items */
.product-item:hover img {
	transform: scale(1.1); /* Increase the image size slightly on hover */
	transition: transform 0.2s ease-in-out;
	/* Add a smooth transition effect */
}

/* footer code */
footer {
	background-color: #378da0;
	padding: 20px 0;
	display: flex;
	font-family: Arial, sans-serif;
	justify-content: space-between;
	align-items: center;
	/* position: fixed; */
	bottom: 0;
	padding-left: 30px;
	flex-direction: column;
	width: 100%;
}

.col1, .col2, .col3 {
	display: flex;
	font-size: 16px;
	flex-direction: column;
	width: 25%;
	padding-left: 10px;
	margin: 10px 0px 10px 10px;
}

.col1 h4, .col2 h4, .col3 h4 {
	margin-bottom: 20px; /*new*/
}

.col1 a, .col2 a, .col3 a {
	color: #000;
	text-decoration: none;
	transition: color 0.2s ease-in-out;
	margin-bottom: 15px;
}

.col1 a:hover, .col2 a:hover, .col3 a:hover {
	color: #EDF10AD1
}

.follow .icon {
	cursor: pointer;
}

.follow .icon i:hover {
	color: red;
}

.copyright {
	/* text-align: center; */
	font-family: Arial, sans-serif;
	font-size: 16px;
	padding: 10px;
	bottom: 0;
	display: flex;
}

.top-footer {
	display: flex;
	justify-content: center;
	align-items: center;
}
</style>
</head>

<body>
	<header>
		<nav>

			<div class="nav-center">
				<a href="home">Home</a> <a href="product">Product</a> <a
					href="aboutus">About Us</a>


			</div>
			<div class="nav-right">
				<div class="search">
					<form action="<%=request.getContextPath()%>/searchItems"
						method="post" class="search">
						<input type="text" class="search-input" name="searchQuery"
							placeholder="Search">
						<button type="submit">Search</button>
					</form>

				</div>
				<%
				// Check if the user is logged in
				if (session.getAttribute("username") != null) {
				%><p>
				<div class="nav-box">
					<a href="<%=request.getContextPath()%>/addToCart">View Cart</a>
				</div>
				</p>
				<p>
				<div class="nav-box">
					<a href="<%=request.getContextPath()%>/userConfirmOrder">Confirmed
						Orders</a>
				</div>
				<p>
					<%
				} else {
				%>
				
				<div class="nav-box">
					<a href="<%=request.getContextPath()%>/login">View Cart</a>
				</div>
				<%
				}
				%>
				<%
				// Check if the user is logged in
				if (session.getAttribute("username") != null) {
				%>
				<p>
					Welcome, <a href="<%=request.getContextPath()%>/editUser"><%=session.getAttribute("username")%></a>
					<!--  ><%=session.getAttribute("username")%></p>-->
					<a href="<%=request.getContextPath()%>/logout">Logout</a>
					<%
				} else {
				%>
					<a href="<%=request.getContextPath()%>/login">Login</a>
					<%
				}
				%>
				
			</div>
		</nav>
	</header>


	<br>

	<div class="container">
		<h2>Latest Products</h2>
		<div class="product-list">
			<c:forEach var="product" items="${listProduct}">
				<div class="product-item">
					<a href="productdesc1.html?product-id=1"> <img
						src="data:image/jpg;base64,${product.imageBase64}" width="100"
						height="100" />
					</a>
					<div class="product-info">
						<h3>
							<c:out value="${product.product_name }"></c:out>
						</h3>
						<br>
						<p>
							<c:out value="${product.description }"></c:out>
						</p>
						<br>
						<p>
							<b>Price: $</b>
							<c:out value="${product.price }"></c:out>
						</p>
						<p>
							<b>Quantity: </b>
							<c:out value="${product.stock }"></c:out>
						</p>
						<!-- -><button class="quick-buy" value="Add to Cart" onclick="addToCart">Quick Buy</button>-->
						<%
						if (session.getAttribute("username") != null) {
						%>

						<form action="addToCart" method="post">
							<input type="hidden" name="product_id"
								value="${product.product_id}" /> <input type="number"
								name="quantity" value="1" min="1" max="${product.stock}" /> <input
								type="submit" value="Add to Cart" />
						</form>

						<%
						} else {
							%>
						<form action="login" method="get">
							<input type="hidden" name="product_id"
								value="${product.product_id}" /> <input type="number"
								name="quantity" value="1" min="1" max="${product.stock}" /> <input
								type="submit" value="Add to Cart" />
						</form>
						<%}
						%>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<!-- Footer -->
	<footer>
		<div class="top-footer">
			<div class="col">

				<h4>Contact</h4>
				<p>
					<strong>Address: </strong> Time Treasures Store UK Headquarters,
					160 Borough High Street, London SE1 1PF
				</p>
				<p>
					<strong>Phone: </strong> +44 20 7946 0000
				</p>
				<p>
					<strong>Hours: </strong> Open 10:00 AM - 6:00 PM, weekdays
				</p>
				<div class="follow">
					<h4>Follow us</h4>
					<div class="icon">
						<i class='bx bxl-facebook'></i> <i class='bx bxl-instagram'></i>
						<i class='bx bxl-twitter'></i> <i class='bx bxl-pinterest'></i>
						<i class='bx bxl-youtube'></i>
					</div>
				</div>
			</div>
			<div class="col1">
				<h4>About</h4>
				<a href="#">Product</a> <a href="#">About Us</a> <a href="#">Cart
					Information</a>
			</div>
			<div class="col2">
				<h4>My Account</h4>
				<a href="#">Log In</a> <a href="#">View Cart</a> <a href="#">View
					Profile</a>
			</div>
			<div class="col3">
				<h4>Socials</h4>
				<a href="#">Instagram</a> <a href="#">Youtube</a> <a href="#">Facebook</a>
			</div>
		</div>
		<div class="col-bottom">
			<div class="copyright">
				<p>&#169 2024 Time Treaures Ltd. All Rights Reserved.</p>
			</div>
		</div>

	</footer>
</body>

</html>
