<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Hardway - Login</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<script type="text/javascript">
	function fMasc(objeto, mascara) {
		obj = objeto
		masc = mascara
		setTimeout("fMascEx()", 1)
	}
	function fMascEx() {
		obj.value = masc(obj.value)
	}
	function mCPF(cpf) {
		cpf = cpf.replace(/\D/g, "")
		cpf = cpf.replace(/(\d{3})(\d)/, "$1.$2")
		cpf = cpf.replace(/(\d{3})(\d)/, "$1.$2")
		cpf = cpf.replace(/(\d{3})(\d{1,2})$/, "$1-$2")
		return cpf
	}
	function mRG(rg) {
		rg = rg.replace(/\D/g, "")
		rg = rg.replace(/(\d{2})(\d)/, "$1.$2")
		rg = rg.replace(/(\d{3})(\d)/, "$1.$2")
		rg = rg.replace(/(\d{3})(\d{1,2})$/, "$1-$2")
		return rg
	}
</script>
</head>

<body>
	<jsp:include page="Cabecalho.jsp" />
	<jsp:include page="NavBar.jsp" />
	<div id="main" class="container">
		<div class="col-md-6">
		<h3 class="page-header">Faça seu Cadastro:</h3>
		<c:if test="${erroCadastro != null}"> <!-- Teste de erro de cadastro -->
			<h5 style="color:red">${erroCadastro}</h5>
		</c:if>
		<form action="controller.do" method="post">
			<div class="row">
				<div class="form-group col-md-12">
					<label for="cdnome">Nome:</label> <input type="text"
						class="form-control" name="cdnome" id="cdnome" required
						maxlength="100" placeholder="Digite seu nome" />
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label for="cdsobrenome">Sobrenome:</label> <input type="text"
						class="form-control" name="cdsobrenome" id="cdsobrenome" required
						maxlength="100" placeholder="Digite seu sobrenome" />
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label for="cdcpf">CPF:</label> <input type="text"
						class="form-control" name="cdcpf" id="cdcpf" onkeydown="javascript: fMasc( this, mCPF );" required
						maxlength="14" placeholder="Digite seu CPF" />
				</div>
				<div class="form-group col-md-12">
					<label for="cdrg">RG:</label> <input type="text"
						class="form-control" name="cdrg" id="cdrg" onkeydown="javascript: fMasc( this, mRG );" required maxlength="12"
						placeholder="Digite seu RG" />
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label for="cdemail">E-mail:</label> <input type="text"
						class="form-control" name="cdemail" id="cdemail" required
						maxlength="100" placeholder="Digite seu e-mail" />
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-6">
					<label for="cdsenha">Senha:</label> <input type="password"
						class="form-control" name="cdsenha" id="cdsenha" required
						maxlength="100" placeholder="Digite sua senha" />
				</div>
				<div class="form-group col-md-6">
					<label for="cfsenha">Confirmar senha:</label> <input
						type="password" class="form-control" name="cfsenha" id="cfsenha"
						required maxlength="100" placeholder="Confirme sua senha" />
				</div>
			</div>
			<hr />
			<div id="actions1" class="row col-xs">
				<div class="col-xs-9">
					<a href="Index.jsp" class="btn btn-default">Voltar</a>
				</div>
				<div class="col-xs-3 text-right">
					<input type="hidden" name="pagina" value="${pagina}">
					<button type="submit" class="btn btn-primary" name="command" value="CadastroCliente">Cadastrar</button>
				</div>
			</div>
		</form>
		</div>
		<div class="col-md-6">
		<h3 class="page-header">Login:</h3>
		<c:if test="${erro != null}"> <!-- Teste de erro de login -->
			<h5 style="color:red">${erro}</h5>
		</c:if>
		<form action="controller.do" method="post">
			<div class="row">
				<div class="form-group col-md-12">
					<label for="cdemail">E-mail:</label> <input type="text"
						class="form-control" name="cdemail" id="cdemail" required
						maxlength="100" placeholder="Digite seu e-mail" />
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label for="cdsenha">Senha:</label> <input type="password"
						class="form-control" name="cdsenha" id="cdsenha" required
						maxlength="100" placeholder="Digite sua senha" />
				</div>
			</div>
			<hr />
			<div id="actions2" class="row">
				<div class="col-md-12 text-right">
					<input type="hidden" name="pagina" value="${pagina}">
					<button type="submit" class="btn btn-primary" name="command" value="Login">Login</button>
				</div>
			</div>
		</form>
		</div>			
	</div>
	<div id="main2" class="container col-md-6">

	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>

</html>