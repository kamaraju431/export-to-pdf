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
<base href="/Sample">
<title>AIZANT::View_Study</title>
<link rel="stylesheet"
	href='<x:url value="/resources/css/bootstrap.min.css"></x:url>' />
<script src='<x:url value="/resources/js/jquery.min.js"></x:url>'></script>
<script src='<x:url value="/resources/js/bootstrap.min.js"></x:url>'></script>
<script src='<x:url value="/resources/js/angular.min.js"></x:url>'></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/display_user.css" />

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/viewuser.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/period_button.css" />

<script
	src="<%=request.getContextPath()%>/resources/js/display_view_study_Controller.js" /></script>

</head>
<body ng-app="viewStudyApp" ng-controller="viewStudyController">
	<%@ include file="/WEB-INF/views/template/header.jsp"%>
	<br>
	<br>
	<br>
	<div class="container">
		<div class="section">


			<div
				class="col-xs-9 col-sm-9 col-md-0 col-lg-4 col-xs-offset-0 col-sm-offset-0 col-md-offset-0 col-lg-offset-0 toppad">


				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">${study.name}</h3>
					</div>
					<div class="panel-body">
						<div class="row">


							<div class=" col-md-9 col-lg-9 ">
								<table class="table table-user-information">
									<tbody>

										<tr>
											<td>Experiment Name:</td>
											<td>${study.name}</td>
										</tr>
										<tr>
											<td>Aliquot</td>
											<td>${study.aliquot}</td>
										</tr>
										<tr>
											<td>Sample Collection Size(in ml)</td>
											<td>${study.sampleCollectionSize_in_ml}</td>
										</tr>
										<tr>
											<td>Periods</td>
											<td>${study.periods}</td>
										</tr>
										<tr>
											<td>Client Study ID</td>
											<td>${study.clientStudyId}</td>
										</tr>

										<tr>
										<tr>
											<td>Collection Times</td>
											<td>${study.sample}</td>

										</tr>

									</tbody>
								</table>

								<a href="/Sample/edit_study?id=${study.id}" target="_self"
									class="btn btn-primary">EDIT</a> <a
									href="/Sample/Jasper?id=${study.id}" target="_self"
									class="btn btn-primary">print</a>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<br />

		<div class="section right">

			<div class="col-md-7 col-md-offset-1">

				<div class="panel panel-default panel-table">
					<div class="panel-heading">
						<div class="row">
							<div class="col col-xs-6">
								<h3 class="panel-title">
									<b>Study Volunteers</b>
								</h3>
							</div>
						<div class="col col-xs-6 text-right">
					Periods :<b ng-repeat="time in periodsArr track by $index">
					<span ng-click="onClick($index+1)" id="round-button" ng-class="$index+1 === currentPeriod ? 'selected' : ''">P{{$index+1}} </span></b>
					</div>
						</div>
					</div>
					
					<div>

						<table class="table table-hover table-bordered">
							<tr>

								<th>Volunteer ID</th>
								<th>Volunteer Name</th>
								<th>Action</th>
								<th>Status</th>

							</tr>
							<tr ng-repeat="a in study.studyVolunteers">

								<td>{{a.volunteerId}}</td>

								<td>{{a.volunteerName}}</td>



								<td><a ng-href="/Sample/view_studyVolunteer?id={{a.id}}"
									target="_self"><span class="glyphicon glyphicon-eye-open"></span></a></td>
								<td></td>
								<!-- <a href="${pageContext.servletContext.contextPath}/deleteuser?id={{s.id}}"><span
								class="glyphicon glyphicon-trash"></span></a></td> -->
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>