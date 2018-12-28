<#import "parts/common.ftl" as C>

<@C.page>
${message?ifExists}
<h5>${username}</h5>
<form method="post" xmlns="http://www.w3.org/1999/html">
        <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Password:</label>
        <div class="col-sm-5">
            <input type="password" name="password" class="form-control" placeholder="Password"/>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Email:</label>
        <div class="col-sm-5">
            <input type="email" name="email" class="form-control" placeholder="Some@mail.com" value="${email!''}"/>
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit" class="btn btn-primary"/>Save</button>
</form>
</@C.page>
