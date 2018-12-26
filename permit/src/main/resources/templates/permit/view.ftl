<div style="margin: 15px;">
    <table class="layui-table" >
        <colgroup>
            <col width="150">
            <col width="200">
            <col>
        </colgroup>
        <thead>
        <tr>
            <th>名字</th>
            <th>url</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="view">
        <#if roleJnList??>
        <#list roleJnList as r>
        <tr>
            <td>${r.jurisdiction.jnName}</td>
            <td>${r.jurisdiction.jnUrl}</td>
            <td>
                <#if r.had==true>
                    <a href="javascript:" data-id="${r.roleJnId!}"  data-roleid="${roleId}" data-jnid="${r.jurisdiction.jnId!}" data-opt="del" class="layui-btn layui-btn-normal layui-btn-mini">删除</a>
                <#else>
                    <a href="javascript:" data-id="${r.roleJnId!}"  data-roleid="${roleId}" data-jnid="${r.jurisdiction.jnId!}" data-opt="add" class="layui-btn layui-btn-mini">添加</a>
                </#if>
            </td>
        </tr>
        </#list>
        </#if>
        </tbody>
    </table>
</div>