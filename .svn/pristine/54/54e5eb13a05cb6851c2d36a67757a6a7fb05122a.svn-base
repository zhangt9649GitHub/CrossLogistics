$(document).ready(function () {




    layui.use(['layer','table'], function() {
        var layer = layui.layer
            , table = layui.table;



    });

    // 获取查看信息
    $.ajax({
        url: '/taskOrder/taskOrderDetail',
        data: {'taskOrderId':$('input[name = taskOrderId]').val()},
        dataType: 'JSON',
        success: function (data) {
            if(data.data != null){
                // 订单编号
                $('.task-info .orderNumber .inline span').text(data.data.orderNumber);
                // 订单名称
                $('.task-info .name .inline span').text(data.data.name);
                // 所属区域
                $('.task-info .singaporeAreaName .inline span').text(data.data.singaporeAreaName);
                // 订单类型
                $('.task-info .type .inline span').text(data.data.type);
                // 订单金额
                $('.task-info .totalMoney .inline span').text(data.data.totalMoney);
                // 超时奖励金额
                $('.task-info .addMoney .inline span').text(data.data.addMoney);
                // 订单状态
                var state;
                switch(data.data.state){
                    case 1:
                       state = '已创建';
                        break;
                    case 2:
                        state = '已预约';
                        break;
                    case 3:
                        state = '进行中';
                        break;
                    case 4:
                        state = '已确认';
                        break;
                    case 5:
                        state = '已完成';
                        break;
                    case 6:
                        state = '已取消';
                        break;
                }
                $('.task-info .state .inline span').text(state);
                // 订单积分
                $('.task-info .totalIntegral .inline span').text(data.data.totalIntegral);
                // 超时奖励积分
                $('.task-info .addIntegral .inline span').text(data.data.addIntegral);
                // 经纬度
                $('.task-info .singaporeAreaAtitudeLongitude .inline span').text(data.data.singaporeAreaAtitudeLongitude);

                // 货袋信息
                var cargobagInfo = data.data.taskOrderDeliveryRoutes;
                for(let i in cargobagInfo){
                    $('.cargobag-info tbody').append('<tr><td>'+ cargobagInfo[i].bagNumber +'</td><td>'+ cargobagInfo[i].length +'</td><td>'+ cargobagInfo[i].width +'</td><td>'+ cargobagInfo[i].high +'</td><td>'+cargobagInfo[i].load+'</td></tr>')
                }
            }
        }
    })


});