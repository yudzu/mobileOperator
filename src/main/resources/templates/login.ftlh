<!DOCTYPE html>
<html>
<head>
    <title>Some mobile operator</title>
</head>
<body>
<a href="/"><h1>Some mobile operator</h1></a><hr>
<h4>Авторизация</h4>
<form action="/login" method="post">
    Эл. почта: <input type="email" name="username"><br><br>
    Пароль: <input type="password" name="password"><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Вход"/>
</form>
<br>
<form action="/registration" method="get">
    <input type="submit" value="Зарегистрироваться"/>
</form>
</body>
</html>