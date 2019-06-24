<#include "../public_header.ftl">

<link rel="stylesheet" href="/static/css/index/homepage.css">
<script type="text/javascript" src="/static/js/index/homepage.js"></script>

</head>
<body class="zq-bodybagcolor">

<!-- 刷新 start -->

<!-- 刷新 end -->


<!-- 内容 start -->

<div class="zq-body">
	<div id="usertype" class="hide">${(Session.user.adminUserTypeId)!''}</div>


	<#-- 三方 -->
	<div class="sanfang hide">
		<div class="layui-row ">

			<div class="layui-col-md4 quantityDispatched">

				<div class="layui-card">
					<div class="layui-card-header">
						已派送订单数量
						<span class="layui-badge layui-bg-blue layuiadmin-badge">总</span>
					</div>
					<div class="layui-card-body layuiadmin-card-list">
						<p class="layuiadmin-big-font"></p>
					</div>

				</div>

			</div>
			<div class="layui-col-md4 orderFulfillment">

				<div class="layui-card">
					<div class="layui-card-header">
						已完成订单数量
						<span class="layui-badge layui-bg-blue layuiadmin-badge">总</span>
					</div>
					<div class="layui-card-body layuiadmin-card-list">
						<p class="layuiadmin-big-font"></p>

					</div>

				</div>

			</div>
			<div class="layui-col-md4 orderException">

				<div class="layui-card">
					<div class="layui-card-header">
						订单异常件数量
						<span class="layui-badge layui-bg-blue layuiadmin-badge">总</span>
					</div>
					<div class="layui-card-body layuiadmin-card-list">
						<p class="layuiadmin-big-font"></p>

					</div>

				</div>

			</div>




			<div class="layui-col-md4 undeliveredQuantity">

				<div class="layui-card">
					<div class="layui-card-header">
						未派送订单数量
						<span class="layui-badge layui-bg-blue layuiadmin-badge">总</span>
					</div>
					<div class="layui-card-body layuiadmin-card-list">
						<p class="layuiadmin-big-font"></p>
					</div>

				</div>

			</div>
			<div class="layui-col-md4 accountsReceivable">

				<div class="layui-card">
					<div class="layui-card-header">
						货到付款应收总金额
						<span class="layui-badge layui-bg-blue layuiadmin-badge">总</span>
					</div>
					<div class="layui-card-body layuiadmin-card-list">
						<p class="layuiadmin-big-font"></p>

					</div>

				</div>

			</div>
			<div class="layui-col-md4 realCollection">

				<div class="layui-card">
					<div class="layui-card-header">
						货到付款实收金额
						<span class="layui-badge layui-bg-blue layuiadmin-badge">总</span>
					</div>
					<div class="layui-card-body layuiadmin-card-list">
						<p class="layuiadmin-big-font"></p>

					</div>

				</div>

			</div>


		</div>
	</div>



	<#-- 后台 -->
	<div class="admin hide">
		<div class="layui-row">

			<div class="layui-col-md4 dailyOrderQuantity">

				<div class="layui-card">
					<div class="layui-card-header">
						订单数
						<span class="layui-badge layui-bg-blue layuiadmin-badge">天</span>
					</div>
					<div class="layui-card-body layuiadmin-card-list">
						<p class="layuiadmin-big-font"></p>

					</div>

				</div>

			</div>
			<div class="layui-col-md4 toSendSuccessfully">

				<div class="layui-card">
					<div class="layui-card-header">
						派送成功订单数
						<span class="layui-badge layui-bg-blue layuiadmin-badge">天</span>
					</div>
					<div class="layui-card-body layuiadmin-card-list">
						<p class="layuiadmin-big-font"></p>

					</div>

				</div>

			</div>
			<div class="layui-col-md4 totalAmountCollectOnDelivery">

				<div class="layui-card">
					<div class="layui-card-header">
						派送件货到付款总金额数（美元单位：$）
						<span class="layui-badge layui-bg-blue layuiadmin-badge">天</span>
					</div>
					<div class="layui-card-body layuiadmin-card-list">
						<p class="layuiadmin-big-font"></p>

					</div>

				</div>

			</div>
            <div class="layui-col-md4 transshipmentRmbCollection">

                <div class="layui-card">
                    <div class="layui-card-header">
                        转运订单人民币收款（单位：￥）
                        <span class="layui-badge layui-bg-blue layuiadmin-badge">总</span>
                    </div>
                    <div class="layui-card-body layuiadmin-card-list">
                        <p class="layuiadmin-big-font">

                            <span></span>
                        </p>

                    </div>

                </div>

            </div>
            <div class="layui-col-md4 withdrawalSpending">

                <div class="layui-card">
                    <div class="layui-card-header">
                        余额提现总支出
                        <span class="layui-badge layui-bg-blue layuiadmin-badge">微信/支付宝</span>
                    </div>
                    <div class="layui-card-body layuiadmin-card-list">
                        <p class="layuiadmin-big-font">
                            ￥
                            <span></span>
                        </p>

                    </div>

                </div>

            </div>
			<div class="layui-col-md4 withdrawalSGDSpending">

				<div class="layui-card">
					<div class="layui-card-header">
						余额提现总支出
						<span class="layui-badge layui-bg-blue layuiadmin-badge">银行卡</span>
					</div>
					<div class="layui-card-body layuiadmin-card-list">
						<p class="layuiadmin-big-font">
							S$
							<span></span>
						</p>

					</div>

				</div>

			</div>





			<#--<div class="layui-col-md4 transshipmentCentCollection">

				<div class="layui-card">
					<div class="layui-card-header">
						转运订单美分收款（单位：分）
						<span class="layui-badge layui-bg-blue layuiadmin-badge">总</span>
					</div>
					<div class="layui-card-body layuiadmin-card-list">
						<p class="layuiadmin-big-font">

							<span></span>
						</p>

					</div>

				</div>

			</div>

			<div class="layui-col-md4 transshipmentSGDCentCollection">

				<div class="layui-card">
					<div class="layui-card-header">
						转运订单新币收款（单位：分）
						<span class="layui-badge layui-bg-blue layuiadmin-badge">总</span>
					</div>
					<div class="layui-card-body layuiadmin-card-list">
						<p class="layuiadmin-big-font">

							<span></span>
						</p>

					</div>

				</div>

			</div>-->


		</div>



	</div>


</div>

<!-- 内容 end -->


</body>
</html>