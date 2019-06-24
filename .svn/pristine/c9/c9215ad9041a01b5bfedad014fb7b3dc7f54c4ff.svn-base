<#include "../public_header.ftl">


	<script type="text/javascript" src="/static/js/financialFlow/index.js"></script>

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
		<div class="zq-query">
			<form class="layui-form">
				 <div class="layui-form-item">


				 	<div class="layui-inline">
				 		<div class="layui-input-inline">

				 			<input type="text" name="createTime" placeholder="<@spring.message code="financialFlow_Pleaseselectthestarttime"/>" autocomplete="off" class="layui-input" id="start-item" />

				 		</div>
				 	</div>
				 	<div class="layui-inline">
				 		<div class="layui-input-inline">

				 			<input type="text" name="endTime" placeholder="<@spring.message code="financialFlow_Pleaseselecttheendtime"/>" autocomplete="off" class="layui-input" id="end-item" />

				 		</div>
				 	</div>

				 	<div class="layui-inline">
				 		<div class="layui-input-inline">

				 			<select name="financeMoneyType">
				 				<option value=""><@spring.message code="financialFlow_Pleaseselectapurpose"/></option>
                                <option value="余额提现"><@spring.message code="financialFlow_Balancewithdrawal"/></option>
                                <option value="工资发放"><@spring.message code="financialFlow_salarygiving"/></option>
                                <option value="订单支付"><@spring.message code="financialFlow_Orderpayment"/></option>
							</select>

				 		</div>
				 	</div>

				 	<div class="layui-inline">
				 		<button class="layui-btn" lay-submit="" lay-filter="*">
				 			<i class="layui-icon">&#xe615;</i>
				 		</button>

				 	</div>

				 	
				 </div>
			</form>
			
		</div>
		<!-- 查询 end -->

		<!-- 操作按钮 start-->
		
		<!-- 操作按钮 end-->

		<!-- 数据表格 start -->

		<table id="idTest" lay-filter="demo"></table>


		<!-- 数据表格 end -->

	</div>

	<!-- 内容 end -->

	<script type="text/html" id="unit">

		{{# if(d.unit == 1){ }}

		人民币
		{{# } }}
		{{# if(d.unit == 2){ }}

		美元
		{{# } }}
		{{# if(d.unit == 3){ }}

		新币
		{{# } }}
		<#--{{# if(d.unit == 4){ }}-->

		<#--美元-->
		<#--{{# } }}-->
		<#--{{# if(d.unit == 5){ }}-->

		<#--新币(元)-->

		<#--{{# } }}-->
	</script>
</body>
</html>