<#include "../public_header.ftl">


	<script type="text/javascript" src="/static/js/ManifestManagement/index.js"></script>

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
                             <input type="text" placeholder="表单编号" name="fromNumber" autocomplete="off" class="layui-input" />
                         </div>
                     </div>
				 	<#--<div class="layui-inline">-->
				 		<#--<div class="layui-input-inline">-->
				 			<#--<input type="text" placeholder="司机姓名" name="driverName" autocomplete="off" class="layui-input" />-->
				 		<#--</div>-->
				 	<#--</div>-->
				 	
				 	<div class="layui-inline">
				 		<button class="layui-btn" lay-submit="" lay-filter="*">
				 			<i class="layui-icon">&#xe615;</i>
				 		</button>

				 	</div>

				 	
				 </div>
			</form>
			
		</div>
		<!-- 查询 end -->

		<!-- 操作按钮 start-->
		
		<!-- 操作按钮 end-->

		<!-- 数据表格 start -->

		<table id="idTest" lay-filter="demo"></table>

		<!-- 数据表格 end -->

	</div>

	<!-- 内容 end -->

	<!-- 表格操作 start -->
	<script type="text/html" id="barDemo">
		<#if getGoodsWarningOperateNameById??>
        <a class="zq-btn-open" onclick="zq_admin_show('<@spring.message code="zq_View"/>','/admin/ManifestManagement/open?formId={{d.formId}}&type={{d.type}}')"><@spring.message code="zq_View"/></a>
		</#if>
		<#if updateGoodsFromPrint??>
		<a class="zq-btn-startusing" onclick="dayin('{{d.formId}}')">打印</a>
		</#if>
	</script>
	<!-- 表格操作 end -->

	<script type="text/html" id="type">
		{{# if(d.type == 1){ }}
			直接配送
		{{# } }}
		{{# if(d.type == 2){ }}
			订单配送
		{{# } }}
	</script>
</body>
</html>