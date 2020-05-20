<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

<div id="cabecalho" class="container-line">
	<div class="row center-hr">
		<div id="logo">
			<img src="img/hardway_fp.jpg" class="img-responsive" alt="Hardway"
				onclick="location.href='Index.jsp'" style="padding-top: 20px; padding-right:30px;" /> <!-- Componente de imagem, onclick = ao clicar -->
		</div>
		<div id="barraPesquisa">
			<form action="controller.do" method="get">
				<c:if test="${cliente != null && cliente.nome != \"guest\"}"> <!-- Condição para diferenciar cabeçalho do usuario e "convidado" -->
						<div style="padding-right: 15px; padding-left: 15px;">
							<strong style="color: white;">Seja bem-vindo(a),
								${cliente.nome}</strong>
						</div>
						<div>
							<a id="btncarrinho" class="btn btn-default"
								style="border-radius: 30px 30px 30px 30px; color: #ffffff; background-color: #000000;"
								href="controller.do?command=VisualizarPedidos">Meus Pedidos</a>
						</div>
						<br> <br>
				</c:if>
				<div>
					<input id="txfpesquisa" type="text" class="form-control"
						name="chave" id="pesquisa" maxlength="100"
						placeholder="Procurar..."
						style="border-radius: 30px 30px 30px 30px;" />
				</div>
				<div>
					<button id="btnpesquisa" class="btn btn-primary" name="command"
						value="Buscar" style="border-radius: 30px 30px 30px 30px;">Pesquisar</button>
				</div>
				<div>
					<a id="btncarrinho" class="btn btn-default"
						style="border-radius: 30px 30px 30px 30px; color: #337ab7; background-color: #000000; border-color: #337ab7;"
						href="Carrinho.jsp">Carrinho</a>
				</div>
				<c:if test="${cliente != null && cliente.nome != \"guest\"}">
					<div>
						<button id="btnlogin" class="btn btn-default"
							style="border-radius: 30px 30px 30px 30px; color: #dc1818; background-color: #000000; border-color: #dc1818;"
							name="command" value="Deslogar">Sair</button>
					</div>
				</c:if>
				<c:if test="${cliente == null || cliente.nome == \"guest\"}">
					<div>
						<a id="btnlogin" class="btn btn-default"
							style="border-radius: 30px 30px 30px 30px; color: #19ba00; background-color: #000000; border-color: #19ba00;"
							href="Login.jsp">Login</a>
					</div>
				</c:if>
		</form>
		</div>
	</div>
</div>