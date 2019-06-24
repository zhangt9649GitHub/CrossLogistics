$(document).ready(function () {



    layui.use(['form','layer'],function () {
        var form = layui.form,
            layer = layui.layer;

        form.on('submit(add)', function(data){


            if(data.field.actualLong){
                if(data.field.actualLong < 0){
                    layer.msg('亲，货物长度不能为负数！');
                    return false;
                }
            }
            if(data.field.actualWidth){
                if(data.field.actualWidth < 0){
                    layer.msg('亲，货物宽度不能为负数！');
                    return false;
                }
            }

            if(data.field.actualHeight){
                if(data.field.actualHeight < 0){
                    layer.msg('亲，货物高度不能为负数！');
                    return false;
                }
            }

            if(data.field.actualWeight){
                if(data.field.actualWeight < 0){
                    layer.msg('亲，货物重量不能为负数！');
                    return false;
                }
            }

            if(data.field.itemValue){
                if(data.field.itemValue < 0){
                    layer.msg('亲，货值不能为负数！');
                    return false;
                }
            }

            // 获取货物照片
            data.field.ufSavePath =$('#preview img').attr('src');
            if(data.field.headPic == '/static/image/dj.jpg'){
                layer.msg('请添加货物照片');
                return false;
            }



            var that = this;
            // 禁止点击
            $(that).addClass('zq-submit-disabled');
            axios.post("/goods/insertTransshipmentGoodsInfo", data.field)
                .then(function (response) {
                    console.log(response)
                    // // 提示信息并跳转页面
                    layer.msg(response.data.msg);
                    if (response.data.code == 20013) {
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