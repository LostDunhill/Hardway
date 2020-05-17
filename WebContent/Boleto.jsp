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

	<div id="boleto" class="container">
		<div class="col-md-1" style="display: inline-block; text-align: right">
			<label>CPF:</label>
		</div>
		<div class="col-lg-6" style="display: inline-block;">
			<input id="txfcpf" type="text" class="form-control"
				style="display: inline-block;" name="cpf" id="cpf" onkeydown="javascript: fMasc( this, mCPF );" required
				maxlength="14" placeholder="Digite seu CPF" />
		</div>
		<a href="controller.do?command=FinalizarCompra" class="btn btn-primary">Finalizar Compra</a>
	</div>
	<script src="js/jquery.min.js"></script>
	<script type="js/bootstrap.min.js"></script>
</body>
</html>