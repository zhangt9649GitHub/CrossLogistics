<#include "../public_header.ftl">


<script type="text/javascript" src="/static/js/carlock/add.js"></script>

</head>
<body>

<div class="zq-body">

    <form class="layui-form layui-form-pane zq-form">
        <div class="layui-form-item">
            <label  for="name" class="layui-form-label">
                车锁编号
            </label>
            <div class="layui-input-inline">
                <input type="text" id="name" name="lockNumber" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label  for="name" class="layui-form-label">
               车锁类型
            </label>
            <div class="layui-input-inline" >
                <select name="lockPosition" lay-verify="required" lay-search>
                    <option value=""></option>
                    <option value="1">小车车厢锁</option>
                    <option value="2">小车锁</option>
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