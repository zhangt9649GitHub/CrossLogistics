$(document).ready(function(){

    layui.use(['layer', 'form','table'], function(){
        var layer = layui.layer
            ,form = layui.form
            ,table = layui.table;


        // 数据表格

        table.render({
            elem: '#idTest'
            ,height: 500
            ,url: "/admin/user/getAppUserCertificationList"//数据接口
            ,page: true

            ,cols: [[
                {type:'numbers'}
                ,{field: 'number', title: '用户编号'}
                ,{field: 'userTrueName', title: '真实姓名',align:'center'}
                ,{field: 'userType', title: '审核类型',align:'center'}
                // ,{field: 'mobile', title: '联系方式'}
                ,{field: 'userCertificationStatus', title: '审核状态',align:'center'}
                ,{align:'center', toolbar: '#barDemo',title: '操作',minWidth:300}
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
                    userTrueName: $.trim(data.field.userTrueName),
                    userType: $.trim(data.field.userType)


                }
            });


            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });





    });



});


// 通过
function disabled(id) {
    layer.confirm('确认审核通过吗？', {
        btn: ['确定', '取消'] //可以无限个按钮
    }, function (index, layero) {
        $.ajax({
            url: '/admin/user/updateUserCertificationStatus',
            data: {'certificationId':id,'userCertificationStatus':'已通过'},
            dataType: 'JSON',
            success: function (data) {
                layui.layer.msg(data.msg);

                data.msg == '更新成功' ? layui.table.reload('idTest') : '';
            },
            error: function (data) {

            }
        })
    }, function (index) {
        //按钮【取消】的回调
    });
};

// // 驳回
// function startusing(appUserId,certificationId) {
//     layer.confirm('确认驳回通过吗？', {
//         btn: ['确定', '取消'] //可以无限个按钮
//     }, function (index, layero) {
//         $.ajax({
//             url: '/admin/user/checkAppUser',
//             data: {'appUserId':appUserId,'certificationId':certificationId,'userCertificationStatus':'已驳回'},
//             dataType: 'JSON',
//             success: function (data) {
//                 layui.layer.msg(data.message);
//                 data.msg == '更新成功' ? layui.table.reload('idTest') : '';
//             },
//             error: function (data) {
//
//             }
//         })
//     }, function (index) {
//         //按钮【取消】的回调
//     });
// };
// 驳回
function startusing(id) {
    layer.prompt({title: '请填写驳回原因', formType: 2}, function(text, index){
        layer.close(index);
        $.ajax({
            url: '/admin/user/updateUserCertificationStatus',
            dataType: 'JSON',
            data: {'certificationId':id,'userCertificationStatus':'已驳回','dismissExplain':text},
            success: function (data) {
                layui.layer.msg(data.msg);

                data.msg == '更新成功' ? layui.table.reload('idTest') : '';
            }
        })

    });
}

