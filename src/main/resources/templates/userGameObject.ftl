<#import "parts/common.ftl" as c>

<@c.page>
    <#if isCurrentUser>
        <#include "parts/gameObjectEdit.ftl"/>
    </#if>
    <#include "parts/gameObjectList.ftl"/>
</@c.page>

