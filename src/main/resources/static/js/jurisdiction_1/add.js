$(document).ready(function(){

	//全局定义一次, 加载formSelects
    layui.config({
        base: '../../layui/src/' //此处路径请自行处理, 可以使用绝对路径
    }).extend({
        formSelects: 'formSelects-v4'
    });

	layui.use(['layer', 'form','formSelects'], function(){
		var layer = layui.layer
		  	,form = layui.form
	  	  	,formSelects = layui.formSelects;

        form.on('submit(add)', function(data){
            var that = this;
           data.field.saIds = (data.field.saIds).split(',');
            // 禁止点击
            $(that).addClass('zq-submit-disabled');
            axios.post("/staffGroupAccess/addStaffGroup", data.field)
                .then(function (response) {
                    console.log(response)
                    // 提示信息并跳转页面
                    layer.msg(response.data.message);
                    if (response.data.message == '添加成功') {
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


        // 获取管理模块
        $.ajax({
            url: '/staff/staffAccessAll',
            dataType: 'JSON',
            success: function (data) {
                if(data.code == 0){
                    var res = data.data;
                    for(let i in res){
                        $('#saIds').append('<option value="'+res[i].saId+'">'+res[i].saName+'</option>')
                    }
                    layui.formSelects.render();

                }
            },
            error: function (data) {

            }
        });



	});



})