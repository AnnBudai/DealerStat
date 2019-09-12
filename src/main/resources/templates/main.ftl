<#import "parts/common.ftl" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-8">
            <form method="get" action="/main" class="form-inline">
                <input type="text" name="title" class="form-control" value="${title!}"
                       placeholder="Search by game object title">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Add new game object
    </a>
    <div>
        <div class="collapse" id="collapseExample">
            <div class="form-group mt-3">
                <form method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <input type="text" class="form-control" name="game_name" placeholder="game"/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="title" placeholder="title"/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="text" placeholder="text"/>
                    </div>
                    <div class="form-group">
                        <div class="custom-file">
                            <input type="file" name="file" id="customFile">
                            <label class="custom-file-label" for="customFile">Choose file</label>
                        </div>
                    </div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Add</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

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
                    <p>Trader: <i>${gameobject.user.username}</i></p>
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
</@c.page>
