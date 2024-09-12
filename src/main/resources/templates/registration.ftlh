<!DOCTYPE html>
<html>
<head>
    <title>Some mobile operator</title>
</head>
<body>
<a href="/"><h1>Some mobile operator</h1></a><hr>
<h4>Регистрация</h4>
<form action="/registration" method="post">
    Имя пользователя: <input type="text" name="name"><br><br>
    Эл. почта: <input type="email" name="email"><br><br>
    Пароль: <input type="password" name="password"><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Зарегистрироваться"/>
</form>
<#if errorMessage??>
    <h2 style="color:red">${errorMessage}</h2>
</#if>
</body>
</html>