<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
</head>
<body>
	<nav class="navbar" id="menu">
		<div class="container-fluid">
			<div class="navbar-header">
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar-right">
						<c:forEach var="categoria" items="${categorias }">
							<div class="dropdown1">
								<button class="dropbtn">
									${categoria.nome } <i class="fa fa-caret-down"></i>
								</button>
								<div class="dropdown-content1">
									<c:forEach var="subCategoria"
										items="${categoria.getSubCategorias() }">
										<a href="controller.do?command=SelecionarSubCategoria&subcategoria=${subCategoria.id}">${subCategoria.nome }</a>
									</c:forEach>
								</div>
							</div>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</nav>
	<script src="js/jquery.min.js"></script>
	<script type="js/bootstrap.min.js"></script>
</body>
</html>