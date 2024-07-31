<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Contact Us</title>
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/Css/aboutus.css" />

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
	integrity="sha512-QsHKzSKj8upqRokag0lNrBs/GNEknaybv8gPlFgwPHzGY+9eXaHdOUZdzSqTTqWInKy'MH2O+gseW3jU4Yz3yQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<style>
* {
	padding: 0;
	margin: 0;
	box-sizing: border-box;
}

body {
	font-family: sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f2f2f2;
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

.nav-box a {
	text-decoration: none;
	margin-right: 10px;
	color: black;
	font-size: 20px;
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

.container {
	display: flex;
	width: 100%;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
}

.contact-section {
	width: 350px;
	padding: 50px;
	border-radius: 10px;
	background-color: #378DA0;
	/*text-align: center;
    color: #FFFFFF; 
    margin: 50px auto;
 */
	text-align: left;
	color: #FFFFFF /* Adjust text color for contrast */
}

.contact-section h1 {
	margin-bottom: 20px;
	font-size: 1.5em;
}

.form-group {
	margin-bottom: 15px;
	border-radius: 5px; /* Add border radius for rounded corners */
}

.form-group label {
	display: block;
	margin-bottom: 5px; /
	font-weight: bold;
	/
}

.form-group input, .form-group textarea {
	width: 100%;
	padding: 10px;
	border: 1px solid #ffff;
	border-radius: 5px;
	height: 100 px;
}

#contact-details .map {
	width: 100%;
	height: 400px;
}

.contact-section button[type="submit"] {
	background-color: #2471a3; /* Adjust button color */
	color: #fff; /* White text */
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	cursor: pointer; /* Indicate clickable element */
	text-align: center;
	display: block; /* Make button a block element on its own line */
	margin-top: 15px;
}

.info {
	width: 650px;
	padding: 30px;
	border-radius: 10px;
	background-color: #378DA0;
	min-height: 67vh;
	margin-left: 30px;
	text-align: justify;
	line-height: 2;
	font-size: 16px;
}

.info h2 {
	margin-bottom: 15px;
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

	<div class="container">

		<div class="info" style="background-color: #f2f2f2 !important;">
			<h1>About Us</h1>
			<p>Welcome to Time Treasures, the place where elegance and
				diversity collide in the watch industry.We were founded in 2021 and
				are your go-to source for fine watches from top global brands. Our
				carefully chosen collection highlights the best of watchmaking
				creativity, showcasing everything from classic Swiss workmanship to
				cutting-edge Japanese designs. Our team is committed to providing
				individual service and competence, and we can assist you in finding
				the ideal watch for any occasion. Explore our collection now to find
				the height of sophistication and style.</p>

			<h1>Contact Us</h1>
			<p>Please don't hesitate to get in touch with our dedicated staff
				at Time Treasures with any questions, concerns, or comments. We're
				excited to hear from you!</p>


			<p>
				<i class="fas fa-phone"></i> Phone: 0129283748
			</p>
			<p>
				<i class="fas fa-envelope"></i> Email: info@timetreasures.com
			</p>

		</div>

		<section class="contact-section">
			<h1>Get In Touch</h1>

			<form action="">
				<div class="form-group">
					<label for="name">Name</label> <input type="text" id="name"
						name="name" required>
				</div>
				<div class="form-group">
					<label for="email">Email</label> <input type="email" id="email"
						name="email" required>
				</div>
				<div class="form-group">
					<label for="phone">Phone Number</label> <input type="tel"
						id="phone" name="phone" required>
				</div>
				<div class="form-group">
					<label for="subject">Subject</label> <input type="text"
						id="subject" name="subject" required>
				</div>
				<div class="form-group">
					<label for="message">Message</label>
					<textarea id="message" name="message" cols="50" rows="10" required></textarea>
				</div>
				<button type="submit">Send Message</button>
			</form>

		</section>
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