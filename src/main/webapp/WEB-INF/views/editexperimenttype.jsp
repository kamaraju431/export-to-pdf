
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Aizant::EditUser</title>
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
			
			<div
				class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">


				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">${experimentType.name}</h3>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-3 col-lg-3 " align="center">
							<img alt="User Pic"
									src="http://previews.123rf.com/images/krisdog/krisdog1308/krisdog130800150/21822848-An-illustration-of-a-cartoon-scientist-holding-a-test-tube-and-clipboard-in-a-white-lab-coat-perform-Stock-Vector.jpg"
									class="img-circle img-responsive">
							</div>

							<div class=" col-md-9 col-lg-9 ">
								<form:form modelAttribute="ExperimentType" method="post"
									action="updateexperimentType">
									<table class="table table-user-information">
										<tbody>
											<tr>
												<td><form:label path="id">Study ID:</form:label></td>
												<td><form:input path="id" value="${experimentType.id}"
														text="readonly" /></td>
											</tr>
											<tr>
												<td><form:label path="name">Study Name :</form:label></td>
												<td><form:input path="name"
														value="${experimentType.name}" text="readonly" /></td>
											</tr>
											<tr>
												<td><form:label path="aliquot">Aliquot:</form:label></td>
												<td><form:input path="aliquot"
														value="${experimentType.aliquot}" text="readonly" /></td>
											</tr>
											<tr>
												<td><form:label path="sample">Collection Times:</form:label></td>
												<td><form:input path="sample" value="${experimentType.sample}"
														text="readonly" /></td>
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
