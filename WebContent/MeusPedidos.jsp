<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<fmt:setLocale value="pt_BR"/>
	<meta charset="UTF-8">
	
	<title>Meus Pedidos</title>
	
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
</head>
<body>

	<c:import url="Cabecalho.jsp"/>
	<div class="container-home">
		<div id="tblFornecedor">
			<table id="carrinho">
				<thead>
					<tr>
						<th>Número do Pedido</th>
						<th>Data da compra</th>
						<th>Valor</th>
						<th>Status</th>
						<th>Opções</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="pedido" items="${pedidos}">
						<tr>
							<td>${pedido.numPedido}</td>
							<td>${pedido.dataCompra}</td>
							<td><fmt:formatNumber value="${pedido.valorCompra}" type="currency"/></td>
							<td>${pedido.statusCompra}</td>
							<td>
								<div class="row">
									<form action="controller.do" method="get">
										<input type="hidden" name="NumPedido" value="${pedido.numPedido}"/>
										<button class="btn btn-success btn-xs" type="submit" name="command" value="VisualizarPedidoCliente">Visualizar Pedido</button>
									</form>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div><br>
	<script src="js/jquery.min.js"></script>
	<script type="js/bootstrap.min.js"></script>
</body>
</html>