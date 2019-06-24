$(document).ready(function(){

	layui.use(['layer', 'form','table','laydate'], function(){
		var layer = layui.layer
		  	,form = layui.form
	  	  	,table = layui.table
	  	  	,laydate = layui.laydate;

		// 监听表单提交
        form.on('submit(add)', function(data){
            var that = this;

            var zhengze = /^([0-9]+)(\+)?([0-9]+)$/;

            if(!zhengze.test(data.field.mobile)){
                layer.msg('联系方式格式不正确');
                return false;
            }

            if((data.field.password).length < 6){
                layer.msg('密码最少6位');
                return false;
            }
            if((data.field.password).length >20){
                layer.msg('密码长度不能超过20位');
                return false;
            }

            if(data.field.password != $('.queren-password').val()){
                layer.msg('两次密码不一致！');
                return false;
            }
            // 获取头像
            data.field.headPic = $('#preview img').attr('src');
            if(data.field.headPic == '/static/image/dj.jpg'){
                layer.msg('请添加头像');
                return false;
            }

            data.field.mobile = $.trim(data.field.mobile);
            data.field.userName = $.trim(data.field.userName);
            data.field.actualName = $.trim(data.field.actualName);
            // 禁止点击
            $(that).addClass('zq-submit-disabled');
            axios.post("/appUser/addAppUser", data.field)
                .then(function (response) {
                    console.log(response)
                    //提示信息并跳转页面
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
	
	});



})