<#include "../public_header.ftl">
	<link rel="stylesheet" type="text/css" href="/static/css/userList/open.css">
	<script type="text/javascript" src="/static/js/userList/open.js"></script>

</head>
<body>

	<div class="zq-body">
        <input type="hidden" value="${appUserId}" name="appUserId">
		<div class="section-main">
			<div class="aside-left">
				<div>
					<!-- 头像 -->
					<img src="" name="headPic">
					<div class="row">
						<div class="flex">
							<div class="color-1 font" name="userName"></div>
							<#--<div class="color-2 center">出生日期</div>-->
							<#--<div class="color-2 center">账户余额</div>-->
							<#--<div class="color-2 center">账户积分</div>-->
							<#--<div class="color-2 center">完成订单数</div>-->
						</div>
						<div class="flex">
							<div class="color-2" name="number" style="width: 200px;flex:none"><@spring.message code="userList_Numbering"/>：<span></span></div>
							<div style="width: 200px;flex:none" name="actualName">真实姓名：<span></span></div>
							<div name="houseNumber">门牌号：<span></span> </div>
							<#--<div class="color-1 center">积：<span></span></div>-->
							<#--<div class="color-1 center">444</div>-->
						</div>

						<hr>
						<div class="flex">
							
							<div name="mobile">
								<label><@spring.message code="zq_contactdetails"/>：</label>
								<span></span>
							</div>
							<div name="carApproveStatus">
								<label><@spring.message code="userList_Carcertificationstatus"/>：</label>
								<span></span>
							</div>
                            <div name="sex">
                                <label><@spring.message code="zq_sex"/>：</label>
                                <span></span>
                            </div>
						</div>
                        <div class="flex">

                            <div name="zipCode">
                                <label><@spring.message code="zq_Zipcode"/>：</label>
                                <span></span>
                            </div>
                            <div name="truckApproveStatus">
                                <label><@spring.message code="userList_Truckcertificationstatus"/>：</label>
                                <span></span>
                            </div>
                            <div name="age">
                                <label><@spring.message code="zq_age"/>：</label>
                                <span></span>
                            </div>
                        </div>
						<div class="flex">
                            <div name="email">
                                <label><@spring.message code="zq_mailbox"/>：</label>
                                <span></span>
                            </div>
							<div name="address">
								<label><@spring.message code="zq_address"/>：</label>
								<span></span>
							</div>


						</div>

					</div>
				</div>
				
			</div>
			<#--<div class="aside-right">-->
				<#---->
			<#--</div>-->
		</div>

        <div class="layui-tab layui-tab-card open-tab">
            <ul class="layui-tab-title">
                <li class="layui-this"><@spring.message code="userList_Car"/></li>
                <li><@spring.message code="userList_truck"/></li>

            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">

                    <div class="info car">
                        <div class="commissionAmount">
                            <label><@spring.message code="userList_AccountBalance"/>：</label><span></span>
                        </div>
                        <div class="integral">
                            <label><@spring.message code="userList_Accountcredit"/>：</label><span></span>
                        </div>
                        <div class="completeOrderNum">
                            <label><@spring.message code="userList_Numberofcompletedorders"/>：</label><span></span>
                        </div>
                    </div>
                    <div class="layui-tab layui-tab-card">
                        <ul class="layui-tab-title">
                            <li class="layui-this"><@spring.message code="userList_Orderrecord"/></li>
                            <li><@spring.message code="userList_Withdrawalsrecord"/></li>
                            <li><@spring.message code="userList_PointsHistory"/></li>
                        </ul>
                        <div class="layui-tab-content">
                            <div class="layui-tab-item layui-show">
                                <table id="car-idTest1" lay-filter="car-demo1"></table>
                            </div>
                            <div class="layui-tab-item">
                                <table id="car-idTest2" ></table>
                            </div>
                            <div class="layui-tab-item">
                                <table id="car-idTest3" ></table>
                            </div>
                        </div>
                    </div>



                </div>
                <div class="layui-tab-item">
                    <div class="info truck">
                        <div class="commissionAmount">
                            <label><@spring.message code="userList_AccountBalance"/>：</label><span></span>
                        </div>
                        <div class="integral">
                            <label><@spring.message code="userList_Accountcredit"/>：</label><span></span>
                        </div>
                        <div class="completeOrderNum">
                            <label><@spring.message code="userList_Numberofcompletedorders"/>：</label><span></span>
                        </div>
                    </div>
                    <div class="layui-tab layui-tab-card">
                        <ul class="layui-tab-title">
                            <li class="layui-this"><@spring.message code="userList_Orderrecord"/></li>
                            <li><@spring.message code="userList_Withdrawalsrecord"/></li>
                            <li><@spring.message code="userList_PointsHistory"/></li>
                        </ul>
                        <div class="layui-tab-content">
                            <div class="layui-tab-item layui-show">
                                <table id="truck-idTest1" ></table>
                            </div>
                            <div class="layui-tab-item">
                                <table id="truck-idTest2" ></table>
                            </div>
                            <div class="layui-tab-item">
                                <table id="truck-idTest3" ></table>
                            </div>
                        </div>
                    </div>
				</div>
            </div>
        </div>


		
	</div>

    <#-- 订单状态 -->
    <script type="text/html" id="idTest1">
        {{# if(d.state == 1){}}
        已创建
        {{# } }}
        {{# if(d.state == 2){}}
        已预约
        {{# } }}
        {{# if(d.state == 3){}}
        进行中
        {{# } }}
        {{# if(d.state == 4){}}
        已确认
        {{# } }}
        {{# if(d.state == 5){}}
        已完成
        {{# } }}
        {{# if(d.state == 6){}}
        已取消
        {{# } }}

    </script>


</body>
</html>