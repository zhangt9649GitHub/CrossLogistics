$(document).ready(function () {
    var usertype = $('#usertype').text();


    if(usertype == 1){
        $('.admin').show();
    }else{
        $('.sanfang').show();
    }


    // 后台
    $.ajax({
        url: '/statistics/getStatistics',
        success: function (data) {

            if(data.code == 20001){

                // 订单数
                $('.admin .dailyOrderQuantity .layuiadmin-big-font').text(data.data.dailyOrderQuantity);

                // 派送成功订单数
                $('.admin .toSendSuccessfully .layuiadmin-big-font').text(data.data.toSendSuccessfully);

                // 派送件货到付款总金额数
                $('.admin .totalAmountCollectOnDelivery .layuiadmin-big-font').text(data.data.totalAmountCollectOnDelivery);

                // 转运订单总收款(￥)
                $('.admin .transshipmentRmbCollection .layuiadmin-big-font span').text(data.data.transshipmentRmbCollection);

                // 转运订单总收款(￠)
                $('.admin .transshipmentCentCollection .layuiadmin-big-font span').text(data.data.transshipmentCentCollection);

                // 余额提现总支出(￥)
                $('.admin .withdrawalSpending .layuiadmin-big-font span').text(data.data.withdrawalSpending);

                // 转运订单新币收款（单位：分）
                $('.admin .transshipmentSGDCentCollection .layuiadmin-big-font span').text(data.data.transshipmentSGDCentCollection);

                // 余额提现总支出（单位：新币）
                $('.admin .withdrawalSGDSpending .layuiadmin-big-font span').text(data.data.withdrawalSGDSpending);

            }
        }
    });


    // 三方
    $.ajax({
        url: '/statistics/getThreeGoodsStatistics',
        success: function (data) {


            if(data.code == 20001){


                // 订单数量
                $('.sanfang .quantityDispatched .layuiadmin-big-font').text(data.data.quantityDispatched);

                // 已完成订单数量
                $('.sanfang .orderFulfillment .layuiadmin-big-font').text(data.data.orderFulfillment);

                // 订单异常件数量
                $('.sanfang .orderException .layuiadmin-big-font').text(data.data.orderException);

                // 未派送订单数量
                $('.sanfang .undeliveredQuantity .layuiadmin-big-font').text(data.data.undeliveredQuantity);

                // 货到付款应收总金额
                $('.sanfang .accountsReceivable .layuiadmin-big-font').text(data.data.accountsReceivable);

                // 货到付款实收金额
                $('.sanfang .realCollection .layuiadmin-big-font').text(data.data.realCollection);

            }
        }
    })



});
