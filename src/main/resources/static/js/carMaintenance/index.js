$(document).ready(function(){

    layui.use(['layer', 'form','table'], function(){
        var layer = layui.layer
            ,form = layui.form
            ,table = layui.table;


        // 数据表格

        table.render({
            elem: '#idTest'
            ,height: 500
            ,url: "/CarMaintenance/getCarMaintenancelist"//数据接口
            ,page: true
            ,cellMinWidth:80
            ,cols: [[
                {field: 'carNumber', title: '小车编号'}
                ,{field: 'state', title: '任务状态',templet:'#state',align:'center',width:120}
                ,{field: 'address', title: '地址'}
                ,{field: 'content', title: '描述'}

                ,{field: 'cbmAddtime', title: '创建时间'}
                ,{align:'center', toolbar: '#barDemo',title: '操作'}
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

        form.on('submit(*)', function(data){
            //执行重载
            table.reload('idTest', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    carNumber: $.trim(data.field.carNumber),
                    state: $.trim(data.field.state),
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
            url: '/admin/user/deleteAdminUser',
            type: 'post',
            data: {'adminUid':id},
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
            url: '/admin/user/editAdminUserStatus',
            type: 'post',
            data: {'adminUid':id,'status':0},
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
            url: '/admin/user/editAdminUserStatus',
            type: 'post',
            data: {'adminUid':id,'status':1},
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