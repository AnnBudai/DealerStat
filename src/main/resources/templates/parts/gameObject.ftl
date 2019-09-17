<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
   aria-controls="collapseExample">
    Game object editor
</a>
<div>
    <div class="collapse<#if gameObject??>show</#if>" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div>
                    <p><input type="radio" name="game_name" value="CS:GO" checked>CS:GO</p>
                    <p><input type="radio" name="game_name" value="Fifa">Fifa</p>
                    <p><input type="radio" name="game_name" value="Dota">Dota</p>
                    <p><input type="radio" name="game_name" value="Team Fortress">Team Fortress</p>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control ${(titleError??)?string('is-invalid', '')}"
                           value="<#if gameObject??>${gameObject.title}</#if>" name="title" placeholder="title"/>
                    <#if titleError??>
                        <div class="invalid-feedback">
                            ${titleError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" value="<#if gameObject??>${gameObject.text}</#if>"
                           name="text" placeholder="text"/>
                    <#if textError??>
                        <div class="invalid-feedback">
                            ${textError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file" id="customFile">
                        <label class="custom-file-label" for="customFile">Choose file</label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Add game object</button>
                </div>
            </form>
        </div>
    </div>
</div>
