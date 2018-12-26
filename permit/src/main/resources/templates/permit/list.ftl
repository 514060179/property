<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>角色管理</title>
		<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="../css/global.css" media="all">
		<link rel="stylesheet" href="../plugins/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="../css/table.css" />
	</head>

	<body>
		<div class="admin-main">
            <blockquote class="layui-elem-quote">
                <button type="button" class="layui-btn layui-btn-small" id="add"><i class="fa fa-plus" aria-hidden="true"></i> 添加</button>
                <div class="layui-form" style="float:right;">
                    <div class="layui-form-item" style="margin:0;">
                        <label class="layui-form-label">关键字</label>
                        <div class="layui-input-inline">
                            <input type="text" name="keyword" id="keyword" autocomplete="off" class="layui-input">
                        </div>
                        <#--<label class="layui-form-label">商户类型</label>-->
                        <#--<div class="layui-input-inline">-->
                            <#--<select name="type" id="type" lay-search="">-->
                                <#--<option value="-99">所有类型</option>-->
                                <#--<option value="1" >平台账户</option>-->
                                <#--<option value="2" >私有账户</option>-->
                            <#--</select>-->
                        <#--</div>-->
                        <div class="layui-form-mid layui-word-aux" style="padding:0;">
                            <button id="search" lay-filter="search" class="layui-btn" lay-submit><i class="fa fa-search" aria-hidden="true"></i> 查询</button>
                        </div>
                    </div>
                </div>
            </blockquote>

			<fieldset class="layui-elem-field">
				<legend>角色列表</legend>
				<div class="layui-field-box layui-form">
					<table class="layui-table admin-table">
						<thead>
							<tr>
								<th style="width: 30px;"><input type="checkbox" lay-filter="allselector" lay-skin="primary"></th>
								<th>ID</th>
								<th>名称</th>
								<th>说明</th>
								<th>创建时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="content">
						</tbody>
					</table>
				</div>
			</fieldset>
			<div class="admin-table-page">
				<div id="paged" class="page">
				</div>
			</div>
		</div>
		<!--模板-->
		<script type="text/html" id="tpl">
			{{# layui.each(d.list, function(index, item){ }}
			<tr>
				<td><input type="checkbox" lay-skin="primary"></td>
				<td>{{ item.roleId }}</td>
				<td>{{ item.roleName }}</td>
				<td>
                    {{item.roleDescription}}
				</td>

				<td>{{ item.createTime }}</td>
				<td>
					<a href="javascript:" data-id="{{ item.roleId }}" data-opt="view" class="layui-btn layui-btn-normal layui-btn-mini">详情</a>
					<a href="javascript:" data-id="{{ item.roleId }}" data-opt="edit" class="layui-btn layui-btn-mini">编辑</a>
					<a href="javascript:" data-id="{{ item.roleId }}" data-opt="del" class="layui-btn layui-btn-mini">删除</a>
				</td>
			</tr>
			{{# }); }}
		</script>
		<script type="text/javascript" src="../plugins/layui/layui.js"></script>
		<script>
			layui.config({
				base: '../js/'
			});

			layui.use(['paging', 'form'], function() {
				var $ = layui.jquery,
					paging = layui.paging(),
					layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
					layer = layui.layer, //获取当前窗口的layer对象
					form = layui.form();

                paging.init({
                    openWait: true,
					url: '/permit/list?v=' + new Date().getTime(), //地址
					elem: '#content', //内容容器
					params: { //发送到服务端的参数
					},
					type: 'GET',
					tempElem: '#tpl', //模块容器
					pageConfig: { //分页参数配置
						elem: '#paged', //分页容器
						pageSize: 15 //分页大小
					},
					success: function() { //渲染成功的回调
						//alert('渲染成功');
					},
					fail: function(msg) { //获取数据失败的回调
						//alert('获取数据失败')
					},
					complate: function() { //完成的回调
						//alert('处理完成');
						//重新渲染复选框
						form.render('checkbox');
						form.on('checkbox(allselector)', function(data) {
							var elem = data.elem;

							$('#content').children('tr').each(function() {
								var $that = $(this);
								//全选或反选
								$that.children('td').eq(0).children('input[type=checkbox]')[0].checked = elem.checked;
								form.render('checkbox');
							});
						});

						//绑定所有编辑按钮事件						
						$('#content').children('tr').each(function() {
							var $that = $(this);
							$that.children('td:last-child').children('a[data-opt=notify]').on('click', function() {
                                editForm($(this).data('id'));
							});
						});

                        //绑定所有预览按钮事件
                        $('#content').children('tr').each(function() {
                            var $that = $(this);
                            $that.children('td:last-child').children('a[data-opt=view]').on('click', function() {
                                viewForm($(this).data('id'));
                            });
                        });

                        //绑定所有预览按钮事件
                        $('#content').children('tr').each(function() {
                            var $that = $(this);
                            $that.children('td:last-child').children('a[data-opt=edit]').on('click', function() {
                                editForm($(this).data('id'));
                            });
                        });

                        //绑定所有删除按钮事件
                        $('#content').children('tr').each(function() {
                            var $that = $(this);
                            $that.children('td:last-child').children('a[data-opt=del]').on('click', function() {
                                layer.msg($(this).data('id'));
                            });
                        });

					},
				});
				//获取所有选择的列
				$('#getSelected').on('click', function() {
					var names = '';
					$('#content').children('tr').each(function() {
						var $that = $(this);
						var $cbx = $that.children('td').eq(0).children('input[type=checkbox]')[0].checked;
						if($cbx) {
							var n = $that.children('td:last-child').children('a[data-opt=edit]').data('name');
							names += n + ',';
						}
					});
					layer.msg('你选择的名称有：' + names);
				});

				$('#search').on('click', function() {
                    var keyword = $("#keyword").val();
                    paging.get({
                        "keyword": keyword,
                        "v":new Date().getTime()
                    });
				});

				var addBoxIndex = -1;
				$('#add').on('click', function() {
					if(addBoxIndex !== -1)
						return;
                    editForm();
				});

				$('#import').on('click', function() {
					var that = this;
					var index = layer.tips('只想提示地精准些', that, { tips: [1, 'white'] });
					$('#layui-layer' + index).children('div.layui-layer-content').css('color', '#000000');
				});

                function viewForm(id) {
                    //本表单通过ajax加载 --以模板的形式，当然你也可以直接写在页面上读取
                    $.get('/permit/view.html?roleId=' + id, null, function(form) {
                        addBoxIndex = layer.open({
                            type: 1,
                            title: '权限详情',
                            content: form,

                            shade: false,
                            offset: ['100px', '30%'],
                            area: ['600px', '450px'],
                            zIndex: 19950924,
                            maxmin: false,

                            full: function(elem) {
                                alert("full");
                                var win = window.top === window.self ? window : parent.window;
                                $(win).on('resize', function() {
                                    var $this = $(this);
                                    elem.width($this.width()).height($this.height()).css({
                                        top: 0,
                                        left: 0
                                    });
                                    elem.children('div.layui-layer-content').height($this.height() - 95);
                                });
                            },
                            success:function(){
                                //tr 绑定
                                $('#view').children('tr').each(function() {
                                    var $that = $(this);
                                    $that.children('td:last-child').children('a[data-opt=del]').on('click', function() {
                                        var self = this;
                                        var id = $(this).data('id');
                                        var roleId = $(this).data('roleid');
                                        var jnId = $(this).data('jnid');
                                        layerTips.confirm('确定删除记录'+id, function(index){
                                            $.get('/permit/roleJnDel?roleJnId='+id,function (data) {
                                                var d = JSON.parse(data);
                                                if (d.success){
                                                    layerTips.msg('删除成功!');
                                                    $(self).after('<a href="javascript:" data-id="'+id+'"  data-roleid="'+roleId+'" data-jnid="'+jnId+'" data-opt="add" class="layui-btn layui-btn-mini">添加</a>');
                                                    $(self).remove();
                                                }else{
                                                    alert(d.msg)
                                                }
                                            })
                                            layer.close(index);
                                        });
                                    });
                                });
                                //tr 绑定
                                $('#view').children('tr').each(function() {
                                    var $that = $(this);
                                    $that.children('td:last-child').children('a[data-opt=add]').on('click', function() {
                                        var self = this;
                                        var roleId = $(this).data('roleid');
                                        var jnId = $(this).data('jnid');
                                        var url = "/permit/roleJnAdd?roleId="+roleId+"&jnId="+jnId;
                                        layerTips.confirm('确定添加', function(index){
                                            $.get(url,function (data) {
                                                var d = JSON.parse(data);
                                                if (d.success){
                                                    layerTips.msg('添加成功!');
                                                    $(self).before('<a href="javascript:" data-id="'+d.data.roleJnId+'"  data-roleid="'+roleId+'" data-jnid="'+jnId+'" data-opt="del" class="layui-btn layui-btn-normal layui-btn-mini">删除</a>');
                                                    $(self).remove();
                                                    //添加删除按钮
                                                }else{
                                                    alert(d.msg)
                                                }
                                            })
                                            layer.close(index);
                                        });
                                    });
                                });
                            },
                            end: function() {
                                addBoxIndex = -1;
                            }
                        });
                        layer.full(addBoxIndex);
                    });
                }

				function editForm(roleId) {
                    if (roleId!=undefined){
                        roleId = "?roleId="+roleId;
                    }else {
                        roleId="";
                    }
                    //本表单通过ajax加载 --以模板的形式，当然你也可以直接写在页面上读取
                    $.get('/permit/edit.html' + roleId, null, function(form) {
                        addBoxIndex = layer.open({
                            type: 1,
                            title: '添加角色',
                            content: form,
                            btn: ['保存', '取消'],
                            shade: false,
                            offset: ['100px', '30%'],
                            area: ['600px', '450px'],
                            zIndex: 19950924,
                            maxmin: false,
                            yes: function(index) {
                                //触发表单的提交事件
                                $('form.layui-form').find('button[lay-filter=edit]').click();
                            },
                            full: function(elem) {
                                var win = window.top === window.self ? window : parent.window;
                                $(win).on('resize', function() {
                                    var $this = $(this);
                                    elem.width($this.width()).height($this.height()).css({
                                        top: 0,
                                        left: 0
                                    });
                                    elem.children('div.layui-layer-content').height($this.height() - 95);
                                });
                            },
                            success: function(layero, index) {
                                //弹出窗口成功后渲染表单
                                var form = layui.form();
                                form.render();
                                form.on('submit(edit)', function(data) {
                                    //这里可以写ajax方法提交表单
                                    $.ajax({
                                        type: "POST",
                                        url: "/permit/add",
                                        data: "params=" + JSON.stringify(data.field),
                                        success: function(msg){
                                            var data = JSON.parse(msg);
                                            if(data.success) {
                                                layerTips.msg('保存成功');
                                                layerTips.close(index);
                                                location.reload(); //刷新
                                            }else {
                                                layerTips.msg('保存失败');
                                                layerTips.close(index);
                                                location.reload(); //刷新
                                            }
                                        }
                                    });
                                    return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
                                });

                            },
                            end: function() {
                                addBoxIndex = -1;
                            }
                        });
                        layer.full(addBoxIndex);
                    });
				}
			});
		</script>
	</body>

</html>