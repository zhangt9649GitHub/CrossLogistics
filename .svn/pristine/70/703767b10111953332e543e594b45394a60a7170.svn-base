<#include "../public_header.ftl">


	<script type="text/javascript" src="/static/js/normalGoods/zhuanyunruku.js"></script>

</head>
<body>

	<div class="zq-body">
		<form class="layui-form zq-form">
			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">
					<b>*</b>
					货物单号
				</div>
				<div class="layui-col-md8">
					<input type="text" name="tripartiteNumber" class="layui-input" autocomplete="off" lay-verify="required|number" />
				</div>
			</div>

			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">
					<b>*</b>
					用户编号
				</div>
				<div class="layui-col-md8">
					<input type="text" name="number" class="layui-input" autocomplete="off" lay-verify="required|number"/>
				</div>
			</div>

			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">
					<b>*</b>
					长（单位：cm）
				</div>
				<div class="layui-col-md8">
					<input type="text" name="actualLong" class="layui-input" autocomplete="off" lay-verify="required|number"/>
				</div>
			</div>

			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">
					<b>*</b>
					宽（单位：cm）
				</div>
				<div class="layui-col-md8">
					<input type="text" name="actualWidth" class="layui-input" autocomplete="off" lay-verify="required|number"/>
				</div>
			</div>

			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">
					<b>*</b>
					高（单位：cm）
				</div>
				<div class="layui-col-md8">
					<input type="text" name="actualHeight" class="layui-input" autocomplete="off" lay-verify="required|number"/>
				</div>
			</div>

			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">
					<b>*</b>
					重量（单位：kg）
				</div>
				<div class="layui-col-md8">
					<input type="text" name="actualWeight" class="layui-input" autocomplete="off" lay-verify="required|number"/>
				</div>
			</div>



			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">
					<b>*</b>
					特殊货物
				</div>
				<div class="layui-col-md8">
					<select name="isSpecialGoods" lay-verify="required">
						<option value=""></option>
						<option value="1">是</option>
						<option value="2">否</option>
					</select>
				</div>
			</div>

			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">
					<b>*</b>
					货物分类
				</div>
				<div class="layui-col-md8" >
					<select name="category" lay-verify="required">
						<option value=""></option>
					</select>
				</div>
			</div>

			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">
					<b>*</b>
					货物类型
				</div>
				<div class="layui-col-md8">
					<input type="text" name="goodType" class="layui-input" autocomplete="off" lay-verify="required"/>
				</div>
			</div>



			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">
					<b>*</b>
					入库仓位
				</div>
				<div class="layui-col-md8">
					<input type="text" name="intoWpNumber" class="layui-input" autocomplete="off" lay-verify="required"/>
				</div>
			</div>

			<div class="layui-col-md12 zq-col-bottom">
				<div class="layui-col-md2 zq-col-left">
					<b>*</b>
					货物照片
					<br/>
				</div>
				<div class="layui-col-md10">
					<div class="zq-div-img">
						<div class="zq-domt">
							<div id="preview">
								<img src="/static/image/dj.jpg">
							</div>
							<input class="zq-file" type="file"  onchange="preview(this)" />
						</div>
					</div>


				</div>
			</div>

			<div class="layui-col-md12 zq-col-bottom">
				<div class="layui-col-md2 zq-col-left">
					<b></b>
					备注
				</div>
				<div class="layui-col-md10">
					<textarea name="remark" class="layui-textarea"></textarea>
				</div>
			</div>



			<div class="zq-submit">
				<button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="add"> <@spring.message code="zq_save"/></button>
			</div>
		</form>




	</div>

</body>
</html>