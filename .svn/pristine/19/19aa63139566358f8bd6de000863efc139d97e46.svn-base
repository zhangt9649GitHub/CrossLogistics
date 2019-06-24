$(document).ready(function () {

    layui.use(['element','form'],function(){
        var form = layui.form
            ,element = layui.element;





        // 监听表单提交

        form.on('submit(*)',function(data){
            var field = data.field;
            for(let i in field){
                if(field[i] < 0){
                    layer.msg('参数不能设置为负数');
                    return false;
                }
            }

            // var zhengze = /^([0-9]+)(\+)?([0-9]+)$/;

            // if(!zhengze.test(field.STC)){
            //     layer.msg('客服电话格式不正确');
            //     return false;
            // }

            if(field.generateOrderTimeLimitCar){
                // console.log((field.generateOrderTimeLimitCar).indexOf('.'));
                if((field.generateOrderTimeLimitCar).indexOf('.') != -1){
                    layer.msg('参数必须为整数');
                    return false;
                }
            }
            if(field.generateOrderTimeLimitTruck){
                // console.log((field.generateOrderTimeLimitCar).indexOf('.'));
                if((field.generateOrderTimeLimitTruck).indexOf('.') != -1){
                    layer.msg('参数必须为整数');
                    return false;
                }
            }
            // return false;
            $.ajax({
                url: "/adminconfig/updateAdminConfig",
                type: "post",
                data: JSON.stringify(data.field),
                contentType: "application/json",
                success: function (data) {
                    layer.msg(data.msg);
                    if(data.code == 30003){
                        setTimeout(function () {
                            window.href= location.reload();
                        }, 500);

                    }
                }
            });
            return false;
        });

        // 获取信息
        $.ajax({
            url:'/adminconfig/getAdminConfiglist',
            dataType: 'JSON',
            success: function (data) {
                if(data.code == 30001){
                    var res = data.data;
                    if(res != null){

                        for(let i in res){

                        // 基本参数设置
                            // 骑手配送货物金额（每件）

                            if(res[i].key == 'anOrderPrice'){
                                console.log(res[i].value);
                                $('input[name ="anOrderPrice"]').val(res[i].value)
                            }
                            //   货车配送货物金额（每袋）

                            if(res[i].key == 'anBagPrice'){
                                console.log(res[i].value);
                                $('input[name ="anBagPrice"]').val(res[i].value)
                            }

                            // 客服电话
                            if(res[i].key == 'STC'){
                                $('input[name ="STC"]').val(res[i].value)
                            }

                            // 异常件扣费
                            if(res[i].key == 'waringGoodsPrice'){
                                $('input[name ="waringGoodsPrice"]').val(res[i].value)
                            }


                            // 汇率换算
                            if(res[i].key == 'SGDtoCNYExchangeFrate'){
                                $('input[name ="SGDtoCNYExchangeFrate"]').val(res[i].value)
                            }

                            // 汇率换算
                            if(res[i].key == 'USDtoCNYExchangeFrate'){
                                $('input[name ="USDtoCNYExchangeFrate"]').val(res[i].value)
                            }

                            // 汇率换算
                            if(res[i].key == 'SGDtoUSDtoExchangeFrate'){
                                $('input[name ="SGDtoUSDtoExchangeFrate"]').val(res[i].value)
                            }

                        // 任务订单设置
                            // 生成小车订单时间
                            if(res[i].key == 'generateOrderTimeLimitCar'){
                                $('input[name ="generateOrderTimeLimitCar"]').val(res[i].value)
                            }

                            // 生成货车订单时间
                            if(res[i].key == 'generateOrderTimeLimitTruck'){
                                $('input[name ="generateOrderTimeLimitTruck"]').val(res[i].value)
                            }

                         // 运费设置
                            // 普货：0.5kg以内
                            if(res[i].key == 'regularAirPriceOne'){
                                $('input[name ="regularAirPriceOne"]').val(res[i].value)
                            }
                            // 普货：0.5kg以上每超过0.5kg加收
                            if(res[i].key == 'regularAirPriceTwo'){
                                $('input[name ="regularAirPriceTwo"]').val(res[i].value)
                            }

                            // 普货：超过10kg-50kg 每千克
                            if(res[i].key == 'regularAirPriceThree'){
                                $('input[name ="regularAirPriceThree"]').val(res[i].value)
                            }

                            // 普货：超过50kg-100kg 每千克
                            if(res[i].key == 'regularAirPriceFour'){
                                $('input[name ="regularAirPriceFour"]').val(res[i].value)
                            }

                            // 超过100kg 每千克
                            if(res[i].key == 'regularAirPriceFive'){
                                $('input[name ="regularAirPriceFive"]').val(res[i].value)
                            }

                            // 敏感：0.5kg以内
                            if(res[i].key == 'sensitiveAirPriceOne'){
                                $('input[name ="sensitiveAirPriceOne"]').val(res[i].value)
                            }

                            // 敏感：0.5kg以上每超过0.5kg
                            if(res[i].key == 'sensitiveAirPriceTwo'){
                                $('input[name ="sensitiveAirPriceTwo"]').val(res[i].value)
                            }

                            // 敏感：超过10kg-50kg
                            if(res[i].key == 'sensitiveAirPriceThree'){
                                $('input[name ="sensitiveAirPriceThree"]').val(res[i].value)
                            }

                            // 敏感：超过10kg-50kg
                            if(res[i].key == 'sensitiveAirPriceFour'){
                                $('input[name ="sensitiveAirPriceFour"]').val(res[i].value)
                            }

                            // 1-5立方
                            if(res[i].key == 'marinePriceOne'){
                                $('input[name ="marinePriceOne"]').val(res[i].value)
                            }

                            // 5-10立方
                            if(res[i].key == 'marinePriceTwo'){
                                $('input[name ="marinePriceTwo"]').val(res[i].value)
                            }

                            // 10-20立方
                            if(res[i].key == 'marinePriceThree'){
                                $('input[name ="marinePriceThree"]').val(res[i].value)
                            }

                            // 20立方+
                            if(res[i].key == 'marinePriceFour'){
                                $('input[name ="marinePriceFour"]').val(res[i].value)
                            }

                            // 空运：货物长、宽、高是否有>=150cm  加收
                            if(res[i].key == 'unilateralOverlengthPrice'){
                                $('input[name ="unilateralOverlengthPrice"]').val(res[i].value)
                            }
                            // 空运：货物重量是否有>=40kg  加收
                            if(res[i].key == 'overweightPrice'){
                                $('input[name ="overweightPrice"]').val(res[i].value)
                            }

                            // 空运和海运： 是否紧急加收
                            if(res[i].key == 'emergencyDeliveryPrice'){
                                $('input[name ="emergencyDeliveryPrice"]').val(res[i].value)
                            }

                            // 货品超过2021RMB（SGD400）交GST税
                            if(res[i].key == 'GSTPrice'){
                                $('input[name ="GSTPrice"]').val(res[i].value)
                            }

                            // // 缴纳GST税描述
                            // if(res[i].key == 'ExceedGSTPrompt'){
                            //     $('input[name ="ExceedGSTPrompt"]').val(res[i].value)
                            // }

                            // 税率
                            if(res[i].key == 'rate'){
                                $('input[name ="rate"]').val(res[i].value)
                            }

                            // permit费用
                            if(res[i].key == 'permit'){
                                $('input[name ="permit"]').val(res[i].value)
                            }

                            // 海运敏感货每单加收
                            if(res[i].key == 'sensitiveMarinePrice'){
                                $('input[name ="sensitiveMarinePrice"]').val(res[i].value)
                            }



                        }


                    }
                }
            }
        })


    })



});
