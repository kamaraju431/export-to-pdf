
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Aizant::Editexperiment</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/template/header.jsp"%>
	<br>
	<br>
	<br>

	<div class="container">
		<div class="row">
			<div class="col-md-5  toppad  pull-right col-md-offset-3 ">
				<A href="#"><%=session.getAttribute("loggedInUser")%></A> <A
					href="${pageContext.request.contextPath}/Logout">Logout</A> <br>
			</div>
			<div
				class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">


				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">${login.username}</h3>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-3 col-lg-3 " align="center">
								<img alt="User Pic"
									src="http://previews.123rf.com/images/krisdog/krisdog1308/krisdog130800150/21822848-An-illustration-of-a-cartoon-scientist-holding-a-test-tube-and-clipboard-in-a-white-lab-coat-perform-Stock-Vector.jpg"
									class="img-circle img-responsive">
							</div>

							<div class=" col-md-9 col-lg-9 ">
								<form:form modelAttribute="PatientTrail" method="post"
									action="update_patientTrail">
									<table class="table table-user-information">
										<tbody>
											<tr>
												<td><form:label path="id">Id:</form:label></td>
												<td><form:input path="id" value="${patienttrail.id}"
														text="readonly" /></td>
											</tr>
											<tr>
												<td><form:label path="experimentType">Experiment Id:</form:label></td>
												<td><form:input path="experimentType"
														value="${patienttrail.experimentType}" text="readonly" /></td>
											</tr>
											<tr>
												<td><form:label path="date">Date :</form:label></td>
												<td><form:input path="date"
														value="${patienttrail.date}" text="readonly" /></td>
											</tr>
											<tr>
												<td><form:label path="volunteerId">Volunteer Id:</form:label></td>
												<td><form:input path="volunteerId"
														value="${patienttrail.volunteerId}" text="readonly" /></td>
											</tr>
											<tr>
												<td><form:label path="volunteerName">Volunteer Name:</form:label></td>
												<td><form:input path="volunteerName"
														value="${patienttrail.volunteerName}" text="readonly" /></td>
											</tr>
											<td><input type="submit" value="submit" /></td>
										</tbody>
									</table>
								</form:form>

							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</body>
</html>
