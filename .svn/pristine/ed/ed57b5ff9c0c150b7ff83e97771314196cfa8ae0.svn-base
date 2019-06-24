
<#include "../public_header.ftl">

	<script type="text/javascript" src="/static/js/codcargo/index.js"></script>

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
            <#if importCODExcel??>
			<button class="layui-btn layui-btn-normal " id="import"><@spring.message code="zq_Import"/></button>
            </#if>
			<button class="layui-btn"  onclick="zq_query_show('<@spring.message code="zq_Inquire"/>','#zq-query','500','400')"><i class="layui-icon">&#xe615;</i><@spring.message code="zq_Inquire"/></button>

            <#if insertCodGoods??>
                <button class="layui-btn" onclick="zq_admin_show('新增','/admin/codcargo/add')"><i class="layui-icon">&#xe608;</i>新增</button>
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

        <#if getCodGoodsById??>
            <a class="zq-btn-open" onclick="zq_admin_show('查看','/admin/codcargo/open?goodsId={{d.goodsId}}')">查看</a>
        </#if>
        <#if deleteCodGoodsById??>
            <a class="zq-btn-delete"  onclick="delthis('{{d.goodsId}}')"><@spring.message code="zq_delete"/></a>
        </#if>

	</script>
	<!-- 表格操作 end -->
	<#-- 查询页 -->
    <div id="zq-query" class="zq-query-body" style="display: none">
        <form class="layui-form  layui-form-pane zq-query-form">
            <div class="layui-form-item">
                <label  for="name" class="layui-form-label">
                    开始时间
                </label>
                <div class="layui-input-inline">
                    <input type="text" name="addTime"  autocomplete="off" class="layui-input" id="addTime" />
                </div>
            </div>
            <div class="layui-form-item">
                <label  for="name" class="layui-form-label">
                   结束时间
                </label>
                <div class="layui-input-inline">
                    <input type="text" name="endTime"  autocomplete="off" class="layui-input" id="endTime" />
                </div>
            </div>
            <div class="layui-form-item">
                <label  for="name" class="layui-form-label">
                   快递单号
                </label>
                <div class="layui-input-inline">
                    <input type="text" name="deliveryNumber" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label  for="name" class="layui-form-label">
                    货物单号
                </label>
                <div class="layui-input-inline">
                    <input type="text" name="tripartiteNumber" autocomplete="off" class="layui-input">
                </div>
            </div>


            <div class="layui-form-item">
                <button class="layui-btn  layui-btn-normal" lay-submit="" lay-filter="query"><@spring.message code="zq_Inquire"/></button>
            </div>

        </form>
	</div>


</body>
</html>