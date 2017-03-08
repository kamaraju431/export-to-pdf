<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
	ng-app="myApp">
<head>
<title>AIZANT::AllUsers</title>
<link rel="stylesheet"
	href='<x:url value="/resources/css/bootstrap.min.css"></x:url>' />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/display.css" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">



<!-- Bootstrap modals -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/ui-bootstrap-custom-2.5.0-csp.css" />
<script
	src="<%=request.getContextPath()%>/resources/js/ui-bootstrap-custom-2.5.0.min.js" /></script>
<script
	src="<%=request.getContextPath()%>/resources/js/ui-bootstrap-custom-tpls-2.5.0.min.js" /></script>

<script
	src="<%=request.getContextPath()%>/resources/js/displayexperimentcontroller.js" /></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/template/header.jsp"%>

	<br>
	<br>
	<br />
	<div class="col-md-10 col-md-offset-1">

		<div class="panel panel-default panel-table">
			<div class="panel-heading">
				<div class="row">
					<div class="col col-xs-6">
						<h3 class="panel-title">experiments</h3>
					</div>
					<div class="col col-xs-6 text-right">
						<a href="add_experiment"><button type="button"
								class="btn btn-sm btn-primary btn-create">Add
								Experiment</button></a>
					</div>
				</div>
			</div>

			<div ng-controller="experimentCtrl">

				<table class="table table-hover table-bordered">
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Length</th>


					</tr>
					<tr ng-repeat="s in names | filter:searchBy">
						<td>{{s.id}}</td>
						<td>{{s.name}}</td>
						<td>{{getLengthFromSamples(s.sample)}}</td>



						<td><a
							href="${pageContext.servletContext.contextPath}/experiment_view?id={{s.id}}"><span
								class="glyphicon glyphicon-eye-open"></span></a> <a
							href="${pageContext.servletContext.contextPath}/edit_experimentType?id={{s.id}}"><span
								class="glyphicon glyphicon-pencil"></span></a> <!-- Button to trigger modal -->
							<button ng-click="openDeleteModal(s.id)"
								class="glyphicon glyphicon-trash"></button>
							</div> <!-- /.modal-dialog -->
							</div> <!-- /.modal --></td>
						<!-- <a href="${pageContext.servletContext.contextPath}/deleteuser?id={{s.id}}"><span
								class="glyphicon glyphicon-trash"></span></a></td> -->
					</tr>
				</table>




				<div class="panel-footer">
					<div class="row">
						<div class="col col-xs-4">Page 1 of 5</div>
						<div class="container">
							<ul class="pagination">
								<li class="disabled"><a href="#">«</a></li>
								<li ng-repeat="i in numlist track by $index">
									<button ng-click="goToPage($index + 1)">{{$index + 1}}</button>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	</div>

	</div>



</body>
</html>



