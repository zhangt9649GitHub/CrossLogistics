
<#include "../public_header.ftl">

	<script type="text/javascript" src="/static/js/warehouseManagement/edit.js"></script>

</head>
<body>

<div class="zq-body">
    <form class="layui-form zq-form">
        <input type="hidden" value="${wpId}" name="wpId"  id="id">
        <div class="layui-col-md6 zq-col-bottom">
            <div class="layui-col-md4 zq-col-left">
                <b>*</b>
                <@spring.message code="warehouseManagement_Positionnumber"/>
            </div>
            <div class="layui-col-md8">
                <input type="text" name="wpNumber" class="layui-input" autocomplete="off" lay-verify="required"/>
            </div>
        </div>


        <div class="layui-col-md6 zq-col-bottom">
            <div class="layui-col-md4 zq-col-left">
                <b>*</b>
                <@spring.message code="warehouseManagement_Positionuse"/>
            </div>
            <div class="layui-col-md8">
                <input type="text" name="wpUse" class="layui-input" autocomplete="off" lay-verify="required"/>

            </div>
        </div>

        <div class="layui-col-md6 zq-col-bottom">
            <div class="layui-col-md4 zq-col-left">
                <b>*</b>
                 <@spring.message code="cargoBagList_Ownwarehouse"/>
            </div>
            <div class="layui-col-md8">
                <select name="warehouseId" lay-verify="required" lay-search>
                    <option value=""><@spring.message code="warehouseManagement_Pleaseselectwarehouse"/></option>
                </select>
            </div>
        </div>


        <div class="zq-submit">
            <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="add"><@spring.message code="zq_save"/></button>
        <#--<input type="submit" lay-submit lay-filter="add">-->
        </div>

    </form>
</div>

</body>
</html>