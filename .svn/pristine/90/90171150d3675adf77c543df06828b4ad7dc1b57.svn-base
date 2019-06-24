$(document).ready(function(){

	layui.use(['layer', 'form','table','upload','laydate'], function(){
		var layer = layui.layer
		  	,form = layui.form
	  	  	,table = layui.table
			,upload = layui.upload
            ,laydate = layui.laydate;

        // 数据表格

        table.render({
            elem: '#idTest'
            ,height: 500
            ,url: "/taskOrder/taskOrderAll"//数据接口
            ,page: true

            ,cols: [[
                {type:'numbers'}
                ,{field: 'orderNumber', title: '任务订单编号'}
                ,{field: 'name', title: '订单名称'}
                ,{field: 'totalMoney', title: '订单金额',align:'center'}
                ,{field: 'totalIntegral', title: '订单积分',align:'center'}
                ,{field: 'singaporeAreaName', title: '订单区域',align:'center'}
                ,{field: 'type', title: '订单类型',align:'center'}
                ,{field: 'state', title: '订单状态',align:'center',templet:'#stateID'}
                ,{field: 'createTime', title: '订单时间', sort: true}
                ,{align:'center', toolbar: '#barDemo',title: '操作',minWidth:200}
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
                    search: $.trim(data.field.search),
                    singaporeAreaId: $.trim(data.field.singaporeAreaId),
                    state: $.trim(data.field.state),
                    type: $.trim(data.field.type),
                    startTime: $.trim(data.field.startTime),
                    endTime: $.trim(data.field.endTime)

                }
            });
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });


        // 开始时间
        laydate.render({
            elem: '#startTime' //指定元素
        });
        // 结束时间
        laydate.render({
            elem: '#endTime' //指定元素
        });

        // 查询区域下拉框数据
        $.ajax({
            url: '/appUserAddress/appUserAdressAreaAll',
            dataType: 'JSON',
            success: function (data) {
                if(data.data != null){
                    var res = data.data;
                    console.log(res);
                    console.log( $('#singaporeAreaIds'));
                    for(let i in res){
                        $('#singaporeAreaId').append('<option value="'+ res[i].singaporeAreaId +'">'+ res[i].singaporeAreaName +'</option>');
                    }
                    form.render();
                }
            }
        })




    });





});


