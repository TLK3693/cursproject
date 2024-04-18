<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<%@ page import="java.time.LocalDate" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
          <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
    <title>Документ</title>
</head>
<body>

<section class="wrapper">
        <div class="container-fostrap">
<nav class="navbar navbar-expand-lg navbar-light bg-light" >
    <div class="container-fluid">
        <a class="navbar-brand" href="#">
            <img th:src="@{/images/logo.png}"  src="../static/images/logo.png" width="auto" height="40" class="d-inline-block align-top" alt=""/>
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
		
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <h4>Добро пожаловать ${ user.username } </h4>
            <ul class="navbar-nav mr-auto"></ul>
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" sec:authorize="isAuthenticated()" href="http://localhost:8080/admin/Dashboard">Админ</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" th:href="@{/}" href="#">Корзинка</a>
                </li>
                 <li class="nav-item active">
                    <a class="nav-link" href="profileDisplay" >Профиль</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" sec:authorize="isAuthenticated()" href="http://localhost:8080/">Выйти</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Магазин</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <style>
    body {
      padding: 20px;
    }
    .card {
      height: 490px; /* Увеличиваем высоту карточки */
      overflow: hidden; /* Скрываем прокрутку, пока не нужна */
    }
    .card-body {
      max-height: 350px; /* Ограничиваем высоту блока с описанием */
      overflow-y: auto; /* Добавляем вертикальную прокрутку только при необходимости */
    }
    .card-img-top {
      margin-top: 10px;
      max-height: 120px; /* Увеличиваем высоту изображения продукта */
      object-fit: contain;
    }
  </style>
</head>
<body class="bg-light">
  <header>

  </header>
  <main>

    <div class="container">
      <h1>Сведения о продуктах</h1>
      <div class="row">
      <c:forEach var="product" items="${products}">
        <div class="col-md-3">
          <div class="card mb-4">
            <img class="card-img-top" src="${product.image}" alt="Product 1">
            <div class="card-body">
             <b> <h4 class="card-title">${product.name}</h4></b>
              <h5 class="card-text">Категория: ${product.category.name}</h5>
              <h5 class="card-text">Цена: ${product.price}</h5>
              <h5 class="card-text">Скидка: ${product.discount} %</h5>
              <h5 class="card-text">Годен с: ${product.startDate}</h5>
              <h5 class="card-text">Годен до: ${product.endDate}</h5>
              <%--
                <h5 class="card-text">Годен с: ${product.startDate != null ? java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy").format(product.startDate) : ''}</h5>
               <h5 class="card-text">Годен до: ${product.endDate != null ? java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy").format(product.endDate) : ''}</h5>
              --%>
              <p class="card-text">Описание: ${product.description}</p>

                <div class="text-center"> <!-- Центрируем кнопку -->
                  <a href="#" class="btn btn-primary">Добавить в корзину</a>
                </div>
            </div>
          </div>
        </div>
        </c:forEach>
      </div>

    </div>
  </main>
  <footer>
    <footer class="fixed-bottom text-center"> <!-- Фиксируем подвал внизу -->
    <div class="container">
      <p>&copy; 2023 ;)</p>
    </div>
  </footer>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>