<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
</head>
<body>

	<body>
	<c:import url="CabecalhoAdmin.jsp"/>
	<div class="container-home">
		<div id="tblFornecedor">
			<table id="carrinho">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nome</th>
						<th>Opções</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="categoria" items="${categorias}">
						<tr>
							<td>${categoria.id}</td>
							<td>${categoria.nome}</td>
							<td>
								<div class="row">
									<form action="controller.do" method="get">
										<input type="hidden" name="id" value="${categoria.id}"/>
										<input type="hidden" name="acao" value="EditarCategoria"/>
										<button class="btn btn-warning btn-xs" type="submit" name="command" value="CarregarFormularioCategoria">Editar</button>
										<button class="btn btn-danger btn-xs"  type="submit" name="command" value="ExcluirCategoria">Excluir</button>
									</form>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div><br>
	<div id="botoes" class="container-home">
		<div><button id="btnVoltar" class="btn btn-default" onclick="location.href='Admin.jsp'">Voltar</button></div>
		<div><a id="btnCdFornecedor" class="btn btn-primary" href="controller.do?command=CarregarFormularioCategoria&acao=CriarCategoria">Cadastrar Categoria</a></div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script type="js/bootstrap.min.js"></script>
</body>
</html>