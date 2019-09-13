<#include "security.ftl"/>

<div class="card-row">
    <#list gameObjects as gameobject>
        <div class="card my-1">
            <#if gameobject.filename??>
                <img src="/img/${gameobject.filename}" class="card-img-top">
            </#if>
            <div class="card-body">
                <h5 class="card-title">${gameobject.title}</h5>
                <p>${gameobject.text}</p>
                <p>Game: <i>${gameobject.game.name}</i></p>
                <a href="/user-gameObject/${gameobject.user.id}">${gameobject.user.username}</a>
                <#if gameobject.user.id == currentUserId>
                    <a class="btn btn-primary" href="/user-gameObject/${gameobject.user.id}?message=${gameobject.id}">
                        Edit
                    </a>
                </#if>
            </div>
            <div class="card-footer">
                <small class="text-muted">Create at ${gameobject.created_at}    </small>
                <small class="text-muted">Update at ${gameobject.updated_at}</small>
            </div>
        </div>
    <#else>
        No game object
    </#list>
</div>