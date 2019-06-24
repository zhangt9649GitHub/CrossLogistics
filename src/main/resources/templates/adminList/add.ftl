<#include "../public_header.ftl">


	<link rel="stylesheet" type="text/css" href="/static/css/adminList/add.css">
	<script type="text/javascript" src="/static/js/adminList/add.js"></script>

</head>
<body>

	<div class="zq-body">
		 
		<form class="layui-form layui-form-pane zq-form">
			<div class="layui-form-item">
				<label  for="name" class="layui-form-label">
					<@spring.message code="adminList_Administratorname"/>
				</label>
				<div class="layui-input-inline">
                    <input type="text" id="name" name="adminName" lay-verify="required" autocomplete="off" class="layui-input">
                </div>
			</div>
			<div class="layui-form-item">
				<label  for="name" class="layui-form-label">
					<@spring.message code="adminList_Privilegegroupname"/>
				</label>
				<div class="layui-input-inline" >
					<select name="groupId" lay-filter="aihao" lay-verify="required" lay-search>
						<option value=""><@spring.message code="adminList_Pleaseelectpermission"/></option>

					</select>
				</div>
			</div>

			<div class="layui-form-item">
				<label  for="name" class="layui-form-label">
					管理员类型
				</label>
				<div class="layui-input-inline" >
					<select name="adminUserTypeId" lay-verify="required" lay-search>
						<option value=""></option>

					</select>
				</div>
			</div>

			<div class="layui-form-item">
				<label  for="name" class="layui-form-label">
					归属地
				</label>
				<div class="layui-input-inline" >
					<select name="attribution" lay-verify="required" lay-search>
						<option value=""></option>

					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label  for="name" class="layui-form-label">
					所属仓库
				</label>
				<div class="layui-input-inline" >
					<select name="warehouseId" lay-verify="required" lay-search>
						<option value=""></option>

					</select>
				</div>
			</div>

			<div class="layui-form-item">
				<label  for="password" class="layui-form-label">
					<@spring.message code="zq_password"/>
				</label>
				<div class="layui-input-inline">
                    <input type="password"  name="password" lay-verify="required" autocomplete="off" class="layui-input">
                </div>
			</div>
			<div class="layui-form-item">
				<label  for="confirmPassword" class="layui-form-label">
					<@spring.message code="zq_confirmpassword"/>
				</label>
				<div class="layui-input-inline">
                    <input type="password" id="confirmPassword"  lay-verify="required" autocomplete="off" class="layui-input">
                </div>
			</div>
			<div class="zq-submit">
            	<button class="layui-btn  layui-btn-normal" lay-submit="" lay-filter="add"><@spring.message code="zq_save"/></button>
            </div>
		</form>

	</div>
	
</body>
</html>