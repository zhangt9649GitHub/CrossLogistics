<#include "../public_header.ftl">


	<link rel="stylesheet" type="text/css" href="/static/css/adminList/index.css">
	<script type="text/javascript" src="/static/js/adminList/index.js"></script>

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
		<div class="zq-query">
			<form class="layui-form">
				 <div class="layui-form-item">

				 	<div class="layui-inline">
				 		<div class="layui-input-inline">

				 			<select id="group-id" name="groupId">
				 				<option value=""><@spring.message code="jurisdiction_index_Pleaseselectthepermissiongroupname"/></option>
				 			</select>		

				 		</div>
				 	</div>

				 	<div class="layui-inline">
				 		<div class="layui-input-inline">

				 			<input type="text" name="search" placeholder='<@spring.message code="adminList_Pleaseenteranadministratorname"/>' autocomplete="off" class="layui-input" />

				 		</div>
				 	</div>
				 	<div class="layui-inline">
				 		<div class="layui-btn"  lay-submit="" lay-filter="add">
				 			<i class="layui-icon">&#xe615;</i>
				 		</div>

				 	</div>

				 	
				 </div>
			</form>
			
		</div>
		<!-- 查询 end -->

		<!-- 操作按钮 start-->
		<div class="zq-actionbuttons">
			<#if addAdminUser??>
			<button class="layui-btn" onclick="zq_admin_show('<@spring.message code="zq_New"/>','/admin/adminList/add','450','500')"><i class="layui-icon">&#xe608;</i><@spring.message code="zq_New"/></button>
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
		<#if editAdminUser??>
	    <a class="zq-btn-edit" lay-event="edit" onclick="zq_admin_show('<@spring.message code="zq_edit"/>','/admin/adminList/edit?adminUid={{d.adminUid}}','450','500')"><@spring.message code="zq_edit"/></a>
		</#if>
		<#if deleteAdminUser??>
	   	<a class="zq-btn-delete" lay-event="delete" onclick="delthis('{{d.adminUid}}')"><@spring.message code="zq_delete"/></a>
		</#if>

		<#if editAdminUserStatus??>
        {{# if(d.status != 0){}}
        <a class="zq-btn-disabled" onclick="disabled('{{d.adminUid}}')"><@spring.message code="zq_Disable"/></a>
        {{# } }}
        {{# if(d.status != 1){}}
        <a class="zq-btn-startusing" onclick="startusing('{{d.adminUid}}')"><@spring.message code="zq_Enable"/></a>
        {{# } }}
		</#if>
	</script>
	<!-- 表格操作 end -->
</body>
</html>