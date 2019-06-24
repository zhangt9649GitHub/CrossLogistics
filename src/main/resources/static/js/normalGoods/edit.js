$(document).ready(function () {


    if($('input[name="from"]').val() == '三方货单'){
        $('.zhuanyun').hide();
    }else{
        $('.sanfang').hide();
    }




    layui.use(['form','layer'],function () {
        var form = layui.form,
            layer = layui.layer;

        form.on('submit(add)', function(data){
            var zhengze = /^([0-9]+)(\+)?([0-9]+)$/;

            if($.trim(data.field.receiptContactMobile)){

                if(!zhengze.test(data.field.receiptContactMobile)){
                    layer.msg('联系方式格式不正确');
                    return false;
                }
            }
            // return false;
            if($.trim(data.field.actualLong)){
                if(data.field.actualLong < 0){
                    layer.msg('亲，货物长度不能为负数！');
                    return false;
                }
            }
            if($.trim(data.field.actualWidth)){
                if(data.field.actualWidth < 0){
                    layer.msg('亲，货物宽度不能为负数！');
                    return false;
                }
            }

            if($.trim(data.field.actualHeight)){
                if(data.field.actualHeight < 0){
                    layer.msg('亲，货物高度不能为负数！');
                    return false;
                }
            }

            if($.trim(data.field.actualWeight)){
                if(data.field.actualWeight < 0){
                    layer.msg('亲，货物重量不能为负数！');
                    return false;
                }
            }

            if($.trim(data.field.itemValue)){
                if(data.field.itemValue < 0){
                    layer.msg('亲，货值不能为负数！');
                    return false;
                }
            }




            var that = this;
            // 禁止点击
            $(that).addClass('zq-submit-disabled');
            axios.post("/goods/updateGoodsAndGoodsDetailsInfo", data.field)
                .then(function (response) {
                    // console.log(response)
                    // // 提示信息并跳转页面
                    layer.msg(response.data.msg);
                    if (response.data.code == 20005) {
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
                active.editInfo();
            }

        });

        var active = {
            editInfo : function(){
                $.ajax({
                    url: '/goods/getGoodsAndGoodsDetailsById',
                    data: {'goodsId': $('input[name="goodsId"]').val()},
                    success: function (data) {
                        console.log(data);
                        if(data.code == 20001){
                            var res = data.data;
                            // 收货人
                            $('input[name="receiptContact"]').val(res.receiptContact);

                            // 收货人联系方式
                            $('input[name="receiptContactMobile"]').val(res.receiptContactMobile);

                            // 收货地址
                            $('input[name="receiptAddress"]').val(res.receiptAddress);

                            // 邮编
                            $('input[name="zipCode"]').val(res.zipCode);

                            // 用户编号
                            $('input[name="number"]').val(res.number);

                            // 发货人
                            $('input[name="shipContact"]').val(res.shipContact);

                            // 发货人联系方式
                            $('input[name="shipContactMobile"]').val(res.shipContactMobile);

                            // 发货地址
                            $('input[name="shipAddress"]').val(res.shipAddress);

                            //三方物流单号
                            $('input[name="tripartiteNumber"]').val(res.tripartiteNumber);

                            // 特殊货物
                            $('select[name="isSpecialGoods"] option').each(function (i,item) {
                               if($(item).val() == res.isSpecialGoods){
                                   $(item).prop('selected',true)
                               }
                            });

                            // 货物分类
                            $('select[name="category"] option').each(function (i,item) {
                                if($(item).val() == res.category){
                                    $(item).prop('selected',true)
                                }
                            });

                            // 货物类型
                            $('input[name="goodType"]').val(res.goodType);

                            // 长
                            $('input[name="actualLong"]').val(res.actualLong);

                            // 宽
                            $('input[name="actualWidth"]').val(res.actualWidth);

                            // 高
                            $('input[name="actualHeight"]').val(res.actualHeight);

                            // 重量
                            $('input[name="actualWeight"]').val(res.actualWeight);

                            // 入库仓位
                            $('input[name="intoWpNumber"]').val(res.intoWpNumber);

                            // 出库仓位
                            $('input[name="outWpNumber"]').val(res.outWpNumber);

                            // 出境物流方式
                            $('select[name="exitWay"] option').each(function (i,item) {
                                if($(item).val() == res.exitWay){
                                    $(item).prop('selected',true)
                                }
                            });

                            // 加急配送
                            $('select[name="isUrgent"] option').each(function (i,item) {
                                if($(item).val() == res.isUrgent){
                                    $(item).prop('selected',true)
                                }
                            });

                            // 货值
                            $('input[name="itemValue"]').val(res.itemValue);

                            // 备注
                            $('textarea[name="remark"]').text(res.remark);

                            // 货袋编号
                            $('input[name="bagNumber"]').val(res.bagNumber);

                            form.render('select');
                        }

                    }
                })
            }
        }


    })


});