<!--
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Spring Security Example </title>
</head>
<body>
<p>Login Page</p>
<form action="/login" method="post">
    <div><label> User Name : <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <input type="hidden" name="_csrf" value="{{_csrf.token}}">
    <div><input type="submit" value="Sign In"/></div>
</form>
<a href = "registration">Зарегистрироваться</a>
</body>
</html>
-->
<#import "parts/common.ftl" as C>
<#import "parts/login.ftl" as L>
<@C.page>
<#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
<div class="alert alert-danger" role="alert">
    ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
</div>
</#if>
    <#if message??>
    <div class="alert alert-${messageType}" role="alert">
    ${message}
    </div>
    </#if>
<@L.login "/login" false/>
</@C.page>