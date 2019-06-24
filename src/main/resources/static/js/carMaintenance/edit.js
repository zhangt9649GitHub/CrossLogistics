




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
                    $('input[name="carId"]').val(res.carId);
                    $('.carNumber').val(res.carNumber);
                    $('input[name="address"]').val(res.address);
                    $('textarea[name="content"]').text(res.content);
                    $('select[name = "state"] option').each(function (i,item) {
                        if($(item).val() == res.state){
                            $(item).prop('selected',true);
                            form.render('select');
                        }
                    });
                }
            }
        });

        // 监听表单提交
        form.on('submit(edit)',function(data){
            var that = this;

            // 禁止点击
            $(that).addClass('zq-submit-disabled');
            axios.post("/CarMaintenance/editCarMaintenance", data.field)
                .then(function (response) {
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





    });





});

