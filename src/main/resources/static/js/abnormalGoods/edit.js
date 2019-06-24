$(document).ready(function(){

	layui.use(['layer', 'form','table'], function(){
		var layer = layui.layer
		  	,form = layui.form
	  	  	,table = layui.table;

		// 监听表单提交
        // 监听表单提交
        form.on('submit(add)', function(data){

            var zhengze = /^([0-9]+)(\+)?([0-9]+)$/;

            if($.trim(data.field.receiptContactMobile)){

                if(!zhengze.test(data.field.receiptContactMobile)){
                    layer.msg('发货人联系方式格式不正确');
                    return false;
                }
            }

            if($.trim(data.field.shipContactMobile)){

                if(!zhengze.test(data.field.shipContactMobile)){
                    layer.msg('发货人联系方式格式不正确');
                    return false;
                }
            }


            var that = this;
            // 禁止点击
            $(that).addClass('zq-submit-disabled');
            axios.post("/abnormalGoods/updateGoodsWarningDetails", data.field)
                .then(function (response) {
                    console.log(response)
                    // // 提示信息并跳转页面
                    layer.msg(response.data.msg);
                    if (response.data.msg == '更新成功') {
                        setTimeout(function () {
                            window.parent.location.reload(); //刷新父页面
                        }, 1000);
                    }
                    // 取消禁止点击
                    else {
                        $(that).removeClass('zq-submit-disabled');
                    }


                });
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });



		// 获取编辑信息
		$.ajax({
			url: '/abnormalGoods/getGoodsWarningDetails',
			data: {'goodsId':$('input[name = "goodsId"]').val()},
			dataType: 'JSON',
			success: function (data) {
				if(data.data != null){
					// 收货人
					$('input[name="receiptContact"]').val(data.data.receiptContact);
					// 收货人电话
					$('input[name = "receiptContactMobile"]').val(data.data.receiptContactMobile);
					//	收货人地址
					$('input[name = "receiptAddress"]').val(data.data.receiptAddress);
					//	邮编
					$('input[name = "zipCode"]').val(data.data.zipCode);
                    //	用户编号
                    $('input[name = "number"]').val(data.data.number);
					//	发货人
					$('input[name = "shipContact"]').val(data.data.shipContact);
					// 发货人电话
					$('input[name = "shipContactMobile"]').val(data.data.shipContactMobile);
					// 发货人地址
					$('input[name = "shipAddress"]').val(data.data.shipAddress);
					// 货物类型
					$('input[name = "goodType"]').val(data.data.goodType);
					// 承运方
					$('input[name = "shipper"]').val(data.data.shipper);
					// 三方物流单号
					$('input[name = "tripartiteNumber"]').val(data.data.tripartiteNumber);
					// 特殊货物
					// $('select[name="isSpecialGoods"] option').each(function (i,item) {
					// 	if($(item).val() == data.data.isSpecialGoods){
                    //         $(item).prop('selected',true);
                    //         form.render('select');
					// 	}
                    // });
					// 异常状态
                    $('select[name="warningStateNumber"] option').each(function (i,item) {

                        if($(item).attr('value') == data.data.warningStateNumber){
                            $(item).prop('selected',true);
                            form.render('select');
                        }
                    });
					// // 长
					// $('input[name = "actualLong"]').val(data.data.actualLong);
					// // 宽
					// $('input[name = "actualWidth"]').val(data.data.actualWidth);
					// // 高
					// $('input[name = "actualHeight"]').val(data.data.actualHeight);
					// // 重量
					// $('input[name = "actualWeight"]').val(data.data.actualWeight);
					// 异常类型
					$('input[name = "warningType"]').val(data.data.warningType);

                    //总箱数
                    $('input[name = "totalGoods"]').val(data.data.totalGoods);
					// 异常描述
					$('textarea[name = "abnormalText"]').text(data.data.abnormalText);

                    // 处理描述
                    $('textarea[name = "dealComment"]').text(data.data.dealComment);
				}

            }
		})
	});



});