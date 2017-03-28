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
<script
	src="<%=request.getContextPath()%>/resources/js/display_add_study_Controller.js" /></script>
</head>
</script>
 <style>  
   table, th , td {  
      border: 1px solid grey;  
      border-collapse: collapse;  
      padding: 5px;  
   }  
     
   table tr:nth-child(odd) {  
      background-color: #f2f2f2;  
   }  
  
   table tr:nth-child(even) {  
      background-color: #ffffff;  
   } 
</style>  


</head>
<body ng-app="myApp" ng-controller="addStudy" >

	<%@ include file="/WEB-INF/views/template/header.jsp"%>
	<br>
	<br>
	<br>
	<div class="container">
		<div class="row">
			<div
				class="col-xs-12 col-sm-12 
				col-md-1 col-lg-0
				col-xs-offset-0
				col-sm-offset-0 
				col-md-offset-0 
				col-lg-offset-3 toppad">

			
			<div class="center">
					<div class="panel-body" align="center" >
						<div class="row">
							<div class="col-md-3 col-lg-3 " align="center"></div>
							<div class=" col-md-9 col-lg-9 " align="center">
								<form ng-submit="addStudy()">
									
											<tr>
												<th>Number of Volunteers:</th>
												<td ng-model="volunteerCount"></td>
											</tr>

											<tr>
												<th>Volunteers:</th>
												<td>
                                                     		
													<table class="table table-hover table-bordered">
														<tr>
															<th>Id</th>
															<th>Name</th>
														</tr>
														<tr 
															ng-repeat="a in study.studyVolunteers">
															<td>{{a.volunteerId}}</td>
															<td>{{a.volunteerName}}</td>
														</tr>
												
																	<td><input type="submit" value="submit"
										class="btn-success" /></td>
													
													</table>
												</td>
											

											</tr>
									</table>
									
								
									</tbody>
									
									</table>
										</center>
								</form>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>



</body>
</html>