<#include "../public_header.ftl">


	<link rel="stylesheet" type="text/css" href="/static/css/dataDictionary/index.css">
	<script type="text/javascript" src="/static/js/dataDictionary/index.js"></script>

</head>
<body class="zq-bodybagcolor">
    <!-- 刷新 start -->
    <div class="zq-nav">
        <a class="layui-btn right" href="javascript:location.replace(location.href);" title="刷新">
            <i class="layui-icon">&#xe669;</i>
        </a>
    </div>
    <!-- 刷新 end -->
	<div class="zq-body">
        <!-- 操作按钮 start-->
        <div class="zq-actionbuttons">
            <#if insertBizdictionary??>
            <button class="layui-btn" onclick="zq_admin_show('<@spring.message code="zq_New"/>','/admin/dataDictionary/add','400','500')"><i class="layui-icon">&#xe608;</i><@spring.message code="zq_New"/></button>
            </#if>
        </div>
        <!-- 操作按钮 end-->
		<div>
			<table class="layui-hidden" id="treeTable" lay-filter="treeTable"></table>		
		</div>

	</div>

    <script type="text/html" id="titleTpl">

        <#if updateBizdictionary??>
            <a class="zq-btn-edit" lay-event="edit" onclick="zq_admin_show('<@spring.message code="zq_edit"/>','/admin/domesticAddress/edit?chinaAreaId='+ obj.data.chinaAreaId,'400','500')"><@spring.message code="zq_edit"/></a>
        </#if>


        <#if deleteBizdictionaryById??>
             <a class="zq-btn-delete" lay-event="delete" onclick="del('{{d.id}}')"><@spring.message code="zq_delete"/></a>
        </#if>


    </script>

</body>
</html>