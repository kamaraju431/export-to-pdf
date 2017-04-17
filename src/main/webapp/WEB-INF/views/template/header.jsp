<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
<title>AIZANT</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/header.css" />
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
</head>
<body>
<div class="container">
    <header class="navbar navbar-fixed-top">
    <!------------- Navbar -------------->
    <nav class="navbar navbar-inverse bs-dark">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" style="color:#ffffff" href="http://aizant.com/">AIZANT SAMPLE TRACKING</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav" width="100px">
            <li><a href="/aizantit/display_study" style="color:#ffffff">HOME</a></li>
             <li><a href="/aizantit/Blood_Sample_Collection_Record" style="color:#ffffff">BLOOD SAMPLE</a></li>
           
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" style="color:#ffffff" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">WHY WE <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="http://aizant.com/current-jobs.html">CAREERS</a></li>
                <li><a href="http://aizant.com/news.html">NEWS</a></li>
                <li><a href="http://aizant.com/contact-us.html">CONTACT US</a></li>
                <li><a href="http://aizant.com/about-us.html">ABOUT</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="http://aizant.com/services.html">SERVICES</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="http://aizant.com/facilities.html">FACILITIES</a></li>
              </ul>
            </li>
          </ul>
          <form class="navbar-form navbar-left form-horizontal" role="search">
              <div class="input-group">
                 <input type="text" class="search-box" placeholder="Search">
                 <button type="submit" class="btn"><span class="glyphicon glyphicon-search"></span></button>
              </div>
          </form>
          <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle navbar-img" style="color:#ffffff" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><%=session.getAttribute("loggedInUser")%>
              
             <img alt="User Pic"
									src="https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSAkfruQ08cqE2z_cXt2IgAtgmFUiyc_aq82Edt4SsOeybyJyE3"
									class="img-circle">
              </a>
              <ul class="dropdown-menu">
           
               <li><a href="${pageContext.request.contextPath}/Logout" target="_self">Log out</a></li>
           
              </ul>
            </li>
          </ul>
        </div>
    </nav>
</header>
</body>
</html>