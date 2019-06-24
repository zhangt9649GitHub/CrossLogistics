<#include "../public_header.ftl">

	<link rel="stylesheet" href="/static/css/userAddressList/index.css">
	<script type="text/javascript" src="/static/js/userAddressList/index.js"></script>

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

				 	<div class="layui-inline search">
				 		<div class="layui-input-inline">
				 			<input type="text" name="search" placeholder="<@spring.message code="userAddressList_Namemobilephonenumberzipcode"/>" autocomplete="off" class="layui-input" />
				 		</div>
				 	</div>

				 	<div class="layui-inline">
				 		<div class="layui-input-inline" style="width: 300px;">

				 			<select name="singaporeAreaIds" xm-select="select1" id="singaporeAreaIds">
				 				<option value=""><@spring.message code="userAddressList_Pleaseselectregion"/></option>

				 			</select>		

				 		</div>
				 	</div>



				 	<div class="layui-inline">
				 		<button class="layui-btn" lay-submit="" lay-filter="add">
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
	<script type="text/html" id="addressType">

		{{# if(d.addressType == 1){ }}
		普通
		{{# } }}
		{{# if(d.addressType == 2){ }}
		低屋
		{{# } }}
	</script>
</body>
</html>