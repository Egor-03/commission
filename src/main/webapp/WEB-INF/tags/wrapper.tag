<%@ tag language="java" pageEncoding="ISO-8859-1"%>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>


<title><c:out value = "${pageTitle}"/></title>
<script src="js/helpers.js"></script>
<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
</head>


<nav class="black" role="navigation">
		<div class="nav-wrapper container">


			<ul class="left hide-on-med-and-down">
				<li><a href="/faculty"><i class="material-icons prefix">home</i>Faculty</a></li>
				<li><a class="dropdown-trigger" href="#!"	
					data-target="dropdown_menu">Common objects<i
						class="material-icons right">arrow_drop_down</i></a></li>
						<li><a href="/persone">Persone list</a></li>	
				<li><a href="/request">candidates list</a></li>
			</ul>
			<a href="#" data-target="mobile-demo" class="sidenav-trigger"><i
				class="material-icons">menu</i></a>
			<ul class="right hide-on-med-and-down">
				<li class="active"><a onclick="sendHTTPDelete('/login')">Logout</a></li>
			</ul>
		</div>
	</nav>

	<ul class="sidenav" id="mobile-demo">
		<li><a href="/faculty"><i class="material-icons prefix">home</i>Faculty</a></li>
		<li><a class="dropdown-trigger" 
			data-target="dropdown_mobile">Registration<i
				class="material-icons right">arrow_drop_down</i></a></li>
		<li><a href="/persone">Persone list</a></li>		
		<li><a href="/request">candidates list</a></li>

	</ul>
	<ul id="dropdown_mobile" class="dropdown-content">
		<li><a href="/role">role</a></li>
		<li><a href="/state">state</a></li>
		<li class="divider"></li>
		<li><a href="/subject">subject</a></li>
	</ul>
	<ul id="dropdown_menu" class="dropdown-content">
		<li><a href="/role">role</a></li>
		<li><a href="/state">state</a></li>
		<li class="divider"></li>
		<li><a href="/subject">subject</a></li>
	</ul>


<div class="section no-pad-bot" id="index-banner">
	<div class="container">
		 <jsp:doBody/> <!-- Page body will be here -->
	</div>
</div>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>