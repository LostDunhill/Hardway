<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
</head>
<body>
	<div>
		<div id="lblNCartao" class="container col-md-2"
			style="text-align: right; display: inline-block">
			<div>
				<label>Número do Cartão:</label>
			</div>
		</div>
		<div id="txfNCartao" class="container col-md-10"
			style="text-align: left; display: inline-block">
			<div style="display: inline-block">
				<input id="num1" type="text" class="form-control"
					style="display: inline-block;" name="num1" required maxlength="16"
					placeholder="0000 0000 0000 0000" />
			</div>
		</div>
	</div>
	<br>
	<br>
	<div>
		<div id="lblCodigo" class="container col-md-2"
			style="text-align: right; display: inline-block">
			<div>
				<label>Código de Segurança:</label>
			</div>
		</div>
		<div id="txfCodigo" class="container col-md-2"
			style="text-align: left; display: inline-block">
			<div>
				<input id="codigo" type="text" class="form-control" name="codigo"
					required maxlength="3"
					placeholder="CCV" />
			</div>
		</div>
	</div>
	<br>
	<br>
	<div>
		<div id="lblTitular" class="container col-md-2"
			style="text-align: right; display: inline-block">
			<div>
				<label>Nome do Titular:</label>
			</div>
		</div>
		<div id="txfTitular" class="container col-md-3"
			style="text-align: left; display: inline-block">
			<div>
				<input id="titular" type="text" class="form-control" name="titular"
					required maxlength="100" placeholder="Digite o nome do Titular" />
			</div>
		</div>
	</div>
	<br>
	<br>
	<div>
		<div id="lblNCartao" class="container col-md-2"
			style="text-align: right; display: inline-block">
			<div>
				<label>Número do Cartão:</label>
			</div>
		</div>
		<div>
			<div id="txfValidade" class="container col-md-7"
				style="text-align: left; display: inline-block">
				<div style="display: inline-block;">
					<input id="mesValidade" type="text" class="form-control"
						style="display: inline-block;" name="mesValidade" required
						maxlength="2" placeholder="Mês" />
				</div>
				<div style="display: inline-block;">
					<input id="anoValidade" type="text" class="form-control"
						style="display: inline-block;" name="anoValidade" required
						maxlength="4" placeholder="Ano" />
				</div>
			</div>
		</div>
	</div>
	<a href="controller.do?command=FinalizarCompra" class="btn btn-primary">Finalizar Compra</a>
	<script src="js/jquery.min.js"></script>
	<script type="js/bootstrap.min.js"></script>
</body>
</html>