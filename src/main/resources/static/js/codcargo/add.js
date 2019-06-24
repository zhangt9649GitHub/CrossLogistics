$(document).ready(function () {



    layui.use(['form','layer'],function () {
        var form = layui.form,
            layer = layui.layer;

        form.on('submit(add)', function(data){
            var zhengze = /^([0-9]+)(\+)?([0-9]+)$/;

            if(!zhengze.test(data.field.receiptContactMobile)){
                layer.msg('联系方式格式不正确');
                return false;
            }

            if(data.field.tripartiteWeight){
                if(data.field.tripartiteWeight < 0){
                    layer.msg('亲，货物重量不能为负数！');
                    return false;
                }
            }

            if(data.field.totalGoods){
                if(data.field.totalGoods < 0){
                    layer.msg('亲，总箱数不能为空！');
                    return false;
                }
            }


            if(data.field.itemValue){
                if(data.field.itemValue < 0){
                    layer.msg('亲，货值不能为负数！');
                    return false;
                }
            }

            var that = this;
            // 禁止点击
            $(that).addClass('zq-submit-disabled');
            axios.post("/codGoods/insertCodGoods", data.field)
                .then(function (response) {
                    console.log(response)
                    // // 提示信息并跳转页面
                    layer.msg(response.data.msg);
                    if (response.data.code == 20003) {
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


        $.ajax({
            url: '/pdaGoods/getGoodsCategory',
            data: {'language':'zh'},
            success: function (data) {

                if(data.code == 20001){

                    $.each(data.data,function (i,item) {

                        $('select[name="category"]').append('<option value="'+ item.value +'">'+ item.value +'</option>')

                    });
                    form.render('select');

                }
            }

        });



    })


});