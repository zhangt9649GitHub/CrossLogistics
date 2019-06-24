




$(document).ready(function(){

	layui.use(['layer', 'form','table'], function(){
		var layer = layui.layer
		  	,form = layui.form
	  	  	,table = layui.table;

        //加载
        var editLoad = layer.load(1, {
            shade: [0.1,'#fff']  //0.1透明度的白色背景
        });

        // 获取区域列表
        var res;
        $.ajax({
            url: '/area/getSingaporeAreaListNoPaging',
            type: 'post',
            dataType: 'JSON',
            async: false,
            success:function (data) {
                if(data.data != null){
                    res = data.data;

                    for(var i in res){
                        $('select[name = "singaporeAreaId"]').append('<option value="'+ res[i].singaporeAreaId +'">'+res[i].singaporeAreaName +'</option>')
                    }
                    layui.form.render('select');
                }
            }
        });

        // 车锁
        $.ajax({
            url: '/car/getVacancyCarLock',
            dataType: 'JSON',
            async: false,
            success:function (data) {

                if(data.code == 0 ){
                    if(data.data != null){

                        $.each(data.data,function (i,n) {
                            $('select[name = "carLockId"]').append('<option value="'+ n.lockId +'">'+n.lockNumber +'</option>')
                        });

                        layui.form.render('select');
                    }
                }


            }
        });

        // 后箱锁
        $.ajax({
            url: '/car/getVacancyCarLockBox',
            dataType: 'JSON',
            async: false,
            success:function (data) {

                if(data.code == 0 ){
                    if(data.data != null){

                        $.each(data.data,function (i,n) {
                            $('select[name = "carLockBoxId"]').append('<option value="'+ n.lockId +'">'+n.lockNumber +'</option>')
                        });

                        layui.form.render('select');
                    }
                }


            }
        });



        // 监听区域选择

        form.on('select(singaporeAreaId)', function(data){

            // 在地图上删除在添加
            setMapOnAll(null);
            markers = [];
            remove();
            // 小车集结点
            $('select[name="rallyPointId"]').html('<option value=""></option>');

            // 显示区域
            for(let i in res){
                if(res[i].singaporeAreaId == data.value){
                    var singaporePoints = res[i].singaporePoints;

                    var latlngArray = [];
                    for(let a in singaporePoints){
                        latlngArray.push({lat:Number(singaporePoints[a].lat),lng:Number(singaporePoints[a].lng)})
                    }

                    latlngArray.push(latlngArray[0]);

                    addLatLngs(latlngArray);
                }
            }


            if(data.value){
                $.ajax({
                    url: '/truckTask/selectRallyPointBySA',
                    data: {'singaporeAreaId': data.value},
                    dataType: 'JSON',
                    async: false,
                    success: function (data) {
                        if(data.message == '获取成功'){
                            if(data.data != null){
                                // 经纬度集合
                                console.log(data.data)
                                $.each(data.data,function (i,n) {
                                    addMarker({lat:Number(n.rallyPointLat),lng:Number(n.rallyPointLng)},n.rallyPointName);

                                    $('select[name="rallyPointId"]').append('<option value="'+n.rallyPointId+'">'+n.rallyPointName+'</option>')
                                });
                            }
                        }

                    }
                })
            }

            layui.form.render('select');

        });


        // 监听表单提交
        form.on('submit(add)',function(data){
            var that = this;

            // 禁止点击
            $(that).addClass('zq-submit-disabled');
            axios.post("/car/insertCar", data.field)
                .then(function (response) {
                    //提示信息并跳转页面
                    layer.msg(response.data.msg);
                    if (response.data.msg == '添加成功') {
                        setTimeout(function () {
                            window.parent.location.reload(); //刷新父页面
                        }, 1000);
                    }
                    // 取消禁止点击
                    else {
                        $(that).removeClass('zq-submit-disabled');
                    }
                });
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });

        // 页面加载完成触发

        window.onload = function (ev) {
            layer.close(editLoad);
        }
    });





});

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
function addMarker(location,name) {
    var marker = new google.maps.Marker({
        position: location,
        map: map,
        title: name
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


// 区域
var paths = [];
var line = [];
// 区域显示
function addLatLngs(location){
    paths = location;
    var poly = new google.maps.Polygon({
        path: paths,
        strokeColor: '#000000',
        strokeOpacity: 1.0,
        strokeWeight: 2,
        fillColor: '#FF0000',   // 区域颜色
        fillOpacity: 0.35 ,
    });

    poly.setMap(map);
    line.push(poly);
}
// 删除区域
function remove(){

    // 删除标记
    setMapOnAlls(null);
    paths = [];
    line = [];
}

function setMapOnAlls(map) {

    for (let i=0; i<line.length; i++)
    {
        line[i].setMap(map);
    }
}