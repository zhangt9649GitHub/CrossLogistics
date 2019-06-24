$(document).ready(function(){

	layui.use(['layer', 'form','table'], function(){
		var layer = layui.layer
		  	,form = layui.form
	  	  	,table = layui.table;

        // 数据表格
        table.render({
            elem: '#idTest'
            ,height: 500
            ,url: "/warehouse/getwarehouselist" // 数据接口
            ,page: true // 开启分页
            ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [[ // 表头
                {field: 'warehouseNumber', title: '仓库编号',fixed: 'left'}
                ,{field: 'warehouseName', title: '仓库名称'}
                ,{field: 'warehouseAddress', title: '仓库地点'}
                ,{field: 'warehousePhone', title: '联系方式'}
                ,{field: '', title: '操作', toolbar: '#barDemo',align:'center'}
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
        form.on('submit(query)', function(data){
            table.reload('idTest', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    warehouseName	: $.trim(data.field.warehouseName)	,
                    warehouseNumber: $.trim(data.field.warehouseNumber),
                    warehousePhone: $.trim(data.field.warehousePhone)

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
        //按钮【确定】的回调
        axios.get('/warehouse/deletewarehousebyid?warehouseId=' + id)
            .then(function (response) {
                // console.log(response);
                layui.layer.msg(response.data.msg);
                response.data.msg == '删除成功' ? layui.table.reload('idTest') : '';
            });
    }, function (index) {
        //按钮【取消】的回调
    });
};