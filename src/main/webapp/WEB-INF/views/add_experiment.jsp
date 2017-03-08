<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="n">
<head>
<title>AIZANT::Adduser</title>
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
</head>
<body ng-app="myApp" ng-controller="addexperiment">

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
								<center>
									<img src="<c:url value="/resources/images/test.jpg" />"
										style="width: 25%">
								</center>

							</div>

							<div class=" col-md-9 col-lg-9 ">
								<c:url var="add_experiment" value="add_experiment"></c:url>
								<form:form commandName="ExperimentType" method="post"
									action="store_experiments">
									<table class="table table-bordered">
										<tbody>
											<

											<form ng-submit="addexperiment()">
												<tr>
													<td><label>Name: <input type="text"
															ng-model="name" required /></label></td>
												</tr>
												<tr>
													<td><label>Aliquot: <input type="number"
															ng-model="aliquot" required /></label></td>
												</tr>
												<tr>
													<td><label>Number of samples: <input
															type="number" ng-model="count" required /></label></td>
												</tr>
												<tr>
													<input ng-repeat="time in sample">
													<td><label>Collection times: <input
															type="number" ng-model="time" required /></label></td>
												</tr>


												<td><input type="submit" value="submit"
													class="btn-success" /></td>
										</tbody>
									</table>
									</form>
								</form:form>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		var app = angular.module("myApp", []);
		app.controller("myController", [ "$scope", function($scope) {
			$scope.random_array = [ 0 ]
			$scope.add_input = function() {
				var i = $scope.random_array.length
				$scope.random_array.push(i)
			}
		} ]);
	</script>



</body>
</html>