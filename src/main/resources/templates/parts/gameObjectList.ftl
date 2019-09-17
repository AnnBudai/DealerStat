<#include "security.ftl">

<div class="card-row">
    <#list gameObjects as gameObject>
        <div class="card my-1">
            <#if gameObject.filename??>
                <img src="/img/${gameObject.filename}" class="card-img-top" alt="image">
            </#if>
            <div class="card-body">
                <h5 class="card-title">${gameObject.title}</h5>
                <p>${gameObject.text}</p>
                <p>Game: <i>${gameObject.game.name}</i></p>
                <a href="/user-gameObject/${gameObject.user.id}">${gameObject.user.username}</a>
                <#if gameObject.user.id == currentUserId>
                    <a class="btn btn-primary"
                       href="/user-gameObject/${gameObject.user.id}?gameObject=${gameObject.id}">
                        Edit
                    </a>
                </#if>
            </div>
            <div class="card-footer">
                <table>
                    <tr>
                        <td width="950"><small class="text-muted">Create at ${gameObject.created_at}</small></td>
                        <td rowspan="2">
                            <a class="btn btn-primary btn-sm" href="/comment/${gameObject.id}">
                                Add comment
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td><small class="text-muted">Update at ${gameObject.updated_at}</small></td>
                    </tr>
                </table>
            </div>
        </div>
    <#else>
        No game object
    </#list>
</div>