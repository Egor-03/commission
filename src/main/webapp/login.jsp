<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title><c:out value="Login" /></title>

<!-- Compiled and minified CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<script src="js/helpers.js"></script>
</head>


<div class="section no-pad-bot" id="index-banner">
	<div class="container">
		<form action="/login" method="post">
			<div class="row">
				<div class="col l4 m6 s12 offset-l4 offset-m3">
					<div class="row">
						<div class="col s12">
							<input autofocus name="login" type="text" size="30" placeholder="email" />
						</div>
					</div>
					<div class="row">
						<div class="col s12">
							<input name="password" type="password" size="30" placeholder="password" />
						</div>
					</div>

					<div class="row">
						<div class="col s12 center-align">
							<input type="submit" name="submit" value="Login" />
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>