<#include "../public_header.ftl">


    <link rel="stylesheet" href="/static/css/domesticAddress/add.css">
	<script type="text/javascript" src="/static/js/domesticAddress/add.js"></script>

</head>
<body>

<div class="zq-body">

    <form class="layui-form layui-form-pane zq-form">
        <div class="layui-form-item">
            <label  for="name" class="layui-form-label">
                <@spring.message code="domesticAddress_Areaname"/>
            </label>
            <div class="layui-input-inline">
                <input type="text" name="chinaAreaName" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label  for="name" class="layui-form-label">
                pId
            </label>
            <div class="layui-input-inline">
                <input type="text"  name="chinaAreaParentId" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label  for="name" class="layui-form-label">

                <@spring.message code="domesticAddress_Regionaltype"/>
            </label>
            <div class="layui-input-inline">
                <select name="chinaAreaType" lay-verify="required">
                    <option value=""><@spring.message code="zq_pleasechoose"/></option>
                    <option value="1"><@spring.message code="domesticAddress_province"/></option>
                    <option value="2"><@spring.message code="domesticAddress_city"/></option>
                    <option value="3"><@spring.message code="domesticAddress_Area"/></option>
                </select>
            </div>
        </div>
        <div class="zq-submit">
            <button class="layui-btn  layui-btn-normal" lay-submit="" lay-filter="add"><@spring.message code="zq_save"/></button>
        </div>
    </form>

</div>

</body>
</html>