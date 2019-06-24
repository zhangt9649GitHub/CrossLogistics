<#include "../public_header.ftl">


	<script type="text/javascript" src="/static/js/carOrder/edit.js"></script>

</head>
<body>


	<div class="zq-body">
		<form class="layui-form zq-form"> 
			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">
					<b>*</b>
					任务模板
				</div>
				<div class="layui-col-md8">
					<select>
						<option value=""></option>
						<option value="模板A">模板A</option>
						<option value="模板B">模板B</option>
					</select>
				</div>
			</div>
			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">
					<b>*</b>
					货袋
				</div>
				<div class="layui-col-md8">
					<select>
						<option value=""></option>
						<option value="货袋A">货袋A</option>
						<option value="货袋B">货袋B</option>
					</select>
				</div>
			</div>
			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">
					<b>*</b>
					订单时间
				</div>
				<div class="layui-col-md8">
					<input type="text" id="name" name="name" lay-verify="required" autocomplete="off" class="layui-input" id="order-item">
				</div>
			</div>
			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">
					<b>*</b>
					订单价格
				</div>
				<div class="layui-col-md8">
					<input type="text" id="name" name="name" lay-verify="required" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="zq-submit">
            	<button class="layui-btn  layui-btn-normal" lay-submit="" lay-filter="add">保存</button>
            </div>
		</form>
	</div>

</body>
</html>