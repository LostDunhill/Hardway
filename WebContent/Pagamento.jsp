<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ page import="javax.servlet.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Hardway - Pagamento</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
</head>

<body>
	<c:import url="Cabecalho.jsp"/>
	<c:import url="NavBar.jsp" />
	<div class="container">
		<div id="main" class="container col-md-8">
			<h3 class="page-header">Meios de Pagamento:</h3>
			<div id="pagamentopg" class="container">
				<a href="controller.do?command=Pagamento&id=0" id="boleto" class="btn btn-default">Boleto</a>
				<br> <br>
				<c:if test="${id == 0}"> <!-- Teste de id passado pelo sistema -->
					<c:import url="Boleto.jsp" />
				</c:if>
				<br> <br>
				<a href="controller.do?command=Pagamento&id=1" id="cartao" class="btn btn-default">Cartão de credito</a>
				<br> <br>
				<c:if test="${id == 1}"> <!-- Teste de id passado pelo sistema -->
					<c:import url="CartaoCredito.jsp" />
				</c:if>
			</div>
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>

</html>