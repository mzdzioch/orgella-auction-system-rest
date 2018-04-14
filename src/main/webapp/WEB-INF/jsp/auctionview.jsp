<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Success</title>
<link href="/assets/css/bootstrap-united.css" rel="stylesheet" />
</head>
<body>
    <script src="/jquery-1.8.3.js">

    </script>

	<script src="/bootstrap/js/bootstrap.js">
		
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
				<li><a href="/index.html">Home</a></li>
				<li><a href="/signup.html">Signup</a></li>
				<li class="active"><a href="/login.html">Login</a></li>
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



	<div>
		<a class="btn btn-primary" href="<spring:url value="/myaccount.html"/>">My account</a>
		<a class="btn btn-primary" href="<spring:url value="/mainlogin.html"/>">Auctions</a>
		<a class="btn btn-primary" href="<spring:url value="/addauction.html"/>">Add auction</a>
		<a class="btn btn-primary" href="<spring:url value="/login.html"/>">Login as different user?</a>

	</div>
	<div>

		<table  class="table table-hover">

			<tbody>
				<tr>
					<th scope="col">Title</th>
					<td scope="col">${auction.title}</td>
				</tr>
				<tr>
					<th scope="col">Price</th>
					<td scope="col">${lastPrice}

						<form:form commandName="bid">
								<form:input path="bidPrice"/>
								<input class="btn btn-primary" type="submit" value="Make a Bid"/>
						</form:form>

								<c:if test="${not empty message}">
									<div class="message green">${message}</div>
								</c:if>


					</td>
				</tr>
				<tr>
					<th scope="col">Category ID</th>
					<td scope="col">${auction.categoryId}</td>
				</tr>
				<tr>
					<th scope="col">Description</th>
					<td scope="col">${auction.description}</td>

				</tr>
			</tbody>
		</table>

	</div>

</body>
</html>