<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Hardway - Cadastro Produto</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>

<body> 	        	
	<c:import url="CabecalhoAdmin.jsp"/>
    <div id="main" class="container-fluid col-md-6">
        <h3 class="page-header">${titulo}:</h3>
        <form action="controller.do" method="post" enctype="multipart/form-data">
	        <div class="row">
	            <div class="form-group col-md-12">
	            	<label for="cdnome">Nome:</label>
	            	<input type="text" class="form-control" name="cdnome" id="cdnome" required maxlength="50" placeholder="Digite o nome" value="${nome}"/>
	            </div>
	        </div>
	        <div class="row">
	            <div class="form-group col-md-12">
	            	<label for="cddesc">Descrição:</label>
	            	<textarea class="form-control" name="cddesc" id="cddesc" required maxlength="400" placeholder="Digite a descrição do produto">${desc}</textarea>
	            </div>
	        </div>
	        <div class="row">
	            <div class="form-group col-md-12">
	            	<label for="cdpreco">Preço:</label>
	            	<input type="text" class="form-control" name="cdpreco" id="cdpreco" required maxlength="100" placeholder="Digite o preço do produto" value="${preco}"/>
	            </div>
	        </div>
	        <div class="row">
	            <div class="form-group col-md-12">
	            	<label for="cdqtdeestoque">Quantidade no Estoque:</label>
	            	<input type="text" class="form-control" name="cdqtdeestoque" id="cdqtdeestoque" required maxlength="100" placeholder="Digite a quantidade de produtos no estoque" value="${qtdeestoque}"/>
	            </div>
	        </div>
	        <div class="row">
	            <div class="form-group col-md-12">
	            	<label for="cdimagem">Imagem:</label>
	            	<input type="hidden" name="imagem" value="${imagem}">
	            	<input type="file" accept="image/.png/.jpeg"class="form-control" name="cdimagem" id="cdimagem" placeholder="Insira a imagem do produto"/>
	            </div>
	        </div>
	        <div class="row">
	        	<div class="form-group col-md-12">
	        		<label for="cdfornecedor">Fornecedor:</label>
	        		<select name="cdfornecedor" class="form-control">
	        			<c:forEach var="fornecedor" items="${fornecedores}">
	        				<c:if test="${idFornecedor == fornecedor.id}">
	        					<option selected="selected" value="${fornecedor.id}">${fornecedor.nome}</option>
	        				</c:if>
	        				<c:if test="${idFornecedor != fornecedor.id}">
	        					<option value="${fornecedor.id}">${fornecedor.nome}</option>
	        				</c:if>
	        			</c:forEach>
	        		</select>
	        	</div>
	        </div>
	        <div class="row">
	        	<div class="form-group col-md-12">
	        		<label for="cdsubcategoria">Sub Categoria:</label>
	        		<select name="cdsubcategoria" class="form-control">
						<c:forEach var="subCategoria" items="${subCategorias}">	        			
	        				<c:if test="${idSubCategoria == subCategoria.id}">
	        					<option selected="selected" value="${subCategoria.id}">${subCategoria.nome}</option>
	        				</c:if>
	        				<c:if test="${idSubCategoria != subCategoria.id}">
	        					<option value="${subCategoria.id}">${subCategoria.nome}</option>
	        				</c:if>
	        			</c:forEach>
	        		</select>
	        	</div>
	        </div>
	        <div id="actions1" class="container-home">
	               <a href="Admin.jsp" class="btn btn-default">Voltar</a>
	               <button type="submit" class="btn btn-primary" name="command" value="${command}">${titulo}</button>
	        </div>
        </form>
    </div>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>

</html>