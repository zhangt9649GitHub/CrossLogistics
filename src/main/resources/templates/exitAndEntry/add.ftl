<#include "../public_header.ftl">



	<script type="text/javascript" src="/static/js/exitAndEntry/add.js"></script>

</head>
<body>

	<div class="zq-body">
		 
		<form class="layui-form layui-form-pane zq-form">
			<div class="layui-form-item">
				<label class="layui-form-label">
					承运公司
				</label>
				<div class="layui-input-inline">
                    <input type="text" name="carrierCompany" lay-verify="required" autocomplete="off" class="layui-input">
                </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">
					联系人
				</label>
				<div class="layui-input-inline" >
                    <input type="text" name="contact" lay-verify="required" autocomplete="off" class="layui-input">
                </div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">
					联系方式
				</label>
				<div class="layui-input-inline">
                    <input type="text"  name="mobile" lay-verify="required|phone" autocomplete="off" class="layui-input">
                </div>
			</div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    运输方式
                </label>
                <div class="layui-input-inline">
                    <select name="exitWay" lay-verify="required">
                        <option value="">请选择运输方式</option>
                        <option value="1">空运</option>
                        <option value="2">海运</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    公司地址
                </label>
                <div class="layui-input-inline">
                    <input type="text"  name="address" lay-verify="required" autocomplete="off" class="layui-input">
                </div>
            </div>

			<div class="zq-submit">
            	<button class="layui-btn  layui-btn-normal" lay-submit="" lay-filter="add">保存</button>
            </div>
		</form>

	</div>
	
</body>
</html>