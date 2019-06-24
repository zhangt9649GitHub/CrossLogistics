$(document).ready(function(){

    layui.use(['layer', 'form','table'], function() {
        var layer = layui.layer
            , form = layui.form
            , table = layui.table;

        // 查看货物物流进程
        $.ajax({
            url: '/bag/getBagLogisticInfo',
            dataType: 'JSON',
            data: {'bagId':$('input[name = "bagId"]').val()},
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

        // 获取货袋信息
        $.ajax({
            url: '/bag/getBagDetails',
            data: {'bagId': $('input[name="bagId"]').val()},
            dataType: 'JSON',
            success: function (data) {
                if(data.code == 0){
                    if(data.data != null){
                        var res = data.data;
                        // 货袋编号
                        $('.cargobag-info .bagNumber .inline span').text(res.bagNumber);
                        // 状态
                        var state ;
                        switch (res.state) {
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
                        $('.cargobag-info .state .inline span').text(state);

                        // 所属仓库
                        $('.cargobag-info .lastWarehouseName .inline span').text(res.lastWarehouseName);

                        // 所属区域
                        $('.cargobag-info .singaporeAreaName .inline span').text(res.singaporeAreaName);

                        // 所属集结点
                        $('.cargobag-info .rallyPointName .inline span').text(res.rallyPointName);

                        // 所属仓位
                        $('.cargobag-info .lastWpNumber .inline span').text(res.lastWpNumber);

                    }
                }
            }
        });

        // 货物清单
        // 货袋列表信息
        table.render({
            elem: '#idTest'
            ,height: 500
            ,url: "/bag/getGoodsByBag"//数据接口
            ,page: true

            ,cols: [[
                {type:'numbers'}
                ,{field: 'deliveryNumber', title: '快递单号'}
                ,{field: 'from', title: '来源',align:'center'}
                ,{field: 'receiptContact', title: '收货人'}
                ,{field: 'receiptAddress', title: '收货地址'}
                ,{field: 'zipCode', title: '邮编'}
                ,{field: 'goodType', title: '货物类型'}
                ,{field: 'status', title: '货物状态',align:'center' , templet:'#status'}
                ,{field: 'operateResult', title: '物流进程'}
                ,{align:'center', toolbar: '#barDemo',title: '操作'}

            ]]
            ,where: {
                bagId: $('input[name = "bagId"]').val()


            }
            // ,autoSort: false //禁用前端自动排序。注意：该参数为 layui 2.4.4 新增
        });





        // 获取货袋关联小车信息
        $.ajax({
            url: '/bag/getCarDetailsByBag',
            data: {'bagId': $('input[name="bagId"]').val()},
            dataType: 'JSON',
            success: function (data) {
                // console.log(data)
                if(data.code == 0){
                    if(data.data != null){
                        // 小车编号
                        $('.car-info .carNumber .inline span').text(data.data.carNumber);
                        // 状态
                        var state;
                        switch (data.data.state) {
                            case 1:
                                state = '空闲';
                                break;
                            case 2:
                                state = '派送中';
                                break;
                            case 3:
                                state = '维护中';
                                break;
                            case 4:
                                state = '内有异常件';
                                break;
                            case 5:
                                state = '报废';
                                break;
                        }

                        $('.car-info .state .inline span').text(state);
                        // 关联区域
                        $('.car-info .singaporeAreaName .inline span').text(data.data.singaporeAreaName);
                        // 关联集结点
                        $('.car-info .rallyPointName .inline span').text(data.data.rallyPointName);
                    }
                }

            }
        });


        // 获取货袋关联大货车信息
        $.ajax({
            url: '/bag/getTruckDetailsByBag',
            data: {'bagId': $('input[name="bagId"]').val()},
            dataType: 'JSON',
            success: function (data) {
                // console.log(data);
                if(data.data != null){
                    // 司机名称
                    $('.truck-info .name .inline span').text(data.data.name);
                    // 载重量
                    $('.truck-info .load .inline span').text(data.data.load);

                    // 联系方式
                    $('.truck-info .mobile .inline span').text(data.data.mobile);
                    // 关联区域
                    $('.truck-info .singaporeAreaName .inline span').text(data.data.singaporeAreaName);
                    // 牌照
                    $('.truck-info .licensePlate .inline span').text(data.data.licensePlate);
                    // 状态

                    var state;
                    switch (data.data.state) {
                        case 1:
                            state = '订单进行中';
                            break;
                        case 2:
                            state = '空闲';
                            break;
                    }
                    $('.truck-info .state .inline span').text(state);
                }
            }
        });

    });











});
// 删除
function delthis(id) {
    layer.confirm('确认删除吗？', {
        btn: ['确定', '取消'] //可以无限个按钮
    }, function (index, layero) {
        $.ajax({
            url: '/bag/updateGoodsPackingCondition',
            data: {'goodsId':id},
            dataType: 'JSON',
            type: 'post',
            success: function (data) {
                layui.layer.msg(data.msg);
                data.code == 20083 ? layui.table.reload('idTest') : '';
            },
            error: function (data) {

            }
        })
    }, function (index) {
        //按钮【取消】的回调
    });
};