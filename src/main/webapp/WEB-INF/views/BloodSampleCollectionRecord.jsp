<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Blood Sample Collection Record</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/BloodSampleCollectionRecord.css" />
	<script
	src="<%=request.getContextPath()%>/resources/js/BloodSampleCollectionRecord.js" /></script>

</head>
<body>
<div class="container" align="center">

    <div class="listWrap">
    
        <ul class="list">
        
            <li>
                <span>Date</span>
                <span>Time Point (hr)</span>
                <span>Scheduled Time of Collection</span>
                <span>Changed Scheduled Time of Collection (if any)</span>
                <span>Actual time o Collection</span>
                <span>Comments*</span>
            </li>
                    
        </ul>

    </div>

</div>
</body>
</html>