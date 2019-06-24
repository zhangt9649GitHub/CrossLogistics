<#include "../public_header.ftl">


	<script type="text/javascript" src="/static/js/carlock/index.js"></script>

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
				 			<input type="text" name="lockNumber" placeholder="车锁编号" autocomplete="off" class="layui-input" />
				 		</div>
				 	</div>
				 	<div class="layui-inline">
				 		<div class="layui-input-inline">

				 			<select name="status">
				 				<option value="">请选择状态</option>
				 				<option value="1">使用中</option>
				 				<option value="2">损坏</option>
                                <option value="3">空置</option>
							</select>

				 		</div>
				 	</div>

				 	<div class="layui-inline">
				 		<div class="layui-btn" lay-submit="" lay-filter="*">
				 			<i class="layui-icon">&#xe615;</i>
				 		</div>

				 	</div>

				 	
				 </div>
			</form>
			
		</div>
		<!-- 查询 end -->

		<!-- 操作按钮 start-->
		<div class="zq-actionbuttons">
			<#if addLock??>
			<button class="layui-btn" onclick="zq_admin_show('<@spring.message code="zq_New"/>','/admin/carlock/add','450','500')"><i class="layui-icon">&#xe608;</i><@spring.message code="zq_New"/></button>
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
		<#if updateLock??>
			{{# if(d.status != 1){ }}
			<a class="zq-btn-edit" onclick="zq_admin_show('<@spring.message code="zq_edit"/>','/admin/carlock/edit?lockId={{d.lockId}}','450','500')"><@spring.message code="zq_edit"/></a>
			{{# } }}
		</#if>

		<#if scrapLock??>
			{{# if(d.status != 2){ }}
				{{# if(d.carNumber != null){ }}
        		<a class="zq-btn-disabled"  onclick="disabled('{{d.carNumber}}','{{d.lockId}}')">报废</a>
				{{# }else{ }}
				<a class="zq-btn-disabled"  onclick="disableds('{{d.lockId}}')">报废</a>
				{{# } }}
			{{# } }}
		</#if>
		<#if repairLock??>
			{{# if(d.status == 2){ }}
			<a class="zq-btn-startusing"  onclick="startusing('{{d.lockId}}')">修复</a>
			{{# } }}
		</#if>
	</script>
	<!-- 表格操作 end -->

	<#-- 状态 -->
    <script type="text/html" id="status">
        {{# if(d.status == 1){ }}
		使用中
        {{# } }}
        {{# if(d.status == 2){ }}
		损坏
        {{# } }}
        {{# if(d.status == 3){ }}
		空置
        {{# } }}

    </script>
</body>
</html>