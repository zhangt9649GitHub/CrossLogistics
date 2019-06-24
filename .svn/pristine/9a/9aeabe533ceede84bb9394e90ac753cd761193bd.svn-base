
$(document).ready(function(){

    layui.use(['layer', 'form','table'], function(){
        var layer = layui.layer
            ,form = layui.form
            ,table = layui.table;

        //加载
        var editLoad = layer.load(1, {
            shade: [0.5,'#000'] //0.1透明度的白色背景
        });

        // 监听表单提交
        form.on('submit(add)', function(data){
            var that = this;

            if(($('input[name="rallyPointLat"]').val()).length == 0){
                layer.msg('请在地图上选择位置');
                return false;
            }

            // 禁止点击
            $(that).addClass('zq-submit-disabled');
            axios.post("/area/updaterallypoint", data.field)
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
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });

        // 获取区域列表
        var res;
        $.ajax({
            url: '/area/getSingaporeAreaListNoPaging',
            type: 'post',
            dataType: 'JSON',
            async:false,
            success:function (data) {
                console.log(data);
                if(data.data != null){
                   res = data.data;
                    for(var i in res){
                        $('select[name = "singaporeAreaId"]').append('<option value="'+ res[i].singaporeAreaId +'">'+res[i].singaporeAreaName +'</option>')
                    }
                    form.render('select');

                }
            }
        });


        // 监听区域选择
        form.on('select(quyu)', function(data){
            remove();
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

        });



        // 获取编辑信息
       window.onload = function() {

            $.ajax({
                url: '/area/getRallyPoint',
                dataType: 'JSON',
                data: {'rallyPointId':$('input[name="rallyPointId"]').val()},
                success: function (data) {
                    if(data.data != null ){

                        $('input[name="rallyPointName"]').val(data.data.rallyPointName);


                        $('input[name="rallyPointAddress"]').val(data.data.rallyPointAddress);

                        $('select[name="singaporeAreaId"] option').each(function (i,item) {
                            console.log($(item).attr('value'));
                            if($(item).val() == data.data.singaporeAreaId){
                                $(item).prop('selected',true);
                                form.render('select');
                            }
                        });

                        $('textarea[name="rallyPointAccount"]').text(data.data.rallyPointAccount);



                        $('input[name="rallyPointLat"]').val(data.data.rallyPointLat);
                        $('input[name="rallyPointLng"]').val(data.data.rallyPointLng);

                        addMarker({lat: Number(data.data.rallyPointLat), lng: Number(data.data.rallyPointLng)});

                        // 区域显示
                        for(let i in res){
                            if(res[i].singaporeAreaId == data.data.singaporeAreaId){
                                var singaporePoints = res[i].singaporePoints;

                                var latlngArrays = [];
                                for(let a in singaporePoints){
                                    latlngArrays.push({lat:Number(singaporePoints[a].lat),lng:Number(singaporePoints[a].lng)})
                                }

                                latlngArrays.push(latlngArrays[0]);

                                addLatLngs(latlngArrays);
                            }
                        }

                    }

                    layer.close(editLoad);
                }
            })
        };





    });



});

var map;
var markers = [];


// 区域
var paths = [];
var line = [];
function initMap() {

    // 经纬度
    var uluru = {lat: 1.351993, lng: 103.866343};

    // 创建地图并设置中心点
    map = new google.maps.Map(document.getElementById('map'), {
        center: uluru,
        zoom: 13
    });

    // 地图标点
    // console.log(location);
    // var marker = new google.maps.Marker({
    //     position: location,
    //     map: map,
    //     title: 'Hello World!'
    // });
    // markers.push(marker);
    var infowindow = new google.maps.InfoWindow({ content: "当前选择的位置" });


    // 单击事件
    google.maps.event.addListener(map, "click", function(event) {
        addMarker(event.latLng);
    });
}

// 添加标记
function addMarker(location) {
    setMapOnAll(null);
    markers = [];
    var marker = new google.maps.Marker({
        position: location,
        map: map,
        title: '当前选择的位置'
    });
    $('input[name="rallyPointLat"]').val(location.lat);
    $('input[name="rallyPointLng"]').val(location.lng);
    markers.push(marker);
}
function setMapOnAll(map) {

    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(map);
    }
}

// 区域显示
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
// 删除区域
function remove(){

    // 删除标记
    setMapOnAlls(null);
    paths = [];

}

function setMapOnAlls(map) {

    for (let i=0; i<line.length; i++)
    {
        line[i].setMap(map);
    }
}
