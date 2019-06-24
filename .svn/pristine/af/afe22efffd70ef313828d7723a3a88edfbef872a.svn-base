



$(document).ready(function(){

    layui.use(['layer', 'form','table'], function(){
        var layer = layui.layer
            ,form = layui.form
            ,table = layui.table;

        //表单提交
        form.on('submit(add)',function(data){

            var that = this;

            var msg = duplicateName(data.field.warehouseName,data.field.warehouseId);
            if(msg == 'no'){
                layer.msg('仓库名称重复，请重新输入');
                return false;
            }

            var zhengze = /^([0-9]+)(\+)?([0-9]+)$/;

            if(!zhengze.test(data.field.warehousePhone)){
                layer.msg('联系方式格式不正确');
                return false;
            }

            if(!(data.field.warehouseLng)){
                layer.msg('请在地图上选择位置！')
                return false;
            }

            // 禁止点击
            $(that).addClass('zq-submit-disabled');
            axios.post("/warehouse/updatewarehouse", data.field)
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
        })
    });

    //	验证重名
    function duplicateName(name,id) {
        var msg = 'ok';
        $.ajax({
            url:'/warehouse/checkWarehouseName',
            data: {'warehouseId':id,'warehouseName':name},
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

    // 获取信息
    $.ajax({
        url: '/warehouse/getwarehouse',
        data: {'warehouseId':$('input[name ="warehouseId"]').val()},
        dataType:"JSON",
        success:function(data){
            console.log(data);
            if(data.msg == '获取成功'){

                $('input[name = "warehouseName"]').val(data.data.warehouseName);
                $('input[name = "warehouseAddress"]').val(data.data.warehouseAddress);
                $('input[name = "warehousePhone"]').val(data.data.warehousePhone);


                $('.lat').val(data.data.warehouseLat);
                $('.lng').val(data.data.warehouseLng);
            }

        },
        error:function(data){
            console.log("错误！！");
            // window.clearInterval(timer);
        }
    })

    // 地图加载完成设置标点
    window.onload = function (ev) {
        addMarker({lat: Number($('.lat').val()), lng: Number($('.lng').val())});
        console.log({lat: Number($('.lat').val()), lng: Number($('.lng').val())});
    }

});

// 谷歌地图 start

var map;
var markers = [];
function initMap() {

    // 经纬度
    var uluru = {lat: 1.351993, lng: 103.866343}

    // 创建地图并设置中心点
    map = new google.maps.Map(document.getElementById('map'), {
        center: uluru,
        zoom: 13
    });

    // 地图标点
    // var marker = new google.maps.Marker({
    //     position: uluru,
    //     map: map,
    //     title: 'Hello World!'
    // });

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
    $('.zq-google-search .lat').val(location.lat);
    $('.zq-google-search .lng').val(location.lng);
    markers.push(marker);
}
function setMapOnAll(map) {

    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(map);
    }
}


// 谷歌地图 end