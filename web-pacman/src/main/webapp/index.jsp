<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="fr">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

		<title> Pacman </title>
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

	<br/> <br/>


	<h1> Top 10 parties jouées </h1>
		<table class="table table-striped">
        <thead>
          <tr>
          	<th>Classement</th>
          	<th>Joueur</th>
            <th>Score obtenu</th>
            <th>Date de la partie</th>
          </tr>
        </thead>
        <c:forEach var="part" items="${ partie }" varStatus="status">
        <tbody>
          <tr>
          	<td> N° <c:out value="${ status.count }" /> </td>
          	<td> <c:out value="${ part.getJoueur() }"/> </td>
            <td> <c:out value="${ part.score }"/> </td>
            <td> <c:out value="${ part.date }"/> </td>
          </tr>

        </tbody>
        </c:forEach>
      </table>

</div>

</body>

</html>