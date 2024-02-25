<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="fr">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
		
		<title> Accueil </title>
		
	</head>

<body>

<script>
	function myFunction() {
	  var r = confirm("Press a button!");
	  if (r == true) {
		  window.open('/web-pacman/supprimer);
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
	
		<br /> <br />
		<h2> Historique de mes parties jouées </h2> <br/>
		<table class="table table-striped">
        <thead>
          <tr>
            <!-- <th>ID</th>-->
            <th>Joueur</th>
            <th>Score obtenu</th>
            <th>Date de la partie</th>
          </tr>
        </thead>
        <c:forEach var="p" items="${ partie }" >
        <tbody>
          <tr>
            <td> <c:out value="${ p.getJoueur() }"/> </td>
            <td> <c:out value="${ p.score }"/> </td>
            <td> <c:out value="${ p.date }"/> </td>
          </tr>
          
        </tbody>
        </c:forEach>
      </table>

	<br/><br/>
	<p>
		<a href="/web-pacman/modification"> <span class="glyphicon glyphicon-edit"></span> Modifier mes informations </a>
	</p>
	
	<p>
		<a href="/web-pacman/supprimer" class="w3-btn w3-red" style="color:red;" onclick="return confirm('Voulez-vous supprimer diffinitivement votre profil ?');"> <span style="color:red;" class="glyphicon glyphicon-remove"></span> Supprimer mon compte </a>
	</p>
	
	<!-- <p>
		<a href="/web-pacman/supprimer" style="color:red;"> <span style="color:red;" class="glyphicon glyphicon-remove"></span> Supprimer mon compte </a>
	</p>-->

</div>
	
</body>
</html>