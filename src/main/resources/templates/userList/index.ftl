<#include "../public_header.ftl">


	<script type="text/javascript" src="/static/js/userList/index.js"></script>

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
				 			<input type="text" name="search" placeholder='编号/用户名/联系方式' autocomplete="off" class="layui-input" />
				 		</div>
				 	</div>
				 	<div class="layui-inline">
				 		<div class="layui-input-inline">

				 			<select name="sex">
				 				<option value=""><@spring.message code="userList_Pleaseselectgender"/></option>
				 				<option value="男"><@spring.message code="zq_male"/></option>
				 				<option value="女"><@spring.message code="zq_Female"/></option>
				 			</select>		

				 		</div>
				 	</div>
				 	<div class="layui-inline">
				 		<div class="layui-input-inline">

				 			<select name="userStatus">
				 				<option value=""><@spring.message code="zq_pleasechoose"/></option>
				 				<#--<option value="普通用户"><@spring.message code="userList_Ordinaryuser"/></option>-->
				 				<option value="小车用户"><@spring.message code="userList_Caruser"/></option>
				 				<option value="货车用户"><@spring.message code="userList_Truckuser"/></option>
				 			</select>		

				 		</div>
				 	</div>



				 	<div class="layui-inline">
				 		<button class="layui-btn" lay-submit="" lay-filter="add">
				 			<i class="layui-icon">&#xe615;</i>
				 		</button>

				 	</div>

				 	
				 </div>
			</form>
			
		</div>
		<!-- 查询 end -->

		<!-- 操作按钮 start-->
		<div class="zq-actionbuttons">
			<#if addAppUser??>
			<button class="layui-btn" onclick="zq_admin_show('<@spring.message code="zq_New"/>','/admin/userList/add')"><i class="layui-icon">&#xe608;</i><@spring.message code="zq_New"/></button>
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
		<#if editAppUser??>
		<a class="zq-btn-edit" onclick="zq_admin_show('<@spring.message code="zq_edit"/>','/admin/userList/edit?appUserId={{d.appUserId}}')"><@spring.message code="zq_edit"/></a>
		</#if>
		<#if appUserDetail??>
        <a class="zq-btn-open" onclick="zq_admin_show('<@spring.message code="zq_View"/>','/admin/userList/open?appUserId={{d.appUserId}}')"><@spring.message code="zq_View"/></a>
		</#if>

		<#if deleteAppUser??>
        <a class="zq-btn-delete" lay-event="delete" onclick="delthis('{{d.appUserId}}')"><@spring.message code="zq_delete"/></a>
		</#if>

		<#if editAppUserStatus??>
        {{# if(d.status != '禁用'){}}
        <a class="zq-btn-disabled" onclick="disabled('{{d.appUserId}}')"><@spring.message code="zq_Disable"/></a>
        {{# } }}
        {{# if(d.status == '禁用'){}}
        <a class="zq-btn-startusing" onclick="startusing('{{d.appUserId}}')"><@spring.message code="zq_Enable"/></a>
        {{# } }}
		</#if>
	</script>
	<!-- 表格操作 end -->
</body>
</html>