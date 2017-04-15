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
<!--  <style>  
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
 -->

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
									<table class="table table-bordered"align="center">

										<tbody>
<tr>
												<th>Client Study Id:</th>
												<td><input type="text" 
												ng-model="study.clientStudyId"></td>
											</tr>
											<tr>
												<th>Date :</th>
												<td><input type="text" ng-model="study.date"
													placeholder="dd/mm/yyyy"></td>
											</tr>
											<tr>
												<th>Study name:</td>
												<td><input type="text"
													ng-model="study.name"></td>
											</tr>
											<tr>
												<th>Number of Aliquots:</th>
												<td><input type="text"
													ng-model="study.aliquot"></td>
											</tr>
											<tr>
												<th>sampleCollectionSize(ml):</th>
												<td><input type="text"
													ng-model="study.sampleCollectionSize_in_ml"></td>
											</tr>
											<tr>
												<th>Periods:</th>
												<td><select
													ng-model="study.periods" required>
														<option value="1">1</option>
														<option value="2">2</option>
														<option value="3">3</option>
														<option value="4">4</option>
												</select></td>
											</tr>
											<tr>
												<th>Number of samples:</th>
												<td><input type="text" ng-model="sampleCount"></td>
											</tr>
											
											<tr>
												<th>Collection times :</th>
												
												<td>
													<table class="table table-hover table-bordered">
												<tr type="text"ng-repeat="s in study.sampleTime">
												<td><input type="text" style="width:50px;" ng-model="s.timePoint"></td>
												</tr>
												</table>
												</td>
												</tr>
												</tbody>
												
													

											<tr>
												<th>Number of Volunteers:</th>
												<td><input type="text" ng-model="volunteerCount"></td>
											</tr>

											<tr>
												<th>Volunteers:</th>
												<td>

													<table class="table table-hover table-bordered">
														<tr>
															<th>Id</th>
															<th>Name</th>
														</tr>
														<tr type="text"
															ng-repeat="a in study.studyVolunteers">
															<td><input type="text" ng-model="a.volunteerId"></td>
															<td><input type="text" ng-model="a.volunteerName"></td>
														</tr>
										
																	<td><input type="submit" value="submit"
										class="btn-success" /></td>
													
													</table>
												</td>
											

											</tr>
									</table>
									
								
								</body>
									
								</form>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>



</body>
<%@ include file="/WEB-INF/views/template/footer.jsp"%>
</html>