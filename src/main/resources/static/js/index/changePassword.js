$(document).ready(function(){


    layui.use(['layer', 'form'], function(){
        var layer = layui.layer
            ,form = layui.form;

        // 监听表单提交
        form.on('submit(*)', function(data){

            var that = this;
            if((data.field.newPassword).length < 6){
                layer.msg('新密码最少6位');
                return false;
            }
            if((data.field.newPassword).length >20){
                layer.msg('新密码长度不能超过20位');
                return false;
            }

            // 禁止点击
            $(that).addClass('zq-submit-disabled');
            axios.post("/admin/user/editAdminUser", data.field)
                .then(function (response) {
                    console.log(response)
                    //提示信息并跳转页面
                    layer.msg(response.data.message);
                    if (response.data.message == '编辑成功') {
                        setTimeout(function () {
                            // window.parent.location.reload(); //刷新父页面

                            $.ajax({
                                url: '/adminlogin/logOutAdmin',
                                type: 'post',
                                success: function (data) {
                                    if(data.msg == '退出成功'){
                                        // layer.msg(data.msg, {icon: 1});
                                        // setTimeout(function(){
                                        window.parent.location.href="/admin/index/login"
                                        // },500)
                                    }
                                }
                            })
                        }, 1000);
                    }
                    // 取消禁止点击
                    else {
                        $(that).removeClass('zq-submit-disabled');
                    }
                });
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });



        // 获取编辑信息
        $.ajax({
            url: '/admin/user/selectEditAdminUser',
            type: 'post',
            data: {'adminUid':$('input[name="adminUid"]').val()},
            dataType: 'JSON',
            success: function (data) {
                if(data.code == 0){
                    $('input[name = "groupId"]').val(data.data.groupId);
                }
            }
        })

    });

});