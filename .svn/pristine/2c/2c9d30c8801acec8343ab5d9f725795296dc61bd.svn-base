$(document).ready(function(){

    layui.use(['layer', 'form','table'], function(){
        var layer = layui.layer
            ,form = layui.form
            ,table = layui.table;

        //表单提交
        form.on('submit(add)',function(data){
            var that = this;
            if(data.field.parentId == data.field.id){
                layer.msg('parentId的值不能与自己的ID相同');

                return false;
            }

            // 禁止点击
            $(that).addClass('zq-submit-disabled');
            axios.post("/bizdictionary/updateBizdictionary", data.field)
                .then(function (response) {
                    console.log(response)
                    //提示信息并跳转页面
                    layer.msg(response.data.msg);
                    if (response.data.msg == '更新成功') {
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
            url: '/bizdictionary/getBizdictionary',
            dataType: 'JSON',
            data: {'id':$('#id').val()},
            success: function (data) {

                if(data.code == 0){

                    $('input[name = "bizName"]').val(data.data.bizName);
                    $('input[name = "value"]').val(data.data.value);
                    $('input[name = "parentId"]').val(data.data.parentId);
                    // $('input[name = "disorder"]').val(data.data.disorder);

                }
            },
            error: function (data) {

            }
        })


    });



});