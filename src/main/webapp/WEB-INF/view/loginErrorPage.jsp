<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Error</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f5f5f5;
	margin: 0;
	padding: 0;
}

.container {
	width: 50%;
	margin: 50px auto;
	text-align: center;
}

h2 {
	color: #333;
}

p {
	color: #666;
	line-height: 1.6;
}

a {
	color: #007bff;
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
	<div class="container">
		<h2>Error Has Occurred</h2>
		<p>You may not have logged in or your session has expired!</p>
		<p>
			Please proceed to <a href="<%=request.getContextPath()%>/login">login</a>.
		</p>
	</div>
</body>
</html>
