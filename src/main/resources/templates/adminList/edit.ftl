<#include "../public_header.ftl">


	<link rel="stylesheet" type="text/css" href="/static/css/adminList/add.css">
	<script type="text/javascript" src="/static/js/adminList/edit.js"></script>

</head>
<body>

	<div class="zq-body">
		 
		<form class="layui-form layui-form-pane zq-form">
            <input type="hidden" value="${adminUid}" name="adminUid">
			<div class="layui-form-item">
				<label  for="name" class="layui-form-label">
					<@spring.message code="adminList_Administratorname"/>
				</label>
				<div class="layui-input-inline">
                    <input type="text" name="adminName" lay-verify="required" autocomplete="off" class="layui-input">
                </div>
			</div>
			<div class="layui-form-item">
				<label  for="name" class="layui-form-label">
					<@spring.message code="adminList_Privilegegroupname"/>
				</label>
				<div class="layui-input-inline">
                    <select name="groupId" lay-filter="aihao" lay-verify="required" lay-search>
				        <option value=""><@spring.message code="adminList_Pleaseelectpermission"/></option>
				    </select>
                </div>
			</div>


			<div class="layui-form-item">
				<label  for="name" class="layui-form-label">
					管理员类型
				</label>
				<div class="layui-input-inline">
					<input type="text" name="userType" class="layui-input" readonly/>
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
					<@spring.message code="zq_Originalpassword"/>
				</label>
				<div class="layui-input-inline">
                    <input type="password"  name="password" lay-verify="required" autocomplete="off" class="layui-input">
                </div>
			</div>
			<div class="layui-form-item">
				<label  for="confirmPassword" class="layui-form-label">
					<@spring.message code="zq_newpassword"/>
				</label>
				<div class="layui-input-inline">
                    <input type="password" name="newPassword" lay-verify="required" autocomplete="off" class="layui-input">
                </div>
			</div>
			<div class="zq-submit">
            	<button class="layui-btn  layui-btn-normal" lay-submit lay-filter="edit"><@spring.message code="zq_save"/></button>
            </div>
		</form>

	</div>
	
</body>
</html>