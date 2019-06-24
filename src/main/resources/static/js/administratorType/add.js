$(document).ready(function(){

	layui.use(['layer', 'form'], function(){
		var layer = layui.layer
		  	,form = layui.form;


		// 监听表单提交
		form.on('submit(add)', function(data){

            var that = this;
            var msg = active.yanzhengbianhao(data.field.name);
            if(msg){

                layer.msg(msg);
                return false;
            }
            // 禁止点击
            $(that).addClass('zq-submit-disabled');

            axios.post("/adminUserType/addUserType", data.field)
                .then(function (response) {
                    // console.log(response)
                    //提示信息并跳转页面
                    layer.msg(response.data.msg);
                    if (response.data.code == 30005) {
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
            yanzhengbianhao : function (name) {
                var msg;
                $.ajax({
                    url: '/adminUserType/checkUserTypeName',
                    type: 'post',
                    data: {'name': name},
                    async: false,
                    success: function (data) {
                        console.log(data);
                        if (data.code == 30056) {
                            msg = '用户类型名称已存在，请不要重复添加';
                        }

                    }
                });

                return msg;
            }
        }

		// 获取权限组
        $.ajax({
            url: '/admin/user/selectGroupAll',
            type: 'post',
            dataType: 'JSON',
            success: function (data) {

                if(data.data != null){
                    var res = data.data;
                    for(let i in res){
                        $('select[name = "groupId"]').append('<option value="'+ res[i].groupId +'">'+res[i].groupName+'</option>')

                    }
                    form.render('select');
                }
            },
            error: function (data) {

            }
        });
	});



})