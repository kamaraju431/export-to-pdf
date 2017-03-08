<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>
	<%@ include file="/WEB-INF/views/template/header.jsp"%>
	<br>
	<br>
	<br>
	<h1>hello Admin...!!!</h1>

	<p>
		<a href="display_user"><span class="glyphicon glyphicon-bookmark"></span>
			Manage Users</a>
	<p>
		<a href="display_patientTrail"><span
			class="glyphicon glyphicon-bookmark"></span> Manage Patient Trails</a>
	<p>
		<a href="display_experiments"><span
			class="glyphicon glyphicon-bookmark"></span> Manage Experiments</a>
	<p>
		<a href="${pageContext.request.contextPath}/Logout">Log out</a>
	</p>




</body>
</html>