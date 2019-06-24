<#include "../public_header.ftl">


	<script type="text/javascript" src="/static/js/transportList/index.js"></script>

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
            <button class="layui-btn"  onclick="zq_query_show('<@spring.message code="zq_Inquire"/>','#zq-query','480','550')"><i class="layui-icon">&#xe615;</i><@spring.message code="zq_Inquire"/></button>
        </div>
		<!-- 操作按钮 end-->

		<!-- 数据表格 start -->

		<table id="idTest" lay-filter="demo"></table>

		<!-- 数据表格 end -->

	</div>

	<!-- 内容 end -->

	<!-- 表格操作 start -->
	<script type="text/html" id="barDemo">
        <#if getTransshipmentGoodsDetails??>
        <a class="zq-btn-open" onclick="zq_admin_show('<@spring.message code="zq_View"/>','/admin/transportList/open?goodsId={{d.goodsId}}')"><@spring.message code="zq_View"/></a>
        </#if>

	</script>
	<!-- 表格操作 end -->

	<#-- 查询页 -->
    <div id="zq-query" class="zq-query-body" style="display: none">
        <form class="layui-form  layui-form-pane zq-query-form">
            <div class="layui-form-item">
                <label  for="name" class="layui-form-label">
                    <@spring.message code="transportList_Startingtime"/>
                </label>
                <div class="layui-input-inline">
                    <input type="text" name="addTime" placeholder="<@spring.message code="transportList_Orderstarttime"/>" autocomplete="off" class="layui-input" id="start-item" />
                </div>
            </div>
            <div class="layui-form-item">
                <label  for="name" class="layui-form-label">
                    <@spring.message code="transportList_EndTime"/>
                </label>
                <div class="layui-input-inline">
                    <input type="text" name="endTime" placeholder="<@spring.message code="transportList_Orderendtime"/>" autocomplete="off" class="layui-input" id="end-item" />
                </div>
            </div>
            <div class="layui-form-item">
                <label  for="name" class="layui-form-label">
                    <@spring.message code="normalGoods_Expressordernumber"/>
                </label>
                <div class="layui-input-inline">
                    <input type="text" name="deliveryNumber" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label  for="name" class="layui-form-label">
                    <@spring.message code="abnormalGoods_consignee"/>
                </label>
                <div class="layui-input-inline" >
                    <input type="text" name="receiptContact" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label  for="name" class="layui-form-label">
                   <@spring.message code="truckMission_status"/>
                </label>
                <div class="layui-input-inline" >
                    <select name="paymentStatus">
                        <option value=""><@spring.message code="zq_pleasechoose"/></option>
                        <option value="待支付"> <@spring.message code="transportList_Tobepaid"/></option>
                        <option value="已支付"><@spring.message code="transportList_Paid"/></option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label  for="name" class="layui-form-label">
                    <@spring.message code="transportList_Transfertype"/>
                </label>
                <div class="layui-input-inline" >
                    <select name="transportType">
                        <option value=""><@spring.message code="zq_pleasechoose"/></option>
                        <option value="组合转运"><@spring.message code="transportList_Combinedtransport"/></option>
                        <option value="普通转运"><@spring.message code="transportList_Ordinarytransport"/></option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <button class="layui-btn  layui-btn-normal" lay-submit="" lay-filter="query"><@spring.message code="zq_Inquire"/></button>
            </div>

        </form>
    </div>
</body>
</html>