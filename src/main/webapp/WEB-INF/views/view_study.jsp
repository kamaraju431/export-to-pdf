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
						<h3 class="panel-title">${study.name}</h3>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-3 col-lg-3 " align="center">
								<img alt="User Pic"
									src="http://babyinfoforyou.com/wp-content/uploads/2014/10/avatar-300x300.png"
									class="img-circle img-responsive">
							</div>

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

								<a href="edit_study?id=${study.id}" class="btn btn-primary">EDIT</a>
								<a href="Jasper?id=${study.id}" class="btn btn-primary">print</a>

							</div>
						</div>
					</div>
				</div>
			</div>
			</div>
			</div>
			
</body>
</html>