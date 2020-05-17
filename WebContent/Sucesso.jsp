<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Hardway - Sucesso</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
</head>

<body>
	<c:import url="Cabecalho.jsp"/>
	<c:import url="NavBar.jsp" />
	<div class="container">
		<div id="main" class="container col-md-8">
			<h3 class="page-header" style="color: green;">Compra Finalizada!</h3>
			<div id="pagamentopg" class="container">
				<p>Agora só aguardar. Enviaremos seu pedido em um prazo de 24h.</p>
				<a href="Index.jsp" class="btn btn-default">Voltar a página inicial!</a>
			</div>
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>

</html>