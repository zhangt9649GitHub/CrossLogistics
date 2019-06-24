<#include "../public_header.ftl">


	<script type="text/javascript" src="/static/js/staffList/index.js"></script>

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
				 			<input type="text" name="search" placeholder='<@spring.message code="staffList_NumberNameContact"/>' autocomplete="off" class="layui-input" />
				 		</div>
				 	</div>

				 	<div class="layui-inline">
				 		<div class="layui-input-inline">

				 			<select name="staffGroupId">
				 				<option value=""><@spring.message code="staffList_Pleaseselectemployeepermissions"/></option>
				 			</select>		

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
			<#if addStaff??>
			<button class="layui-btn" onclick="zq_admin_show('<@spring.message code="zq_New"/>','/admin/staffList/add')"><i class="layui-icon">&#xe608;</i><@spring.message code="zq_New"/></button>
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
		<#if editStaff??>
	    <a class="zq-btn-edit" lay-event="edit" onclick="zq_admin_show('<@spring.message code="zq_edit"/>','/admin/staffList/edit?staffId={{d.staffId}}')"><@spring.message code="zq_edit"/></a>
		</#if>

		<#if staffDetail??>
        <a class="zq-btn-open" onclick="zq_admin_show('<@spring.message code="zq_View"/>','/admin/staffList/open?staffId={{d.staffId}}')"><@spring.message code="zq_View"/></a>
		</#if>
		<#if deleteStaff??>
	   	<a class="zq-btn-delete" lay-event="<@spring.message code="zq_delete"/>" onclick="delthis('{{d.staffId}}')"><@spring.message code="zq_delete"/></a>
		</#if>

		<#if ditStaffStatus??>
        {{# if(d.status != 0){}}
        <a class="zq-btn-disabled" onclick="disabled('{{d.staffId}}')"><@spring.message code="zq_Disable"/></a>
        {{# } }}
        {{# if(d.status != 1){}}
        <a class="zq-btn-startusing" onclick="startusing('{{d.staffId}}')"><@spring.message code="zq_Enable"/></a>
        {{# } }}
		</#if>
	</script>
	<!-- 表格操作 end -->
    <!--列表--封面图片开始-->
    <script type="text/html" id="zq-img">
        <img src="{{d.headPic}}" class="zq-img-height" alt="">
    </script>
    <!--列表--封面图片结束-->
</body>
</html>