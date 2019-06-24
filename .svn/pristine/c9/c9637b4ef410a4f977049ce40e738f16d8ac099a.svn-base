$(document).ready(function(){

    layui.use(['layer', 'form','table'], function(){
        var layer = layui.layer
            ,form = layui.form
            ,table = layui.table;

        //表单提交
        form.on('submit(add)',function(data){
            var that = this;
            // 禁止点击
            $(that).addClass('zq-submit-disabled');
            axios.post("/area/editchinaarea", data.field)
                .then(function (response) {
                    console.log(response)
                    //提示信息并跳转页面
                    layer.msg(response.data.msg);
                    if (response.data.msg == '修改成功') {
                        setTimeout(function () {
                            window.parent.location.reload(); //刷新父页面
                        }, 1000);
                    }
                    // 取消禁止点击
                    else {
                        $(that).removeClass('zq-submit-disabled');
                    }
                });
            return false;
        });

        // 获取信息
        $.ajax({
            url: '/area/getChinaAreaByChinaAreaId',
            dataType: 'JSON',
            data: {'chinaAreaId':$('input[name="chinaAreaId"]').val()},
            success: function (data) {

                if(data.data != null){

                    $('input[name = "chinaAreaName"]').val(data.data.chinaAreaName);
                    $('input[name = "chinaAreaParentId"]').val(data.data.chinaAreaParentId);

                    $('select[name="chinaAreaType"] option').each(function (i,item) {
                        if($(item).val() == data.data.chinaAreaType){
                            $(item).prop('selected',true);
                            form.render('select');
                        }
                    })

                }
            },
            error: function (data) {

            }
        })


    });



});