
var map;
var markers = [];
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
function addMarker(location,name,dizhi) {
    var marker = new google.maps.Marker({
        position: location,
        map: map,
        title: dizhi
    });
    var infowindow = new google.maps.InfoWindow({ content: name });
    infowindow.open(map, marker);
    markers.push(marker);
}
// 删除标记
function setMapOnAll(map) {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(map);
    }
}


$(document).ready(function(){
    //全局定义一次, 加载formSelects
    layui.config({
        base: '../../layui/src/' //此处路径请自行处理, 可以使用绝对路径
    }).extend({
        formSelects: 'formSelects-v4'
    });


    var warehouse = new Array(); // 仓库
	var rallyPoint = new Array(); // 集结地
    layui.use(['layer', 'form','table','formSelects'], function(){
        var layer = layui.layer
            ,form = layui.form
            ,table = layui.table
            ,formSelects = layui.formSelects;

        //加载
        var editLoad = layer.load(1, {
            shade: [0.1,'#fff'] //0.1透明度的白色背景
        });

        // 监听提交
        form.on('submit(add)', function(data){

            var that = this;

			// 路线
        	var truckTaskRouteDto = new String();
        	// 仓库经纬度
            truckTaskRouteDto +=data.field.warehouseId+';2;1';
			for(let i in warehouse) {
				if(data.field.warehouseId == warehouse[i].warehouseId){
                    data.field.latLng = warehouse[i].warehouseLat +','+warehouse[i].warehouseLng;
				}

			}
			delete data.field.warehouseId;

			// 集结地经纬度
			var saIds = (data.field.saIds).split(',');
            var number = 1;
            console.log(saIds);
			for(let a in saIds){

				for(let b in rallyPoint){

					if(saIds[a] == rallyPoint[b].rallyPointId){
                        number += 1;
                        data.field.latLng += ';'+rallyPoint[b].rallyPointLat + ','+rallyPoint[b].rallyPointLng;
                        truckTaskRouteDto+=','+ saIds[a]+';1;'+number;

					}
				}
			}


            var formData = new FormData();
            formData.append("truckTaskName", data.field.truckTaskName);
            formData.append("truckDriverId", data.field.truckDriverId);
            formData.append("latLng", data.field.latLng);
            formData.append("truckTaskRoutes",truckTaskRouteDto);
            formData.append("singaporeAreaId",data.field.singaporeAreaId);

            // 禁止点击
            $(that).addClass('zq-submit-disabled');
			$.ajax({
				url: '/truckTask/addTruckTask',
				type: 'post',
				dataType: 'JSON',
				data:  formData,
                contentType: false,
                processData: false,
				success: function (data) {
                            layer.msg(data.message);
                            if (data.message == '添加成功') {
                                setTimeout(function () {
                                    window.parent.location.reload(); //刷新父页面
                                }, 1000);
                            }
                            // 取消禁止点击
                            else {
                                $(that).removeClass('zq-submit-disabled');
                            }
                }
			});



            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });



        // 监听区域选择
        form.on('select(singaporeAreaId)', function(data){
            setMapOnAll(null);
            markers = [];
            if(data.value){
            	// 小车集结点
                $('.saIds').html('<option value=""></option>');
                $.ajax({
                    url: '/truckTask/selectRallyPointBySA',
                    data: {'singaporeAreaId': data.value},
                    dataType: 'JSON',
                    success: function (data) {
                        if(data.data != null){
                            rallyPoint = data.data;
                            // 经纬度集合

                            for(let i in rallyPoint){
                                var rallyPointAddress = rallyPoint[i].rallyPointAddress;
                                var name = rallyPoint[i].rallyPointName;
                                var location = {lat: Number(rallyPoint[i].rallyPointLat), lng: Number(rallyPoint[i].rallyPointLng)};
                                addMarker(location,name,rallyPointAddress)


								$('.saIds').append('<option value="'+rallyPoint[i].rallyPointId+'">'+rallyPoint[i].rallyPointName+'</option>')


                            }
                            layui.formSelects.render('select1');
                        }
                    }
                })
            }else{
                $('.saIds').html('<option value=""></option>');
                layui.formSelects.render('select1');
            }

        });


        //	获取货车司机列表
        $.ajax({
            url: '/truckTask/selectAppUserByTruck',
            dataType: 'JSON',
            async:false,
            success: function (data) {
                if(data.data != null){
                    var res = data.data;
                    for(let i in res){
                        $('.truckDriverId').append('<option value="'+ res[i].appUserId +'">'+ res[i].userName  +'</option>')
                    }
                    layui.formSelects.render('select2');
                }
            }
        });

        //	获取区域列表
        $.ajax({
            url: '/truckTask/singaporeAreaAll',
            dataType: 'JSON',
            async:false,
            success: function (data) {
                if(data.data != null){
                    var res = data.data;
                    for(let i in res){
                        $('select[name="singaporeAreaId"]').append('<option value="'+ res[i].singaporeAreaId +'">'+ res[i].singaporeAreaName  +'</option>')
                    }
                    layui.form.render('select');
                }
            }
        });

        //	仓库
        $.ajax({
            url: '/truckTask/selectWarehouseAll',
            dataType: 'JSON',
            success: function (data) {
                if(data.data != null){
                    warehouse = data.data;
                    for(let i in warehouse){
                        $('select[name="warehouseId"]').append('<option value="'+ warehouse[i].warehouseId +'">'+ warehouse[i].warehouseName  +'</option>')
                    }
                    layui.form.render('select');
                }
            }
        });

        // 加载完成后获取
        window.onload = function (ev) {
            layer.close(editLoad);
        }

    });




});