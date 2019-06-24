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
            axios.post("/payGive/updatePayGive", data.field)
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
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });


        // 获取编辑信息

        $.ajax({
            url: '/payGive/getPayGiveById',
            data: {'payGiveId':$('input[name="payGiveId"]').val()},
            dataType: 'JSON',
            success: function (data) {
                console.log(data);
                if(data.code == 20001){
                    if(data.data != null){
                        var res = data.data;
                        $('input[name="number"]').val(res.number);
                        $('input[name="staffName"]').val(res.staffName);
                        $('input[name="giveMoney"]').val(res.giveMoney);
                        $('input[name="moneyGiveMonth"]').val(res.moneyGiveMonth);
                    }
                }
            }

        })

    });



});