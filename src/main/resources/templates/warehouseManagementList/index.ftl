
<#include "../public_header.ftl">
<script type="text/javascript" src="/static/js/warehouseManagementList/index.js"></script>

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
				 			<input type="text" name="warehouseNumber" placeholder="<@spring.message code="warehouseManagementList_Warehousenumber"/>" autocomplete="off" class="layui-input" />
				 		</div>
				 	</div>

				 	<div class="layui-inline">
				 		<div class="layui-input-inline">
				 			<input type="text" name="warehouseName" placeholder="<@spring.message code="warehouseManagementList_warehousename"/>" autocomplete="off" class="layui-input" />
				 		</div>
				 	</div>
				 	<div class="layui-inline">
				 		<div class="layui-input-inline">
				 			<input type="text" name="warehousePhone" placeholder="<@spring.message code="zq_contactdetails"/>" autocomplete="off" class="layui-input" />
				 		</div>
				 	</div>
				 	<div class="layui-inline">
				 		<button class="layui-btn" lay-submit="" lay-filter="query">
				 			<i class="layui-icon">&#xe615;</i>
				 		</button>

				 	</div>

				 	
				 </div>
			</form>
			
		</div>
		<!-- 查询 end -->

		<!-- 操作按钮 start-->
		<div class="zq-actionbuttons">
			<#if addwarehouse??>
			<button class="layui-btn" onclick="zq_admin_show('<@spring.message code="zq_New"/>','/admin/warehouseManagementList/add')"><i class="layui-icon">&#xe608;</i><@spring.message code="zq_New"/></button>
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
		<#if updatewarehouse??>
	    <a class="zq-btn-edit" lay-event="edit" onclick="zq_admin_show('<@spring.message code="zq_edit"/>','/admin/warehouseManagementList/edit?warehouseId={{d.warehouseId}}')"><@spring.message code="zq_edit"/></a>
		</#if>
		<#if deletewarehousebyid??>
	   	<a class="zq-btn-delete" lay-event="delete" onclick="delthis('{{d.warehouseId}}')"><@spring.message code="zq_delete"/></a>
		</#if>
	</script>
	<!-- 表格操作 end -->
</body>
</html>