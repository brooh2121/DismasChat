<#import "parts/common.ftl" as C>

<@C.page>
<#if isCurrentUser>
    <#include "parts/messageEdit.ftl"/>
</#if>
    <#include "parts/messageList.ftl"/>
</@C.page>