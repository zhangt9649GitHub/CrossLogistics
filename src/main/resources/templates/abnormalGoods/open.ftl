<#include "../public_header.ftl">

	<link rel="stylesheet" type="text/css" href="/static/css/abnormalGoods/open.css">
	<script type="text/javascript" src="/static/js/abnormalGoods/open.js"></script>

</head>
<body>

	<div class="zq-body">
        <input type="hidden" value="${goodsId}" name="goodsId">
		<div class="zq-main">
			<div class="title"><@spring.message code="normalGoods_Logisticsprocess"/></div>
			<hr />
			<div class="content">
				<ul class="layui-timeline">
					<span class="no-process layui-text"><@spring.message code="normalGoods_Thegoodshavenotarrivedinthewarehouse"/></span>
				</ul>
			</div>
			<hr/>

			<div class="title"><@spring.message code="normalGoods_Consignorinformation"/></div>
			<hr />
			<div class="content shipper-info">
				<div class="layui-row">
					<div class="layui-col-md2 shipContact">
						<label><@spring.message code="zq_Name"/>：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>
					<div class="layui-col-md2 shipContactMobile">
						<label><@spring.message code="zq_contactdetails"/>：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>
					
					<div class="layui-col-md8 shipAddress">
						<label><@spring.message code="normalGoods_deliveryaddress"/>：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>
					
				</div>
				
			</div>
			<hr/>

			<div class="title"><@spring.message code="normalGoods_recipientinformation"/></div>
			<hr />
			<div class="content receiver-info">
				<div class="layui-row">
					<div class="layui-col-md2 receiptContact">
						<label><@spring.message code="zq_Name"/>：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>
					<div class="layui-col-md2 receiptContactMobile">
						<label><@spring.message code="zq_contactdetails"/>：</label>
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
					
					
				</div>
				<div class="layui-row">
					
					<div class="layui-col-md12 receiptAddress">
						<label><@spring.message code="normalGoods_recipientinformation"/>：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>
					
				</div>
				
			</div>
			<hr/>

			<div class="title"><@spring.message code="normalGoods_Tripartitelogisticsinformation"/></div>
			<hr />
			<div class="content there-logistics-info">
				<div class="layui-row">
					<div class="layui-col-md2 shipper">
						<label><@spring.message code="normalGoods_Carrier"/>：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>
					<div class="layui-col-md10 tripartiteNumber">
						<label>货物单号：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>
				
					
				</div>
				
			</div>
			<hr/>
			
			<div class="title"><@spring.message code="normalGoods_Cargoinformation"/></div>
			<hr />
			<div class="content goods-info">
				<div class="layui-row">
                    <div class="layui-col-md3 deliveryNumber">
                        <label><@spring.message code="normalGoods_Expressordernumber"/>：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>
                    <div class="layui-col-md3 intoWarehouseName">
                        <label><@spring.message code="normalGoods_Storagewarehouse"/>：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>
                    <div class="layui-col-md3 intoWpNumber">
                        <label><@spring.message code="normalGoods_Warehousingposition"/>：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>


                    <div class="layui-col-md3 isSpecialGoods">
                        <label><@spring.message code="normalGoods_Specialitems"/>：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>

					
				</div>
				<div class="layui-row">
                    <div class="layui-col-md3 bagNumber">
                        <label><@spring.message code="normalGoods_Ownbag"/>：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>
                    <div class="layui-col-md3 outWarehouseName">
                        <label><@spring.message code="normalGoods_Outofwarehouse"/>：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>
                    <div class="layui-col-md3 outWpNumber">
                        <label><@spring.message code="normalGoods_Outboundposition"/>：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>

					<div class="layui-col-md3 goodType">
						<label><@spring.message code="normalGoods_Typeofcargo"/>：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>

				</div>
				<div class="layui-row">
                    <div class="layui-col-md3 actualLong">
                        <label><@spring.message code="zq_long"/>：</label>
                        <div class="inline">
                            <span></span>cm
                        </div>
                    </div>
                    <div class="layui-col-md3 actualWidth">
                        <label><@spring.message code="zq_width"/>：</label>
                        <div class="inline">
                            <span></span>cm
                        </div>
                    </div>
                    <div class="layui-col-md3 actualHeight">
                        <label><@spring.message code="zq_high"/>：</label>
                        <div class="inline">
                            <span></span>cm
                        </div>
                    </div>
                    <div class="layui-col-md3 actualWeight">
                        <label><@spring.message code="zq_weight"/>：</label>
                        <div class="inline">
                            <span></span>KG
                        </div>
                    </div>
				</div>
				<div class="layui-row ">
                    <div class="layui-col-md12 status">
                        <label><@spring.message code="normalGoods_Cargostate"/>：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>


				</div>
					
			</div>
			<hr/>





			<div class="title"><@spring.message code="normalGoods_Nodeoperatorrecord"/></div>
			<hr />
			<div class="content">
				<div class="table">
                    <table id="idTest" lay-filter="demo"></table>

				</div>
			</div>
		</div>

	</div>

</body>
</html>