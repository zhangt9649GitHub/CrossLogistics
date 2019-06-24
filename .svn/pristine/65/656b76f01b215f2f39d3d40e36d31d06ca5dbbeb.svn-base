<#include "../public_header.ftl">


	<script type="text/javascript" src="/static/js/userAuditList/index.js"></script>

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
				 			<input type="text" name="userTrueName" placeholder="真实姓名" autocomplete="off" class="layui-input" />
				 		</div>
				 	</div>

				 	<div class="layui-inline">
				 		<div class="layui-input-inline">

				 			<select name="userType">
				 				<option value=""><@spring.message code="userAuditList_Pleaseselectanaudittype"/></option>
				 				<option value="小车"><@spring.message code="userList_Car"/></option>
				 				<option value="货车"><@spring.message code="userList_truck"/></option>
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

	<!-- 表格操作 start -->
	<script type="text/html" id="barDemo">
		<#if getAppUserCertificationDetails??>
        <a class="zq-btn-open" onclick="zq_admin_show('<@spring.message code="zq_View"/>','/admin/userAuditList/open?certificationId={{d.certificationId}}')"><@spring.message code="zq_View"/></a>
		</#if>
		<#if updateUserCertificationStatus??>
        {{# if(d.userCertificationStatus == '待审核'){}}
        <a class="zq-btn-disabled" onclick="disabled('{{d.certificationId}}')"><@spring.message code="zq_Check"/></a>
        {{# } }}
        {{# if(d.userCertificationStatus == '待审核'){}}
        <a class="zq-btn-startusing" onclick="startusing('{{d.certificationId}}')"><@spring.message code="zq_turndown"/></a>
        {{# } }}
		</#if>
	</script>
	<!-- 表格操作 end -->
</body>
</html>