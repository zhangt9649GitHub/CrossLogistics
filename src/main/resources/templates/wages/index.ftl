<#include "../public_header.ftl">

	<script type="text/javascript" src="/static/js/wages/index.js"></script>

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

				 			<input type="text" name="addTime" placeholder="请选择开始时间" autocomplete="off" class="layui-input" id="start-item" />

				 		</div>
				 	</div>
				 	<div class="layui-inline">
				 		<div class="layui-input-inline">

				 			<input type="text" name="endTime" placeholder="请选择结束时间" autocomplete="off" class="layui-input" id="end-item" />

				 		</div>
				 	</div>

				 	<div class="layui-inline">
				 		<div class="layui-input-inline">

				 			<input type="text" name="staffName" placeholder="请输入员工名称" autocomplete="off" class="layui-input"/>
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
        <div class="zq-actionbuttons">
			<#if insertPayGive??>
            <button class="layui-btn" onclick="zq_admin_show('添加','/admin/wages/add','400','500')"><i class="layui-icon">&#xe608;</i>新增</button>
			</#if>
        </div>
        <!-- 操作按钮 end-->

		<!-- 数据表格 start -->

		<table id="idTest" lay-filter="demo"></table>

		<!-- 数据表格 end -->

	</div>

	<!-- 内容 end -->

	<!-- 表格操作 start -->
	<script type="text/html" id="barDemo">
		<#if updatePayGive??>
        <a class="zq-btn-edit" onclick="zq_admin_show('编辑','/admin/wages/edit?payGiveId={{d.payGiveId}}','400','500')">编辑</a>
		</#if>
		<#if deletePayGiveById??>
        <a class="zq-btn-delete" onclick="delthis({{d.payGiveId}})">删除</a>
		</#if>
	</script>
	<!-- 表格操作 end -->
</body>
</html>