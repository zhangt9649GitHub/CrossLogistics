$(document).ready(function(){

    layui.use(['layer', 'form','table'], function(){
        var layer = layui.layer
            ,form = layui.form
            ,table = layui.table;


        // 数据表格

        table.render({
            elem: '#idTest'
            ,height: 500
            ,url: "/carTask/carTaskAll"//数据接口
            ,page: true
            ,cols: [[
                {type:'numbers'}
                ,{field: 'carTaskNumber', title: '模板编号'}
                ,{field: 'carTaskName', title: '模板名称',align:'center'}
                ,{field: 'userName', title: '用户名',align:'center'}
                ,{field: 'rallyPointName', title: '集结地名称'}
                ,{field: 'status', title: '状态', templet:'#status',align:'center'}
                ,{field: 'adminName', title: '操作人',align:'center'}
                ,{field: 'addTime', title: '创建时间',align:'center'}
                ,{align:'center', toolbar: '#barDemo',title: '操作',minWidth:260}
            ]]
            ,where: {rallyPointId:0}
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


        // 查询区域列表
        $.ajax({
            url: '/carOrder/singaporeAreaAll',
            dataType: 'JSON',
            success: function (data) {
                if(data.message == "获取成功"){
                    if(data.data != null){
                        $.each(data.data,function (i,n) {
                            $('select[name="singaporeAreaId"]').append('<option value="'+ n.singaporeAreaId +'">'+ n.singaporeAreaName +'</option>')
                        })
                    }
                }
                form.render('select');
            }
        });

        // 监听区域选择
        form.on('select(singaporeAreaId)',function(data){
            $('select[name="rallyPointId"]').html(' ');
            console.log((data.value).length)
            if((data.value).length == 0 ){
                $('select[name="rallyPointId"]').append('<option value="">集结地</option>');
                form.render('select');
            }else{
                $.ajax({
                    url: '/carOrder/rallyPointIdAll',
                    data: {'singaporeAreaId': data.value},
                    dataType: 'JSON',
                    success: function (data) {
                        if(data.message == "获取成功"){
                            if(data.data != null){
                                $('select[name="rallyPointId"]').append('<option value="">集结地</option>');
                                $.each(data.data,function (i,n) {
                                    $('select[name="rallyPointId"]').append('<option value="'+ n.rallyPointId +'">'+ n.rallyPointName +'</option>')
                                })
                            }
                        }
                        form.render('select');
                    }
                });
            }
        });


        // 监听查询
        form.on('submit(add)', function(data){


            delete data.field.singaporeAreaId;
            if((data.field.rallyPointId).length ==0){
                data.field.rallyPointId = 0;
            }
            //执行重载
            table.reload('idTest', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    search : $.trim(data.field.search),
                    rallyPointId : $.trim(data.field.rallyPointId)


                }
            });
            return false;
        });






    });



});
// 删除
function delthis(id) {
    layer.confirm('确认删除吗？', {
        btn: ['确定', '取消'] //可以无限个按钮
    }, function (index, layero) {
        $.ajax({
            url: '/carTask/delCarTask',
            data: {'carTaskId':id},
            type: 'post',
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
            url: '/carTask/editStatus',
            data: {'carTaskId':id,'status':2},
            dataType: 'JSON',
            type:'post',
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
}

// 启用
function startusing(id) {
    layer.confirm('确认启用？', {
        btn: ['确定', '取消'] //可以无限个按钮
    }, function (index, layero) {
        $.ajax({
            url: '/carTask/editStatus',
            data: {'carTaskId':id,'status':1},
            dataType: 'JSON',
            type:'post',
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
}


