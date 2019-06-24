$(document).ready(function(){

    layui.use(['layer', 'form'], function(){
        var layer = layui.layer
            ,form = layui.form;


        // 监听表单提交
        form.on('submit(add)', function(data){

            var that = this;

            data.field.appUserId=parseInt(data.field.appUserId);
            data.field.taskOrderId=parseInt(data.field.taskOrderId);

            var formData = new FormData();
            formData.append("appUserId", data.field.appUserId);
            formData.append("taskOrderId", data.field.taskOrderId);
            // 禁止点击
            $(that).addClass('zq-submit-disabled');
            $.ajax({
                url: '/carOrder/editCarOrderUser',
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

        // 获取用户
        var date = $('#createTime').val();
        // console.log(date);
        // console.log(date.substring(0,10));
        $.ajax({
            url: '/carOrder/selectCarUser',
            data: {'createTime':date.substring(0,10)},
            dataType: 'JSON',
            async:false,
            success: function (data) {
                if(data.data != null){
                    var res = data.data;
                    console.log(res);
                    $.each(res,function(i,n){
                        $('select[name = "appUserId"]').append('<option value="'+ n.appUserId +'">'+n.userName+'</option>')
                    });
                    form.render('select');
                }
            }
        });

        // 获取编辑信息
        // $.ajax({
        //     url: '/carOrder/selectAppUserByCarOrderId',
        //     data: {'taskOrderId': $('input[name="taskOrderId"]').val()},
        //     dataType: 'JSON',
        //     success: function (data) {
        //         console.log(data);
        //         if(data.data != null){
        //             var res = data.data;
        //             if(res.appUserId != null){
        //                 console.log(res.appUserId);
        //                 $('select[name="appUserId"] option').each(function (i,item) {
        //                     // console.log($(item).val());
        //                     if($(item).val() == res.appUserId){
        //
        //                         $(item).prop('selected',true);
        //                         form.render('select');
        //                         return false;
        //                     }else {
        //
        //                         if(i == ($('select[name="groupId"] option').length -1)){
        //
        //                             $('select[name = "groupId"]').append('<option value="'+res.appUserId+'" selected>'+res.userName+'</option>');
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