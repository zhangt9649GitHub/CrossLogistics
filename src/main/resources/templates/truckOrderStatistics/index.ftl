
<#include "../public_header.ftl">

	<script type="text/javascript" src="/static/js/truckOrderStatistics/index.js"></script>

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


		<!-- 操作按钮 start-->
		<div class="zq-actionbuttons">
            <#if getTruckStatisticsFile??>
			<button class="layui-btn layui-btn-normal" id="export">导出</button>
            </#if>
			<button class="layui-btn"  onclick="zq_query_show('<@spring.message code="zq_Inquire"/>','#zq-query','500','400')"><i class="layui-icon">&#xe615;</i><@spring.message code="zq_Inquire"/></button>
        </div>
		<!-- 操作按钮 end-->

		<!-- 数据表格 start -->

		<table id="idTest" lay-filter="demo"></table>



		<!-- 数据表格 end -->

	</div>

	<!-- 内容 end -->
    <!-- 查询 start -->
    <div class="zq-query-body" style="display: none" id="zq-query">
        <form class="layui-form  layui-form-pane zq-query-form">
            <div class="layui-form-item">
                <label  for="name" class="layui-form-label">
                    开始时间
                </label>
                <div class="layui-input-inline">
                    <input type="text" name="startDate" id="startDate" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label  for="name" class="layui-form-label">
                    结束时间
                </label>
                <div class="layui-input-inline">
                    <input type="text" name="endDate" id="endDate" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label  for="name" class="layui-form-label">
                    司机姓名
                </label>
                <div class="layui-input-inline">
                    <input type="text" name="userTrueName" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="*"><@spring.message code="zq_Inquire"/></button>
                <button class="export" lay-submit="" lay-filter="export" style="display: none"></button>
            </div>

        </form>
    </div>
    <!-- 查询 end -->

	<!-- 表格操作 start -->

	<!-- 表格操作 end -->



	<#-- 状态 -->
    <script type="text/html" id="isReceiveGoods">
        {{# if(d.isReceiveGoods == 1){ }}
			已接收
		{{# } }}
        {{# if(d.isReceiveGoods == 2){ }}
        未接收
        {{# } }}

    </script>
	<#-- 特殊货物 -->
    <script type="text/html" id="isArrivalPay">
        {{# if(d.isArrivalPay == 1){ }}
        否
        {{# } else if(d.isArrivalPay == 2){ }}
       是
        {{# } }}
    </script>
</body>
</html>