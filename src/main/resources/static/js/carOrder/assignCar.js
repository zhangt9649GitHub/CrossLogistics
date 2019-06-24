$(document).ready(function(){

    layui.use(['layer', 'form'], function(){
        var layer = layui.layer
            ,form = layui.form;


        // 监听表单提交
        form.on('submit(add)', function(data){

            var that = this;

            data.field.carId=parseInt(data.field.carId);
            data.field.taskOrderId=parseInt(data.field.taskOrderId);

            var formData = new FormData();
            formData.append("carId", data.field.carId);
            formData.append("taskOrderId", data.field.taskOrderId);
            // 禁止点击
            $(that).addClass('zq-submit-disabled');
            $.ajax({
                url: '/carOrder/editCarOrderCar',
                type: 'post',
                dataType: 'JSON',
                data:  formData,
                contentType: false,
                processData: false,
                success: function (data) {
                    console.log(data);
                    layer.msg(data.message);
                    if (data.message == '编辑成功') {
                        setTimeout(function () {
                            window.parent.location.reload(); //刷新父页面
                        }, 1000);
                    }
                    // 取消禁止点击
                    else {
                        $(that).removeClass('zq-submit-disabled');
                    }
                }
            });
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });

        // 小车
        var date = $('#createTime').val();
        $.ajax({
            url: '/carOrder/selectCarNoCarOrder',
            data: {'createTime':date.substring(0,10)},
            dataType: 'JSON',
            async:false,
            success: function (data) {
                if(data.data != null){
                    var res = data.data;
                    console.log(res);
                    $.each(res,function(i,n){
                        $('select[name = "carId"]').append('<option value="'+ n.carId +'">'+n.carNumber+'</option>')
                    });
                    form.render('select');
                }
            }
        });

        // 获取编辑信息
        // $.ajax({
        //     url: '/carOrder/selectCarByCarOrder',
        //     data: {'taskOrderId': $('input[name="taskOrderId"]').val()},
        //     dataType: 'JSON',
        //     success: function (data) {
        //         console.log(data);
        //         if(data.data != null){
        //             var res = data.data;
        //             if(res.carId != null){
        //                 console.log(res.carId);
        //                 $('select[name="carId"] option').each(function (i,item) {
        //                     // console.log($(item).val());
        //                     if($(item).val() == res.carId){
        //
        //                         $(item).prop('selected',true);
        //                         form.render('select');
        //                         return false;
        //                     }else {
        //
        //                         if(i == ($('select[name="carId"] option').length -1)){
        //
        //                             $('select[name = "carId"]').append('<option value="'+res.carId+'" selected>'+res.carNumber+'</option>');
        //                             form.render('select');
        //                         }
        //                     }
        //                 });
        //
        //             }
        //
        //         }
        //     }
        // })
    });



})