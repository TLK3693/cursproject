<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<%@page import ="java.io.FileOutputStream" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@page import=" java.io.ObjectOutputStream" %>
<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<title>Документы</title>
</head>
<body class="bg-light">
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="#"> <img
				th:src="@{/images/logo.png}" src="../static/images/logo.png"
				width="auto" height="40" class="d-inline-block align-top" alt="" />
			</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto"></ul>
				<ul class="navbar-nav">
					<li class="nav-item active"><a class="nav-link" href="http://localhost:8080/admin/Dashboard">Домашняя страница</a></li>
					<li class="nav-item active"><a class="nav-link" href="http://localhost:8080/admin/login">Выйти</a></li>
					</li>
				</ul>
			</div>
		</div>
	</nav><br>
	<div class="container-fluid">

		<a style="margin: 20px 0" class="btn btn-primary"
			href="/admin/products/add">Добавить продукт</a><br>
		<table class="table">

			<tr>
				<th scope="col">Серийный №.</th>
				<th scope="col">Название продукта</th>
				<th scope="col">Категория</th>
				<th scope="col">Предпросмотр</th>
				<th scope="col">Количество</th>
				<th scope="col">Цена</th>
				<th scope="col">Скидка</th>
                <th scope="col">Годен с </th>
                <th scope="col">Годен до</th>
				<th scope="col">Описание</th>
				<th scope="col">Удалить</th>
				<th scope="col">Обновить</th>
			</tr>
			<tbody>

				<c:forEach var="product" items="${products}">
				<tr>
					<td>
						${product.id}
					</td>
					<td>
						${product.name }
					</td>
					<td>
						${product.category.name}
					</td>
					<td><img src="${product.image}"
						height="100px" width="100px"></td>
					<td>
						${product.quantity }
						ШТ.
					</td>
					<td>
						${product.price }
						РУБ.
					</td>
					<td>
						${product.discount }
						%
					</td>
					<td>
                    	${product.startDate }
                    </td>
                    <td>
                         ${product.endDate }
                    </td>
					<td>
						${product.description }
					</td>

					<td>
					<form action="products/delete" method="get">
							<input type="hidden" name="id" value="${product.id}">
							<input type="submit" value="Удалить" class="btn btn-danger">
					</form>
					</td>
					<td>
					<form action="products/update/${product.id}" method="get">
                        <input type="submit" value="Обновить" class="btn btn-warning">
                    </form>

					</td>

				</tr>
				</c:forEach>

			</tbody>
		</table>

	</div>



	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
</body>
</html>