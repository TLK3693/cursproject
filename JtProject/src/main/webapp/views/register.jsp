<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <title>Документ</title>
    <style>
        /* Центрирование содержимого */
        .center-content {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; /* Выравниваем по вертикали по высоте окна */
        }
    </style>
</head>
<body>

<br>
<div class="container my-3 center-content">
    <div class="col-sm-6">
        <h3 class="text-center" style="margin-top: 10px">Зарегистрируйтесь сейчас</h3>
        <p class="text-center">Пожалуйста, заполните это, чтобы зарегистрироваться</p>
        <form action="newuserregister" method="post">
            <div class="form-group">
                <label for="firstName">Имя пользователя</label>
                <input type="text" name="username" id="firstName" required placeholder="Ваше имя пользователя*" required class="form-control form-control-lg">
            </div>
            <div class="form-group">
                <label for="email">Адрес электронной почты</label>
                <input type="email" class="form-control form-control-lg" required minlength="6" placeholder="Почта*" required name="email" id="email"
                       aria-describedby="emailHelp">
                <small id="emailHelp" class="form-text text-muted">Мы никогда не передадим вашу электронную почту кому-либо еще.</small>
            </div>
            <div class="form-group">
                <label for="password">Пароль</label>
                <input type="password" class="form-control form-control-lg" required placeholder="Пароль*" required name="password" id="password">
            </div>
            <div class="form-group">
                <label for="Address">Адрес</label>
                <textarea class="form-control form-control-lg" rows="3" placeholder="Введите ваш адрес*" name="address"></textarea>
            </div>
            <span style="margin-top: 10px" class="text-center">Уже есть аккаунт? <a class="linkControl" href="/">Войти здесь!</a></span> <br><br>
            <input type="submit" value="Зарегистрироваться" class="btn btn-primary btn-block"><br>
            <br><h3 style="color:red;" class="text-center">${msg}</h3>
            <br>
        </form>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>