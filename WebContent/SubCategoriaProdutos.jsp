<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<fmt:setLocale value="pt_BR"/>
	<meta charset="UTF-8">
	
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
	
	<title>Hardway - ${nomeSubCategoria}</title>
	
</head>
<body>
	<jsp:include page="Cabecalho.jsp"/>
	<jsp:include page="NavBar.jsp"/>
	<div class="container" id="main">
		<h3>${nomeSubCategoria}</h3>
		<div class="container">
			<div class="row">
				<table>
					<tbody>
						<c:forEach var="i" begin="0" end="15" step="4">
							<tr>
								<c:forEach var="j" begin="${i}" end="${i+3}">
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
	</div>
</body>
</html>