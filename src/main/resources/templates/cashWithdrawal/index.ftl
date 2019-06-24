<#include "../public_header.ftl">


	<script type="text/javascript" src="/static/js/cashWithdrawal/index.js"></script>

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

				 			<input type="text" name="addTime" placeholder="<@spring.message code="financialFlow_Pleaseselectthestarttime"/>" autocomplete="off" class="layui-input" id="start-item" />

				 		</div>
				 	</div>
				 	<div class="layui-inline">
				 		<div class="layui-input-inline">

				 			<input type="text" name="endTime" placeholder="<@spring.message code="financialFlow_Pleaseselecttheendtime"/>" autocomplete="off" class="layui-input" id="end-item" />

				 		</div>
				 	</div>

				 	<div class="layui-inline">
				 		<div class="layui-input-inline">

				 			<select name="withdrawRole">
				 				<option value=""><@spring.message code="cashWithdrawal_Pleaseselectthecashwithdrawalrole"/></option>
				 				<option value="货车"><@spring.message code="cashWithdrawal_Truckenduser"/></option>
				 				<option value="小车"><@spring.message code="cashWithdrawal_Carenduser"/></option>
				 			</select>		

				 		</div>
				 	</div>
				 	<div class="layui-inline">
				 		<button class="layui-btn"  lay-submit="" lay-filter="*">
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

	<!-- 表格操作 start -->
	<script type="text/html" id="barDemo">
		<#if updateWithdrawApplication??>
        {{# if(d.withdrawType != 2){ }}
			{{# if(d.withdrawWay == '银行卡'){ }}
			<a class="zq-btn-delete" onclick="cashWithdrawal1('{{d.withdrawId}}')"><@spring.message code="cashWithdrawal_ConfirmWithdraw"/></a>
			{{# } else { }}
			<a class="zq-btn-delete" onclick="cashWithdrawal2('{{d.withdrawId}}')"><@spring.message code="cashWithdrawal_ConfirmWithdraw"/></a>
			{{# } }}
        {{# }else{ }}
        	<a class="zq-btn-delete"><@spring.message code="cashWithdrawal_confirmed"/></a>
        {{# } }}
		</#if>
	</script>
	<!-- 表格操作 end -->

	<#-- 状态 start -->
    <script type="text/html" id="withdrawType">
        {{# if(d.withdrawType == 2){}}
        已完成
        {{# } }}
        {{# if(d.withdrawType == 1){}}
        待处理
        {{# } }}
    </script>

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