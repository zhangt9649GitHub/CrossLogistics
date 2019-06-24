<#include "../public_header.ftl">


	<script type="text/javascript" src="/static/js/truck/index.js"></script>

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

				 			<select name="state" >
				 				<option value=""><@spring.message code="truck_Usagestatus"/></option>
				 				<option value="1"><@spring.message code="truck_Orderinprogress"/></option>
				 				<option value="2"><@spring.message code="truck_idle"/></option>
				 			</select>		

				 		</div>
				 	</div>

				 	<div class="layui-inline">
				 		<div class="layui-input-inline">
				 			<input type="text" name="licensePlate" placeholder="<@spring.message code="normalGoods_Trucklicense"/>" autocomplete="off" class="layui-input" />
				 		</div>
				 	</div>

				 	<div class="layui-inline">
				 		<div class="layui-input-inline">
				 			<input type="text" name="mobile" placeholder="<@spring.message code="zq_contactdetails"/>" autocomplete="off" class="layui-input" />
				 		</div>
				 	</div>
				 	
				 	

				 	<div class="layui-inline">
				 		<button class="layui-btn" lay-submit lay-filter="*">
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
		<#if getTruckById??>
        <a class="zq-btn-open" onclick="zq_admin_show('<@spring.message code="zq_View"/>','/admin/truck/open?truckId={{d.truckId}}')"><@spring.message code="zq_View"/></a>
		</#if>
	</script>
	<!-- 表格操作 end -->

    <script type="text/html" id="state">

        {{# if(d.state == 1){}}
        订单进行中
        {{# } }}
		{{# if(d.state == 2){  }}
		空闲
		{{# } }}
	</script>
</body>
</html>