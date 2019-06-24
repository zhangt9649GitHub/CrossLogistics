$(document).ready(function () {


    layui.use(['layer','table'], function() {
        var layer = layui.layer
            , table = layui.table;


    });

    //  获取审批用户信息
    $.ajax({
        url: '/admin/user/getAppUserCertificationDetails',
        data: {'certificationId':$('input[name = "certificationId"]').val()},
        dataType: 'JSON',
        success: function (data) {
            console.log(data);
            if(data.code == 0){
                if(data.data != null){
                    // 用户编号
                    $('.user-info .number .inline span').text(data.data.number);
                    // 用户姓名
                    $('.user-info .userTrueName .inline span').text(data.data.userTrueName);
                    // 联系方式
                    $('.user-info .mobile .inline span').text(data.data.mobile);
                    // 审核类型
                    $('.user-info .userType .inline span').text(data.data.userType);
                    // 审核状态
                    $('.user-info .userCertificationStatus .inline span').text(data.data.userCertificationStatus);

                    // 货车显示
                    if(data.data.userType == '货车'){

                        // 货车车牌
                        $('.user-info .licensePlate .inline span').text(data.data.licensePlate);

                        // 货车载重
                        $('.user-info .load .inline span').text(data.data.load);

                        // 货车载重
                        $('.user-info .models .inline span').text(data.data.models);
                    }else{
                        $('.user-info .truck').hide();
                    }

                    $.each(data.data.images,function(i,n){
                        $('.images').append('<img src="'+ n +'">');
                    })

                }
            }

        }
    })

    // 查看原图
    $('.images').on('click','img',function () {
        $('.fixed').show();
        $('.fixed img').attr('src',$(this).attr('src'));
    })
    // 关闭查看原图
    $('.fixed').click(function () {
        $(this).hide();
    })

});