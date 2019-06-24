$(document).ready(function(){

    layui.use(['layer', 'form','table'], function(){
        var layer = layui.layer
            ,form = layui.form
            ,table = layui.table;

        //加载
        var editLoad = layer.load(1, {
            shade: [0.5,'#000'] //0.1透明度的白色背景
        });

        //表单提交
        form.on('submit(add)',function(data){
            var that = this;
            if(latlngArray.length < 3){
                layer.msg('最少选择三个标点！');
                return false;
            }

            var msg = duplicateName(data.field.singaporeAreaName);
            if(msg == 'no'){
                layer.msg('区域名称重复，请重新输入');
                return false;
            }
            console.log(latlngArray);
            data.field.singaporePoints = latlngArray;
            // 禁止点击
            $(that).addClass('zq-submit-disabled');
            axios.post("/area/editsingaporearea", data.field)
                .then(function (response) {
                    console.log(response)
                    //提示信息并跳转页面
                    layer.msg(response.data.msg);
                    if (response.data.msg == '修改成功') {
                        setTimeout(function () {
                            window.parent.location.reload(); //刷新父页面
                        }, 1000);
                    }
                    // 取消禁止点击
                    else {
                        $(that).removeClass('zq-submit-disabled');
                    }
                });
            return false;
        });


		// 获取编辑信息
		window.onload = function (ev) {

        	$.ajax({
				url: '/area/getSingaporeAreaById',
                dataType: 'JSON',
                data: {'singaporeAreaId':$('input[name ="singaporeAreaId"]').val()},
                success: function (data) {
                    if(data.msg = '获取成功'){
                        if(data.data != null){
                            // 区域名称
                            $('input[name="singaporeAreaName"]').val(data.data.singaporeAreaName);
                            // 区域划分
                            var singaporePoints = data.data.singaporePoints;
                            for(let i in singaporePoints){
                                // 经纬度
                                latlngArray.push({lat:Number(singaporePoints[i].lat),lng:Number(singaporePoints[i].lng)})
                                addMarker({lat:Number(singaporePoints[i].lat),lng:Number(singaporePoints[i].lng)},Number(i)+1);
                            }


                            latlngArray.push(latlngArray[0]);

                            addLatLngs(latlngArray);
                            latlngArray.pop();
                        }
                    }
                    layer.close(editLoad);
                }
			})

		}




    });

    //	验证重名
    function duplicateName(name) {
        var msg = 'ok';
        $.ajax({
            url:'/area/checkSingaporeAreaName',
            data: {'singaporeAreaId':$('input[name ="singaporeAreaId"]').val(),'singaporeAreaName':name},
            type: 'post',
            dataType: 'JSON',
            async: false,
            success: function (data) {
                if(data.code == 30018){
                    msg = 'no'
                }
            }
        });
        return msg;
    }



});

var map;
var markers = [];
var line = [];
var paths = [];

// 经纬度数据
var latlngArray = [];
function initMap() {
    // 经纬度
    var uluru = {lat: 1.351993, lng: 103.866343}
    // 创建地图并设置中心点
    map = new google.maps.Map(document.getElementById('map'), {
        center: uluru,
        zoom: 13
    });

    // 单击事件
    google.maps.event.addListener(map, "click", function(event) {
        addLatLng(event.latLng);
    });
}
// 折线
function addLatLng(location) {
    paths.push(location);
    var poly = new google.maps.Polyline({
        path: paths,
        strokeColor: '#000000',
        strokeOpacity: 1.0,
        strokeWeight: 2
    });

    poly.setMap(map);
    line.push(poly);

    var path = poly.getPath();
    var marker = new google.maps.Marker({
        position: location,
        title: '#' + path.getLength(),
        map: map
    });
    markers.push(marker);
    $('.zq-google-search .lat').val(location.lat);
    $('.zq-google-search .lng').val(location.lng);
    addlatlng();
}
function addlatlng() {
    latlngArray.push({lat:$('.zq-google-search .lat').val(),lng:$('.zq-google-search .lng').val()});
    console.log(latlngArray);
}

function remove(){

    // 删除标记
    setMapOnAll(null);
    markers = [];
    paths = [];
    latlngArray = [];

}

function setMapOnAll(map) {
    for (let i = 0; i < markers.length; i++) {
        markers[i].setMap(map);
    }

    for ( let i=0; i<line.length; i++)
    {
        line[i].setMap(map);
    }
}

// 加载编辑信息显示折线与标点
function addMarker(location,i){
    var marker = new google.maps.Marker({
        position: location,
        title: '#' + i,
        map: map
    });
    markers.push(marker);
}

function addLatLngs(location){
    console.log(location)
    paths = location;
    var poly = new google.maps.Polyline({
        path: paths,
        strokeColor: '#000000',
        strokeOpacity: 1.0,
        strokeWeight: 2
    });

    poly.setMap(map);
    line.push(poly);
}
