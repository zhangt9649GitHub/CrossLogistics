<#include "../public_header.ftl">



	<script type="text/javascript" src="/static/js/carOrder/assign.js"></script>

</head>
<body>

<div class="zq-body">

    <form class="layui-form layui-form-pane zq-form">
        <input type="hidden" value="${taskOrderId}" name="taskOrderId"/>
        <input type="hidden" value="${date}" id="createTime" />
        <div class="layui-form-item">
            <label  for="name" class="layui-form-label">
                <@spring.message code="zq_user"/>
            </label>
            <div class="layui-input-inline" >
                <select name="appUserId" lay-filter="aihao" lay-verify="required" lay-search>
                    <option value=""><@spring.message code="zq_pleasechoose"/></option>

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