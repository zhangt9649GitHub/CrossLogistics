$(document).ready(function(){

	layui.use(['layer', 'form'], function(){
		var layer = layui.layer
		  	,form = layui.form;


		// 监听表单提交
		form.on('submit(add)', function(data){

            var that = this;
			$('#confirmPassword').val();

			if((data.field.password).length < 6){
                layer.msg('密码最少6位');
                return false;
			}
			if((data.field.password).length >20){
                layer.msg('密码长度不能超过20位');
                return false;
			}

			if(data.field.password != $('#confirmPassword').val()){
				layer.msg('两次密码不一致！');
				return false;
			}

			data.field.adminName = $.trim(data.field.adminName);
            // 禁止点击
            $(that).addClass('zq-submit-disabled');

            axios.post("/admin/user/addAdminUser", data.field)
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

		// 获取权限组
        $.ajax({
            url: '/admin/user/selectGroupAll',
            type: 'post',
            dataType: 'JSON',
            success: function (data) {

                if(data.data != null){
                    var res = data.data;
                    $.each(res,function(i,item){
                        $('select[name = "groupId"]').append('<option value="'+ item.groupId +'">'+item.groupName+'</option>')
                    });
                    form.render('select');
                }
            },
            error: function (data) {

            }
        });

        // 管理员类型
        $.ajax({
            url: '/adminUserType/getAllUserType',
            dataType: 'JSON',
            success: function (data) {

                if(data.code == 0){
                    var res = data.data;
                    $.each(res,function(i,item){
                        $('select[name = "adminUserTypeId"]').append('<option value="'+ item.adminUserTypeId +'">'+item.name+'</option>')
                    });
                    form.render('select');
                }
            },
            error: function (data) {

            }
        });

        // 员工归属地
        $.ajax({
            url: "/staff/getStaffAttribution",
            dataType: 'JSON',
            success: function (data) {
                if(data.code == 0){
                    var res = data.data;
                    $.each(res,function(i,item){
                        $('select[name = "attribution"]').append('<option value="'+ item.value +'">'+item.bizName+'</option>')
                    });

                    form.render('select');
                }
            }
        });
        // 所属仓库
        $.ajax({
            url: "/staff/getWarehouseList",
            dataType: 'JSON',
            success: function (data) {
                // console.log(data);
                if(data.code == 30001){
                    var res = data.data;
                    $('select[name="warehouseId"]').append('<option value="0">后台人员</option>');
                    $.each(res,function(i,item){
                        $('select[name = "warehouseId"]').append('<option value="'+ item.warehouseId +'">'+item.warehouseName+'</option>')
                    });
                    form.render('select');
                }
            }
        });
	});



});