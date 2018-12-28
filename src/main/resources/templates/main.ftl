<!--
<!DOCTYPE HTML>
<html>
<head>
    <title>Getting Started: Serving Web Content</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<div>
    <form action="/logout" method="post">
       <input type="hidden" name="_csrf" value="{{_csrf.token}}">
        <input type="submit" value="Sign Out"/>
    </form>
</div>
<div>
    <form method="post">
        <input type="text" name="text" placeholder="Введите сообщение" />
        <input type="text" name="tag" placeholder="Тэг" />
        <input type="hidden" name="_csrf" value="{{_csrf.token}}">
        <button type="submit">Добавить</button>
    </form>
</div>
<div>Список сообщений</div>
<form method="post" action="filter">
    <input type="text" name="filter">
   <input type="hidden" name="_csrf" value="{{_csrf.token}}">
    <button type="submit">Найти</button>
</form>
{{#messages}}
<div>
    <b>{{id}}</b>
    <span>{{text}}</span>
    <i>{{tag}}</i>
    <strong>{{authorName}}</strong>
</div>
{{/messages}}
</body>
</html>
-->
<#import "parts/common.ftl" as C>

<@C.page>
<div class="form-row">
    <div class = "form-group col-md-6">
<form method="get" action="/main" class="form-inline">
    <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by tag">
    <button type="submit" class="btn btn-primary ml-2">Search</button>
</form>
    </div>
</div>

<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Add new Message
</a>

<div class="collapse <#if message??>show</#if>"  id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
            <input type="text" name="text" class="form-control ${(textError??)?string('is-invalid','')}"
                   value= "<#if message??>${message.text}</#if>" placeholder="Введите сообщение" />
                <#if textError??>
                <div class="invalid-feedback">
                    ${textError}
                </div>
                </#if>
            </div>
            <div class="form-group">
            <input type="text" name="tag" class="form-control" placeholder="Тэг" value= "<#if message??>${message.text}</#if>"/>
                <#if tagError??>
                    <div class="invalid-feedback">
                    ${tagError}
                    </div>
                </#if>
            </div>
            <div class="custom-file">
            <input type="file" name="file" id="customFile"/>
                <label class="custom-file-label" for="customFile">Choose file</label>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <div class="form-group mt-3">
            <button type="submit" class="btn btn-primary">Добавить</button>
            </div>
        </form>
    </div>
</div>
<div class="card-columns">
    <#list  messages as message>
    <div class="card my-3">
        <!-- <div enctype="multipart/form-data">-->
            <#if message.filename??>
                <img src="/img/${message.filename}" class="card-img-top">
            </#if>
    <div class="m-2">
        <!--<b>${message.id}</b>-->
        <span>${message.text}</span>
        <i>${message.tag}</i>
    </div>
    <div class="card-footer text-muted">
        ${message.authorName}
    </div>
    </div>
    <#else>
    No messages
    </#list>
</div>
</@C.page>