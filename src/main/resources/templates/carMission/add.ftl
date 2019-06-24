<#include "../public_header.ftl">


	<script type="text/javascript" src="/static/js/carMission/add.js"></script>

</head>
<body>

	<div class="zq-body">
		<form class="layui-form layui-form-pane zq-form">
            <div class="layui-form-item">
                <label  for="name" class="layui-form-label">
                    <@spring.message code="carMission_Templatename"/>
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="name" name="carTaskName" lay-verify="required" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label  for="name" class="layui-form-label">
                    <@spring.message code="carMission_user"/>
                </label>
                <div class="layui-input-inline">
                    <select name="userId" lay-verify="required" lay-search>
                        <option value=""> <@spring.message code="zq_pleasechoose"/></option>
					</select>
                </div>
            </div>

            <div class="layui-form-item">
                <label  for="name" class="layui-form-label">
                    <@spring.message code="carMission_Aggregate"/>
                </label>
                <div class="layui-input-inline">
                    <select name="rallyPointId" lay-verify="required" lay-search>
                        <option value=""> <@spring.message code="zq_pleasechoose"/></option>
                    </select>
                </div>
            </div>





			<div class="zq-submit">
				<button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="add"> <@spring.message code="zq_save"/></button>
			</div>
		</form>
	</div>

</body>
</html>