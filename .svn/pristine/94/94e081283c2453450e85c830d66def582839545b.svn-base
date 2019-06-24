$(document).ready(function(){

    layui.use(['layer', 'form','table'], function(){
        var layer = layui.layer
            ,form = layui.form
            ,table = layui.table;

        //表单提交
        form.on('submit(add)',function(data){

            axios.post("/entryAndExit/updateEntryAndExit", data.field)
                .then(function (response) {
                    console.log(response)
                    //提示信息并跳转页面
                    layer.msg(response.data.msg);
                    if (response.data.msg == '修改成功') {
                        setTimeout(function () {
                            window.parent.location.reload(); //刷新父页面
                        }, 1000);
                    }
                    //重新赋值token数据
                    else {

                    }
                });
            return false;
        });

        // 获取信息
        $.ajax({
            url: '/entryAndExit/getEntryAndExitById',
            dataType: 'JSON',
            data: {'eaeId':$('input[name="eaeId"]').val()},
            success: function (data) {

                if(data.data != null){

                    $('input[name = "carrierCompany"]').val(data.data.carrierCompany);
                    $('input[name = "contact"]').val(data.data.contact);
                    $('input[name = "mobile"]').val(data.data.mobile);

                    $('select[name="exitWay"] option').each(function (i,item) {
                        if($(item).val() == data.data.exitWay){
                            $(item).prop('selected',true);
                            form.render('select');
                        }
                    });
                    $('input[name = "address"]').val(data.data.address);

                }
            },
            error: function (data) {

            }
        })


    });



});