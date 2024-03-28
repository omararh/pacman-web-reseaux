<!DOCTYPE html>
<html lang="fr">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<title> Modification </title>
	<style>
		body {
			padding-top: 70px;
		}
		.navbar-default {
			background-color: #f8f8f8;
			border-color: #e7e7e7;
		}
		.navbar-default .navbar-brand {
			color: #007bff;
		}
		.navbar-default .navbar-brand:hover, .navbar-default .navbar-brand:focus {
			color: #0056b3;
		}
		.navbar-default .navbar-nav > li > a {
			color: #555;
		}
		.navbar-default .navbar-nav > li > a:hover, .navbar-default .navbar-nav > li > a:focus {
			color: #333;
		}
		.btn-primary {
			background-color: #4CAF50;
			border-color: #4CAF50;
		}
		.btn-primary:hover, .btn-primary:focus {
			background-color: #45a049;
		}
		.form-control {
			border-radius: 0;
		}
		.alert-success, .alert-danger {
			border-radius: 0;
		}
	</style>
</head>
<body>
<div class="container">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="/web-pacman/index">Pacman</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="/web-pacman/index"><span class="glyphicon glyphicon-home"></span> Home </a></li>
			</ul>
			<c:if test="${!empty sessionScope.sessionUtilisateur}">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/web-pacman/accueil"><span class="glyphicon glyphicon-user"></span> ${sessionScope.sessionUtilisateur.pseudo} </a></li>
					<li><a href="/web-pacman/deconnexion"><span class="glyphicon glyphicon-log-out"></span> Deconnexion </a></li>
				</ul>
			</c:if>
		</div>
	</nav>
	<h1> Modifier mon profil </h1> <br/> <br/>
	<form action="modification" method="post">
		<div class="form-group">
			<label for="new_username"> Nom d'utilisateur </label>
			<input type="text" class="form-control" value="<c:out value="${sessionScope.sessionUtilisateur.pseudo}"/>" name="new_pseudo" id="new_pseudo" required />
			<span class="erreur" style="color:red;">${form.erreurs['new_pseudo']}</span>
		</div> <br/>
		<div class="form-group">
			<label for="email"> E-mail </label>
			<input type="email" class="form-control" value="<c:out value="${sessionScope.sessionUtilisateur.email}"/>" name="new_email" id="new_email" required />
			<span class="erreur" style="color:red;">${form.erreurs['new_email']}</span>
		</div> <br/>
		<div class="form-group">
			<label for="new_password"> Nouveau mot de passe </label>
			<input type="password" class="form-control" value="" name="new_password" id="new_password" required />
			<span class="erreur" style="color:red;">${form.erreurs['new_password']}</span>
		</div> <br/>
		<div class="form-group">
			<label for="conf_new_password"> Confirmation nouveau mot de passe </label>
			<input type="password" class="form-control" value="" name="conf_new_password" id="conf_new_password" required />
			<span class="erreur" style="color:red;">${form.erreurs['conf_new_password']}</span>
		</div> <br/>
		<div class="form-group">
			<button class="btn btn-primary" type="submit" onclick="return confirm('Voulez-vous modifier votre profil ?');">Modifier</button>
		</div>
		<p class="${empty form.erreurs ? 'alert alert-success' : 'alert alert-danger'}" role="alert">${form.resultat}</p> <br />
	</form>
</div>
</body>
</html>
