<#include "../public_header.ftl">

	<script type="text/javascript" src="/static/js/copybookSettings/index.js"></script>

</head>
<body class="zq-bodybagcolor">

<!-- 刷新 start -->
<div class="zq-nav">
    <a class="layui-btn right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon">&#xe669;</i>
    </a>
</div>
<!-- 刷新 end -->


<!-- 内容 start -->

<div class="zq-body">
    <!-- 查询 start -->
    <div class="zq-query">
        <form class="layui-form">

        </form>

    </div>
    <!-- 查询 end -->

    <!-- 操作按钮 start-->
    <div class="zq-actionbuttons">
        <#if insertCopyWriter??>
        <button class="layui-btn" onclick="zq_admin_show('<@spring.message code="zq_New"/>','/admin/copybookSettings/add')"><i class="layui-icon">&#xe608;</i><@spring.message code="zq_New"/></button>
        </#if>
    </div>
    <!-- 操作按钮 end-->

    <!-- 数据表格 start -->

    <table id="idTest" lay-filter="demo"></table>


    <!-- 数据表格 end -->

</div>

<!-- 内容 end -->

<!-- 表格操作 start -->
<script type="text/html" id="barDemo">
    <#if updateCopyWriter??>
    <a class="zq-btn-edit" lay-event="edit" onclick="zq_admin_show('<@spring.message code="zq_edit"/>','/admin/copybookSettings/edit?copyWriterId={{d.cwId}}')"><@spring.message code="zq_edit"/></a>
    </#if>
    <#if deleteCopyWriterById??>
    <a class="zq-btn-delete" lay-event="delete" onclick="delthis('{{d.cwId}}')"><@spring.message code="zq_delete"/></a>
    </#if>

    <#if getCopyWriterById??>
    <a class="zq-btn-open" lay-event="edit" onclick="zq_admin_show('<@spring.message code="zq_View"/>','/admin/copybookSettings/open?copyWriterId={{d.cwId}}')"><@spring.message code="zq_View"/></a>
    </#if>

</script>
<!-- 表格操作 end -->


</body>
</html>