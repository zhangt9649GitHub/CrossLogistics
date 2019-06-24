
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
		laydate.render({
	    	elem: '#test1' //指定元素
            ,lang: 'en'
	  	});

        // 验证新增员工用户名重复
        var verificationUserName = function(name) {
            var value;
            $.ajax({
                url: '/staff/checkUserName',
                type: 'post',
                data: {'userName':name},
                dataType: 'JSON',
                async: false,
                success: function (data) {
                    console.log(data.message);
                       value = data.message;
                }

            });
            return value;
        };



		// 监听表单提交
        form.on('submit(add)', function(data){
            var that = this;
            data.field.userName = $.trim(data.field.userName)
            var value = verificationUserName(data.field.userName);
            if(value == '员工账号重复'){
                layer.msg(value);
                return false;
            }

            var zhengze = /^([0-9]+)(\+)?([0-9]+)$/;

            if(!zhengze.test(data.field.mobile)){
                layer.msg('联系方式格式不正确');
                return false;
            }

            if((data.field.password).length < 6){
                layer.msg('密码长度最少6位');
                return false;
            }
            if((data.field.password).length >20){
                layer.msg('密码长度不能超过20位');
                return false;
            }
            if(data.field.password != $('#queren-password').val()){
                layer.msg('两次密码不一致！');
                return false;
            }
            // 获取头像
            data.field.headPic =$('#preview img').attr('src');
            if(data.field.headPic == '/static/image/dj.jpg'){
                layer.msg('请添加员工头像');
                return false;
            }

            // 禁止点击
            $(that).addClass('zq-submit-disabled');


            axios.post("/staff/addStaff", data.field)
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



        // 员工权限列表
        $.ajax({
            url: "/staff/staffGroupAll",
            dataType: 'JSON',
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
            success: function (data) {
                if(data.code == 30001){
                    var res = data.data;

                    for( let i in res ){
                        $('select[name="warehouseId"]').append('<option value="'+res[i].warehouseId+'">'+res[i].warehouseName+'</option>');
                    }
                    form.render('select');
                }
            }
        });


    });





});