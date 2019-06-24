$(function () {



    // 消息提醒

    $.ajax({
        url: '/CarMaintenance/getMessagesOfCarAndGoods',
        data: 'JSON',
        success: function (data) {
            console.log(data);
            if(data.code == 30043){

                // $.each(data.data.carMaintenances,function (i,n) {
                //     $('.list').append('<li class="item"><p><em>系统：</em>有小车需要维护，请您及时处理！小车编号：<span>'+n+'</span></p></li>');
                // });

                $.each(data.data.goodsWaringMessage,function (i,n) {
                    $('.list').append('<li class="item"><p><em>系统：</em>有新的异常货物，请您及时处理！快递单号：<span>'+n+'</span></p></li>');
                })
            }
        }
    })

});