
<#include "../public_header.ftl">

	<link rel="stylesheet" href="/static/css/truckOrder/open.css">
	<script type="text/javascript" src="/static/js/truckOrder/open.js"></script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAkZGIP6knat07rkfXefRk3IDQ0DU9oagc&callback=initMap"
            async defer></script>

</head>
<body>

	<div class="zq-body">
        <input type="hidden" name="taskOrderId" value="${taskOrderId}">
		<div class="zq-main">
			
			<div class="title">详细信息</div>
			<hr/>
			<div class="content info">
				<div class="layui-row">
					<div class="layui-col-md3 orderNumber">
						<label>订单编号：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>
					<div class="layui-col-md2 totalMoney">
						<label>订单金额：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>
					<div class="layui-col-md2 addMoney">
						<label>订单奖励金额：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>
				</div>
				<div class="layui-row">
					<div class="layui-col-md3 name">
						<label>订单名称：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>
					<div class="layui-col-md2 totalIntegral">
						<label>订单积分：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>
					<div class="layui-col-md2 addIntegral">
						<label>订单奖励积分：</label>
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
					<div class="layui-col-md2 singaporeAreaName">
						<label>订单区域：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>
                    <div class="layui-col-md2 createTime">
                        <label>订单时间：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>

				</div>
			</div>
			<hr/>

			<div class="title huodai">货袋列表</div>
			<hr class="huodai"/>
			<div class="content huodai">
				<div class="table">
					<table>
						<tr class="biaoti">
							<td width="100"></td>
							<td>货袋编号</td>
						</tr>

					</table>
				</div>
			</div>
			<hr class="huodai"/>


			<div class="title">地图显示</div>
			<hr/>
			<div class="content zq-google-map">
                <div id="map"></div>
			</div>
		</div>
	</div>

</body>
</html>