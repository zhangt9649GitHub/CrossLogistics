$(document).ready(function(){

	layui.use(['layer', 'form','table'], function(){
		var layer = layui.layer
		  	,form = layui.form
	  	  	,table = layui.table;
        // 数据表格

        table.render({
            elem: '#idTest'
            ,height: 500
            ,url: "/abnormalGoods/getGoodsWarningList"//数据接口
            ,page: true

            ,cols: [[
                // {type:'numbers'}
                {field: 'deliveryNumber', title: '快递单号'}
                ,{field: 'tripartiteNumber', title: '货物单号'}
                ,{field: 'from', title: '货物来源'}
                ,{field: 'goodData', title:'货物数据（长*宽*高，重量）',align:'center',minWidth:240}
                // ,{field: 'goodType', title: '货物类型'}
                ,{field: 'warningState', title: '异常状态'}
                ,{field: 'warningComment', title: '异常描述'}
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
        // 监听表单提交
        form.on('submit(add)', function(data){
            //执行重载
            table.reload('idTest', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    tripartiteNumber: $.trim(data.field.tripartiteNumber),
                    warningStateNumber : $.trim(data.field.warningStateNumber),
                    deliveryNumber: $.trim(data.field.deliveryNumber)

                }
            });
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });


	});



});