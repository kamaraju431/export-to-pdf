<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin</title>

</head>
<body>
	<%@ include file="/WEB-INF/views/template/header.jsp"%>
	<br>
	<br>
	<br>
<div class="container">
			<div class="row">
				<div class="col-xs-12">
					<div class="form-wrap">
						<h1>Hello Admin...!!!</h1>

						
							<div class="form-group">
								<a href="display_user"><span class="glyphicon glyphicon-bookmark"></span>
			Manage Users</a>
							</div>
							<div class="form-group">
								<a href="display_patientTrail"><span
			class="glyphicon glyphicon-bookmark"></span> Manage Patient Trails</a>
							</div>
							<div class="form-group">
								<a href="experiments"><span
			class="glyphicon glyphicon-bookmark"></span> Manage Study</a>
							</div>

							<a href="${pageContext.request.contextPath}/Logout">Log out</a>
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