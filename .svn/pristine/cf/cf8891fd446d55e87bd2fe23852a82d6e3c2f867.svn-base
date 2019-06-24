<#include "../public_header.ftl">

	<script type="text/javascript" src="/static/js/domesticAddress/index.js"></script>

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
        <#if addchinaarea??>
        <button class="layui-btn" onclick="zq_admin_show('新增','/admin/domesticAddress/add','400','500')"><i class="layui-icon">&#xe608;</i><@spring.message code="zq_New"/></button>
        </#if>
    </div>
    <!-- 操作按钮 end-->
    <div>
        <table class="layui-hidden" id="treeTable" lay-filter="treeTable"></table>
    </div>

    <script type="text/html" id="titleTpl">
        <#if editchinaarea??>
            <a class="zq-btn-edit" lay-event="edit" onclick="zq_admin_show('<@spring.message code="zq_edit"/>','/admin/domesticAddress/edit?chinaAreaId='+ obj.data.chinaAreaId,'400','500')"><@spring.message code="zq_edit"/></a>
        </#if>

        <#if delchinaarea??>
            <a class="zq-btn-delete" lay-event="delete" onclick="del('{{d.chinaAreaId}}')"><@spring.message code="zq_delete"/></a>
        </#if>

    </script>
</div>

</body>
</html>