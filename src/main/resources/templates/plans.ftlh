<!DOCTYPE html>
<html>
<head>
    <title>Some mobile operator</title>
</head>
<body>
<a href="/"><h1>Some mobile operator</h1></a><hr>
<#if user.email??>
    <h3>Имя пользователя: <i>${user.name}</i></h3>
    <h3>Телефон: <i>${user.phoneNumber}</i></h3>
    <#if user.plan??>
        <h3>Тариф: <i>${user.plan.title}</i></h3>
    </#if>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Выйти"/>
    </form>
    <#if user.isAdmin()>
        <br>
        <form action="/admin" method="get">
            <input type="submit" value="Панель администратора"/>
        </form>
    </#if>
<#else>
    <form action="/login" method="get">
        <input type="submit" value="Войти"/>
    </form>
</#if>
<hr>
<h4>Выберите тариф</h4>
<#list plans as plan>
    <div>
        <p><b>${plan.title}</b> ${plan.price} руб. | <a href="/plan/${plan.id}">Подробнее...</a></p>
    </div>
<#else>
    <h3>Тарифов нет</h3>
</#list>
</body>
</html>