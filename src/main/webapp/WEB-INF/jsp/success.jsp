<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Success</title>
<link href="assets/css/bootstrap-united.css" rel="stylesheet" />
</head>
<body>
	<script src="jquery-1.8.3.js">
		
	</script>

	<script src="bootstrap/js/bootstrap.js">
		
	</script>

	<div class="navbar navbar-default">

		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-responsive-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>

		<div class="navbar-collapse collapse navbar-responsive-collapse">
			<form class="navbar-form navbar-right">
				<input type="text" class="form-control" placeholder="Search">
			</form>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/mainlogin.html">Home</a></li>
				<li><a href="signup.html">Signup</a></li>
				<li class="active"><a href="login.html">Login</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Explore<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#">Contact us</a></li>
						<li class="divider"></li>
						<li><a href="#">Further Actions</a></li>
					</ul></li>
			</ul>
		</div>
		<!-- /.nav-collapse -->
	</div>

	<!-- 
	<legend>Student Enrollment Login Success</legend>
	 -->
	<div class="panel panel-success">

		<div class="panel-body">
		<div class="alert alert-dismissable alert-success">
              <button type="button" class="close" data-dismiss="alert">×</button>
              <strong>Well done!</strong> You successfully logged-into the system. 
              Now you can explore the complete features!
            </div>
		</div>
	</div>
	<div>
		<a class="btn btn-primary" href="<spring:url value="myaccount.html"/>">My account</a>
		<a class="btn btn-primary" href="<spring:url value="addauction.html"/>">Add auction</a>
		<a class="btn btn-primary" href="<spring:url value="login.html"/>">Login as different user?</a>

	</div>
	<div>

		<table  class="table table-hover">
			<thead>
			<tr>
				<th scope="col">Title</th><th scope="col">Price</th><th scope="col">Category ID</th><th scope="col">Description</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${auctionList}" var = "auctionList">

				<tr class="table-light">
					<td scope="col">${auctionList.title}</td>
					<td scope="col">${auctionList.price}</td>
					<td scope="col">${auctionList.categoryName}</td>
					<td scope="col">${auctionList.description}</td>
					<td>
						<a class="btn btn-primary" href="<c:url value='/${auctionList.id}/auctionview/'/>">Make a Bid</a>
						<a class="btn btn-danger"  ng-click="">Delete</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>

		</table>



	</div>



</body>
</html>