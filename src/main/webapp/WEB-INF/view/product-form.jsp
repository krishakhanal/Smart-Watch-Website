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
	max-width: 600px;
	margin: 0 auto;
	padding: 20px;
}

t
.card {
	border-radius: 4px;
	padding: 20px;
	margin-bottom: 20px;
}

.form-group {
	margin-bottom: 20px;
}

label {
	font-weight: bold;
}

input[type="text"], input[type="file"] {
	width: 100%;
	padding: 8px;
	border: 1px solid #ddd;
	border-radius: 4px;
	box-sizing: border-box;
}

button {
	padding: 8px 16px;
	border: none;
	border-radius: 4px;
	background-color: #378da0;
	color: white;
	cursor: pointer;
}

button:hover {
	background-color: #F65A83;
}

img {
	max-width: 100px;
	max-height: 100px;
}
</style>
</head>

<body>
	<header>
		<nav>
			<div>
				<h1>Admin Panel</h1>
			</div>
			<ul>
				<li><a href="<%=request.getContextPath()%>/list">Back to
						Product Page</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container">
		<div class="card">
			<div class="card-body">
				<c:if test="${product != null}">
					<form action="update" method="post" enctype="multipart/form-data">
				</c:if>
				<c:if test="${product == null}">
					<form action="insert" method="post" enctype="multipart/form-data">
				</c:if>

				<caption>
					<h2>
						<c:if test="${product != null}">
                            Edit Product
                        </c:if>
						<c:if test="${product == null}">
                            Add New Product
                        </c:if>
					</h2>
				</caption>

				<c:if test="${product != null}">
					<input type="hidden" name="product_id"
						value="<c:out value='${product.product_id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Product Name</label> <input type="text"
						value="<c:out value='${product.product_name}' />"
						class="form-control" name="product_name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Description</label> <input type="text"
						value="<c:out value='${product.description}' />"
						class="form-control" name="description">
				</fieldset>

				<fieldset class="form-group">
					<label>Price</label> <input type="text"
						value="<c:out value='${product.price}' />" class="form-control"
						name="price">
				</fieldset>

				<fieldset class="form-group">
					<label>Stock</label> <input type="text"
						value="<c:out value='${product.stock}' />" class="form-control"
						name="stock">
				</fieldset>

				<fieldset class="form-group">
					<label>Current Image</label><br>
					<c:if test="${product != null and product.image != null}">
						<img src="data:image/jpg;base64,${product.imageBase64}"
							width="100" height="100" />
					</c:if>
				</fieldset>

				<fieldset class="form-group">
					<label>New Image</label> <input type="file" name="image"
						class="form-control-file">
				</fieldset>

				<button type="submit" class="btn">Save</button>

			</div>
			</form>
		</div>
	</div>
	</div>
</body>

</html>
