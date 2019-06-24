<#include "../public_header.ftl">



	<link rel="stylesheet" href="/static/css/jurisdiction/edit.css">
	<script type="text/javascript" src="/static/js/jurisdiction/edit.js"></script>

</head>
<body>

	<div class="zq-body">

		<form class="layui-form layui-form-pane zq-form">
            <#-- 权限组id -->
                <input type="hidden" value="${groupId}" id="id" name="groupId">
			<div class="layui-form-item">
                <label for="name" class="layui-form-label name">
                    <@spring.message code="jurisdiction_index_Privilegegroupname"/>
                </label>
                <div class="layui-input-inline">
                     <input type="text" id="name" name="groupName" required="" lay-verify="required" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item layui-form-text">
            	<label class="layui-form-label"><@spring.message code="jurisdiction_index_Haveaccess"/></label>

                <div class="list">
                </div>
            </div>
			
            <div class="layui-form-item layui-form-text">
            	<label for="desc" class="layui-form-label">
                    <@spring.message code="jurisdiction_index_Permissiondescription"/>
                </label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入内容" name="groupText" class="layui-textarea"></textarea>
                </div>

            	
            </div>

            <div class="zq-submit">
                <button class="layui-btn  layui-btn-normal" lay-submit lay-filter="edit"><@spring.message code="zq_save"/></button>
            </div>

		</form>
		
	</div>

</body>
</html>