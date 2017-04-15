<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<title>AIZANT::AddBloodSample</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style type="text/css">
.errStyle {
	color: red;
	font-style: italic;
	font-weight: bold;
}
</style>
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
					<div class="panel-body">
						<div class="row">
							<div class="col-md-3 col-lg-3 " align="center">
								<img alt="User Pic"
									src="https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSAkfruQ08cqE2z_cXt2IgAtgmFUiyc_aq82Edt4SsOeybyJyE3"
									class="img-circle img-responsive">
							</div>

							<div class=" col-md-9 col-lg-9 ">

								<c:url var="add_BloodSampleCollection" value="add_BloodSampleCollection"></c:url>
								<form:form commandName="BloodSampleCollection" method="post" action="store_BloodSampleCollection">
									<form:errors path="*" cssClass="errStyle" element="div" />
									<table class="table table-user-information">
										<tbody>
											<tr>
												<td><form:label path="date">Date :</form:label></td>
												<td><form:input path="date" placeholder="DD/MM/YYYY"/> <font color="red"><form:errors
															path="date"></form:errors></font></td>
											</tr>
											<tr>
												<td><form:label path="time">Time :</form:label></td>
												<td><form:input path="time" /> <font color="red"><form:errors
															path="time"></form:errors></font></td>
											</tr>
											<tr>
												<td><form:label path="period">Period :</form:label></td>
												<td><form:input path="period" />
													<font color="red"><form:errors
															path="period"></form:errors></font></td>
											</tr>
											<tr>
												<td><form:label path="scanTime">Scan Time :</form:label></td>
												<td><form:input path="scanTime" /> <font color="red"><form:errors
															path="scanTime"></form:errors></font></td>
											</tr>
											<tr>
												<td><form:label path="volunteerId">Volunteer Id :</form:label></td>
												<td><form:input path="volunteerId" /> <font color="red"><form:errors
															path="volunteerId"></form:errors></font></td>
											</tr>
											<tr>
												<td><form:label path="comments">Comments* :</form:label></td>
												<td><form:input path="comments" /> <font color="red"><form:errors
															path="comments"></form:errors></font></td>
											</tr>

											<td><input type="submit" value="submit"
												class="btn-success" /></td>
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
<%@ include file="/WEB-INF/views/template/footer.jsp"%>
</html>