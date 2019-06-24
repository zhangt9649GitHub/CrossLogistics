$(document).ready(function () {

    // 查看货物物流进程
    $.ajax({
        url: '/abnormalGoods/getWarningGoodsLogisticById',
        dataType: 'JSON',
        data: {'goodsId':$('input[name = "goodsId"]').val()},
        success: function (data) {
            console.log(data);
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


    // 查看货物发货人信息及收货人信息
    $.ajax({
        url: '/goods/getGoodsDetailById',
        dataType: 'JSON',
        data: {'goodsId':$('input[name = "goodsId"]').val()},
        success: function (data) {

            if(data.data != null){

                // 发货人信息 start
                // 姓名
                $('.shipper-info .shipContact .inline span').text(data.data.shipContact);
                // 手机号
                $('.shipper-info .shipContactMobile .inline span').text(data.data.shipContactMobile);
                // 地址
                $('.shipper-info .shipAddress .inline span').text(data.data.shipAddress);
                // 发货人信息 end


                // 收货人信息 start
                // 姓名
                $('.receiver-info .receiptContact .inline span').text(data.data.receiptContact);
                // 手机号
                $('.receiver-info .receiptContactMobile .inline span').text(data.data.receiptContactMobile);
                // 地址
                $('.receiver-info .receiptAddress .inline span').text(data.data.receiptAddress);
                // 邮编
                $('.receiver-info .zipCode .inline span').text(data.data.zipCode);
                // 收货人信息 end


            }
        }
    });

    // 查看货物信息及三方物流信息
    $.ajax({
        url: '/goods/getGoodsById',
        dataType: 'JSON',
        data: {'goodsId':$('input[name = "goodsId"]').val()},
        success: function (data) {
            if(data.data != null){

                //货物信息 start

                    //快递单号
                    $('.goods-info .deliveryNumber .inline span').text(data.data.deliveryNumber);
                    //货袋编号
                    $('.goods-info .bagNumber .inline span').text(data.data.bagNumber);
                    //入库仓
                    $('.goods-info .intoWarehouseName .inline span').text(data.data.intoWarehouseName);
                    //入库仓位
                    $('.goods-info .intoWpNumber .inline span').text(data.data.intoWpNumber);
                    //出库仓
                    $('.goods-info .outWarehouseName .inline span').text(data.data.outWarehouseName);
                    //出库仓位
                    $('.goods-info .outWpNumber .inline span').text(data.data.outWpNumber);
                    //长
                    $('.goods-info .actualLong .inline span').text(data.data.actualLong);
                    //宽
                    $('.goods-info .actualWidth .inline span').text(data.data.actualWidth);
                    //高
                    $('.goods-info .actualHeight .inline span').text(data.data.actualHeight)
                    //重量
                    $('.goods-info .actualWeight .inline span').text(data.data.actualWeight);
                    // 特殊货物
                    if(data.data.isSpecialGoods ==1){
                        $('.goods-info .isSpecialGoods .inline span').text('是');
                    }else if(data.data.isSpecialGoods ==2){
                        $('.goods-info .isSpecialGoods .inline span').text('否');
                    }
                    //货物类型
                    $('.goods-info .goodType .inline span').text(data.data.goodType);
                    // 货物状态
                    if(data.data.status ==1){
                        $('.goods-info .status .inline span').text('正常');
                    }else if(data.data.status ==2){
                        $('.goods-info .status .inline span').text('异常');
                    }

                // 货物信息 end

                // 三方物流信息 start

                    // 承运方
                    $('.there-logistics-info .shipper .inline span').text(data.data.shipper);
                    // 货运单号
                    $('.there-logistics-info .tripartiteNumber .inline span').text(data.data.tripartiteNumber);

                // 三方物流信息 end


            }
        }
    });




    layui.use(['layer','table'], function() {
        var layer = layui.layer
            , table = layui.table;

        // 查看节点操作人员信息
        // 数据表格

        table.render({
            elem: '#idTest'
            ,height: 500
            ,url: "/abnormalGoods/getGoodsWarningOperateNameById"//数据接口
            ,page: true

            ,cols: [[
                {type:'numbers'}
                ,{field: 'operateName', title: '节点操作人员',align:'center',width:200}
                ,{field: 'createTime', title: '操作时间',width:200,align:'center'}
                ,{field: 'operateComment', title: '操作内容'}




            ]]
            ,where: {
                goodsId: $('input[name = "goodsId"]').val()


            }
            ,autoSort: false //禁用前端自动排序。注意：该参数为 layui 2.4.4 新增
        });



    });


});