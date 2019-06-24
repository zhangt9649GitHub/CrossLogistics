$(document).ready(function(){

	layui.use(['layer', 'form','table','laydate'], function(){
		var layer = layui.layer
		  	,form = layui.form
	  	  	,table = layui.table
	  	  	,laydate = layui.laydate;

	  	// 订单时间
	  	laydate.render({
	  		elem: "#order-time"
	  	});


        // 数据表格

        table.render({
            elem: '#idTest'
            ,height: 500
            ,url: "/carOrder/taskOrderAll"//数据接口
            ,page: true

            ,cols: [[
                {field: 'orderNumber', title: '订单编号'}
                ,{field: 'name', title: '订单名称'}
                ,{field: 'userName', title: '指派用户'}
                ,{field: 'singaporeAreaName', title: '所属区域'}
                ,{field: 'totalMoney', title: '订单金额',align:'right'}
                // ,{field: 'addMoney', title: '订单奖励金额',align:'right'}
                ,{field: 'totalIntegral', title: '订单积分',align:'right'}
                // ,{field: 'addIntegral', title: '订单奖励积分',align:'right'}
                ,{field: 'carNumber', title: '小车编号'}

                ,{field: 'state', title: '订单状态',align:'center',templet:'#stateID'}
                ,{field: 'createTime', title: '订单时间',align:'center'}
                ,{field: 'grabOrderStatus', title: '抢单状态',align:'center' ,templet:'#grabOrderStatus'}


                ,{field: 'grabOrderTime', title: '抢单时间',align:'center'}
                ,{align:'center', toolbar: '#barDemo',title: '操作',minWidth:300}
            ]]
            // ,autoSort: false //禁用前端自动排序。注意：该参数为 layui 2.4.4 新增
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



        //搜索

        form.on('submit(*)', function(data){
            //执行重载
            console.log(data.field.singaporeAreaId);
            if((data.field.singaporeAreaId).length ==0){
                data.field.singaporeAreaId = 0;
            }
            if((data.field.rallyPointId).length ==0){
                data.field.rallyPointId = 0;
            }
            if((data.field.taskOrderStatus).length ==0){
                data.field.taskOrderStatus = 0;
            }
            table.reload('idTest', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    orderNumber: $.trim(data.field.orderNumber),
                    singaporeAreaId: $.trim(data.field.singaporeAreaId),
                    orderTime: $.trim(data.field.orderTime),
                    taskOrderStatus: $.trim(data.field.taskOrderStatus),
                    rallyPointId: $.trim(data.field.rallyPointId)

                }
            });
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });

	});



});