$(document).ready(function(){


	layui.use(['layer', 'element','table'], function(){
		var layer = layui.layer
            ,table = layui.table
		  	,element = layui.element;



        // 用户信息
        $.ajax({
            url: '/appUser/appUserDetail',
            dataType: 'JSON',
            data: {'appUserId':$('input[name="appUserId"]').val()},
            success: function (data) {
                console.log(data);
                if(data.data != null){
                    // 昵称
                    $('div[name = "userName"]').text(data.data.userName);

                    // 姓名
                    $('div[name = "actualName"] span').text(data.data.actualName);

                    // 门牌号
                    $('div[name = "houseNumber"] span').text(data.data.houseNumber);
                    // 头像
                    $('img[name = "headPic"]').attr('src',data.data.headPic);
                    // 编号
                    $('div[name="number"] span').text(data.data.number);
                    // 联系方式
                    $('div[name="mobile"] span').text(data.data.mobile);
                    // 地址
                    $('div[name = "address"] span').text(data.data.address);
                    // 小车认证状态
                    $('div[name="carApproveStatus"] span').text(data.data.carApproveStatus);
                    // 性别
                    $('div[name = "sex"] span').text(data.data.sex);
                    // 邮编
                    $('div[name = "zipCode"] span').text(data.data.zipCode);
                    // 货车认证状态
                    $('div[name ="truckApproveStatus"] span').text(data.data.truckApproveStatus);
                    // 年龄
                    $('div[name ="age"] span').text(data.data.age);
                    // 邮箱
                    $('div[name="email"] span').text(data.data.email);
                }
            }
        });

        // 获取小车金额、积分、订单数
        $.ajax({
            url:'/appUser/appUserWalletAndOrderNum',
            type:'post',
            dataType:'JSON',
            data:{'appUserId':$('input[name="appUserId"]').val(),'userType':'小车'},
            success:function (data) {
                if(data.message == '获取成功'){
                    if(data.data != null){
                        $('.car .commissionAmount span').text(data.data.commissionAmount);
                        $('.car .integral span').text(data.data.integral);
                        $('.car .completeOrderNum span').text(data.data.completeOrderNum);
                    }
                }
            }
        });

        // 获取小车订单记录
        table.render({
            elem: '#car-idTest1'
            ,height: 472
            ,url: "/appUser/orderRecordByCarUser"//数据接口
            ,page: true
            // ,method: 'post'
            ,cols: [[
                {type:'numbers'}
                ,{field: 'orderNumber', title: '订单编号'}
                ,{field: 'totalMoney', title: '订单金额'}
                ,{field: 'totalIntegral', title: '订单积分'}
                ,{field: 'state', title: '订单状态' ,templet:'#idTest1'}
                ,{field: 'createTime', title: '订单时间'}
            ]]
            ,where: {
                appUserId: $('input[name = "appUserId"]').val(),
                userType: '小车'
            }
            // ,autoSort: false //禁用前端自动排序。注意：该参数为 layui 2.4.4 新增
        });


        // 获取小车提现记录
        table.render({
            elem: '#car-idTest2'
            ,height: 472
            ,url: "/appUser/getWithdrawApplicationList"//数据接口
            ,page: true
            // ,method: 'post'
            ,cols: [[
                {type:'numbers'}
                ,{field: 'withdrawOrderNumber', title: '提现订单编号'}
                ,{field: 'withdrawMoney', title: '提现金额'}
                ,{field: 'withdrawWay', title: '提现方式'}
                ,{field: 'addTime', title: '提现时间'}
                ,{field: 'arriveTime', title: '到账时间'}
            ]]
            ,where: {
                userId: $('input[name = "appUserId"]').val(),
                userType: '小车'
            }
            // ,autoSort: false //禁用前端自动排序。注意：该参数为 layui 2.4.4 新增
        });



        // 获取小车积分记录
        table.render({
            elem: '#car-idTest3'
            ,height: 472
            ,url: "/appUser/getWalletIntegral"//数据接口
            ,page: true
            // ,method: 'post'
            ,cols: [[
                {type:'numbers'}
                ,{field: 'carOrderNumber', title: '订单编号'}
                ,{field: 'addTime', title: '订单时间'}
                ,{field: 'streamText', title: '流水记录内容'}
            ]]
            ,where: {
                appUserId: $('input[name = "appUserId"]').val(),
                userType: '小车'
            }
            // ,autoSort: false //禁用前端自动排序。注意：该参数为 layui 2.4.4 新增
        });





        // 获取货车车金额、积分、订单数
        $.ajax({
            url:'/appUser/appUserWalletAndOrderNum',
            type:'post',
            dataType:'JSON',
            data:{'appUserId':$('input[name="appUserId"]').val(),'userType':'货车'},
            success:function (data) {
                if(data.message == '获取成功'){
                    if(data.data != null){
                        $('.truck .commissionAmount span').text(data.data.commissionAmount);
                        $('.truck .integral span').text(data.data.integral);
                        $('.truck .completeOrderNum span').text(data.data.completeOrderNum);
                    }
                }
            }
        });

        // 获取货车订单记录
        table.render({
            elem: '#truck-idTest1'
            ,height: 472
            ,url: "/appUser/orderRecordByCarUser"//数据接口
            ,page: true
            ,cellMinWidth: 276
            // ,method: 'post'
            ,cols: [[
                {type:'numbers'}
                ,{field: 'orderNumber', title: '订单编号'}
                ,{field: 'totalMoney', title: '订单金额'}
                ,{field: 'totalIntegral', title: '订单积分'}
                ,{field: 'state', title: '订单状态' ,templet:'#idTest1',maxWidth:200}
                ,{field: 'createTime', title: '订单时间'}
            ]]
            // ,autoSort: false //禁用前端自动排序。注意：该参数为 layui 2.4.4 新增
        });

        table.reload('truck-idTest1', {
            page: {
                curr: 1 //重新从第 1 页开始
            }
            ,where: {
                appUserId: $('input[name = "appUserId"]').val(),
                userType: '货车'
            }
        });

        // 获取货车提现记录
        table.render({
            elem: '#truck-idTest2'
            ,height: 472
            ,url: "/appUser/getWithdrawApplicationList"//数据接口
            ,page: true
            ,cellMinWidth: 276
            // ,method: 'post'
            ,cols: [[
                {type:'numbers'}
                ,{field: 'withdrawOrderNumber', title: '提现订单编号'}
                ,{field: 'withdrawMoney', title: '提现金额'}
                ,{field: 'withdrawWay', title: '提现方式'}
                ,{field: 'addTime', title: '提现时间'}
                ,{field: 'arriveTime', title: '到账时间'}
            ]]
            ,where: {
                userId: $('input[name = "appUserId"]').val(),
                userType: '货车'
            }
            // ,autoSort: false //禁用前端自动排序。注意：该参数为 layui 2.4.4 新增
        });



        // 获取货车积分记录
        table.render({
            elem: '#truck-idTest3'
            ,height: 472
            ,url: "/appUser/getWalletIntegral"//数据接口
            ,page: true
            ,cellMinWidth: 460
            // ,method: 'post'
            ,cols: [[
                {type:'numbers'}
                ,{field: 'carOrderNumber', title: '订单编号'}
                ,{field: 'addTime', title: '订单时间'}
                ,{field: 'streamText', title: '流水记录内容'}
            ]]
            ,where: {
                appUserId: $('input[name = "appUserId"]').val(),
                userType: '货车'
            }
            // ,autoSort: false //禁用前端自动排序。注意：该参数为 layui 2.4.4 新增
        });






	});



});