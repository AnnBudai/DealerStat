<#include "security.ftl">

<div class="card-row">
    <#list comments as comment>
        <div class="card my-1">
            <div class="card-body">
                <p>Game object: ${comment.gameObject.id}</p>
                <p>User: ${comment.user}</p>
                <p>${comment.message}</p>
            </div>
            <div class="card-footer">
                <small class="text-muted">Create at ${comment.create_at}</small>
            </div>
        </div>
    <#else>
        No comment
    </#list>
</div>