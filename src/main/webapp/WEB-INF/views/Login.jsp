<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AIZANT::Login</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/Login.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<style type="text/css">
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}

.logoutblock {
	color: blue;
	background-color: lightgreen;
	border: 3px solid blue;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body>

	<%@ include file="/WEB-INF/views/template/header.jsp"%>
	<br>
	<br>
	<br>
	<br>
	<div class="container">
		<c:if test="${error=='true'}">
			<div class="errorblock">Enter Your UserName and Password....</div>
		</c:if>
		<c:if test="${not empty logoutmsg }">
			<div class="logoutblock">
				<c:out value="${logoutmsg}" />
			</div>
		</c:if>
		<section id="login">
		<div class="container">
			<div class="row">
				<div class="col-xs-12">
					<div class="form-wrap">
						<h1>Log in with your email account</h1>

						<form role="form" action="j_spring_security_check" method="post"
							id="login-form" autocomplete="off">
							<div class="form-group">
								<label for="username">UserName:</label> <input type="text"
									name="username" class="form-control"
									placeholder="Enter User Username" />
							</div>
							<div class="form-group">
								<label for="password">Password:</label> <input type="password"
									class="form-control" name="password"
									placeholder="Enter Password" />
							</div>

							<input type="submit" id="btn-login"
								class="btn btn-custom btn-lg btn-block" value="Log in">
						</form>
						<hr>
					</div>
				</div>
				<!-- /.col-xs-12 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.container --> </section>
</body>
</html>
