




$(document).ready(function(){

	layui.use(['layer', 'form','table'], function(){
		var layer = layui.layer
		  	,form = layui.form
	  	  	,table = layui.table;




        // 监听表单提交
        form.on('submit(add)',function(data){
            var that = this;



            var msg = active.yanzhengbianhao(data.field.lockNumber,data.field.lockId);
            if(msg){

                layer.msg(msg);
                return false;
            }

            // return false;
            // 禁止点击
            $(that).addClass('zq-submit-disabled');
            axios.post("/carLock/addLock", data.field)
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

        var active = {
            yanzhengbianhao : function (lockNumber) {
                var msg;
                $.ajax({
                    url: '/carLock/checkNumber',
                    type: 'post',
                    data: {'lockNumber': lockNumber},
                    async: false,
                    success: function (data) {
                        console.log(data);
                        if (data.code == 30048) {
                            msg = data.msg;
                        }

                    }
                });

                return msg;
            }
        }


    });





});


