<#include "../public_header.ftl">
	<script type="text/javascript" src="/static/js/taskOrder/open.js"></script>

</head>
<body>

	<div class="zq-body">
        <input type="hidden" value="${taskOrderId}" name="taskOrderId">
		<div class="zq-main">
			<div class="title">任务信息</div>
			<hr />
			<div class="content task-info">
				<div class="layui-row">
                    <div class="layui-col-md3">
                        <label>订单编号：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>
                    <div class="layui-col-md3 name">
                        <label>订单名称：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>
                    <div class="layui-col-md3 singaporeAreaName">
                        <label>所属区域：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>

				</div>
				<div class="layui-row">
                    <div class="layui-col-md3 type">
                        <label>订单类型：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>
					<div class="layui-col-md3 totalMoney">
                        <label>订单金额：</label>
                        <div class="inline">
                            <span></span>
                        </div>
					</div>
                    <div class="layui-col-md3 addMoney">
                        <label>超时奖励金额：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>
				</div>

                <div class="layui-row">
                    <div class="layui-col-md3 state">
                        <label>订单状态：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>
                    <div class="layui-col-md3 totalIntegral">
                        <label>订单积分：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>
                    <div class="layui-col-md3 addIntegral">
                        <label>超时奖励积分：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-md6 singaporeAreaAtitudeLongitude">
                        <label>经纬度：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>

                </div>



			</div>
			<hr />

            <div class="title">货袋信息</div>
            <hr />
			<div class="content cargobag-info">
				<div class="table">
                    <table class='layui-table'>
                        <thead>
                        <tr>

                            <th>货袋编号</th>
                            <th>长（m）</th>
                            <th>宽（m）</th>
                            <th>高（m）</th>
							<th>重量（kg）</th>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
				</div>
			</div>

            <hr />
            <div class="title">送货路线</div>

		</div>

	</div>

</body>
</html>