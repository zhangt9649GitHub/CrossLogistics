<#include "../public_header.ftl">


	<#--<link rel="stylesheet" type="text/css" href="/static/css/carMaintenance/add.css">-->
	<script type="text/javascript" src="/static/js/carMaintenance/edit.js"></script>

</head>
<body>

	<div class="zq-body">

		<form class="layui-form layui-form-pane zq-form">
            <input type="hidden" value="${carMaintenanceId}" name="carMaintenanceId">
			<div class="layui-form-item">
				<label  for="name" class="layui-form-label">
					小车编号
				</label>
				<div class="layui-input-inline" >
                    <input type="hidden" name="carId" class="layui-input">
                    <input type="text" class="layui-input carNumber" readonly />
                </div>
			</div>
            <div class="layui-form-item">
                <label  for="name" class="layui-form-label">
                    任务状态
                </label>
                <div class="layui-input-inline" >

                    <select name="state" lay-verify="required" lay-search>
                        <option value=""></option>
                        <option value="2">维修中</option>
                        <option value="3">维修成功</option>
                        <option value="4">维修失败</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
					地址
                </label>
                <div class="layui-input-inline">
                    <input type="text" name="address"  lay-verify="required" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">描述</label>
                <div class="layui-input-block">
                    <textarea name="content" placeholder="请输入内容" lay-verify="required" class="layui-textarea"></textarea>
                </div>
            </div>
			<div class="zq-submit">
            	<button class="layui-btn  layui-btn-normal" lay-submit="" lay-filter="edit"><@spring.message code="zq_save"/></button>
            </div>
		</form>

	</div>
	
</body>
</html>