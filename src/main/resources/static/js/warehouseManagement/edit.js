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
            axios.post("/warehouseposition/editwarehouseposition", data.field)
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


        //获取仓库列表
        $.ajax({
            url: '/warehouseposition/getwarehouselistnopage',
            dataType:"JSON",
            success: function(data){
                console.log(data);
                if(data.msg == '获取成功'){
                    var res = data.data;
                    for(let i in res){
                        $('select[name = "warehouseId"]').append('<option value="'+ res[i].warehouseId +'">'+res[i].warehouseName+'</option>');
                    }
                    form.render('select');
                }
                editInfo();
            },
            error: function (data) {

            }
        });

        // 获取编辑信息
        function editInfo(){
            $.ajax({
                url: '/warehouseposition/getwarehouseposition',
                dataType:"JSON",
                data: {'wpId':$('#id').val()},
                type: 'post',
                success: function(data){
                    console.log(data);
                    if(data.msg ='获取成功'){
                        $('input[name ="wpNumber"]').val(data.data.wpNumber);
                        $('input[name ="wpUse"]').val(data.data.wpUse);

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
            });
        }


    });



});