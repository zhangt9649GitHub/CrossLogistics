




$(document).ready(function(){

    layui.use(['layer', 'form','table'], function(){
        var layer = layui.layer
            ,form = layui.form
            ,table = layui.table;

        // 获取编辑信息
        $.ajax({
            url: '/CarMaintenance/getCarMaintenanceById',
            dataType: 'JSON',
            data:{'carMaintenanceId':$('input[name="carMaintenanceId"]').val()},
            async: false,
            success:function (data) {
                console.log(data);
                if(data.code == 0){
                    var res = data.data;
                    $('.carNumber .inline span').text(res.carNumber);
                    $('.address .inline span').text(res.address);
                    $('.describe .inline span').text(res.content);
                    $('.cbmServicetime .inline span').text(res.cbmServicetime);
                    $('.cbmFinishtime .inline span').text(res.cbmFinishtime);

                    var state;
                    switch(res.state){
                        case 1:
                            state = '报修';
                            break;
                        case 2:
                            state = '维修中';
                            break;
                        case 3:
                            state = '维修成功';
                            break;
                        case 4:
                            state = '维修失败';
                            break;
                    }
                    $('.state .inline span').text(state);
                }
            }
        });
    });





});

