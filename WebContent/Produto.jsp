<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<fmt:setLocale value="pt_BR"/>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Hardway - ${produto.nome}</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
</head>

<body>
	<c:import url="Cabecalho.jsp"/>
	<c:import url="NavBar.jsp" />

	<div class="container">
		<div id="main" class="container col-md-8">
			<div id="produtopg" class="container">
				<div id="produtopg" class="col-md-6">
					<h3 id="nomepg" class="page-header">${produto.nome }</h3>
					<img id="produtopg"
						src="data:image/jpg/png;base64,${produto.base64Imagem }" width="400"
						height="400"></img> <br> <br>
					<h4><strong><fmt:formatNumber value="${produto.preco}" type="currency"/></strong></h4>
					<div class="row">
						<div class="container">
							<div class="col-md-6">
								<p style="word-wrap: break-word; text-align: justify;">${produto.desc }</p>
							</div>
						</div>
					</div>
				
				</div>
			</div>
		</div>
		<div id="botoespg" class="container col-md-4">
			<br> <br>
			<div>
				<form action="controller.do" method="post">
					<input type="hidden" name="pagina" value="Carrinho.jsp"/>
					<button type="submit" class="btn btn-primary" name="command" value="AdicionarCarrinho">Comprar</button>
				</form>
			</div>
			<div>
				<form action="controller.do" method="post">
					<input type="hidden" name="pagina" value="Produto.jsp"/>
					<button type="submit" class="btn btn-default" name="command" value="AdicionarCarrinho">Adicionar ao carrinho</button>
				</form>
			</div>
		</div>
	</div>
	<br>
	<br>
	<br>

	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>

</html>