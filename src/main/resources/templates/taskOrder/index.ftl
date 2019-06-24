
<#include "../public_header.ftl">

	<script type="text/javascript" src="/static/js/taskOrder/index.js"></script>

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

		<!-- 查询 end -->

		<!-- 操作按钮 start-->
		<div class="zq-actionbuttons">
			<button class="layui-btn"  onclick="zq_query_show('查询','#zq-query','400','500')"><i class="layui-icon">&#xe615;</i>查询</button>
		</div>
		<!-- 操作按钮 end-->

		<!-- 数据表格 start -->

		<table id="idTest" lay-filter="demo"></table>



		<!-- 数据表格 end -->

	</div>

	<!-- 内容 end -->

	<!-- 表格操作 start -->
	<script type="text/html" id="barDemo">
        <#if taskOrderDetail??>
        <a class="zq-btn-open" onclick="zq_admin_show('查看','/admin/taskOrder/open?taskOrderId={{d.taskOrderId}}')">查看</a>
        </#if>
	</script>
	<!-- 表格操作 end -->
	<#-- 查询页 -->
    <div id="zq-query" class="zq-query-body" style="display: none">
        <form class="layui-form  layui-form-pane zq-query-form">
            <div class="layui-form-item">
                <label  for="name" class="layui-form-label">
                    编号、名称
                </label>
                <div class="layui-input-inline">
                    <input type="text" name="search"  autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label  for="name" class="layui-form-label">
                    订单区域
                </label>
                <div class="layui-input-inline" lay-filter="singaporeAreaId">
                    <select name="singaporeAreaId" id="singaporeAreaId">
                        <option value="0">请选择</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label  for="name" class="layui-form-label">
                   订单类型
                </label>
                <div class="layui-input-inline" >
                    <select name="type">
                        <option value=""></option>
                        <option value="小车">小车</option>
                        <option value="货车">货车</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label  for="name" class="layui-form-label">
                    订单状态
                </label>
                <div class="layui-input-inline" >
                    <select name="state">
                        <option value="0">请选择</option>
                        <option value="1">已创建</option>
                        <option value="2">已预约</option>
                        <option value="3">进行中</option>
                        <option value="4">已确认</option>
                        <option value="5">已完成</option>
                        <option value="6">已取消</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label   class="layui-form-label">
                    开始时间
                </label>
                <div class="layui-input-inline">
                    <input type="text" name="startTime" autocomplete="off" class="layui-input" id="startTime">
                </div>
            </div>
            <div class="layui-form-item">
                <label   class="layui-form-label">
                    结束时间
                </label>
                <div class="layui-input-inline">
                    <input type="text" name="endTime" autocomplete="off" class="layui-input" id="endTime">
                </div>
            </div>

            <div class="layui-form-item">
                <button class="layui-btn  layui-btn-normal" lay-submit="" lay-filter="query">保存</button>
            </div>

        </form>
	</div>

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

    </script>
	<#-- 特殊货物 -->
</body>
</html>