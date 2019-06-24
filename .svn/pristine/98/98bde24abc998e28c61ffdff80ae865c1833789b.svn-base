<#include "../public_header.ftl">



	<script type="text/javascript" src="/static/js/administratorType/index.js"></script>

</head>
<body class="zq-bodybagcolor">

	<!-- 刷新 start -->
	<div class="zq-nav">
		<a class="layui-btn right" href="javascript:location.replace(location.href);" title="刷新">
			<i class="layui-icon">&#xe669;</i>
		</a>
	</div>
	<!-- 刷新 end -->


	<!-- 内容 start -->

	<div class="zq-body">
		<!-- 查询 start -->

		<!-- 查询 end -->

		<!-- 操作按钮 start-->
		<div class="zq-actionbuttons">
			<#if addUserType??>
			<button class="layui-btn" onclick="zq_admin_show('<@spring.message code="zq_New"/>','/admin/administratorType/add','450','500')"><i class="layui-icon">&#xe608;</i><@spring.message code="zq_New"/></button>
			</#if>
		</div>
		<!-- 操作按钮 end-->

		<!-- 数据表格 start -->

		<table id="idTest" lay-filter="demo"></table>


		<!-- 数据表格 end -->

	</div>

	<!-- 内容 end -->

	<!-- 表格操作 start -->
	<script type="text/html" id="barDemo">
		<#if updateUserType??>
	    <a class="zq-btn-edit" lay-event="edit" onclick="zq_admin_show('<@spring.message code="zq_edit"/>','/admin/administratorType/edit?adminUserTypeId={{d.adminUserTypeId}}','450','500')"><@spring.message code="zq_edit"/></a>
		</#if>
		<#if deleteUserType??>
	   	<a class="zq-btn-delete" lay-event="delete" onclick="delthis('{{d.adminUserTypeId}}')"><@spring.message code="zq_delete"/></a>
		</#if>


	</script>
	<!-- 表格操作 end -->

	<script type="text/html" id="type">
		{{# if(d.type == 1){ }}
		管理员权限
		{{# } }}
		{{# if(d.type == 2){ }}
		仓库权限
		{{# } }}
		{{# if(d.type == 3){ }}
		三方权限
		{{# } }}
	</script>
</body>
</html>