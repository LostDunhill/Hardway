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
		<!-- Modal -->
		<div class="modal fade" id="delete-modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Fechar"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="modalLabel">Excluir Categoria</h4>
					</div>
					<div class="modal-body">
						Deseja realmente excluir esta categoria?
					</div>
					<div class="modal-footer">
						<form action="controller.do" method="post">
							<input type="hidden" name="id" id="id_excluir" value="id_excluir"/>
							<button type="submit" class="btn btn-primary" name="command" value="ExcluirCategoria">Sim</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">N&atilde;o</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- /.modal -->

	<c:import url="CabecalhoAdmin.jsp"/>
	<div class="container-home">
		<div id="tblFornecedor">
			<table id="carrinho">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nome</th>
						<th>Categoria</th>
						<th>Opções</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="subCategoria" items="${subCategorias}">
						<tr>
							<td>${subCategoria.id}</td>
							<td>${subCategoria.nome}</td>
							<td>${subCategoria.idCategoria}</td>
							<td>
								<div class="row">
									<form action="controller.do" method="get">
										<input type="hidden" name="id" value="${subCategoria.id}"/>
										<input type="hidden" name="acao" value="EditarSubCategoria"/>
										<button class="btn btn-warning btn-xs" type="submit" name="command" value="CarregarFormularioSubCategoria">Editar</button>
										<button class="btn btn-danger btn-xs"  type="submit" name="command" value="ExcluirSubCategoria">Excluir</button>
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
		<div><a id="btnCdFornecedor" class="btn btn-primary" href="controller.do?command=CarregarFormularioSubCategoria&acao=CriarSubCategoria">Cadastrar Categoria</a></div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script type="js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$("#delete-modal").on('show.bs.modal', function(event) {
			var button = $(event.relatedTarget); //botao que disparou a modal
			var recipient = button.data('categoria');
			$("#id_excluir").val(recipient);
		});
	</script>
</body>
</html>