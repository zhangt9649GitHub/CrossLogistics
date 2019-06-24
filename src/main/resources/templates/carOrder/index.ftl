<#include "../public_header.ftl">


	<script type="text/javascript" src="/static/js/carOrder/index.js"></script>

</head>
<body>

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
				 			<input type="text" name="orderNumber" placeholder="<@spring.message code="carOrder_Ordernumber"/>" autocomplete="off" class="layui-input" />
				 		</div>
				 	</div>

				 	<div class="layui-inline">
				 		<div class="layui-input-inline">
				 			<input type="text" name="orderTime" placeholder="<@spring.message code="transportList_Ordertime"/>" autocomplete="off" class="layui-input" id="order-time"/>
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

				 			<select name="taskOrderStatus">
                                <option value=""></option>
                                <option value="1">已创建</option>
                                <option value="2">已预约</option>
                                <option value="3">进行中</option>
                                <#--<option value="4">已确认</option>-->
                                <option value="5">已完成</option>
                                <#--<option value="6">已取消</option>-->
                                <option value="7">已结算</option>
				 			</select>		

				 		</div>
				 	</div>



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
		<#--<div class="zq-actionbuttons">-->
			<#--<button class="layui-btn" onclick="zq_admin_show('添加订单信息','/admin/carOrder/add')"><i class="layui-icon">&#xe608;</i>新增</button>-->
		<#--</div>-->
		<!-- 操作按钮 end-->

		<!-- 数据表格 start -->

		<table id="idTest" lay-filter="demo"></table>



		<!-- 数据表格 end -->

	</div>

	<!-- 内容 end -->

	<!-- 表格操作 start -->
	<script type="text/html" id="barDemo">
        <#--<a class="zq-btn-edit" onclick="zq_admin_show('编辑订单信息','/admin/carOrder/edit')">编辑</a>-->
		<#if appTaskOrderGoods??>
        <a class="zq-btn-open" onclick="zq_admin_show('<@spring.message code="zq_View"/>','/admin/carOrder/open?taskOrderId={{d.taskOrderId}}')"><@spring.message code="zq_View"/></a>
		</#if>
		<#if selectAppUserByCarOrderId??>
        <a class="zq-btn-assign" onclick="zq_admin_show('<@spring.message code="carOrder_Designee"/>','/admin/carOrder/assign?taskOrderId={{d.taskOrderId}}&date={{d.createTime}}','400','500')"><@spring.message code="carOrder_Designee"/></a>
		</#if>
		<#if selectCarByCarOrder??>
        <a class="zq-btn-assignCar" onclick="zq_admin_show('<@spring.message code="carOrder_Assignacar"/>','/admin/carOrder/assignCar?taskOrderId={{d.taskOrderId}}&date={{d.createTime}}','400','500')"><@spring.message code="carOrder_Assignacar"/></a>
		</#if>
        <#--<a class="zq-btn-delete currency"  onclick="layer.confirm('是否删除？',{btn: ['确定', '取消']},function(){layer.msg('删除成功！', {icon: 1})})">删除</a>-->
	</script>
	<!-- 表格操作 end -->
	<#-- 状态 -->
    <script type="text/html" id="stateID">
        {{# if(d.state == 1){ }}
        已创建
        {{# } }}
        {{# if(d.state == 2){ }}
        已预约
        {{# } }}
        {{# if(d.state == 3){ }}
        进行中
        {{# } }}
        {{# if(d.state == 4){ }}
        已确认
        {{# } }}
        {{# if(d.state == 5){ }}
        已完成
        {{# } }}
        {{# if(d.state == 6){ }}
        已取消
        {{# } }}
        {{# if(d.state == 7){ }}
        已结算
        {{# } }}

    </script>

    <script type="text/html" id="grabOrderStatus">
        {{# if(d.grabOrderStatus == 0){ }}
            未抢
            {{# } }}
        {{# if(d.grabOrderStatus == 1){ }}
            已抢
            {{# } }}
	</script>
</body>
</html>