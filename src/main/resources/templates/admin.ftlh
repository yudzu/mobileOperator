<!DOCTYPE html>
<html>
<head>
    <title>Some mobile operator</title>
</head>
<body>
<a href="/"><h1>Some mobile operator</h1></a><hr>
<h3>Панель администратора</h3>
<table border="1">
    <tr>
        <th>Email</th>
        <th>Номер телефона</th>
        <th>Активность</th>
        <th>Роли</th>
        <th>Поменять роль пользователя</th>
        <th>Забанить пользователя</th>
    </tr>
    <#list users as user>
        <tr>
            <th>${user.email}</th>
            <th>${user.phoneNumber}</th>
            <th><#if user.active>true<#else>false</#if></th>
            <th>${user.role}</th>
            <th><form action="/admin/user/change-role/${user.id}" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <input type="submit" value="Сменить роль">
            </form>
            </th>
            <th>
                <form action="/admin/user/ban/${user.id}" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" value="Забанить">
                </form>
            </th>
        </tr>
        <#else>
            <h3>Пользователей нет</h3>
    </#list>
</table>
<h4>Доступные тарифы</h4>
<#list plans as plan>
<div>
    <p><b>${plan.title}</b> ${plan.price} руб. | <a href="/plan/${plan.id}">Подробнее...</a></p>
</div>
<#else>
    <h3>Тарифов нет</h3>
</#list>
<hr>
<h3>Добавить новый тариф</h3>
<form action="/plan/create" method="post">
    Название: <input type="text" name="title"/><br><br>
    Описание: <input type="text" name="description"/><br><br>
    Цена: <input type="number" name="price"/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Добавить тариф"/>
</form>
</body>
</html>