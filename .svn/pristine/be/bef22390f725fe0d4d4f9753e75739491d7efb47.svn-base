$(document).ready(function(){

	
	layui.use(['layer', 'form'], function(){
		var layer = layui.layer
		  ,form = layui.form;
	  
	  	// 监听表单提交
	  	form.on('submit(login)', function(data){
            var that = this;

            data.field.userName = $.trim(data.field.userName)

            // 禁止点击
            $(that).addClass('zq-submit-disabled');


            axios.post("/adminlogin/loginAdmin", data.field)
                .then(function (response) {
                    console.log(response)
                    //提示信息并跳转页面
                    layer.msg(response.data.msg);
                    if (response.data.msg == '登录成功') {
                        setTimeout(function () {
                            window.location.href="/admin/index/index"  //刷新父页面
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

})