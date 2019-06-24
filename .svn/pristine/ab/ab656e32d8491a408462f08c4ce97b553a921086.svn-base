$(function () {
    //	判断用户是否失效
    if(!($('.container .zq-username').text())){
        console.log($('.container .zq-username').text());
        window.location.href="/admin/index/login"
    }


    // 消息提醒

    $.ajax({
        url: '/CarMaintenance/getMessagesOfCarAndGoods',
        dataType: 'JSON',
        success: function (data) {
            var res = data.data;
            if(data.code == 30043){
                var carLength = 0,goodsLength = 0;

                // if(res.carMaintenances != null){
                //     carLength = (res.carMaintenances).length;
                // }
                if(res.goodsWaringMessage != null){
                    goodsLength = (res.goodsWaringMessage ).length;
                }
                var length = carLength + goodsLength;
                $('#remind span').html(length);
            }else if(data.code == 30042){
                $('#remind span').hide();
            }
        }
    })

});