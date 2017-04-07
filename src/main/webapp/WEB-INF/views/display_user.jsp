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
	href="<%=request.getContextPath()%>/resources/css/display_user.css" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/pagination.css" />

<!-- Bootstrap modals -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/ui-bootstrap-custom-2.5.0-csp.css" />
<script
	src="<%=request.getContextPath()%>/resources/js/ui-bootstrap-custom-2.5.0.min.js" /></script>
<script
	src="<%=request.getContextPath()%>/resources/js/ui-bootstrap-custom-tpls-2.5.0.min.js" /></script>

<script
	src="<%=request.getContextPath()%>/resources/js/displaycontroller.js" /></script>
</head>
<body ng-controller="UserCtrl">
	<%@ include file="/WEB-INF/views/template/header.jsp"%>

	<br>
	<br>
	<br />
	<div class="col-md-10 col-md-offset-1">

		<div class="panel panel-default panel-table">
			<div class="panel-heading">
				<div class="row">
					<div class="col col-xs-6">
						<h3 class="panel-title">Users</h3>
					</div>
					<div class="col col-xs-6 text-right">

						<form class="navbar-form" role="search">

							<div class="input-group custom-search-form" align="center">
								<input type="text" ng-model="searchBy" class="form-control">
								<!-- ng-model="tempfilter" -->
								<span class="input-group-btn">
									<button class="btn btn-primary" ng-click="search()"
										type="button">
										
										<span class="glyphicon glyphicon-search"></span>
									</button>
								</span>
							</div>
							<sec:authorize access="hasRole('ROLE_ADMIN')">
								<a href="add_user"><button type="button"
										class="btn btn-sm btn-primary btn-create">Add User</button></a>
							</sec:authorize>
						</form>
					</div>

				</div>

				<div>

					<table class="table table-hover table-bordered">
						<tr>
						
							<th>USER NAME</th>
							<th>EMAIL</th>
							<th>AUTHORITY</th>
							<th>ACTION</th>

						</tr>
						<tr ng-repeat="s in names | filter:searchBy">
						
							<td>{{s.username}}</td>
							<td>{{s.email}}</td>
							<td>{{s.role}}</td>

							<td><a
								href="${pageContext.servletContext.contextPath}/view_user?id={{s.id}}"><span
									class="glyphicon glyphicon-eye-open"></span></a> <sec:authorize
									access="hasRole('ROLE_ADMIN')">
									<a
										href="${pageContext.servletContext.contextPath}/edit_user?id={{s.id}}"><span
										class="glyphicon glyphicon-pencil"></span></a>
									<!-- Button to trigger modal -->
									<a ng-click="openDeleteModal(s.id)"><span
										class="glyphicon glyphicon-trash"></span></a>
								</sec:authorize>
								</div> <!-- /.modal-dialog -->
								</div> <!-- /.modal --></td>
							<!-- <a href="${pageContext.servletContext.contextPath}/deleteuser?id={{s.id}}"><span
								class="glyphicon glyphicon-trash"></span></a></td> -->
						</tr>
					</table>




					<div class="panel-footer">
						<div class="row">
							<div class="col col-xs-4">Page No:</div>
							<div class="container">
								<ul class="pagination">

									<li ng-repeat="i in numlist track by $index"><a
										ng-click="goToPage($index + 1)" id="round-button" ng-class="$index+1 === currentPage ? 'selected' : ''"><span>{{$index
												+ 1}}</span></a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
