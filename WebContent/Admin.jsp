<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<fmt:setLocale value="pt_BR"/>
<meta charset="UTF-8">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
</head>
<body>
	<c:import url="CabecalhoAdmin.jsp"/>
	<br/>
	<div class="container-home">
		<div class="row">
			<a id="btnProduto" class="btn btn-default" href="controller.do?command=CarregarFormularioProduto&acao=CriarProduto">Cadastrar Produtos</a>
			<a id="btnCategoria" class="btn btn-default" href="controller.do?command=VisualizarCategorias">Visualizar/Cadastrar Categoria</a>
			<a id="btnCategoria" class="btn btn-default" href="controller.do?command=VisualizarSubCategorias">Visualizar/Cadastrar SubCategoria</a>
			<a id="btnFornecedor" class="btn btn-default" href="controller.do?command=VisualizarFornecedores">Visualizar/Cadastrar Fornecedores</a>
		</div>
	</div>
	<br/>
	<div class="container-fluid">
		<div id="tblHistorico" class="container col-md-6">
			<table id="carrinho"> <!-- Tabela com pedidos -->
				<thead>
					<tr>
						<th>Número do Pedido</th>
						<th>Valor da Compra</th>
						<th>Data</th>
						<th>Status do pedido</th>
						<th>Opções</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="pedido" items="${pedidos}">
						<tr>
							<td id="produto">${pedido.numPedido}</td>
							<td><fmt:formatNumber value="${pedido.valorCompra}" type="currency"/></td>
							<td>${pedido.dataCompra}</td>
							<td>${pedido.statusCompra}</td>
							<td><a class="btn btn-success btn-xs" href="controller.do?command=VisualizarPedido&NumPedido=${pedido.numPedido}">Visualizar Detalhes</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div id="tblDeposito" class="container col-md-6">
			<table id="carrinho"> <!-- Tabela com produtos -->
				<thead>
					<tr>
						<th>ID</th>
						<th>Nome</th>
						<th>Preço</th>
						<th>Quantidade</th>
						<th>Opções</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="produto" items="${produtos}">
					<tr>
						<td>${produto.id}</td>
						<td>${produto.nome}</td>
						<td><fmt:formatNumber value="${produto.preco}" type="currency"/></td>
						<td>${produto.qtdeEstoque}</td>
						<td>
							<div class="row">
								<form action="controller.do" method="post">
									<input type="hidden" name="id" value="${produto.id}"/>
									<input type="hidden" name="acao" value="EditarProduto"/>
									<button class="btn btn-warning btn-xs" type="submit" name="command" value="CarregarFormularioProduto">Editar/Repor</button>
									<button class="btn btn-danger btn-xs"  type="submit" name="command" value="ExcluirProduto">Excluir</button>
								</form>
							</div>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<br>
	
	<script src="js/jquery.min.js"></script>
	<script type="js/bootstrap.min.js"></script>
</body>
</html>