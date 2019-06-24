$(document).ready(function(){

	layui.use(['layer', 'form','table','upload','laydate'], function(){
		var layer = layui.layer
		  	,form = layui.form
	  	  	,table = layui.table
			,upload = layui.upload
            ,laydate = layui.laydate;

        // 开始时间
        laydate.render({
            elem: '#addTime'
        });
        // 结束时间
        laydate.render({
            elem: '#endTime'
        });

        // 数据表格

        table.render({
            elem: '#idTest'
            ,height: 500
            ,url: "/ordinaryThreeGoods/getOrdinaryGoodsList"//数据接口
            ,page: true
            ,cellMinWidth:80
            ,cols: [[
                // {type:'numbers'}
                {field: 'deliveryNumber', title: '快递单号',minWidth:160}
                ,{field: 'tripartiteNumber', title: '货物单号',minWidth:160}
                ,{field: 'goodType', title: '货物类型',align:'center'}
                ,{field: 'zipCode', title: '邮编',align:'center'}
                ,{field: 'operateResult', title: '操作进程',align:'center',minWidth:240}
                ,{field: 'addTime', title: '创建时间'}
                ,{align:'center', toolbar: '#barDemo',title: '操作',minWidth:350}
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
        	console.log(data);
            layer.closeAll('page');




            table.reload('idTest', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    tripartiteNumber: $.trim(data.field.tripartiteNumber),
                    deliveryNumber: $.trim(data.field.deliveryNumber),
                    addTime: $.trim(data.field.addTime),
                    endTime: $.trim(data.field.endTime)

                }
            });
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });

    	// 导入
        upload.render({
            elem: '#import'
            ,url: '/ordinaryThreeGoods/importOrdinaryExcel'
			,accept: 'file'
            ,done: function(res, index, upload){ //上传后的回调
                // layui.layer.msg(res.msg);

                layer.alert(res.msg, {
                    // skin: 'layui-layer-molv' //样式类名
                    closeBtn: 0
                    ,anim: 4 //动画类型
                }, function(index){
                    layer.close(index);
                    res.code == 20009 ? layui.table.reload('idTest'): '';
                });
            }

        })


    });





});

// 删除
function delthis(id) {
    layer.confirm('确认删除吗？', {
        btn: ['确定', '取消'] //可以无限个按钮
    }, function (index, layero) {


        axios.get('/ordinaryThreeGoods/deleteOrdinaryGoodsById?goodsId=' + id)
            .then(function (response) {
                layui.layer.msg(response.data.msg);
                response.data.code == 20007 ? layui.table.reload('idTest') : '';
            });
    }, function (index) {
        //按钮【取消】的回调
    });
};
