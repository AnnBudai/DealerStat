<#import "parts/common.ftl" as c>

<@c.page>
    User editor

    <form action="/user" method="post">
        <input type="text" name="first_name" value="${user.first_name!}" placeholder="first_name">
        <input type="text" name="last_name" value="${user.last_name!}" placeholder="last_name">
        <input type="text" name="username" value="${user.username!}" placeholder="username">
        <input type="text" name="email" value="${user.email!}" placeholder="email">
        <#list roles as role>
            <div>
                <label><input type="checkbox"
                              name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
            </div>
        </#list>
        <input type="hidden" value="${user.id}" name="id">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button type="submit">Save</button>
    </form>
</@c.page>
