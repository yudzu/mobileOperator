<!DOCTYPE html>
<html>
<head>
    <title>Some mobile operator</title>
</head>
<body>
<a href="/"><h1>Some mobile operator</h1></a><hr>
<h4>Информация о тарифе</h4>
<b>Название: </b>${plan.title}<br>
<b>Описание: </b>${plan.description}<br>
<b>Цена: </b>${plan.price}<br>
<hr>
<form action="/plan/connect/${plan.id}" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Подключить тариф"/>
</form>
<#if user.isAdmin()>
    <br>
    <form action="/plan/delete/${plan.id}" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Удалить тариф"/>
    </form>
</#if>
</body>
</html>