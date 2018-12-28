<!--
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Spring Security Example </title>
</head>
<body>
<p>ADD New User</p>
{{#message}}
    {{message}}
{{/message}}
<form action="/registration" method="post">
    <div><label> User Name : <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <input type="hidden" name="_csrf" value="{{_csrf.token}}">
    <div><input type="submit" value="Sign In"/></div>
</form>
</body>
</html>
-->
<#import "parts/common.ftl" as C>
<#import "parts/login.ftl" as L>
<@C.page>
<div class="mb-1">ADD New User</div>
${message?ifExists}
<@L.login "/registration" true/>
</@C.page>