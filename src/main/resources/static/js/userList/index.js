$(document).ready(function(){

    layui.use(['layer', 'form','table'], function(){
        var layer = layui.layer
            ,form = layui.form
            ,table = layui.table;


        // 数据表格

        table.render({
            elem: '#idTest'
            ,height: 500
            ,url: "/appUser/selectAppUserAll"//数据接口
            ,page: true

            ,cols: [[
                {field: 'number', title: '编号'}
                ,{field: 'userName', title: '用户名',align:'center'}
                ,{field: 'sex', title: '性别',align:'center',width:80}

                ,{field: 'carApproveStatus', title: '小车认证状态',align:'center'}
                ,{field: 'truckApproveStatus', title: '货车认证状态',align:'center'}
                ,{field: 'mobile', title: '联系方式'}
                ,{field: 'status', title: '状态',align:'center'}
                ,{field: 'addTime', title: '创建时间',align:'center',minWidth:200}
                ,{align:'center', toolbar: '#barDemo',title: '操作',minWidth:320}
            ]]
            ,autoSort: false //禁用前端自动排序。注意：该参数为 layui 2.4.4 新增
        });

        //监听排序事件
        table.on('sort(demo)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"


            //尽管我们的 table 自带排序功能，但并没有请求服务端。
            //有些时候，你可能需要根据当前排序的字段，重新向服务端发送请求，从而实现服务端排序，如：
            table.reload('idTest', {
                initSort: obj //记录初始排序，如果不设的话，将无法标记表头的排序状态。
                ,where: { //请求参数（注意：这里面的参数可任意定义，并非下面固定的格式）
                    field: obj.field //排序字段
                    ,order: obj.type //排序方式
                }
            });
        });
        //搜索
        form.on('submit(add)', function(data){
            //执行重载
            table.reload('idTest', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    search: $.trim(data.field.search),
                    sex: $.trim(data.field.sex),
                    userStatus: $.trim(data.field.userStatus)


                }
            });


            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });





    });



});
// 删除
function delthis(id) {
    layer.confirm('确认删除吗？', {
        btn: ['确定', '取消'] //可以无限个按钮
    }, function (index, layero) {
        $.ajax({
            url: '/appUser/deleteAppUser',
            data: {'appUserId':id},
            dataType: 'JSON',
            success: function (data) {
                layui.layer.msg(data.message);
                data.message == '删除成功' ? layui.table.reload('idTest') : '';
            },
            error: function (data) {

            }
        })
    }, function (index) {
        //按钮【取消】的回调
    });
};

// 禁用
function disabled(id) {
    layer.confirm('确认禁用？', {
        btn: ['确定', '取消'] //可以无限个按钮
    }, function (index, layero) {
        $.ajax({
            url: '/appUser/editAppUserStatus',
            type: 'post',
            data: {'appUserId':id,'status':'禁用'},
            dataType: 'JSON',
            success: function (data) {
                layui.layer.msg(data.message);

                data.message == '操作成功' ? layui.table.reload('idTest') : '';
            },
            error: function (data) {

            }
        })
    }, function (index) {
        //按钮【取消】的回调
    });
};

// 启用
function startusing(id) {
    layer.confirm('确认禁用？', {
        btn: ['确定', '取消'] //可以无限个按钮
    }, function (index, layero) {
        $.ajax({
            url: '/appUser/editAppUserStatus',
            type: 'post',
            data: {'appUserId':id,'status':'正常'},
            dataType: 'JSON',
            success: function (data) {
                layui.layer.msg(data.message);
                data.message == '操作成功' ? layui.table.reload('idTest') : '';
            },
            error: function (data) {

            }
        })
    }, function (index) {
        //按钮【取消】的回调
    });
};