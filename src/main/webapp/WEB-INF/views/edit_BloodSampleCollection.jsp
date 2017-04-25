<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Aizant::Edit BloodSample</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script>
function myFunction() {
    alert("Data Saved !!!");
}
</script>
</head>
<body>
	<%@ include file="/WEB-INF/views/template/header.jsp"%>
	<br>
	<br>
	<br>

	<div class="container">
		<div class="row">
			<div
				class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">


				<div class="panel panel-info">
					
					<div class="panel-body">
						<div class="row">
							

							<div class=" col-md-9 col-lg-9 ">
								<form:form modelAttribute="BloodSampleCollection" method="post"
									action="update_BloodSampleColletion">
									<table class="table table-user-information">
										<tbody>
									       <tr hidden>
												<td><form:label path="id">ID :</form:label></td>
												<td><form:input path="id"
														value="${bloodSampleCollection.id}" text="readonly" /></td>
											</tr> 
											<tr hidden>
												<td><form:label path="date">date :</form:label></td>
												<td><form:input path="date"
														value="${bloodSampleCollection.date}" text="readonly" /></td>
											</tr>
											
											<tr hidden>
												<td><form:label path="time">time:</form:label></td>
												<td><form:input path="time"
														value="${bloodSampleCollection.time}" text="readonly" /></td>
											</tr>
									
											<tr hidden>
												<td><form:label path="period">period:</form:label></td>
												<td><form:input path="period" value="${bloodSampleCollection.period}"
														text="readonly" /></td>
											</tr>
											<tr hidden>
												<td><form:label path="aliquot">aliquot:</form:label></td>
												<td><form:input path="aliquot" value="${bloodSampleCollection.aliquot}"
														text="readonly" /></td>
											</tr>
											<tr hidden>
												<td><form:label path="scanTime">scanTime:</form:label></td>
												<td><form:input path="scanTime" value="${bloodSampleCollection.scanTime}"
														text="readonly" /></td>
											</tr>
											<tr hidden>
												<td><form:label path="registerNumber">registerNumber:</form:label></td>
												<td><form:input path="registerNumber" value="${bloodSampleCollection.registerNumber}"
														text="readonly" /></td>
											</tr>
											<tr>
												<td><form:label path="comments">comments:</form:label></td>
												<td><form:textarea rows="4" cols="50" path="comments" value="${bloodSampleCollection.comments}"
														text="readonly" placeholder="Enter comment here..." ></form:textarea></td>
											</tr>
											<td><input type="submit" onclick="myFunction()" value="submit"/></td>
										</tbody>
									</table>
								</form:form>

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
