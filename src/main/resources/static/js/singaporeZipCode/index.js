$(document).ready(function(){

    layui.use(['layer', 'form','table'], function(){
        var layer = layui.layer
            ,form = layui.form
            ,table = layui.table;


        // 数据表格

        table.render({
            elem: '#idTest'
            ,height: 500
            ,url: "/sgBuilding/getSGBuildingList"//数据接口
            ,page: true
            ,cols: [[
                {type:'numbers',width:60}
                ,{field: 'saBuildingName', title: '名称'}
                ,{field: 'saZipCode', title: '邮编',width:100,align:'center'}
                ,{field: 'saBuildingAddress', title: '地址',minWidth:450}
                // ,{field: 'rallyPointState', title: '集结点状态'}
                ,{field: 'saBuildingLat', title: '纬度'}
                ,{field: 'saBuildingLng', title: '经度'}
                ,{field: 'singaporeAreaName', title: '所属区域'}
                ,{field: 'delState', title: '状态',templet:'#delState'}
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
        // 监听查询
        form.on('submit(*)', function(data){
            //执行重载

            if((data.field.state).length == 0){
                data.field.state = 0;
            }
            table.reload('idTest', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    saZipCode : $.trim(data.field.saZipCode),
                    state: $.trim(data.field.state)

                }
            });
            return false;
        });

    });



});
// 禁用
function disabled(id) {
    layer.confirm('确认禁用此信息吗？', {
        btn: ['确定', '取消'] //可以无限个按钮
    }, function (index, layero) {
        $.ajax({
            url: '/sgBuilding/delSGBuilding',
            data: {'saBuildingId':id},
            dataType: 'JSON',
            success: function (data) {
                layui.layer.msg(data.msg);
                data.msg == '修改成功' ? layui.table.reload('idTest') : '';
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
    layer.confirm('确认启用此信息吗？', {
        btn: ['确定', '取消'] //可以无限个按钮
    }, function (index, layero) {
        $.ajax({
            url: '/sgBuilding/recoveryBuilding',
            data: {'saBuildingId':id},
            dataType: 'JSON',
            success: function (data) {
                layui.layer.msg(data.msg);
                data.msg == '修改成功' ? layui.table.reload('idTest') : '';
            },
            error: function (data) {

            }
        })
    }, function (index) {
        //按钮【取消】的回调
    });
};
