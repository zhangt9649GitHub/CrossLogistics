<#include "../public_header.ftl">


	<#--<link rel="stylesheet" type="text/css" href="/static/css/carMaintenance/add.css">-->
	<script type="text/javascript" src="/static/js/carMaintenance/add.js"></script>

</head>
<body>

	<div class="zq-body">

		<form class="layui-form layui-form-pane zq-form">
			<div class="layui-form-item">
				<label  for="name" class="layui-form-label">
					小车编号
				</label>
				<div class="layui-input-inline" >
                    <select name="carId" lay-verify="required" lay-search>
				        <option value=""></option>

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
            	<button class="layui-btn  layui-btn-normal" lay-submit="" lay-filter="add"><@spring.message code="zq_save"/></button>
            </div>
		</form>

	</div>
	
</body>
</html>