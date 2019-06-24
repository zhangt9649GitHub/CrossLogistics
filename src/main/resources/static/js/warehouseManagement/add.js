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
            axios.post("/warehouseposition/addwarehouseposition", data.field)
                .then(function (response) {
                    console.log(response)
                    //提示信息并跳转页面
                    layer.msg(response.data.msg);
                    if (response.data.msg == '添加成功') {
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
            },
            error: function (data) {

            }
        })

    });



});