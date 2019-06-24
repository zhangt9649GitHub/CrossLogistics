<#include "../public_header.ftl">


	<script type="text/javascript" src="/static/js/exitAndEntry/index.js"></script>

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

				 			<select name="exitWay">
				 				<option value="">请选择运输方式</option>
                                <option value="1">空运</option>
                                <option value="2">海运</option>
							</select>

				 		</div>
				 	</div>

				 	<div class="layui-inline">
				 		<div class="layui-btn" lay-submit="" lay-filter="*">
				 			<i class="layui-icon">&#xe615;</i>
				 		</div>

				 	</div>

				 	
				 </div>
			</form>
			
		</div>
		<!-- 查询 end -->

		<!-- 操作按钮 start-->
		<div class="zq-actionbuttons">
			<button class="layui-btn" onclick="zq_admin_show('添加','/admin/exitAndEntry/add','400','500')"><i class="layui-icon">&#xe608;</i>新增</button>
		</div>
		<!-- 操作按钮 end-->

		<!-- 数据表格 start -->

		<table id="idTest" lay-filter="demo"></table>



		<!-- 数据表格 end -->

	</div>

	<!-- 内容 end -->

	<!-- 表格操作 start -->
	<script type="text/html" id="barDemo">
	    <a class="zq-btn-edit" lay-event="edit" onclick="zq_admin_show('编辑','/admin/exitAndEntry/edit?eaeId={{d.eaeId}}','400','500')">编辑</a>
	   	<a class="zq-btn-delete" lay-event="delete" onclick="delthis('{{d.eaeId}}')">删除</a>
	</script>
	<!-- 表格操作 end -->
	<#-- 运输方式 start -->

    <script type="text/html" id="exitWayID">
        {{# if(d.exitWay == 1){ }}
        空运
        {{# } else if(d.exitWay == 2){ }}
        海运
        {{# } }}
	</script>

	<#-- 运输方式 end -->

</body>
</html>