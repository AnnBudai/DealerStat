<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
   aria-controls="collapseExample"> Add new comment</a>
<div>
    <div class="collapse<#if comment??>show</#if>" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control ${(messageError??)?string('is-invalid', '')}"
                           value="<#if comment??>${comment.message}</#if>" name="message" placeholder="Message"/>
                    <#if messageError??>
                        <div class="invalid-feedback">
                            ${messageError}
                        </div>
                    </#if>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="hidden" name="gameObject" value="${gameObject.id}">
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Add comment</button>
                </div>
            </form>
        </div>
    </div>
</div>
