
<#include "../public_header.ftl">

	<script type="text/javascript" src="/static/js/index/changePassword.js"></script>
	<link rel="stylesheet" href="/static/css/index/changePassword.css">
</head>
<body>

	<div class="zq-body">
		 
		<form class="layui-form layui-form-pane zq-form">
            <input type="hidden" name="adminUid" value="${(Session.user.adminUId)!''}">
            <input type="hidden" name="groupId" value="">
            <input type="hidden" name="adminName" value="${(Session.user.userName)!''}">
			<div class="layui-form-item">
				<label  for="password" class="layui-form-label">
					<@spring.message code="zq_Originalpassword"/>
				</label>
				<div class="layui-input-inline">
                    <input type="password" name="password" lay-verify="required" autocomplete="off" class="layui-input">
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
            	<button class="layui-btn  layui-btn-normal" lay-submit="" lay-filter="*"><@spring.message code="zq_save"/></button>
            </div>
		</form>

	</div>
	
</body>
</html>