
// 谷歌地图 stant
var map;

function initMap() {




    // 中心经纬度
    var uluru = {lat: 1.351993, lng: 103.866343};

    // 创建地图并设置中心点
    map = new google.maps.Map(document.getElementById('map'), {
        center: uluru,
        zoom: 11
    });

}



// 区域显示

function addPolygon(triangleCoords){
    var bermudaTriangle = new google.maps.Polygon({
        paths: triangleCoords,
        strokeColor: '#000000', // 颜色
        strokeOpacity: 0.8,     // 透明
        strokeWeight: 2,        // 宽度
        fillColor: '#FF0000',   // 区域颜色
        fillOpacity: 0.35 ,      // 区域透明
        title: '你好'
    });
    bermudaTriangle.setMap(map);
}

// 集结地
function addMarker(location) {
    var marker = new google.maps.Marker({
        position: location,
        map: map
    });
}

// 谷歌地图 end
$(document).ready(function () {


    layui.use(['layer', 'form','table'], function(){


        var layer = layui.layer
            ,table = layui.table;

        // 遮罩层
        var openLoad = layer.load(1, {
            shade: [0.1,'#fff']  //0.1透明度的白色背景
        });


        // 货物清单
        table.render({
            elem: '#idTest'
            ,height: 500
            ,url: "/carOrder/getGoodsListByTaskOrderId"//数据接口
            ,page: true

            ,cols: [[
                {type:'numbers'}
                ,{field: 'deliveryNumber', title: '快递单号'}
                ,{field: 'from', title: '来源',align:'center'}
                ,{field: 'receiptContact', title: '收货人'}
                ,{field: 'receiptContactMobile', title: '联系方式'}
                ,{field: 'receiptAddress', title: '收货地址'}
                ,{field: 'zipCode', title: '邮编'}
                ,{field: 'status', title: '状态',align:'center',templet:'#status'}
                ,{toolbar:'#barDemo', title: '操作',align:'center'}

            ]]
            ,where: {
                taskOrderId: $('input[name = "taskOrderId"]').val()
            }
            // ,autoSort: false //禁用前端自动排序。注意：该参数为 layui 2.4.4 新增
        });

        // table.reload('idTest', {
        //     page: {
        //         curr: 1 //重新从第 1 页开始
        //     }
        //     ,where: {
        //         taskOrderId: $('input[name = "taskOrderId"]').val()
        //
        //
        //     }
        // });



        window.onload = function () {

            // 货车小车详情
            $.ajax({
                url: '/carOrder/appTaskOrderGoods',
                data: {'taskOrderId': $('input[name="taskOrderId"]').val()},
                dataType: 'JSON',
                success: function (data) {
                    if(data.message == '获取成功'){
                        if(data.data != null){
                            var res = data.data;
                            // 订单编号
                            $('.info .orderNumber .inline span').text(res.orderNumber);
                            // 订单金额
                            $('.info .totalMoney .inline span').text(res.totalMoney);
                            // 订单奖励金额
                            $('.info .addMoney .inline span').text(res.addMoney);
                            // 订单时间
                            $('.info .createTime .inline span').text(res.createTime);

                            // 订单名称
                            $('.info .name .inline span').text(res.name);
                            // 订单积分
                            $('.info .totalIntegral .inline span').text(res.totalIntegral);
                            // 订单奖励积分
                            $('.info .addIntegral .inline span').text(res.addIntegral);

                            // 订单区域
                            $('.info .singaporeAreaName .inline span').text(res.singaporeAreaName);

                            // 集结地
                            $('.info .rallyPointAddress .inline span').text(res.rallyPointAddress);

                            // 订单状态
                            var state;
                            switch (res.state) {
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
                                case 7:
                                    state = '已结算';
                                    break;
                            }
                            $('.info .state .inline span').text(state);



                            // 地图显示区域
                            var singaporePoints = [];
                            $.each(res.singaporePoints,function (i,n) {
                                singaporePoints.push({lat:Number(n.lat),lng:Number(n.lng)})
                            });
                            addPolygon(singaporePoints);

                            // 集结地显示

                            addMarker({lat:Number(res.rallyPointLat),lng:Number(res.rallyPointLng)});


                        }
                    }

                    // 关闭遮罩层
                    layer.close(openLoad);
                }
            })

        }



    });






});
function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}

// 扣除
function startusing(id) {
    layer.prompt({title: '请扣除原因', formType: 2}, function(text, index){
        if((trim(text)).length == 0){
            layer.msg('请输入扣除原因')
            return false;
        }
        layer.close(index);
        // return false;
        $.ajax({
            url: '/carOrder/updateGoodsComment',
            dataType: 'JSON',
            type:'post',
            data: {'goodsId':id,'taskOrderId':$('input[name = "taskOrderId"]').val(),'comment':text},
            success: function (data) {
                layui.layer.msg(data.msg);

                data.code == 20057 ? layui.table.reload('idTest') : '';
            }
        })

    });
}