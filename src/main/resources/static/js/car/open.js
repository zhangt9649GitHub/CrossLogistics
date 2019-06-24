$(document).ready(function(){

	layui.use(['layer', 'form','table'], function(){
		var layer = layui.layer
		  	,form = layui.form
	  	  	,table = layui.table;

		// 获取小车信息
		$.ajax({
            url: '/car/getCarDetails',
            data: {'carId':$('input[name="carId"]').val()},
            dataType: 'JSON',
			success: function (data) {

				if(data.code == 0){
					if(data.data != null){
						var res = data.data;
						// 小车编号
						$('.info .carNumber .inline span').text(res.carNumber);
                        // 车锁编号
                        $('.info .carLockNumber .inline span').text(res.carLockNumber);
                        // 货袋编号
                        $('.info .bagNumber .inline span').text(res.bagNumber);
                        //状态
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
                        $('.info .state .inline span').text(state);
                        // 车箱锁编号
                        $('.info .carLockBoxNumber .inline span').text(res.carLockBoxNumber);
                        // 区域
                        $('.info .singaporeAreaName .inline span').text(res.singaporeAreaName);
                        // 集结点
                        $('.info .rallyPointName .inline span').text(res.rallyPointName);
					}
				}
            }
		});


        // 货物清单
        table.render({
            elem: '#idTest'
            ,height: 500
            ,url: "/car/getCarGoodsList"//数据接口
            ,page: true
            ,cols: [[
                {type:'numbers'}
                ,{field: 'deliveryNumber', title: '快递单号'}
                ,{field: 'from', title: '来源',align:'center'}
                ,{field: 'receiptContact', title: '收货人'}
                ,{field: 'receiptAddress', title: '收货地址'}
                ,{field: 'zipCode', title: '邮编'}
                ,{field: 'goodType', title: '货物类型'}

                // ,{field: 'isSpecialGoods', title: '特殊货物',align:'center' , templet:'#isSpecialGoods'}
                ,{field: 'status', title: '货物状态',align:'center' , templet:'#status'}
                ,{field: 'operateResult', title: '操作进程'}
            ]]
            ,where: {
                carId: $('input[name = "carId"]').val()
            }
            // ,autoSort: false //禁用前端自动排序。注意：该参数为 layui 2.4.4 新增
        });



        // 页面元素都加载完成后添加
        window.onload = function (ev) {
            // 获取小车实时位置
            $.ajax({
                url: '/car/getCarPosition',
                data: {'carId':$('input[name="carId"]').val()},
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