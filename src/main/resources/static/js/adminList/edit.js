$(document).ready(function(){

	layui.use(['layer', 'form'], function(){
		var layer = layui.layer
		  	,form = layui.form;


		// 监听表单提交
		form.on('submit(edit)', function(data){

            var that = this;
            if((data.field.newPassword).length < 6){
                layer.msg('密码最少6位');
                return false;
            }
            if((data.field.newPassword).length >20){
                layer.msg('密码长度不能超过20位');
                return false;
            }
            data.field.adminName = $.trim(data.field.adminName);
            delete data.field.userType;

            // 禁止点击
            $(that).addClass('zq-submit-disabled');
            axios.post("/admin/user/editAdminUser", data.field)
                .then(function (response) {
                    // console.log(response)
                    //提示信息并跳转页面
                    layer.msg(response.data.message);
                    if (response.data.message == '编辑成功') {
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
            async:false,
            success: function (data) {

                if(data.message == '获取成功'){
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
        // 员工归属地
        $.ajax({
            url: "/staff/getStaffAttribution",
            dataType: 'JSON',
            async:false,
            success: function (data) {
                if(data.code == 0){
                    var res = data.data;
                    for( let i in res ){
                        $('select[name="attribution"]').append('<option value="'+res[i].value+'">'+res[i].bizName+'</option>');
                    }
                    form.render('select');

                }
            }
        });
        // 所属仓库
        $.ajax({
            url: "/staff/getWarehouseList",
            dataType: 'JSON',
            async:false,
            success: function (data) {
                if(data.code == 30001){
                    var res = data.data;
                    $('select[name="warehouseId"]').append('<option value="0">后台人员</option>');
                    for( let i in res ){
                        $('select[name="warehouseId"]').append('<option value="'+res[i].warehouseId+'">'+res[i].warehouseName+'</option>');
                    }
                    form.render('select');
                    editInfo();
                }
            }
        });

        // 获取编辑信息
        function editInfo() {
            $.ajax({
                url: '/admin/user/selectEditAdminUser',
                type: 'post',
                data: {'adminUid':$('input[name="adminUid"]').val()},
                dataType: 'JSON',
                success: function (data) {
                    // console.log(data);
                    if(data.message == '获取成功'){
                        $('input[name="adminName"]').val(data.data.adminName);
                        $('select[name="groupId"] option').each(function (i,item) {
                            if($(item).val() == data.data.groupId){
                                $(item).prop('selected',true);
                                form.render('select');
                            }
                        });
                        $('input[name="userType"]').val(data.data.userType);

                        $('select[name="attribution"] option').each(function (i,item) {
                            if($(item).val() == data.data.attribution){
                                $(item).prop('selected',true);
                                form.render('select');
                            }
                        });

                        $('select[name="warehouseId"] option').each(function (i,item) {
                            if($(item).val() == data.data.warehouseId){
                                $(item).prop('selected',true);
                                form.render('select');
                            }
                        });
                    }

                },
                error: function (data) {

                }
            })
        }




    });



});