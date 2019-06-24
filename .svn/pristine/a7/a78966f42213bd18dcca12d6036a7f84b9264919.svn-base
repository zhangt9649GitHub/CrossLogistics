
$(document).ready(function(){

    layui.use(['layer', 'form','table'], function(){
        var layer = layui.layer
            ,form = layui.form
            ,table = layui.table;


        //加载
        var editLoad = layer.load(1, {
            shade: [0.1,'#fff']  //0.1透明度的白色背景
        });

        // 监听表单提交
        form.on('submit(add)', function(data){
            var that = this;
            var msg = active.yanzhengbianhao(data.field.saZipCode,data.field.saBuildingId);
            if(msg){

                layer.msg(msg);
                return false;
            }
            if((data.field.saBuildingLng).length == 0){
                layer.msg('请在地图上选择位置');
                return false;
            }

            // 禁止点击
            $(that).addClass('zq-submit-disabled');
            axios.post("/sgBuilding/updateSGBuilding", data.field)
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
        var active = {
            yanzhengbianhao : function (saZipCode,saBuildingId) {
                var msg;
                // alert(saBuildingId);
                $.ajax({
                    url: '/sgBuilding/checkZipCode',
                    type: 'post',
                    data: {'saZipCode': saZipCode,'singaporeAreaId':saBuildingId},
                    async: false,
                    success: function (data) {
                        console.log(data);
                        if (data.code == 30050) {
                            msg = '该邮编已录入，请不要重复录入';
                        }

                    }
                });

                return msg;
            }
        }


        // 获取区域列表
        var res;
        $.ajax({
            url: '/area/getSingaporeAreaListNoPaging',
            type: 'post',
            dataType: 'JSON',
            async:false,
            success:function (data) {

                if(data.data != null){
                    res = data.data;
                    for(var i in res){
                        $('select[name = "saId"]').append('<option value="'+ res[i].singaporeAreaId +'">'+res[i].singaporeAreaName +'</option>')
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


            // 集结地
            setMapOnAll1(null);
            markers1 = [];
            if(data.value){
                $('select[name="rallyPointId"]').html('<option value=""></option>');

                $.ajax({
                    url: '/sgBuilding/getRallyPoints',
                    data: {'saId':data.value},
                    type:'post',
                    dataType:'JSON',
                    success: function(data){
                        console.log(data);

                        if(data.code == 30001){

                            // 循环集结地
                            $.each(data.data,function(i,n){


                                var location = {lat: Number(n.rallyPointLat), lng: Number(n.rallyPointLng)};
                                // 调用添加集结地函数
                                addMarkers(location,n.rallyPointName);

                                $('select[name="rallyPointId').append('<option value="'+n.rallyPointId+'">'+n.rallyPointName+'</option>');

                            });
                            form.render('select');
                        }
                    }
                })
            }else{
                $('select[name="rallyPointId"]').html('<option value=""></option>');
                form.render('select');
            }

        });



        // 地图加载完成设置标点
        window.onload = function (ev) {

            // 获取编辑信息
            $.ajax({
                url: '/sgBuilding/getSGBuilding',
                dataType: 'JSON',
                data: {'saBuildingId':$('input[name="saBuildingId"]').val()},
                async: false,
                success: function (data) {
                    if(data.msg =="获取成功"){
                        if(data.data != null ){

                            $('input[name="saBuildingName"]').val(data.data.saBuildingName);


                            $('input[name="saBuildingAddress"]').val(data.data.saBuildingAddress);

                            $('input[name="saZipCode"]').val(data.data.saZipCode);



                            $('.lat').val(data.data.saBuildingLat);
                            $('.lng').val(data.data.saBuildingLng);

                            // 添加标记
                            addMarker({lat: Number($('.lat').val()), lng: Number($('.lng').val())});


                            // 区域显示
                            var saId = data.data.saId;
                            $('select[name="saId"] option').each(function (i,item) {
                                if($(item).val() == data.data.saId){
                                    $(item).prop('selected',true);
                                    form.render('select');
                                }
                            });

                            for(let i in res){
                                if(res[i].singaporeAreaId == data.data.saId){
                                    var singaporePoints = res[i].singaporePoints;
                                    var latlngArrays = [];
                                    for(let a in singaporePoints){

                                        latlngArrays.push({lat:Number(singaporePoints[a].lat),lng:Number(singaporePoints[a].lng)})
                                    }

                                    latlngArrays.push(latlngArrays[0]);

                                    addLatLngs(latlngArrays);
                                }
                            }

                            // 集结地
                            var rallyPointId = data.data.rallyPointId;

                            // 集结地
                            $.ajax({
                                url: '/sgBuilding/getRallyPoints',
                                data: {'saId':saId},
                                type:'post',
                                dataType:'JSON',
                                success: function(data){
                                    console.log(data);

                                    if(data.code == 30001){

                                        // 循环集结地
                                        $.each(data.data,function(i,n){


                                            var location = {lat: Number(n.rallyPointLat), lng: Number(n.rallyPointLng)};
                                            // 调用添加集结地函数
                                            addMarkers(location,n.rallyPointName);

                                            if(rallyPointId == n.rallyPointId){
                                                $('select[name="rallyPointId').append('<option value="'+n.rallyPointId+'" selected>'+n.rallyPointName+'</option>');
                                            }else{
                                                $('select[name="rallyPointId').append('<option value="'+n.rallyPointId+'">'+n.rallyPointName+'</option>');
                                            }



                                        });
                                        form.render('select');
                                        layer.close(editLoad);
                                    }
                                }
                            })



                        }

                    }

                }
            });



        }






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
    $('input[name="saBuildingLat"]').val(location.lat);
    $('input[name="saBuildingLng"]').val(location.lng);
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


var markers1 = [];
// 添加集结地
function addMarkers(location,name) {
    var marker = new google.maps.Marker({
        position: location,
        map: map,
        title: name
    });
    var infowindow = new google.maps.InfoWindow({ content: name });
    infowindow.open(map, marker);
    markers1.push(marker);
}

// 删除标记
function setMapOnAll1(map) {
    for (var i = 0; i < markers1.length; i++) {
        markers1[i].setMap(map);
    }
}

