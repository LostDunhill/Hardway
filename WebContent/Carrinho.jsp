<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<fmt:setLocale value="pt_BR" />
<meta charset="UTF-8">

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

<title>Hardway - Compras</title>

<script type="text/javascript">
	function fMasc(objeto, mascara) {
		obj = objeto
		masc = mascara
		setTimeout("fMascEx()", 1)
	}
	function fMascEx() {
		obj.value = masc(obj.value)
	}
	function mCEP(cep) {
		cep = cep.replace(/\D/g, "")
		cep = cep.replace(/^(\d{5})(\d)/, "$1-$2")
		return cep
	}
</script>
</head>
<body>

	<jsp:include page="Cabecalho.jsp" />
	<jsp:include page="NavBar.jsp" />

	<div class="container">
		<div class="container">
			<c:if test="${erroCompra != null}">
				<h5 style="color: red;">${erroCompra}</h5>
				<c:forEach var="produto" items="${disponibilidadeProdutos}">
					<p>${produto.nome} - Quantidade em estoque: ${produto.qtdeEstoque}</p>
				</c:forEach>
			</c:if>
			<table id="carrinho">
				<thead>
					<tr>
						<th>Produtos</th>
						<th>Valor Unitário</th>
						<th>Quantidade</th>
						<th>SubTotal</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty produtosCarrinho}">
						<c:forEach var="produto" items="${produtosCarrinho}">
							<tr>
								<td>${produto.nome}</td>
								<td><fmt:formatNumber value="${produto.preco }"
										type="currency" /></td>
								<td>
									<form action="controller.do" method="post">
										<input type="hidden" name="command" value="AlteraQuantidade" />
										<input type="hidden" name="id" value="${produto.id}" /> 
										<input type="text" name="quantidadeProduto" value="${produto.quantidade}" class="form-control" maxlength="3"/>
									</form>
								</td>
								<td><fmt:formatNumber
										value="${produto.preco * produto.quantidade}" type="currency" /></td>
								<td>
									<form action="controller.do" method="post">
										<input type="hidden" name="id" value="${produto.id}" />
										<button class="btn btn-danger btn-xs" type="submit"
											name="command" value="ExcluirProdutoCarrinho">Excluir</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<tr>
						<td></td>
						<td>Valor Total</td>
						<td></td>
						<td><fmt:formatNumber value="${total}" type="currency" /></td>
						<td><a class="btn btn-danger btn-xs"
							href="controller.do?command=LimparCarrinho">Limpar carrinho</a></td>
				</tbody>
			</table>
		</div>
		<div class="container" style="margin-top: 20px;">
			<c:if test="${erro != null}">
				<h5 style="color: red;">${erro}</h5>
			</c:if>
			<form action="controller.do" method="post">
				<div class="row">
					<div class="form-group col-md-4">
						<input id="cep" type="text" class="form-control" name="cep" onkeydown="javascript: fMasc( this, mCEP );"
							required maxlength="9" placeholder="Digite seu CEP"
							value="${cep}" />
					</div>
					<div class="form-group col-md-4">
						<button id="btncep" class="btn btn-primary" name="command"
							value="CarregarCEP">Calcular frete</button>
					</div>
				</div>
			</form>

			<c:if test="${rua != null}">
				<form action="controller.do" method="post">
					<div class="row">
						<div class="form-group col-md-4">
							<label for="rua">Rua:</label>
							<input id="rua" type="text" class="form-control" name="rua"  required maxlength="50" value="${rua}"/>
						</div>
						<div class="form-group col-md-4">
							<label for="rua">Cidade:</label>
							<input id="cidade" type="text" class="form-control" name="cidade"  required maxlength="50" value="${cidade}"/>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-4">
							<label for="rua">Número:</label>
							<input id="numero" type="text" class="form-control" name="numero"  required maxlength="5" placeholder="Digite o número de sua residência"/>
						</div>
						<div class="form-group col-md-4">
							<label for="estado">Estado:</label>
							<input id="estado" type="text" class="form-control" name="estado"  required maxlength="50" value="${estado}"/>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-4">
							<label for="rua">Bairro:</label>
							<input id="bairro" type="text" class="form-control" name="bairro"  required maxlength="50" value="${bairro}"/>
						</div>
						<div class="form-group col-md-4">
							<label for="Complemento">Complemento:</label>
							<input id="complemento" type="text" class="form-control" name="complemento" maxlength="50" placeholder="Digite o complemento (se houver)"/>
						</div>
					</div>
					
					
					<div class="row">
						<div class="form group col-md-4">
							<p>Valor Frete: <fmt:formatNumber value="${valorFrete}" type="currency"/></p>
							<p>Valor Total: <fmt:formatNumber value="${valorFrete + total}" type="currency"/></p>
						</div>
					</div>
					
					<div class="row form-group">
					<div class="col-md-2"> 
						<input type="hidden" name="cep" value="${cep}"/>
						<a id="btnvoltar" class="btn btn-default " href="Index.jsp">Continuar comprando</a>
						</div>
						<div class="col-md-2">
						<button class="btn btn-primary" type="submit" name="command" value="Comprar">Finalizar compra</button>
					</div>
					</div>
				</form>
			</c:if>
		</div>
		<c:if test= "${cep == null}"> 
				<span class="pull-left"><a id="btnvoltar" class="btn btn-default" href="Index.jsp">Continuar comprando</a></span>
		</c:if>
	</div>


	<script type="js/jquery.min.js"></script>
	<script type="js/bootstrap.min.js"></script>

</body>
</html>