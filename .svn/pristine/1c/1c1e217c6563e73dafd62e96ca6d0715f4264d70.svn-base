<#include "../public_header.ftl">


	<script type="text/javascript" src="/static/js/truck/open.js"></script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAkZGIP6knat07rkfXefRk3IDQ0DU9oagc&callback=initMap"
            async defer></script>

</head>
<body>

	<div class="zq-body">
		<div class="zq-main">
            <input type="hidden" value="${truckId}" name="truckId">
			<div class="title"><@spring.message code="truckMission_details"/></div>
			<hr />
			<div class="content info">
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
					<div class="layui-col-md2 state">
						<label><@spring.message code="truck_Usagestatus"/>：</label>
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
					<div class="layui-col-md2 models">
						<label><@spring.message code="truck_Truckmodels"/>：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>
					<div class="layui-col-md2 load">
						<label><@spring.message code="normalGoods_Truckload"/>：</label>
						<div class="inline">
							<span></span>t
						</div>
					</div>
					<div class="layui-col-md2 orderNumber">
						<label><@spring.message code="truck_ordernumber"/>：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>
				</div>
				<div class="layui-row">
					<div class="layui-col-md12 singaporeAreaName">
						<label><@spring.message code="normalGoods_Associatedarea"/>：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>
				</div>
			</div>
			<hr/>

			<div class="title"><@spring.message code="truck_Baginformation"/></div>
			<hr />
			<div class="content">
				<div class="table">
                    <table id="idTest" lay-filter="demo"></table>

				</div>
			</div>
			<hr/>

			<div class="title"><@spring.message code="normalGoods_Cargoinformation"/></div>
			<hr />
			<div class="content">
				<div class="table">
                    <table id="idTest1" lay-filter="demo"></table>

				</div>
			</div>
			<hr/>


			<div class="title"><@spring.message code="car_Currentlocationinformation"/></div>
			<hr />
			<div class="content zq-google-map">
				<div id="map"></div>
			</div>

		</div>
	</div>
	<#-- 状态 -->
    <script type="text/html" id="statusID">
        {{# if(d.status == 1){ }}
        正常
        {{# } }}
        {{# if(d.status == 2){ }}
        异常
        {{# } }}
    </script>
	<#-- 特殊货物 -->
    <script type="text/html" id="isSpecialGoodsID">
        {{# if(d.isSpecialGoods == 1){ }}
        是
        {{# } else if(d.isSpecialGoods == 2){ }}
        否
        {{# } }}
    </script>
	<#-- 货袋状态 -->
    <script type="text/html" id="state">

        {{#  if(d.state == 1){ }}
        损毁
        {{#  } }}

        {{#  if(d.state == 2){ }}
        打包
        {{#  } }}
        {{#  if(d.state == 3){ }}
        入库
        {{#  } }}
        {{#  if(d.state == 4){ }}
        货袋出库
        {{#  } }}
        {{#  if(d.state == 5){ }}
        货袋送出
        {{#  } }}
        {{#  if(d.state == 6){ }}
        大货车运载中
        {{#  } }}
        {{#  if(d.state == 7){ }}
        小车运载中
        {{#  } }}
        {{#  if(d.state == 8){ }}
        送货完成
        {{#  } }}
        {{#  if(d.state == 9){ }}
        送货完但内有异常件
        {{#  } }}
        {{#  if(d.state == 10){ }}
		包裹直接配送模式
        {{#  } }}
        <#--{{#  if(d.state == 11){ }}-->
        <#--异常件送回仓库-->
        <#--{{#  } }}-->



    </script>
</body>
</html>