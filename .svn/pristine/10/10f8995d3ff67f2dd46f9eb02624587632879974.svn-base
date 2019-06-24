

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




// 谷歌地图 end

// 数组排序
function compare(property){
    return function(a,b){
        var value1 = a[property];
        var value2 = b[property];
        return value1 - value2;
    }
}

$(function () {
    layui.use(['layer'], function(){
        var layer = layui.layer;

        // 遮罩层
        var openLoad = layer.load(1, {
            shade: [0.1,'#fff'] //0.1透明度的白色背景
        });

        window.onload = function () {

            // 获取货车模板信息
            $.ajax({
                url: '/truckTask/truckTaskDetails',
                data: {'truckTaskId':$('input[name="truckTaskId"]').val()},
                dataType: 'JSON',
                success: function (data) {
                    if(data.message == '获取成功'){
                        if(data.data != null){
                            // 货车模板编号
                            $('.info .truckTaskNumber .inline span').text(data.data.truckTaskNumber);
                            // 货车模板名称
                            $('.info .truckTaskName .inline span').text(data.data.truckTaskName);
                            // 司机姓名
                            $('.info .truckDriverName .inline span').text(data.data.truckDriverName);

                            // 状态
                            if( data.data.status == 1){
                                $('.info .status .inline span').text('启用');
                            }else if(data.data.status == 2){
                                $('.info .status .inline span').text('禁用');
                            }

                            // 操作人
                            $('.info .adminName .inline span').text(data.data.adminName);
                            // 创建时间
                            $('.info .addTime .inline span').text(data.data.addTime);

                            // 路线
                            var truckTaskRoutes = data.data.truckTaskRoutes;
                            // 排序路线
                            var truckTaskRoutesArray =  truckTaskRoutes.sort(compare('sort'));

                            var origin = [] ,destination = [],middle = [];
                            for(let i in truckTaskRoutesArray){
                                if(truckTaskRoutesArray[i].sort == 1){

                                    origin.push(truckTaskRoutesArray[i])

                                }else if(truckTaskRoutesArray[i].sort == truckTaskRoutesArray.length){
                                    destination.push(truckTaskRoutesArray[i])

                                    // destination =truckTaskRoutesArray[i].latLng;
                                } else{
                                    middle.push(truckTaskRoutesArray[i])
                                }
                            }


                            calculateAndDisplayRoute(origin,destination,middle)
                        }
                    }

                    // 关闭遮罩层
                    layer.close(openLoad);

                }
            })
        };
    })
});

