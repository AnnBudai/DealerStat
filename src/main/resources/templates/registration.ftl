<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as r>

<@c.page>
    ${message!}
    <@r.registration "/registration"/>
</@c.page>
