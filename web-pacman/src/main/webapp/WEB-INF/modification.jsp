<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="fr">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

		<title> Modification </title>
	</head>
	
	<body>
	
	<script>
	function myFunction() {
	  var r = confirm("Press a button!");
	  if (r == true) {
		  window.open('/web-pacman/modification);
	  } else {
		  
	  }
	}
</script>
	
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
			
			<c:if test="${!empty sessionScope.sessionUtilisateur}">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/web-pacman/accueil"><span class="glyphicon glyphicon-user"></span> ${sessionScope.sessionUtilisateur.pseudo} </a></li>
				<li><a href="/web-pacman/deconnexion"><span class="glyphicon glyphicon-log-out"></span> Déconnexion </a></li>
			</ul>
			</c:if>
		</div>
	</nav>
	
	
	<br />
	
	
		<h1> Modifier mon profil </h1> <br/> <br/>            
		<form action="modification" method = "post">
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
				<button class="btn btn-primary" type="submit"onclick="return confirm('Voulez-vous modifier votre profil ?');">Modifier</button>
			</div>
				<p class="${empty form.erreurs ? 'alert alert-success' : 'alert alert-danger'}" role="alert">${form.resultat}</p> <br />
			
			
			   
		</form>
	</div>
	
	</body>
</html>