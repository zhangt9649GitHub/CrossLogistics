
// 谷歌地图 stant
var map;
var directionsService;
var directionsDisplay;
function initMap() {


    directionsService = new google.maps.DirectionsService();
    directionsDisplay = new google.maps.DirectionsRenderer();

    // 中心经纬度
    var uluru = {lat: 1.351993, lng: 103.866343};

    // 创建地图并设置中心点
    map = new google.maps.Map(document.getElementById('map'), {
        center: uluru,
        zoom: 11
    });
    directionsDisplay.setMap(map);
    // calculateAndDisplayRoute();
}

// 创建路线
function calculateAndDisplayRoute(origin,destination,middle) {
    var origins = origin;
    var destinations = destination;
    var middles = middle;

    // 路线开始位置
    var start = new google.maps.LatLng(Number(origins[0].rallyPointLat),Number(origins[0].rallyPointLng));

    // 路线结束位置
    var end =  new google.maps.LatLng(Number(destinations[0].rallyPointLat),Number(destinations[0].rallyPointLng));

    // 路线中间停靠
    var waypts = [];
    for(var i in middles){

        waypts.push({
            location:new google.maps.LatLng(Number(middles[i].rallyPointLat),Number(middles[i].rallyPointLng)),
            stopover:true
        })
    }
    directionsService.route({
        origin: start,
        destination: end,
        waypoints: waypts,
        optimizeWaypoints: true,
        travelMode: google.maps.DirectionsTravelMode.WALKING
    }, function(response, status) {



        if (status === 'OK') {
            directionsDisplay.setDirections(response);

        } else {
            window.alert('Directions request failed due to ' + status);
        }


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


// 谷歌地图 end

// 数组排序
function compare(property){
    return function(a,b){
        var value1 = a[property];
        var value2 = b[property];
        return value1 - value2;
    }
}






$(document).ready(function () {


    layui.use(['layer', 'form','table'], function(){


        var layer = layui.layer;

        // 遮罩层
        var openLoad = layer.load(1, {
            shade: [0.1,'#fff']  //0.1透明度的白色背景
        });



        window.onload = function () {

            // 货车订单详情
            $.ajax({
                url: '/truckOrder/appTruckOrderDetails',
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

                            // 订单名称
                            $('.info .name .inline span').text(res.name);
                            // 订单积分
                            $('.info .totalIntegral .inline span').text(res.totalIntegral);
                            // 订单奖励积分
                            $('.info .addIntegral .inline span').text(res.addIntegral);

                            // 订单区域
                            $('.info .singaporeAreaName .inline span').text(res.singaporeAreaName);
                            
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
                            }
                            $('.info .state .inline span').text(state);

                            // 订单时间
                            $('.info .createTime .inline span').text(res.createTime);

                            //货袋列表

                            if((res.appTruckOrderBags).length != 0){
                                $('.huodai').show();
                                $.each(res.appTruckOrderBags,function (i,item) {
                                    var number = i + 1;
                                    var html = '<tr><td>'+ number +'</td><td>'+ item.bagNumber+'</td></tr>'
                                   $('.huodai table').append(html)
                                });
                            }




                            // 地图显示区域
                            var singaporePoints = [];
                            $.each(res.singaporePoints,function (i,n) {
                                singaporePoints.push({lat:Number(n.lat),lng:Number(n.lng)})
                            });
                            addPolygon(singaporePoints);

                            // 地图显示路线
                            // 排序路线
                            var truckTaskRoutesArray = (res.truckTaskRoutes).sort(compare('sort'));

                            var origin = [] ,destination = [],middle = [];
                            $.each(truckTaskRoutesArray,function(i,n){
                                if(n.sort == 1){
                                    origin.push(n)
                                }else if(n.sort == truckTaskRoutesArray.length){
                                    destination.push(n)

                                    // destination =truckTaskRoutesArray[i].latLng;
                                } else{
                                    middle.push(n)
                                }
                            });
                            calculateAndDisplayRoute(origin,destination,middle)
                


                        }
                    }

                    // 关闭遮罩层
                    layer.close(openLoad);
                }
            })

        }



    });






});