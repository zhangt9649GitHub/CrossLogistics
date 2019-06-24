$(document).ready(function () {

    // 查看货物物流进程
    $.ajax({
        url: '/ordinaryThreeGoods/getOrdinaryGoodsLogisticById',
        dataType: 'JSON',
        data: {'goodsId':$('input[name = "goodsId"]').val()},
        success: function (data) {
            console.log(data);
            if(data.code == 20001){
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


    //货物信息
    $.ajax({
        url: '/ordinaryThreeGoods/getOrdinaryGoodsById',
        dataType: 'JSON',
        data: {'goodsId':$('input[name = "goodsId"]').val()},
        success: function (data) {

            if(data.code == 20001){
                var res = data.data;

                // 三方物流单号
                $('.goods-info .tripartiteNumber .inline span').text(res[0].tripartiteNumber);

                // 承运方
                $('.goods-info .shipper .inline span').text(res[0].shipper);

                // 货物类型
                $('.goods-info .goodType .inline span').text(res[0].goodType);

                // 邮编
                $('.goods-info .zipCode .inline span').text(res[0].zipCode);

                // 出境方式
                $('.goods-info .exitWay .inline span').text(res[0].exitWay);

                // 收货地址
                $('.goods-info .receiptAddress .inline span').text(res[0].receiptAddress);

                // 收货人
                $('.goods-info .receiptContact .inline span').text(res[0].receiptContact);

                // 收货人联系方式
                $('.goods-info .receiptContactMobile .inline span').text(res[0].receiptContactMobile);

                // 总箱数
                $('.goods-info .totalGoods .inline span').text(res[0].totalGoods);

                // 货物重量
                $('.goods-info .tripartiteWeight .inline span').text(res[0].tripartiteWeight);

                // 货值
                $('.goods-info .itemValue .inline span').text(res[0].itemValue);


            }
        }
    });



});