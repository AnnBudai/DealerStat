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

    <#include "parts/gameObjectEdit.ftl"/>
    <#include "parts/gameObjectList.ftl"/>

</@c.page>
