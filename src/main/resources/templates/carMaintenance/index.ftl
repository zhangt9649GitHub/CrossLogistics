<#include "../public_header.ftl">


	<script type="text/javascript" src="/static/js/carMaintenance/index.js"></script>

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

                             <select name="state">
                                 <option value=""></option>
                                 <option value="1">报修</option>
                                 <option value="2">维修中</option>
                                 <option value="3">维修成功</option>
                                 <option value="4">维修失败</option>
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
			<#if addCarMaintenance??>
                <button class="layui-btn" onclick="zq_admin_show('<@spring.message code="zq_New"/>','/admin/carMaintenance/add','450','500')"><i class="layui-icon">&#xe608;</i><@spring.message code="zq_New"/></button>
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
		<#if editCarMaintenance??>
	    <a class="zq-btn-edit" lay-event="edit" onclick="zq_admin_show('<@spring.message code="zq_edit"/>','/admin/carMaintenance/edit?carMaintenanceId={{d.carMaintenanceId}}','450','500')"><@spring.message code="zq_edit"/></a>
		</#if>
		<#if getCarMaintenanceById??>
        <a class="zq-btn-open" onclick="zq_admin_show('查看','/admin/carMaintenance/open?carMaintenanceId={{d.carMaintenanceId}}')"><@spring.message code="zq_View"/></a>
		</#if>
	</script>
	<!-- 表格操作 end -->
    <script type="text/html" id="state">
        {{# if(d.state == 1){ }}
        报修
        {{# } }}
        {{# if(d.state == 2){ }}
        维修中
        {{# } }}
        {{# if(d.state == 3){ }}
        维修成功
        {{# } }}
        {{# if(d.state == 4){ }}
        维修失败
        {{# } }}

    </script>
</body>
</html>