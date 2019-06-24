<#include "../public_header.ftl">

	<script type="text/javascript" src="/static/js/singaporeZipCode/index.js"></script>

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
            <div class="layui-form-item">

                <div class="layui-inline">
                    <div class="layui-input-inline">

                        <input type="text" placeholder="请输入邮编" autocomplete="off" class="layui-input" name="saZipCode" />

                    </div>
                </div>


                <div class="layui-inline">
                    <div class="layui-input-inline">

                        <select name="state">
                            <option value="">全部</option>
                            <option value="1">已绑定区域</option>
                            <option value="2">未绑定区域</option>
                        </select>

                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-btn"  lay-submit="" lay-filter="*">
                        <i class="layui-icon">&#xe615;</i>
                    </div>

                </div>


            </div>
        </form>

    </div>
    <!-- 查询 end -->

    <!-- 操作按钮 start-->
    <div class="zq-actionbuttons">
        <#if addSGBuilding??>
        <button class="layui-btn" onclick="zq_admin_show('<@spring.message code="zq_New"/>','/admin/singaporeZipCode/add')"><i class="layui-icon">&#xe608;</i><@spring.message code="zq_New"/></button>
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
    <#if updateSGBuilding??>
    <a class="zq-btn-edit" lay-event="edit" onclick="zq_admin_show('<@spring.message code="zq_edit"/>','/admin/singaporeZipCode/edit?saBuildingId={{d.saBuildingId}}')"><@spring.message code="zq_edit"/></a>
    </#if>
    <#if delSGBuilding??>
        {{# if(d.delState != 2){}}
    <a class="zq-btn-disabled" lay-event="delete" onclick="disabled('{{d.saBuildingId}}')">禁用</a>
        {{# } }}
    </#if>
    <#if delSGBuilding??>
        {{# if(d.delState != 1){}}
        <a class="zq-btn-startusing" lay-event="delete" onclick="startusing('{{d.saBuildingId}}')">启用</a>
        {{# } }}
    </#if>

</script>
<!-- 表格操作 end -->

<script type="text/html" id="delState">
    {{# if(d.delState == 1){}}
    启用
    {{# } }}
    {{# if(d.delState == 2){}}
    禁用
    {{# } }}
</script>
</body>
</html>