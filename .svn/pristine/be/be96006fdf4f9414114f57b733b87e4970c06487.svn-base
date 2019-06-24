<#include "../public_header.ftl">



<script type="text/javascript" src="/static/js/truckMission/index.js"></script>

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
				 			<input type="text" placeholder="<@spring.message code="carMission_Templatenumber"/>" name="search" autocomplete="off" class="layui-input" />
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
			<#if addTruckTask??>
			<button class="layui-btn" onclick="zq_admin_show('<@spring.message code="zq_New"/>','/admin/truckMission/add')"><i class="layui-icon">&#xe608;</i><@spring.message code="zq_New"/></button>
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
		<#if editTruckTask??>
        <a class="zq-btn-edit" onclick="zq_admin_show('<@spring.message code="zq_edit"/>','/admin/truckMission/edit?truckTaskId={{d.truckTaskId}}')"><@spring.message code="zq_edit"/></a>
		</#if>

		<#if truckTaskDetails??>
        <a class="zq-btn-open" onclick="zq_admin_show('<@spring.message code="zq_View"/>','/admin/truckMission/open?truckTaskId={{d.truckTaskId}}')"><@spring.message code="zq_View"/></a>
		</#if>

		<#if deleteTruckTask??>
        <a class="zq-btn-delete currency"  onclick="delthis('{{d.truckTaskId}}')"><@spring.message code="zq_delete"/></a>
		</#if>

		<#if editTruckTaskStatus??>
        {{# if(d.status == 1){}}
        <a class="zq-btn-disabled" onclick="disabled('{{d.truckTaskId}}')"><@spring.message code="zq_Disable"/></a>
        {{# } }}
        {{# if(d.status == 2){}}
        <a class="zq-btn-startusing" onclick="startusing('{{d.truckTaskId}}')"><@spring.message code="zq_Enable"/></a>
        {{# } }}
		</#if>
	</script>
	<!-- 表格操作 end -->

	<#-- 状态 start -->
    <script type="text/html" id="status">
        {{# if(d.status == 2){}}
        	禁用
        {{# } }}
        {{# if(d.status == 1){}}
        	启用
        {{# } }}
	</script>

	<#-- 状态 end -->
</body>
</html>