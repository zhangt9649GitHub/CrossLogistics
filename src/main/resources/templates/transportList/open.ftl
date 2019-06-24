<#include "../public_header.ftl">


	<script type="text/javascript" src="/static/js/transportList/open.js"></script>

</head>
<body>

	<div class="zq-body">
		<div class="zq-main">
            <input type="hidden" value="${goodsId}" name="goodsId">
			<div class="title"><@spring.message code="normalGoods_Logisticsprocess"/></div>
            <hr />
            <div class="content">
                <ul class="layui-timeline">
                    <span class="no-process layui-text"><@spring.message code="normalGoods_Thegoodshavenotarrivedinthewarehouse"/></span>
                </ul>
            </div>
            <hr/>

			<div class="title"><@spring.message code="transportList_Orderdetails"/></div>
			<hr/>
			<div class="content order-info">
				<div class="layui-row">
					<div class="layui-col-md2 receiptContact">
						<label><@spring.message code="abnormalGoods_consignee"/>：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>

					<div class="layui-col-md2 deliveryNumber">
						<label><@spring.message code="normalGoods_Expressordernumber"/>：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>

					<div class="layui-col-md2 zipCode">
						<label><@spring.message code="zq_Zipcode"/>：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>
					<div class="layui-col-md2 addTime">
						<label>创建时间：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>
					<div class="layui-col-md2 createTime">
						<label>支付时间：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>

				</div>
				<div class="layui-row">
					<div class="layui-col-md2 receiptContactMobile">
						<label><@spring.message code="zq_contactdetails"/>：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>

					<div class="layui-col-md2 transportType">
						<label><@spring.message code="transportList_Transfertype"/>：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>
                    <div class="layui-col-md2 paymentStatus">
                        <label><@spring.message code="truckMission_status"/>：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>
					<div class="layui-col-md2 amount">
						<label><@spring.message code="transportList_Transshipmentfee"/>：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>
					<div class="layui-col-md2 gstPrice">
						<label>GST：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>


				</div>
				<div class="layui-row">
					<div class="layui-col-md12 receiptAddress">
						<label><@spring.message code="normalGoods_Shippingaddress"/>：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>
				</div>
			</div>
			<hr/>


			<div class="title"><@spring.message code="transportList_Parcelinformationbag"/></div>
			<hr/>
			<div class="content">
                <#--<table id="idTest" lay-filter="demo"></table>-->

				<div class="table">
					<table class='layui-table'>
						<thead>
					        <tr>
						    	<th>信息单号</th>
						        <th>关联小车信息</th>
						        <th>所属自定义区域</th>
                                <th>入库仓</th>
                                <th>入库仓位</th>
                                <th>出库仓</th>
                                <th>出库仓位</th>
						        <th>状态信息</th>

						    </tr>
				        </thead>
				        <tbody>
				        	<tr>
				        		<td class="bagNumber"></td>
				        		<td class="carNumber"></td>
				        		<td class="singaporeAreaName"></td>
				        		<td class="initWarehouseName"></td>
				        		<td class="initWpNumber"></td>
                                <td class="lastWarehouseName"></td>
                                <td class="lastWpNumber"></td>
				        		<td class="state"></td>

				        	</tr>
				        </tbody>
					</table>
				</div>
			</div>

		</div>
	</div>

</body>
</html>