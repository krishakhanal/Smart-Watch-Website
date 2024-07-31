<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f8f9fa;
	margin: 0;
	padding: 0;
}

.login-container {
	max-width: 400px;
	margin: 100px auto;
	padding: 20px;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

input[type="text"], input[type="password"], input[type="submit"] {
	width: 100%;
	padding: 10px;
	margin-top: 10px;
	margin-bottom: 10px;
	border: 1px solid #ccc;
	border-radius: 3px;
}

input[type="submit"] {
	background-color: #007bff;
	color: #fff;
	cursor: pointer;
}

input[type="submit"]:hover {
	background-color: #0056b3;
}

.error {
	color: red;
}
</style>
</head>
<body>
	<div class="login-container">
		<h2>Login</h2>
		<% if (request.getAttribute("error") != null) { %>
		<p class="error"><%=request.getAttribute("error") %></p>
		<% } %>
		<form action="login" method="post">
			<label for="username">Username:</label> <input type="text"
				id="username" name="username" required><br> <label
				for="password">Password:</label> <input type="password"
				id="password" name="password" required><br> <input
				type="submit" value="Login">
		</form>
		<div>
			<p>
				Not registered yet? <a
					href="<%=request.getContextPath()%>/userRegistration">Register
					Here</a>
			</p>
		</div>

	</div>
</body>
</html>
