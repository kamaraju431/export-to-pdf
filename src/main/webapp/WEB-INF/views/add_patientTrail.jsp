<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<title>AIZANT::Addvolunteer</title>
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
<body ng-app="myApp" ng-controller="myController">

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
									src="http://previews.123rf.com/images/krisdog/krisdog1308/krisdog130800150/21822848-An-illustration-of-a-cartoon-scientist-holding-a-test-tube-and-clipboard-in-a-white-lab-coat-perform-Stock-Vector.jpg"
									class="img-circle img-responsive">
							</div>

							<div class=" col-md-9 col-lg-9 ">
								<form:form commandName="PatientTrail" method="post"
									action="store_patientTrail">
									<table border="0">
										<tr>
											<td colspan="2" align="center"><h2>ADD EXPERIMENT</h2></td>
										</tr>
										<div>
											<table>
												<tr>
													<div>Experiment type:</div>
													<div>
														<form:input path="experimentType" />
													</div>
												</tr>

												<tr>
													<div>Volunteer ID:</div>
													<div>
														<form:input path="volunteerId"
															ng-repeat="x in random_array" name="point{{$index}}" />
														<br>


														<div>Volunteer Name:</div>
														<form:input path="VolunteerName"
															ng-repeat="x in random_array" name="point{{$index}}" />
													</div>

												</tr>
											</table>
											<tr>
												<div>Date (mm/dd/yyyy):</div>
												<div>
													<form:input path="date" />
												</div>
											</tr>
											<br>
											<tr>
												<td colspan="2" align="center"><input type="submit"
													value="submit" class="btn-success" /></td>
											</tr>
											<span ng-click="add_input()" class="btn-primary">Add
												Volunteer</span>
										</div>
									</table>
								</form:form>

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

	</div>



</body>
</html>