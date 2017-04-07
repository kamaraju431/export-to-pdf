<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">

<html>
<head>
<title>AIZANT::View_Study_Volunteer</title>
<link rel="stylesheet" href='<x:url value="/resources/css/bootstrap.min.css"></x:url>' />
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/viewuser.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/period_button.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/template/header.jsp"%>
	<br>
	<br>
	<br />

			<div class="col-md-7 col-md-offset-1">

				<div class="panel panel-default panel-table">
					<div class="panel-heading">
						<div class="row">
							<div class="col col-xs-6">
								<h3 class="panel-title">
									<b>Blood Sample Collection Record</b>
								</h3>
							</div>
							<div class="col col-xs-6 text-right">
								<a href="#"><button type="button"
										class="btn btn-sm btn-primary btn-create">Print</button></a> <a
									href="#" id="round-button">P1</a> <a href="#" id="round-button">P2</a>
								<a href="#" id="round-button">P3</a> <a href="#"
									id="round-button">P4</a>
							</div>

						</div>
					</div>
					<div>
						<table>
							<tr>
								<!-- <td ng-repeat="time in periodsArr track by $index"><span
									ng-click="ClickPeriods()" id="round-button">P{{$index+1}}
								</span></td> -->
								<td></td>
							</tr>
						</table>
					</div>
					<div ng-app="myApp" ng-controller="BloodSampleCollectionCtrl">

						<table class="table table-hover table-bordered">
							<tr>

								<th>Date</th>
								<th>Time Point (hr)</th>
								<th>Scheduled Time of Collection</th>
								<th>Changed Scheduled Time of Collection</th>
								<th>Actual Time ofCollection</th>
								<th>Comments*</th>

							</tr>
							<tr ng-repeat="x in names">

								<td>{{x.date}}</td>
								<td>{{x.timePoint_hr}}</td>
								<td>{{x.scheduled_Time_of_Collection}}</td>
								<td>{{x.changed_Scheduled_Time_of_Collection}}</td>
								<td>{{x.Actual_Time_ofCollection}}</td>
								<td>{{x.comments}}</td>




							</tr>
						</table>
					</div>
				</div>
			</div>
<script>
			var app = angular.module('myApp', []);
			app.controller('BloodSampleCollectionCtrl',
					function($scope, $http) {
						$http.get("list4").then(function(response) {
							$scope.names = response.data;
						});
					});
		</script>
</div>

<%@ include file="/WEB-INF/views/template/footer.jsp" %>

</body>
</html>



