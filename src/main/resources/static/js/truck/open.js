
var map;
function initMap() {

    // 经纬度
    var uluru = {lat: 1.351993, lng: 103.866343};

    // 创建地图并设置中心点
    map = new google.maps.Map(document.getElementById('map'), {
        center: uluru,
        zoom: 11
    });
}

// 添加标记
function addMarker(location,name) {
    var marker = new google.maps.Marker({
        position: location,
        map: map,
        title: name
    });
    var infowindow = new google.maps.InfoWindow({ content: name });
    infowindow.open(map, marker);
}




$(document).ready(function(){

	layui.use(['layer', 'form','table'], function(){
		var layer = layui.layer
		  	,form = layui.form
	  	  	,table = layui.table;

		// 详细信息
        $.ajax({
            url: '/truck/getTruckById',
            dataType: 'JSON',
            data:{'truckId':$('input[name="truckId"]').val()},
            success: function (data) {
                if(data.code == 0){
                    if(data.data != null){
                        // 司机姓名
                        $('.info .name .inline span').text(data.data.name);
                        // 联系方式
                        $('.info .mobile .inline span').text(data.data.mobile);
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
                        $('.info .state .inline span').text(state);

                        // 车牌
                        $('.info .licensePlate .inline span').text(data.data.licensePlate);
                        // 车型
                        $('.info .models .inline span').text(data.data.models);
                        // 载重量
                        $('.info .load .inline span').text(data.data.load);
                        // 订单号
                        $('.info .orderNumber .inline span').text(data.data.orderNumber);
                        // 关联区域
                        $('.info .singaporeAreaName .inline span').text(data.data.singaporeAreaName);
                    }
                }
            }
        });





		// 货袋列表信息
        table.render({
            elem: '#idTest'
            ,height: 500
            ,url: "/truck/getBagListByTruckId"//数据接口
            ,page: true

            ,cols: [[
                {type:'numbers'}
                ,{field: 'bagNumber', title: '货袋编号'}
                // ,{field: 'carNumber', title: '关联小车信息'}
                ,{field: 'singaporeAreaName', title: '所属自定义区域'}
                ,{field: 'rallyPointName', title: '所属集结地'}
                // ,{field: 'initWarehouseName', title: '入库仓'}
                // ,{field: 'initWpNumber', title: '入库仓位'}
                ,{field: 'lastWarehouseName', title: '出库仓'}
                ,{field: 'lastWpNumber', title: '出库仓位'}
                ,{field: 'state', title: '状态信息' , templet:'#state'}
            ]]
            ,where: {
                truckId: $('input[name = "truckId"]').val()


            }
            ,autoSort: false //禁用前端自动排序。注意：该参数为 layui 2.4.4 新增
        });



        // 货物列表信息
        table.render({
            elem: '#idTest1'
            ,height: 500
            ,url: "/truck/getGoodsListByTruckId"//数据接口
            ,page: true

            ,cols: [[
                {type:'numbers'}
                ,{field: 'deliveryNumber', title: '快递单号',minWidth:180}
                ,{field: 'tripartiteNumber', title: '三方物流单号',minWidth:180}
                ,{field: 'bagNumber', title: '所属货袋',minWidth:180}
                ,{field: 'goodData', title: '货物数据（长*宽*高，重量）',align:'center',minWidth:220}
                // ,{field: 'goodType', title: '货物类型',align:'center'}
                ,{field: 'zipCode', title: '邮编',align:'center'}
                // ,{field: 'intoWarehouseName', title: '入库仓',align:'center'}
                // ,{field: 'outWarehouseName', title: '出库仓',align:'center'}
                ,{field: 'status', title: '状态',align:'center',templet:'#statusID'}
                // ,{field: 'isSpecialGoods', title: '特殊货物',align:'center',templet:'#isSpecialGoodsID'}
                ,{field: 'operateResult', title: '操作进程'}
            ]]
            ,autoSort: false //禁用前端自动排序。注意：该参数为 layui 2.4.4 新增
        });

        table.reload('idTest1', {
            page: {
                curr: 1 //重新从第 1 页开始
            }
            ,where: {
                truckId: $('input[name = "truckId"]').val()


            }
        });

        // 页面元素都加载完成后添加
        window.onload = function (ev) {
            // 获取货车实时位置
            $.ajax({
                url: '/truck/getTruckPosition',
                data: {'truckId':$('input[name="truckId"]').val()},
                dataType: 'JSON',
                success: function (data) {
                    if(data.code == 0){
                        if(data.data != null){

                            var location = {lat: Number(data.data.latitude), lng: Number(data.data.longitude)};
                            var name = '当前位置';
                            addMarker(location,name)
                        }
                    }
                }
            })
        }

	});



});