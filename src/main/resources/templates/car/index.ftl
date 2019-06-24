<#include "../public_header.ftl">


	<script type="text/javascript" src="/static/js/car/index.js"></script>

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
				 			<input type="text" name="carNumber" placeholder="<@spring.message code="normalGoods_Carnumber"/>" autocomplete="off" class="layui-input" />
				 		</div>
				 	</div>

					 <div class="layui-inline">
						 <div class="layui-input-inline">

							 <select name="singaporeAreaId" lay-filter="singaporeAreaId">
								 <option value=""><@spring.message code="cargoBagList_Area"/></option>

							 </select>

						 </div>
					 </div>

					 <div class="layui-inline">
						 <div class="layui-input-inline">

							 <select name="rallyPointId">
								 <option value=""><@spring.message code="carMission_Aggregate"/></option>

							 </select>

						 </div>
					 </div>

				 	<div class="layui-inline">
				 		<div class="layui-input-inline">

				 			<select name="state">
				 				<option value=""><@spring.message code="normalGoods_statusofuse"/></option>
				 				<option value="1"><@spring.message code="car_idle"/></option>
				 				<option value="2"><@spring.message code="car_Delivery"/></option>
                                <option value="3"><@spring.message code="car_Inmaintenance"/></option>
                                <option value="4"><@spring.message code="car_Thereareabnormalpieces"/></option>
                                <option value="5"><@spring.message code="car_scrapped"/></option>
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
			<#if insertCar??>
			<button class="layui-btn" onclick="zq_admin_show('<@spring.message code="zq_New"/>','/admin/car/add')"><i class="layui-icon">&#xe608;</i><@spring.message code="zq_New"/></button>
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
		<#if updateCar??>
		<a class="zq-btn-edit" onclick="zq_admin_show('<@spring.message code="zq_edit"/>','/admin/car/edit?carId={{d.carId}}')"><@spring.message code="zq_edit"/></a>
		</#if>
		<#if getCarDetails??>
        <a class="zq-btn-open" onclick="zq_admin_show('<@spring.message code="zq_View"/>','/admin/car/open?carId={{d.carId}}')"><@spring.message code="zq_View"/></a>
		</#if>
		<#if deleteCarById??>
        <a class="zq-btn-delete currency"  onclick="delthis('{{d.carId}}')"><@spring.message code="zq_delete"/></a>
		</#if>
	</script>
	<!-- 表格操作 end -->

	<#-- 状态 -->
    <script type="text/html" id="stateID">
        {{# if(d.state == 1){ }}
        空闲
        {{# } }}
        {{# if(d.state == 2){ }}
        派送中
        {{# } }}
        {{# if(d.state == 3){ }}
        维护中
        {{# } }}
        {{# if(d.state == 4){ }}
        内有异常件
        {{# } }}
        {{# if(d.state == 5){ }}
        报废
        {{# } }}

    </script>
</body>
</html>