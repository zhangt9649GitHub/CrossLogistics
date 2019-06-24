$(document).ready(function(){

	layui.use(['layer', 'form','table'], function(){
		var layer = layui.layer
		  	,form = layui.form
	  	  	,table = layui.table;

        // 数据表格

        table.render({
            elem: '#idTest'
            ,height: 500
            ,url: "/bag/getBagList"//数据接口
            ,page: true
            ,cols: [[
                {field: 'bagNumber', title: '货袋编号'}
                ,{field: 'licensePlate', title: '关联货车牌照'}
                ,{field: 'carNumber', title: '关联小车编号'}
                ,{field: 'lastWarehouseName', title: '所属仓库'}
                ,{field: 'lastWpNumber', title: '所属仓位'}
                ,{field: 'singaporeAreaName', title: '所属自定义区域'}
                ,{field: 'rallyPointName', title: '所属集结地'}
                ,{field: 'state', title: '状态',align:'center' , templet:'#state'}
                ,{align:'center', toolbar: '#barDemo',title: '操作'}
            ]]
            // ,autoSort: false //禁用前端自动排序。注意：该参数为 layui 2.4.4 新增
        });

        //监听排序事件
        table.on('sort(demo)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            // console.log(obj.field); //当前排序的字段名
            // console.log(obj.type); //当前排序类型：desc（降序）、asc（升序）、null（空对象，默认排序）
            // console.log(this); //当前排序的 th 对象

            //尽管我们的 table 自带排序功能，但并没有请求服务端。
            //有些时候，你可能需要根据当前排序的字段，重新向服务端发送请求，从而实现服务端排序，如：
            table.reload('idTest', {
                initSort: obj //记录初始排序，如果不设的话，将无法标记表头的排序状态。
                ,where: { //请求参数（注意：这里面的参数可任意定义，并非下面固定的格式）
                    field: obj.field //排序字段
                    ,order: obj.type //排序方式
                }
            });

            // layer.msg('服务端排序。order by '+ obj.field + ' ' + obj.type);
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

        //搜索
        form.on('submit(*)', function(data){
            //执行重载
            table.reload('idTest', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    bagNumber: $.trim(data.field.bagNumber),
                    // singaporeAreaName: data.field.singaporeAreaName,
                    licensePlate: $.trim(data.field.licensePlate),
                    carNumber: $.trim(data.field.carNumber),
                    singaporeAreaId: $.trim(data.field.singaporeAreaId),
                    rallyPointId: $.trim(data.field.rallyPointId)

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
            url: '/bag/deleteBagById',
            data: {'bagId':id},
            dataType: 'JSON',
            success: function (data) {
                layui.layer.msg(data.msg);
                data.msg == '删除成功' ? layui.table.reload('idTest') : '';
            },
            error: function (data) {

            }
        })
    }, function (index) {
        //按钮【取消】的回调
    });
};