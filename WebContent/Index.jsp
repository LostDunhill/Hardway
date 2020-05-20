<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<fmt:setLocale value="pt_BR"/>
	<meta charset="UTF-8">
	
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
	
	<title>Hardway - Home</title>
	
</head>
<body>
	<c:import url="Cabecalho.jsp"/>
	<c:import url="NavBar.jsp" />
	<div class="container-home">
		<div class="row">
			<img src="img/teste1.png" alt="outdoor"/>
		</div>
	</div>
	<div class="container">
		<label id="tag"><h1>Destaques:</h1></label>
	</div>
	<div class="container-home">
		<div class="row">
		<table>
			<tbody>
				<c:forEach var="i" begin="0" end="15" step="4"> <!-- Linha de produtos (indo de 4 em 4 produtos) -->
					<tr>
						<c:forEach var="j" begin="${i}" end="${i+3}"> <!--Produtos da linha -->
							<td>
								<c:if test="${j < produtos.size()}">
						           	<form action="controller.do" method="get">
						           		<input type="hidden" name="command" value="VisualizarProduto">
						           		<button type="submit" class="btn btn-link" name="produto" value="${produtos.get(j).id }">
											<img src="data:image/jpg/png;base64,${produtos.get(j).base64Imagem }" width="200" height="200"></img>
											<p><strong>${produtos.get(j).nome }</strong></p>
											<p><fmt:formatNumber value="${produtos.get(j).preco }" type="currency"/></p>
										</button>
									</form>
								</c:if>
							</td>
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
	</div>
</body>
</html>