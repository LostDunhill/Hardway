<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<fmt:setLocale value="pt_BR"/>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		
		<title>Visualizar Pedido # ${pedidoCliente.numPedido}</title>
		
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/style.css" rel="stylesheet">
	</head>
	<body>
		<c:import url="CabecalhoAdmin.jsp"/>		
		
		<div id="main" class="container">
			<h3 class="page-header">Visualizar Pedido # ${pedidoCliente.numPedido}</h3>
			<div class="row">
				<h4>Cliente:</h4>
				<div class="col-md-3">
					<p><strong>ID:</strong> ${clientePedido.id}</p>
				</div>
				<div class="col-md-3">
					<p><strong>Nome:</strong> ${clientePedido.nome}</p>
				</div>
				<div class="col-md-3">
					<p><strong>CPF:</strong> ${clientePedido.cpf}</p>
				</div>
				<div class="col-md-3">
					<p><strong>E-mail:</strong> ${clientePedido.email}</p>
				</div>
			</div>
			<div class="row">
				<h4>Produtos:</h4>
				<div class="col-md-12">
					<table id="carrinho">
						<thead>
							<tr>
								<th>ID</th>
								<th>Nome</th>
								<th>Preço</th>
								<th>Quantidade</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="produto" items="${carrinho}">
							<tr>
								<td>${produto.id}</td>
								<td>${produto.nome}</td>
								<td><fmt:formatNumber value="${produto.preco}" type="currency"/></td>
								<td>${produto.quantidade}</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row">
				<h4>Informações da Compra:</h4>
				<div class="col-md-4">
					<p><strong>Endereço de entrega:</strong></p>
					<p>${pedidoCliente.rua}, ${pedidoCliente.numero} - ${pedidoCliente.bairro} - ${pedidoCliente.cidade} - ${pedidoCliente.estado}</p>
					<p>CEP: ${pedidoCliente.CEP}</p>
					<c:if test="${pedidoCliente.complemento != null && not empty pedidoCliente.complemento}">
						<p>Complemento: ${pedidoCliente.complemento}</p>
					</c:if>
				</div>
				<div class="col-md-4">
					<p><strong>Valor total: </strong><fmt:formatNumber value="${pedidoCliente.valorCompra}" type="currency"/></p>
				</div>
				<div class="col-md-4">
					<p><strong>Data: </strong> ${pedidoCliente.dataCompra}</p>
				</div>
			</div>
			<c:if test="${pedidoCliente.statusCompra == \"Aguardando envio\"}"> <!-- Enquanto estivar aguardando envio, aparece o botao para cancelar -->
				<div id="actions">
					<div class="row">
						<a href="controller.do?command=AlterarStatusPedido&numPedido=${pedidoCliente.numPedido}&status=2" class="btn btn-success">Enviar Pedido</a>
						<a href="controller.do?command=AlterarStatusPedido&numPedido=${pedidoCliente.numPedido}&status=0" class="btn btn-danger">Cancelar Pedido</a>
					</div>
				</div>
			</c:if>
			<c:if test="${pedidoCliente.statusCompra == \"Enviado\"}">
				<div class="row">
					<div class="col-md-12">
						<h4 style="color: green;">Pedido enviado!</h4>
					</div>
				</div>
			</c:if>
			<c:if test="${pedidoCliente.statusCompra == \"Cancelado\"}">
				<div class="row">
					<div class="col-md-12">
						<h4 style="color: red;">Pedido cancelado!</h4>
					</div>
				</div>
			</c:if>
			<c:if test="${pedidoCliente.statusCompra == \"Cancelado pelo Cliente\"}">
				<div class="row">
					<div class="col-md-12">
						<h4 style="color: red;">Pedido cancelado pelo cliente!</h4>
					</div>
				</div>
			</c:if>
		</div>
		
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</body>	
</html>