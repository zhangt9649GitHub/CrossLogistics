<#include "../public_header.ftl">



	<script type="text/javascript" src="/static/js/wages/add.js"></script>

</head>
<body>

<div class="zq-body">

    <form class="layui-form layui-form-pane zq-form">
        <div class="layui-form-item">
            <label  for="name" class="layui-form-label">
                员工编号
            </label>
            <div class="layui-input-inline">
                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label  for="name" class="layui-form-label">
                员工姓名
            </label>
            <div class="layui-input-inline">
                <input type="text" name="staffName" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label  for="name" class="layui-form-label">
                工资金额
            </label>
            <div class="layui-input-inline">
                <input type="text" name="giveMoney" lay-verify="required|number" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label  for="name" class="layui-form-label">
                所属月份
            </label>
            <div class="layui-input-inline">
                <input type="text" name="moneyGiveMonth" lay-verify="required" autocomplete="off" class="layui-input" id="moneyGiveMonth">
            </div>
        </div>
        <div class="zq-submit">
            <button class="layui-btn  layui-btn-normal" lay-submit="" lay-filter="add">保存</button>
        </div>
    </form>

</div>

</body>
</html>