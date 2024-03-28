<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="fr">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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
			table.table {
				border-collapse: collapse;
			}
			table.table th, table.table td {
				border: 1px solid #ddd;
				padding: 8px;
			}
			table.table th {
				text-align: left;
				background-color: #4CAF50;
				color: white;
			}
		</style>

		<title> Connexion </title>
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
			
			<ul class="nav navbar-nav navbar-right"> 
				<li><a href="/web-pacman/connexion"><span class="glyphicon glyphicon-log-in"></span> Connexion </a></li>
				<li><a href="/web-pacman/inscription"><span class="glyphicon glyphicon-menu-hamburger"></span> Inscription </a></li>
			</ul>
			
		</div>
	</nav>

	
		<h1> Connexion </h1> <br/> <br/>            
		<form action="connexion" method = "post">
			<div class="form-group">
				<label for="username"> Nom d'utilisateur </label>
				<input type="text" class="form-control" value="" name="pseudo" id="pseudo" placeholder="Pseudo ..." required />
				 <span style="color:red;">${form.erreurs['pseudo']}</span>
			</div> <br/>
			<div class="form-group">
				<label for="nom"> Mot de passe </label>
				<input type="password" class="form-control" value="" name="password" id="password" required />
				<span style="color:red;">${form.erreurs['password']}</span>
				
			</div> <br/>
			<div class="form-group">
				<button class="btn btn-primary" type="submit">Connexion</button>
			</div>
			
			<p class="${empty form.erreurs ? 'alert alert-success' : 'alert alert-danger'}" role="alert">${form.resultat}</p> <br />
			
			<div class="form-group">
				<label class="form-check-label"> Vous n'êtes pas encore inscrit ! </label>
				<a href="/web-pacman/inscription"><label class="form-check-label"> Inscrivez-vous maintenant </label></a>
			</div>     
		</form>
		
        <c:if test="${!empty sessionScope.sessionUtilisateur}">
             <p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionUtilisateur.pseudo}</p>
        </c:if>
	</div>
	

</body>
</html>