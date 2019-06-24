$(document).ready(function(){

	layui.use(['layer', 'form','table'], function(){
		var layer = layui.layer
		  	,form = layui.form
	  	  	,table = layui.table;


	  	// 数据表格

		table.render({
			elem: '#idTest'
			,height: 500
			,url: "/area/getSingaporeAreaList"//数据接口
			,page: true
		
			,cols: [[
                {type:'numbers'}
				,{field: 'singaporeAreaName', title: '名称'}
				// ,{field: 'singaporeAreaAtitudeLongitude', title: '位置'}
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
            table.reload('idTest', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    singaporeAreaName : $.trim(data.field.singaporeAreaName)


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
			url: '/area/delsingaporearea',
			data: {'singaporeAreaId':id},
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

// 一键分配大楼
function fenpei(id) {
    layer.confirm('是否一键分配大楼？', {
        btn: ['确定', '取消'] //可以无限个按钮
    }, function (index, layero) {
        var index = layer.load(1, {
            shade: [0.1,'#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url: '/area/subArea',
            data: {'singaporeAreaId':id},
            dataType: 'JSON',
            type:'post',
            success: function (data) {
                layer.close(index);
                layui.layer.msg(data.msg);
                data.code == 0 ? layui.table.reload('idTest') : '';
            },
            error: function (data) {

            }
        })
    }, function (index) {
        //按钮【取消】的回调
    });
};
// 一键分配集结地
function fenpeis(id) {
    layer.confirm('是否一键分配大楼到集结地（注：请先进行分配大楼操作在进行此操作）？', {
        btn: ['确定', '取消'] //可以无限个按钮
    }, function (index, layero) {
        var index = layer.load(1, {
            shade: [0.1,'#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url: '/area/subRallyPoint',
            data: {'singaporeAreaId':id},
            dataType: 'JSON',
            type:'post',
            success: function (data) {
                layer.close(index);
                layui.layer.msg(data.msg);
                data.code == 0 ? layui.table.reload('idTest') : '';
            },
            error: function (data) {

            }
        })
    }, function (index) {
        //按钮【取消】的回调
    });
};


