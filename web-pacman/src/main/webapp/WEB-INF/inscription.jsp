<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="fr">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

		<title> Inscription </title>
	</head>
	
	<body>
	
	<div class="container">
	
	
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="/web-pacman/index">Pacman</a>
			</div>
		
			<ul class="nav navbar-nav">
				<li><a href="/web-pacman/index"><span class="glyphicon glyphicon-home"></span> Home </a></li>
				<li><a href="/web-pacman/telechargement"><span class="glyphicon glyphicon-download-alt"></span> Téléchargement </a></li>
			</ul>
			
			<ul class="nav navbar-nav navbar-right"> 
				<li><a href="/web-pacman/connexion"><span class="glyphicon glyphicon-log-in"></span> Connexion </a></li>
				<li><a href="/web-pacman/inscription"><span class="glyphicon glyphicon-menu-hamburger"></span> Inscription </a></li>
			</ul>
			
		</div>
	</nav>
	
	
	<br />
	
	
		<h1> Inscription </h1> <br/> <br/>            
		<form action="inscription" method = "post">
			<div class="form-group">
				<label for="username"> Nom d'utilisateur* </label>
				<input type="text" class="form-control" value="<c:out value="${utilisateur.pseudo}"/>" name="pseudo" id="pseudo" placeholder="Pseudo ..." required />
				<span class="erreur" style="color:red;">${form.erreurs['pseudo']}</span> 
			</div> <br/>
			
			<div class="form-group">
				<label for="email"> Email* </label>
				<input type="email" class="form-control" value="<c:out value="${utilisateur.email}"/>" name="email" id="email" placeholder="Adresse mail" required />
				<span class="erreur" style="color:red;">${form.erreurs['email']}</span> 
			</div> <br/>
			
			<div class="form-group">
				<label for="password"> Mot de passe* </label>
				<input type="password" class="form-control" value="" name="password" id="password" required />
				<span class="erreur" style="color:red;">${form.erreurs['password']}</span>
			</div> <br/>
			
			<div class="form-group">
				<label for="conf_password"> Confirmation mot de passe* </label>
				<input type="password" class="form-control" value="" name="conf_password" id="conf_password" required />
				<span class="erreur" style="color:red;">${form.erreurs['conf_password']}</span>
			</div> <br/>
			
			<div class="form-group">
				<button class="btn btn-primary" type="submit">Inscription</button>
			</div>
				<p class="${empty form.erreurs ? 'alert alert-success' : 'alert alert-danger'}" role="alert">${form.resultat}</p> <br />
			
			<div class="form-group">
				<label class="form-check-label" for="connexion"> Vous avez déjà un compte ? </label>
				<a href="/web-pacman/connexion"><label class="form-check-label" for="connexion"> Connectez-vous maintenant </label></a>
			</div> 
			
			   
		</form>
	</div>
	
	</body>
</html>