$(document).ready(function(){

	layui.use(['layer', 'form','table','laydate'], function(){
		var layer = layui.layer
		  	,form = layui.form
	  	  	,table = layui.table
	  	  	,laydate = layui.laydate;


	  	laydate.render({
	  	  	elem:'#start-item'
            ,type: 'datetime'
	  	});
	  	laydate.render({
	  		elem:'#end-item'
            ,type: 'datetime'
	  	});

        // 数据表格

        table.render({
            elem: '#idTest'
            ,height: 500
            ,url: "/withdrawApplication/getWithdrawApplicationList"//数据接口
            ,page: true

            ,cols: [[
                {type:'numbers'}
                ,{field: 'withdrawOrderNumber', title: '提现订单号'}
                ,{field: 'userTrueName', title: '提现人'}
                ,{field: 'withdrawRole', title: '提现角色'}
                ,{field: 'withdrawMoney', title: '提现金额'}
                ,{field: 'unit', title: '单位',templet: '#unit'}
                ,{field: 'withdrawWay', title: '提现方式'}
                ,{field: 'withdrawType', title: '提现状态',templet: '#withdrawType'}
                ,{field: 'addTime', title: '提现时间'}
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

        form.on('submit(*)', function(data){
            //执行重载
            table.reload('idTest', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    addTime: $.trim(data.field.addTime),
                    endTime: $.trim(data.field.endTime),
                    withdrawRole: $.trim(data.field.withdrawRole)

                }
            });
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });

	});



});

// 银行卡提现
function cashWithdrawal1(id) {
	$.ajax({
		url: '/withdrawApplication/getWithdrawInfoByCard',
		data: {'withdrawId': id},
		dataType: 'JSON',
		success: function (data) {
			if(data.code == 0){
				if(data.data != null){
					var str = '<div class="layui-form layui-form-pane zq-form-confirm">' +
									'<div class="layui-form-item zq-title">是否确认提现？</div>'+
									'<div class="layui-form-item">' +
										'<label class="layui-form-label">银行</label>'+
										'<div class="layui-input-inline">' +
											'<input type="text" class="layui-input" readonly value="'+ data.data.bankName +'" />'+
										'</div>'+
									'</div>'+
									'<div class="layui-form-item">' +
										'<label class="layui-form-label">卡号</label>'+
										'<div class="layui-input-inline">' +
											'<input type="text" class="layui-input" readonly value="'+ data.data.cardNumber +'" />'+
										'</div>'+
									'</div>'+
									'<div class="layui-form-item">' +
										'<label class="layui-form-label">姓名</label>'+
										'<div class="layui-input-inline">' +
											'<input type="text" class="layui-input" readonly value="'+ data.data.cardName +'" />'+
										'</div>'+
									'</div>'+
									// '<div class="layui-form-item">' +
									// 	'<label class="layui-form-label">开户行</label>'+
									// 	'<div class="layui-input-inline">' +
									// 		'<input type="text" class="layui-input" readonly value="'+ data.data.openingBank +'" />'+
									// 	'</div>'+
									// '</div>'+


						'</div>';
                    layer.confirm(str, {btn: ['确定', '取消'], title: "信息",area: '500px'}, function () {
                        //按钮【确定】的回调
                        axios.get('/withdrawApplication/updateWithdrawApplication?withdrawId=' + id)
                            .then(function (response) {
                                // console.log(response);
                                layui.layer.msg('提现成功');
                                response.data.code == 0 ? layui.table.reload('idTest') : '';
                            });
                    });

                }
			}
        }
	});

}

// 普通提现
function cashWithdrawal2(id){
    $.ajax({
        url: '/withdrawApplication/getWithdrawInfoByCard',
        data: {'withdrawId': id},
        dataType: 'JSON',
        success: function (data) {
            if(data.code == 0){
                if(data.data != null){
                    var str = '<div class="layui-form layui-form-pane zq-form-confirm">' +
                        '<div class="layui-form-item zq-title">是否确认提现？</div>'+
                        '<div class="layui-form-item">' +
                        '<label class="layui-form-label">提现方式</label>'+
                        '<div class="layui-input-inline">' +
                        '<input type="text" class="layui-input" readonly value="'+ data.data.withdrawWay +'" />'+
                        '</div>'+
                        '</div>'+
                        '<div class="layui-form-item">' +
                        '<label class="layui-form-label">账号</label>'+
                        '<div class="layui-input-inline">' +
                        '<input type="text" class="layui-input" readonly value="'+ data.data.account +'" />'+
                        '</div>'+
                        '</div>'+
                        '<div class="layui-form-item">' +
                        '<label class="layui-form-label">姓名</label>'+
                        '<div class="layui-input-inline">' +
                        '<input type="text" class="layui-input" readonly value="'+ data.data.userTrueName +'" />'+
                        '</div>'+
                        '</div>'+
                        // '<div class="layui-form-item">' +
                        // 	'<label class="layui-form-label">开户行</label>'+
                        // 	'<div class="layui-input-inline">' +
                        // 		'<input type="text" class="layui-input" readonly value="'+ data.data.openingBank +'" />'+
                        // 	'</div>'+
                        // '</div>'+


                        '</div>';
                    layer.confirm(str, {btn: ['确定', '取消'], title: "信息",area: '500px'}, function () {
                        //按钮【确定】的回调
                        axios.get('/withdrawApplication/updateWithdrawApplication?withdrawId=' + id)
                            .then(function (response) {
                                // console.log(response);
                                layui.layer.msg('提现成功');
                                response.data.code == 0 ? layui.table.reload('idTest') : '';
                            });
                    });

                }
            }
        }
    });
}