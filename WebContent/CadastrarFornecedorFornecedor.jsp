<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Hardway - Cadastro Fornecedor</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>

<body>
	<c:import url="CabecalhoAdmin.jsp"/>
    <div id="main" class="container-fluid col-md-6">
        <h3 class="page-header">${titulo}:</h3>
        <c:if test="${erro != null}">
        	<h5 style="color:red;">${erro}</h5>
        </c:if>
        <form action="controller.do" method="post">
	        <div class="row">
	            <div class="form-group col-md-12">
	            	<label for="cdnome">Nome:</label>
	            	<input type="text" class="form-control" name="cdnome" id="cdnome" required maxlength="100" placeholder="Digite o nome" value="${nome}"/>
	            </div>
	        </div>
	        <div class="row">
	            <div class="form-group col-md-12">
	            	<label for="cdcnpj">CNPJ:</label>
	            	<input type="text" class="form-control" name="cdcnpj" id="cdcnpj" required maxlength="100" placeholder="Digite o CNPJ" value="${cnpj}"/>
	            </div>
	        </div>
	        <div class="row">
	            <div class="form-group col-md-12">
	            	<label for="cdemail">E-mail:</label>
	            	<input type="text" class="form-control" name="cdemail" id="cdemail" required maxlength="100" placeholder="Digite o e-mail" value="${email}"/>
	            </div>
	        </div>
	        <div class="row">
	            <div class="form-group col-md-12">
	            	<label for="cdtelefone">Telefone:</label>
	            	<input type="text" class="form-control" name="cdtelefone" id="cdtelefone" required maxlength="100" placeholder="Digite o telefone" value="${telefone}"/>
	            </div>
	        </div>
	        <div id="actions1" class="container-home">
	               <a href="controller.do?command=VisualizarFornecedores" class="btn btn-default">Voltar</a>
	               <button type="submit" class="btn btn-primary" name="command" value="${command}">${titulo}</button>
	        </div>
        </form>
    </div>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>

</html>