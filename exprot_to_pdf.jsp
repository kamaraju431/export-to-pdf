<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>example</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="<%=request.getContextPath()%>/resources/js/moment.min.js" /></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<script
	src="<%=request.getContextPath()%>/resources/js/convertToNumber.js" /></script>

<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/angular_material/1.0.0/angular-material.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-animate.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-aria.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-messages.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angular_material/1.0.0/angular-material.min.js"></script>
	 <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.22/pdfmake.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.min.js"></script>

<style type="text/css">
.errStyle {
	color: red;
	font-style: italic;
	font-weight: bold;
}
table, th, td {
    border: 1px solid black;
}
</style>

</head>
<body ng-app="app"  ng-controller="listController">

 <div>
 <div id="signupbox"
			margin-top:50px"  class="col-xs-12 col-sm-9 
                col-md-1 col-lg-6
                col-xs-offset-3
                col-sm-offset-2 
                col-md-offset-6 
                col-lg-offset-3 toppad">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Audit</div>
				</div>
				<div class="panel-body">
					<table class="table table-hover table-bordered">
						<tbody>


							<tr>
								<th>Select User:</th>
								<td><select class="form-control" ng-model="username"
									style="width: 80%">
										<option value="All">All</option>
										<option ng-repeat="u in usernames track by $index"
											value="{{u}}">{{u}}</option>
								</select></td>
							<tr>
								<th>From date :</th>
				<td><md-datepicker ng-model="fd" ng-change="fromDate=fd.toISOString()"></md-datepicker></td> 
							</tr>
							<tr>
								<th>To Date</th>
								<td><md-datepicker ng-model="td" ng-change="toDate=td.toISOString()"
										md-placeholder="Enter date" md-min-date="minDate"
										md-max-date="maxDate"></md-datepicker></td></tr>
						</tbody>
					</table>
						
					   <input type="submit" value="Submit" class="btn btn-primary" ng-click="onClick(username,fromDate,toDate)">
				</div>

			</div>
		</div>
 
    <div id="exportthis">
   <table style="border:1px solid black">
				<tr style="border:1px solid black">
					<th>change_dateAndTime</th>&emsp;
					<th>edited_userId</th>&emsp;
					<th>edited_userName</th>&emsp;
					<th>fieldName</th>&emsp;
					<th>logged_userId</th>&emsp;
					<th>logged_userName</th>&emsp;
					<th>newValue</th>&emsp;
					<th>oldValue</th>
				</tr>
				<tr ng-repeat="ua in secondUserAudit">
					<td>{{ua.change_dateAndTime}}</td><br>
					<td>{{ua.edited_userId}}</td>
					<td>{{ua.edited_userName}}</td>
					<td>{{ua.fieldName}}</td>
					<td>{{ua.logged_userId}}</td>
					<td>{{ua.logged_userName}}</td>
					<td>{{ua.newValue}}</td>
					<td>{{ua.oldValue}}</td>
				</tr>
			</table>
    </div>

    <button ng-click="export()">Export To PDF</button>
    </div>
</body>
<script type="text/javascript">
var app = angular.module("app", ['ngMaterial',
	'ngMessages']);
app.config(function($mdDateLocaleProvider) {
	$mdDateLocaleProvider.formatDate = function(date) {
		return moment(date).format('YYYY-MM-DD HH:mm:ss:sss');
	};
}); //DD-MMM-YYYY H:mm:ss:sss
app.controller("listController",function($scope, $http, $log, $window,
		$location) {
 	$http.get("NamesinUserTable").then(function(response) {
		$scope.usernames = response.data;
		console.log('users in user Table', response.data)
	}); 
    $scope.data=  [{"agence":"CTM","secteur":"Safi","statutImp":"operationnel"}];
    $scope.onClick = function(username, fromDate, toDate) {
		console.log("fromDATE", fromDate)
		$scope.username = username;
		$scope.fromDate = fromDate;
		$scope.toDate = toDate;
		$http.get(
				'/AizantCaseReportApplication/secondUserAudit?username='
						+ $scope.username + '&fromDate='
						+ $scope.fromDate + '&toDate=' + $scope.toDate)
				.then(function(response) {
					$scope.secondUserAudit = response.data;
					console.log('response data', response.data)

				});
	}
    $scope.export = function(){
       html2canvas(document.getElementById('exportthis'), {
           onrendered: function (canvas) {
               var data = canvas.toDataURL();
               var docDefinition = {
                   content: [{
                       image: data,
                       width: 500,
                   }]
               };
               pdfMake.createPdf(docDefinition).download("test.pdf");
           }
       });
    }
  }
);
</script>
</html>