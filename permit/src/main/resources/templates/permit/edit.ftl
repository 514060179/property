<div style="margin: 15px;">
	<form class="layui-form">
		<div class="layui-form-item">
			<input type="text" name="roleId" value="<#if role??>${role.roleId?if_exists }</#if>" hidden>
			<label class="layui-form-label">角色名称（英文）</label>
			<div class="layui-input-block">
				<input type="text" name="roleName" placeholder="请输入角色名称（英文）" autocomplete="off" class="layui-input" value="<#if role??>${role.roleName?if_exists }</#if>">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">角色描述</label>
			<div class="layui-input-block">
				<input type="text" name="roleDescription" lay-verify="required" placeholder="请输入角色描述" autocomplete="off" class="layui-input" value="<#if role??>${role.roleDescription?if_exists }</#if>">
			</div>
		</div>
		<button lay-filter="edit" lay-submit style="display: none;"></button>
	</form>
</div>