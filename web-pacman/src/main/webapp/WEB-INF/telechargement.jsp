<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="fr">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
		<title> Téléchargement </title>
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
			<c:if test="${empty sessionScope.sessionUtilisateur}">
			<ul class="nav navbar-nav navbar-right"> 
				<li><a href="/web-pacman/connexion"><span class="glyphicon glyphicon-log-in"></span> Connexion </a></li>
				<li><a href="/web-pacman/inscription"><span class="glyphicon glyphicon-menu-hamburger"></span> Inscription </a></li>
			</ul>
			</c:if>
			<c:if test="${!empty sessionScope.sessionUtilisateur}">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/web-pacman/accueil"><span class="glyphicon glyphicon-user"></span> ${sessionScope.sessionUtilisateur.pseudo} </a></li>
				<li><a href="/web-pacman/deconnexion"><span class="glyphicon glyphicon-log-out"></span> Déconnexion </a></li>
			</ul>
			</c:if>
		</div>
	</nav>
	<br/> <br/> <br />
	<div style="padding: 10px;" >
		<img src="img/pacman.jpeg" style="float: left; padding-right:50px;">
		 
		<div style="margin-top : 80px; margin-left : 50px;">
			<h2> Pac-Man </h2>
			<p style="text-align:justify;">Pac-Man (à l'origine Puck-man1) est un jeu vidéo créé par Tōru Iwatani pour l’entreprise japonaise Namco, sorti au Japon le 22 mai 1982. Le jeu consiste à déplacer Pac-Man, un personnage qui, vu de profil, ressemble à un diagramme circulaire à l’intérieur d’un labyrinthe, afin de lui faire manger toutes les pac-gommes qui s’y trouvent en évitant d’être touché par des fantômes.</p>
			<br/>
			<a href="download/pacman.jar">
				<button style="background-color: DodgerBlue; border: none; font-size: 20px; color: white; cursor: pointer; padding: 12px 30px;" class="btn">
					<span class="glyphicon glyphicon-download-alt"></span> Télécharger 
				</button>
			</a>
		</div>
		
	</div>	
</div>	
</body>
</html>