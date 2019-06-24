$(document).ready(function(){


	layui.use(['layer', 'form','table'], function(){
        var layer = layui.layer
            ,form = layui.form
            ,table = layui.table;

        // 监听提交
        form.on('submit(add)', function(data){
            var that = this;

            data.field.rallyPointId = parseInt(data.field.rallyPointId);
            data.field.userId = parseInt(data.field.userId);
            // 禁止点击
            $(that).addClass('zq-submit-disabled');
            axios.post("/carTask/addTruckTask", data.field)
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




        //	获取用户列表
        $.ajax({
            url: '/carTask/appUserByCar',
            dataType: 'JSON',
            success: function(data){

                if(data.data != null){
                    var res = data.data;
                    for(let i in res){
                        $('select[name ="userId"]').append('<option value="'+ res[i].appUserId +'">'+ res[i].userName +'</option>')
                    }
                    layui.form.render('select');
                }
            }
        });
        // 获取集结地
        $.ajax({
            url: '/carTask/selectRallyPointAll',
            dataType: 'JSON',
            success: function(data){

                if(data.data != null){
                    var res = data.data;

                    $.each(res,function(i,n){
                        $('select[name ="rallyPointId"]').append('<option value="'+ n.rallyPointId +'">'+ n.rallyPointName +'</option>')
                    });

                    layui.form.render('select');
                }
            }
        })
	});




});