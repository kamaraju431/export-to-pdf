<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">

<head>
<title>AIZANT::View</title>
<link rel="stylesheet"
	href='<x:url value="/resources/css/bootstrap.min.css"></x:url>' />
<script src='<x:url value="/resources/js/jquery.min.js"></x:url>'></script>
<script src='<x:url value="/resources/js/bootstrap.min.js"></x:url>'></script>
<script src='<x:url value="/resources/js/angular.min.js"></x:url>'></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/viewuser.css" />


</head>
<body>
	<%@ include file="/WEB-INF/views/template/header.jsp"%>

	<br>
	<br>
	<br>
	<div class="container">
		<div class="row">


			<div
				class="col-xs-12 col-sm-12 
				col-md-1 col-lg-3 
				col-xs-offset-0 
				col-sm-offset-0 
				col-md-offset-3 
				col-lg-offset-0 toppad">



				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">${experimentType.name}</h3>
					</div>
					<div class="panel-body">
						<div class="row"></div>


						<div class=" col-md-9 col-lg-9 ">
							<table class="table table-user-information">
								<tbody>
									<table class="table table-bordered" align="center">
										<tr>
											<th>ID:</th>
											<td>${experimentType.id}</td>
										</tr>
										<tr>
											<th>Study Name:</th>
											<td>${experimentType.name}</td>
										</tr>
										<tr>
											<th>Aliquot</th>
											<td>${experimentType.aliquot}</td>
										</tr>

										<tr>
										<tr>
											<th>Collection Times</th>
											<td>${experimentType.sample}</td>

										</tr>
										<table>
											</tbody>
										</table>

										<a href="editexperimenttype?id=${experimentType.id}"
											class="btn btn-primary">EDIT</a>
										</div>
						</div>
					</div>
				</div>
			</div>
			<div class="container" align="center">
				<div>
					<h2>Hello World</h2>
					<div>

						<div align="center"
							class="col-xs-12 col-sm-12 
				col-md-1 col-lg-7 
				col-xs-offset-0 
				col-sm-offset-0 
				col-md-offset-3 
				col-lg-offset-0 toppad">
							<div class="panel panel-info" align="center">
								<div class="panel-heading" align="center">
									<h3 class="panel-title">${experimentType.name}</h3>
								</div>
								<div class="panel-body" align="center">
									<div class="row" align="right"></div>


									<div class=" col-md-9 col-lg-9 " align="center">

										<table class="table table-hover table-bordered" align="center">
											<tr>
												<th>VOLUNTEER ID</th>
												<th>VOLUNTEER NAME</th>
												<th>STUDY NAME</th>
												<th>DATE</th>
												<th>ACTION</th>
											</tr>
											<tr ng-repeat="e in names | filter:searchBy">
												<td>{{e.volunteerId}}</td>
												<td>{{e.volunteerName}}</td>
												<td>{{e.experimentType}}</td>
												<td>{{e.date}}</td>

												<td><a
													href="${pageContext.servletContext.contextPath}/view_patientTrail?id={{e.id}}"><span
														class="glyphicon glyphicon-eye-open"></span></a> <sec:authorize
														access="hasRole('ROLE_ADMIN')">
														<a
															href="${pageContext.servletContext.contextPath}/edit_patientTrail?id={{e.id}}"><span
															class="glyphicon glyphicon-pencil"></span></a>
														<!-- Button to trigger modal -->
														<a ng-click="openDeleteModal(e.id)"><span
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
															ng-click="goToPage($index + 1)"><span class="active">{{$index
																	+ 1}}</span></a></li>
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
			</div>
		</div>
	</div>
</body>
</html>