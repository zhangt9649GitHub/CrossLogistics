$(document).ready(function(){

	//全局定义一次, 加载formSelects
    layui.config({
        base: '../../layui/src/' //此处路径请自行处理, 可以使用绝对路径
    }).extend({
        formSelects: 'formSelects-v4'
    });

	layui.use(['layer', 'form','laydate','formSelects'], function(){
		var layer = layui.layer
		  	,form = layui.form
		  	,laydate = layui.laydate
	  	  	,formSelects = layui.formSelects;


		// 出生日期
		var bornYears = laydate.render({
	    	elem: '#date' //指定元素
            ,lang: 'en'
	  	});

        // 监听表单提交
        form.on('submit(add)', function(data){
            var that = this;

            var zhengze = /^([0-9]+)(\+)?([0-9]+)$/;

            if(!zhengze.test(data.field.mobile)){
                layer.msg('联系方式格式不正确');
                return false;
            }


            if((data.field.newPassword).length < 6){
                layer.msg('新密码长度最少6位');
                return false;
            }
            if((data.field.newPassword).length >20){
                layer.msg('新密码长度不能超过20位');
                return false;
            }
            // 获取头像
            data.field.headPic = $('#preview img').attr('src');
            if(data.field.headPic == '/static/image/dj.jpg'){
                layer.msg('请添加员工头像');
                return false;
            }
            // 禁止点击
            $(that).addClass('zq-submit-disabled');
            axios.post("/staff/editStaff", data.field)
                .then(function (response) {
                    console.log(response)
                    // // 提示信息并跳转页面
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



        // 员工权限列表
        $.ajax({
            url: "/staffGroupAccess/staffGroupAll",
            dataType: 'JSON',
            async:false,
            success: function (data) {
                if(data.code == 0){
                    var res = data.data;
                    for( let i in res ){
                        $('select[name="staffGroupId"]').append('<option value="'+res[i].staffGroupId+'">'+res[i].staffGroupName+'</option>');
                    }
                    form.render('select');

                }
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
                    editInfo();
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
                url: '/staff/selectEeditStaff',
                dataType: 'JSON',
                data: {'staffId':$('input[name="staffId"]').val()},
                success: function (data) {
                    if(data.data != null){
                        $('input[name="staffName"]').val(data.data.staffName);
                        $('input[name="mobile"]').val(data.data.mobile);
                        $('input[name="position"]').val(data.data.position);
                        $('input[name="userName"]').val(data.data.userName);
                        $('input[name="age"]').val(data.data.age);
                        $('#preview img').attr('src',data.data.headPic);
                        $('#date').val(data.data.bornYears);
                        $('input[name ="sex"]').each(function (i,item) {
                            if($(item).val() == data.data.sex){
                                $(item).prop('checked',true);
                                form.render();
                            }
                        })
                        $('select[name="staffGroupId"] option').each(function (i,item) {

                            if($(item).val() == data.data.staffGroupId){
                                $(item).prop('selected',true);
                                form.render('select');
                            }
                        })

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
                }
            })
        }

	  	
	});



});

