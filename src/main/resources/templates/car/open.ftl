<#include "../public_header.ftl">


	<script type="text/javascript" src="/static/js/car/open.js"></script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAkZGIP6knat07rkfXefRk3IDQ0DU9oagc&callback=initMap"
        async defer></script>
</head>
<body>
	<div class="zq-body">
        <input type="hidden" name="carId" value="${carId}">
		<div class="zq-main">
			<div class="title"><@spring.message code="truckMission_details"/></div>
			<hr />
			<div class="content info">
				<div class="layui-row">
					<div class="layui-col-md3 carNumber">
						<label><@spring.message code="normalGoods_Carnumber"/>：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>
					<div class="layui-col-md3 carLockNumber">
						<label><@spring.message code="car_Carlocknumber"/>：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>
					<div class="layui-col-md6 bagNumber">
						<label><@spring.message code="normalGoods_Bagnumber"/>：</label>
						<div class="inline">
							<span></span>
						</div>
					</div>
						
				</div>
                <div class="layui-row">
                    <div class="layui-col-md3 state">
                        <label><@spring.message code="car_Carstatus"/>：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>
                    <div class="layui-col-md3 carLockBoxNumber">
                        <label><@spring.message code="car_Numbertrunklock"/>：</label>
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
                    <div class="layui-col-md3 rallyPointName">
                        <label><@spring.message code="cargoBagList_Affiliationpoint"/>：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>
				</div>
			</div>
			<hr />

			<div class="title"><@spring.message code="cargoBagList_Listofgoods"/></div>
			<hr />
			<div class="content">
                <div class="table">
                    <table id="idTest"></table>

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