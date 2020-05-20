<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Hardway - Cadastro Categoria</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>

<body>
	<c:import url="CabecalhoAdmin.jsp"/>
    <div id="main" class="container">
        <h3 class="page-header">${titulo}:</h3>
        <c:if test="${erro != null}"> <!-- Teste para indicar erros -->
        	<h5 style="color:red;">${erro}</h5>
        </c:if>
        <form action="controller.do" method="post" accept-charset="UTF-8">
	        <div class="row">
	            <div class="form-group col-md-12">
	            	<label for="cdnome">Nome:</label>
	            	<input type="text" class="form-control" name="cdnome" id="cdnome" required maxlength="100" placeholder="Digite o nome" value="${nome}"/>
	            </div>
	        </div>
	        <div id="actions" class="row">
	        	<div class="form-group col-md-12">
	               <a href="controller.do?command=VisualizarCategorias" class="btn btn-default">Voltar</a>
	               <button type="submit" class="btn btn-primary" name="command" value="${command}">${titulo}</button> <!-- value = command passado pelo sistema, para indicar se cria ou altera -->
	            </div>
	        </div>
        </form>
    </div>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>

</html>