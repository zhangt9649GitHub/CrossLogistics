<#include "../public_header.ftl">

	<link rel="stylesheet" type="text/css" href="/static/css/cargoBagList/open.css">
	<script type="text/javascript" src="/static/js/cargoBagList/open.js"></script>

</head>
<body>

	<div class="zq-body">
		
		<div class="zq-main">
			<#-- 货袋id -->
            <input type="hidden" value="${bagId}" name="bagId">

			<div class="title"><@spring.message code="normalGoods_Logisticsprocess"/></div>
			<hr />
			<div class="content">
                <ul class="layui-timeline">
                    <span class="no-process layui-text"><@spring.message code="normalGoods_Thegoodshavenotarrivedinthewarehouse"/></span>
                </ul>
			</div>
			<hr/>

			<div class="title"><@spring.message code="cargoBagList_Baginformation"/></div>
			<hr />
			<div class="content cargobag-info">
				<div class="layui-row">
					<div class="layui-col-md3 bagNumber">
						<label><@spring.message code="normalGoods_Bagnumber"/>：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>
					<div class="layui-col-md2 state">
						<label><@spring.message code="cargoBagList_statusinformation"/>：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>
					<div class="layui-col-md7 lastWarehouseName">
						<label><@spring.message code="cargoBagList_Ownwarehouse"/>：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>
					
				</div>
				<div class="layui-row">
					<div class="layui-col-md3 singaporeAreaName">
						<label><@spring.message code="cargoBagList_Area"/>：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>
                    <div class="layui-col-md2 rallyPointName">
                        <label><@spring.message code="cargoBagList_Affiliationpoint"/>：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>
                    <div class="layui-col-md7 lastWpNumber">
                        <label><@spring.message code="cargoBagList_Ownposition"/>：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>
				
					
				</div>
				
			</div>
			<hr/>
			<div class="title"><@spring.message code="normalGoods_Associatedtruckinformation"/></div>
			<hr />
			<div class="content truck-info">
				<div class="layui-row">
					<div class="layui-col-md2 name">
						<label><@spring.message code="normalGoods_Driversname"/>：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>
                    <div class="layui-col-md2 mobile">
                        <label><@spring.message code="zq_contactdetails"/>：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>
                    <div class="layui-col-md2 singaporeAreaName">
                        <label><@spring.message code="normalGoods_Associatedarea"/>：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>
					
				</div>
				<div class="layui-row">
                    <div class="layui-col-md2 licensePlate">
                        <label><@spring.message code="normalGoods_Trucklicense"/>：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>
                    <div class="layui-col-md2 load">
                        <label><@spring.message code="normalGoods_Truckload"/>：</label>
                        <div class="inline">
                            <span></span>KG
                        </div>
                    </div>
                    <div class="layui-col-md3 state">
                        <label><@spring.message code="normalGoods_statusofuse"/>：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>
				</div>
			</div>
			<hr/>


			<div class="title"><@spring.message code="normalGoods_Relatedcarinformation"/></div>
			<hr />
			<div class="content car-info">
				<div class="layui-row">
					<div class="layui-col-md3 carNumber">
						<label><@spring.message code="normalGoods_Carnumber"/>：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>
                    <div class="layui-col-md2 state">
                        <label><@spring.message code="normalGoods_statusofuse"/>：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>
				
					
				</div>
				<div class="layui-row">

					<div class="layui-col-md3 singaporeAreaName">
						<label><@spring.message code="cargoBagList_Area"/>：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>
                    <div class="layui-col-md2 rallyPointName">
                        <label><@spring.message code="cargoBagList_Affiliationpoint"/>：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>
				
					
				</div>
				
			</div>
			<hr/>

			<div class="title"><@spring.message code="cargoBagList_Listofgoods"/></div>
			<hr />
            <div class="content">
                <div class="table">
                   <table id="idTest"></table>

                </div>
            </div>




		</div>

	</div>
	<!-- 表格操作 start -->
	<script type="text/html" id="barDemo">
		<#if updateGoodsPackingCondition??>
			<a class="zq-btn-delete currency"  onclick="delthis('{{d.goodsId}}')">移除</a>
		</#if>
	</script>

    <script type="text/html" id="isSpecialGoods">
        {{# if(d.isSpecialGoods == 1){ }}
        是
        {{# } else if(d.isSpecialGoods == 2){ }}
        否
        {{# } }}
	</script>

    <script type="text/html" id="status">
        {{# if(d.status == 1){ }}
       	正常
        {{# } else if(d.status == 2){ }}
        异常
        {{# } }}
    </script>

</body>
</html>