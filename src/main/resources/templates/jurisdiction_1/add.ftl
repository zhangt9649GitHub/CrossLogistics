<#include "../public_header.ftl">
	<script type="text/javascript" src="/static/js/jurisdiction_1/add.js"></script>

</head>
<body>
	<div class="zq-body">
		<form class="layui-form zq-form">

			<div class="layui-col-md12 zq-col-bottom">
				<div class="layui-col-md2 zq-col-left">
					<b>*</b>
					<@spring.message code="jurisdiction1_Privilegegroupname"/>
				</div>
				<div class="layui-col-md8">
					<input type="text" name="staffGroupName" class="layui-input" autocomplete="off" lay-verify="required"/>
				</div>
			</div>

			<div class="layui-col-md12 zq-col-bottom">
				<div class="layui-col-md2 zq-col-left">
					<b>*</b>
					<@spring.message code="jurisdiction1_Allocationmanagementmodule"/>
				</div>
				<div class="layui-col-md8">
					<select name="saIds" xm-select="select1" id="saIds" lay-verify="required">
                    <option value=""></option>
                </select>
				</div>
			</div>

			<div class="zq-submit">
				<button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="add"><@spring.message code="zq_save"/></button>
			</div>
		</form>


	</div>

</body>
</html>