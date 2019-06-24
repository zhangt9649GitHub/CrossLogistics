$(document).ready(function(){

	layui.use(['layer', 'form'], function(){
		var layer = layui.layer
		  	,form = layui.form;


		// 监听表单提交
		form.on('submit(edit)', function(data){

            var that = this;
            var msg = active.yanzhengbianhao(data.field.name,data.field.adminUserTypeId);
            if(msg){

                layer.msg(msg);
                return false;
            }
            // 禁止点击
            $(that).addClass('zq-submit-disabled');
            axios.post("/adminUserType/updateUserType", data.field)
                .then(function (response) {
                    // console.log(response)
                    //提示信息并跳转页面
                    layer.msg(response.data.msg);
                    if (response.data.code == 30003) {
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
            yanzhengbianhao : function (name,adminUserTypeId) {
                var msg;
                $.ajax({
                    url: '/adminUserType/checkUserTypeName',
                    type: 'post',
                    data: {'name': name,'adminUserTypeId':adminUserTypeId},
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
        };



        // 获取编辑信息

            $.ajax({
                url: '/adminUserType/getByUserTypeId',
                type: 'get',
                data: {'adminUserTypeId':$('input[name="adminUserTypeId"]').val()},
                dataType: 'JSON',
                success: function (data) {
                    // console.log(data);
                    if(data.code == 0){
                        $('input[name="name"]').val(data.data.name);

                        $('textarea[name="comment"]').text(data.data.comment);

                        $('select[name="type"] option').each(function (i,item) {
                            if($(item).val() == data.data.type){
                                $(item).prop('selected',true);
                                form.render('select');
                            }
                        });
                    }

                },
                error: function (data) {

                }
            })





    });



})