<#include "../public_header.ftl">


	<#--<link rel="stylesheet" type="text/css" href="/static/css/administratorType/add.css">-->
	<script type="text/javascript" src="/static/js/administratorType/add.js"></script>

</head>
<body>

	<div class="zq-body">
		 
		<form class="layui-form layui-form-pane zq-form">
			<div class="layui-form-item">
				<label  for="name" class="layui-form-label">
					管理员类型
				</label>
				<div class="layui-input-inline">
                    <input type="text" name="name" lay-verify="required" autocomplete="off" class="layui-input">
                </div>
			</div>
			<div class="layui-form-item">
				<label  for="name" class="layui-form-label">
					权限类型
				</label>
				<div class="layui-input-inline">
					<select name="type" lay-search>
						<option value=""></option>
						<option value="1">管理员权限</option>
						<option value="2">仓库权限</option>
						<option value="3">三方权限</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label">
					备注
				</label>
				<div class="layui-input-block">
					<textarea placeholder="" name="comment" class="layui-textarea"></textarea>
				</div>


			</div>

			<div class="zq-submit">
            	<button class="layui-btn  layui-btn-normal" lay-submit="" lay-filter="add"><@spring.message code="zq_save"/></button>
            </div>
		</form>

	</div>
	
</body>
</html>