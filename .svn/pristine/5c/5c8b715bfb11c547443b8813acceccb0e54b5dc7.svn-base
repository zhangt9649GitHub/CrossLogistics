$(document).ready(function(){

    layui.use(['layer', 'form','table','laydate'], function(){
        var layer = layui.layer
            ,form = layui.form
            ,laydate = layui.laydate;


        laydate.render({
            elem:'#moneyGiveMonth',
            type:'month'
        });

        //监听表单提交
        form.on('submit(add)', function(data){
            var that = this;
            // 禁止点击
            $(that).addClass('zq-submit-disabled');
            axios.post("/payGive/insertPayGive", data.field)
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
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });

    });



});