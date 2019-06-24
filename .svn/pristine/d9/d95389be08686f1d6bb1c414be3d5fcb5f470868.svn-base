<#include "../public_header.ftl">


	<script type="text/javascript" src="/static/js/abnormalGoods/index.js"></script>

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
                             <input type="text" placeholder="<@spring.message code="normalGoods_Expressordernumber"/>" name="deliveryNumber" autocomplete="off" class="layui-input" />
                         </div>
                     </div>
				 	<div class="layui-inline">
				 		<div class="layui-input-inline">
				 			<input type="text" placeholder="货物单号" name="tripartiteNumber" autocomplete="off" class="layui-input" />
				 		</div>
				 	</div>

				 	<div class="layui-inline">
				 		<div class="layui-input-inline">

				 			<select name="warningStateNumber">
				 				<option value=""><@spring.message code="abnormalGoods_Selectionstatus"/></option>
				 				<option value="2"><@spring.message code="abnormalGoods_abnormal"/></option>
				 				<option value="3"><@spring.message code="abnormalGoods_Processing"/></option>
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
		
		<!-- 操作按钮 end-->

		<!-- 数据表格 start -->

		<table id="idTest" lay-filter="demo"></table>

		<!-- 数据表格 end -->

	</div>

	<!-- 内容 end -->

	<!-- 表格操作 start -->
	<script type="text/html" id="barDemo">
		<#if updateGoodsWarningDetails??>
        <a class="zq-btn-edit" onclick="zq_admin_show('<@spring.message code="zq_edit"/>','/admin/abnormalGoods/edit?goodsId={{d.goodsId}}&from={{d.from}}')"><@spring.message code="zq_edit"/></a>
		</#if>
		<#if getWarningGoodsLogisticById??>
				<a class="zq-btn-open" onclick="zq_admin_show('<@spring.message code="zq_View"/>','/admin/abnormalGoods/open?goodsId={{d.goodsId}}')"><@spring.message code="zq_View"/></a>
		</#if>

	</script>
	<!-- 表格操作 end -->
</body>
</html>