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

		<title> Pacman </title>
	</head>

<body>

<div class="container">

	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="/web-pacman/index">Pacman</a>
			</div>

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