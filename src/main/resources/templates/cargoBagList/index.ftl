<#include "../public_header.ftl">


	<script type="text/javascript" src="/static/js/cargoBagList/index.js"></script>

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
				 			<input type="text" name="bagNumber" placeholder="<@spring.message code="normalGoods_Bagnumber"/>" autocomplete="off" class="layui-input" />
				 		</div>
				 	</div>

					 <div class="layui-inline">
						 <div class="layui-input-inline">

							 <select name="singaporeAreaId" lay-filter="singaporeAreaId">
								 <option value=""><@spring.message code="cargoBagList_Area"/></option>

							 </select>

						 </div>
					 </div>

					 <div class="layui-inline">
						 <div class="layui-input-inline">

							 <select name="rallyPointId">
								 <option value=""><@spring.message code="carMission_Aggregate"/></option>

							 </select>

						 </div>
					 </div>
				 	<div class="layui-inline">
				 		<div class="layui-input-inline">
				 			<input type="text" name="licensePlate" placeholder="<@spring.message code="normalGoods_Trucklicense"/>" autocomplete="off" class="layui-input" />
				 		</div>
				 	</div>

				 	<div class="layui-inline">
				 		<div class="layui-input-inline">
				 			<input type="text" name="carNumber" placeholder="<@spring.message code="normalGoods_Carnumber"/>" autocomplete="off" class="layui-input" />
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
		<#if getBagDetails??>
        <a class="zq-btn-open" onclick="zq_admin_show('<@spring.message code="zq_View"/>','/admin/cargoBagList/open?bagId={{d.bagId}}')"><@spring.message code="zq_View"/></a>
		</#if>
		<#if deleteBagById??>
        <a class="zq-btn-delete currency"  onclick="delthis('{{d.bagId}}')"><@spring.message code="zq_delete"/></a>
		</#if>
	</script>
	<!-- 表格操作 end -->

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