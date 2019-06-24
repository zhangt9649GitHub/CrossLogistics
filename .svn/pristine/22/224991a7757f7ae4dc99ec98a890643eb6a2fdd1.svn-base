$(document).ready(function(){

	layui.use(['layer', 'form','table','laydate'], function(){
		var layer = layui.layer
		  	,form = layui.form
	  	  	,table = layui.table
	  	  	,laydate = layui.laydate;

        // 包裹信息-货袋
		//
        // table.render({
        //     elem: '#idTest'
        //     ,height: 500
        //     ,url: "/transshipment/getBagByTransshipmentGoodsId"//数据接口
        //     ,page: false
        //     ,cols: [[
        //         {type:'numbers'}
        //         ,{field: 'bagNumber', title: '货袋编号'}
        //         ,{field: 'carNumber', title: '关联小车信息'}
        //         ,{field: 'singaporeAreaName', title: '所属自定义区域'}
        //         ,{field: 'initWarehouseName', title: '入库仓'}
        //         ,{field: 'initWpNumber', title: '入库仓位'}
        //         ,{field: 'lastWarehouseName', title: '出库仓'}
        //         ,{field: 'lastWpNumber', title: '出库仓位'}
        //         ,{field: 'state', title: '状态信息'}
		//
		//
        //     ]]
        //     ,autoSort: false //禁用前端自动排序。注意：该参数为 layui 2.4.4 新增
        // });
		//
        // table.reload('idTest', {
        //     where: {
        //         goodsId: $('input[name = "goodsId"]').val()
		//
		//
        //     }
        // });
        // 查看转运货物物流进程
        $.ajax({
            url: '/transshipment/getTransshipmentGoodsLogisticInfo',
            dataType: 'JSON',
            data: {'goodsId':$('input[name = "goodsId"]').val()},
            success: function (data) {
                // console.log(data);
                if(data.code == 0){
                    var res = data.data;
                    if(res.length != 0){
                        $('.layui-timeline .no-process').hide();
                        $.each(res , function (i,t) {
                            if(i == 0){
                                $('.layui-timeline').append('<li class="layui-timeline-item">' +
                                    '<i class="layui-icon layui-timeline-axis">&#xe643;</i>'+
                                    '<div class="layui-timeline-content layui-text">'+
                                    '<h3 class="layui-timeline-title">'+ t.createTime +'</h3>'+
                                    '<p>'+ t.operateResult +'</p>'+
                                    '</div>'+
                                    '</li>');
                            }else{
                                $('.layui-timeline').append('<li class="layui-timeline-item">' +
                                    '<i class="layui-icon layui-timeline-axis">&#xe63f;</i>'+
                                    '<div class="layui-timeline-content layui-text">'+
                                    '<h3 class="layui-timeline-title">'+ t.createTime +'</h3>'+
                                    '<p>'+ t.operateResult +'</p>'+
                                    '</div>'+
                                    '</li>');
                            }

                        })

                    }
                }

            }
        });


    	// 转运货物详细信息
		$.ajax({
			url: '/transshipment/getTransshipmentGoodsDetails',
			dataType: 'JSON',
            data: {'goodsId':$('input[name="goodsId"]').val()},
			success: function (data) {
				if(data.data != null){
					// 收货人
					$('.order-info .receiptContact .inline span').text(data.data.receiptContact);
					// 快递单号
                    $('.order-info .deliveryNumber .inline span').text(data.data.deliveryNumber);
                    // 邮编
                    $('.order-info .zipCode .inline span').text(data.data.zipCode);
                    // 创建时间
                    $('.order-info .addTime .inline span').text(data.data.addTime);
                    // 支付时间
                    $('.order-info .createTime .inline span').text(data.data.createTime);
                    // 联系方式
                    $('.order-info .receiptContactMobile .inline span').text(data.data.receiptContactMobile);
                    // 转运类型
                    $('.order-info .transportType .inline span').text(data.data.transportType);
                    // 转运费用
                    $('.order-info .amount .inline span').text(data.data.amount);
                    // 状态
                    $('.order-info .paymentStatus .inline span').text(data.data.paymentStatus);
                    // 收货地址
                    $('.order-info .receiptAddress .inline span').text(data.data.receiptAddress);

                    //GST
                    $('.order-info .gstPrice .inline span').text(data.data.gstPrice);
				}
            }
		});


        // 包裹信息-货袋

        $.ajax({
            url: '/transshipment/getBagByTransshipmentGoodsId',
            dataType: 'JSON',
            data: {'goodsId':$('input[name="goodsId"]').val()},
            success: function (data) {
               if(data.code == 0){
               		if(data.data != null){
               			// 货袋编号
						$('.table .bagNumber').text(data.data.bagNumber);
                        // 关联小车信息
                        $('.table .carNumber').text(data.data.carNumber);
                        // 关联区域
                        $('.table .singaporeAreaName').text(data.data.singaporeAreaName);
                        // 入库仓
                        $('.table .initWarehouseName').text(data.data.initWarehouseName);
                        // 入库仓位
                        $('.table .initWpNumber').text(data.data.initWpNumber);
                        // 出库仓
                        $('.table .lastWarehouseName').text(data.data.lastWarehouseName);
                        // 出库仓位
                        $('.table .lastWpNumber').text(data.data.lastWpNumber);
                        // 状态
                        var state ;
                        switch (data.data.state) {
                            case 1:
                                state = '损毁';
                                break;
                            case 2:
                                state = '打包';
                                break;
                            case 3:
                                state = '入库';
                                break;
                            case 4:
                                state = '货袋出库';
                                break;
                            case 5:
                                state = '货袋送出';
                                break;
                            case 6:
                                state = '大货车运载中';
                                break;
                            case 7:
                                state = '小车运载中';
                                break;
                            case 8:
                                state = '送货完成';
                                break;
                            case 9:
                                state = '送货完但内有异常件';
                                break;
                            case 10:
                                state = '货袋接收';
                                break;
                            // case 11:
                            //     state = '异常件送回仓库';
                            //     break;

                        }

                        $('.table .state').text(state);

					}
			   }
            }
        })

	});



});