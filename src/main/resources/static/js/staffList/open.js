$(document).ready(function(){


	layui.use(['layer', 'element','table'], function(){
		var layer = layui.layer
			,table = layui.table
		  	,element = layui.element;


        // 查看节点操作人员信息
        // 数据表格

        table.render({
            elem: '#idTest'
            ,height: 472
            ,url: "/staff/logisticInfoByStaff"//数据接口
            ,page: true
			// ,method: 'post'
            ,cols: [[
                {type:'numbers'}
                ,{field: 'operateName', title: '操作人员',align:'center'}
                ,{field: 'deliveryNumber', title: '快递单号'}
                ,{field: 'bagNumber', title: '货袋编号'}
                ,{field: 'operateType', title: '操作类型'}
                ,{field: 'operateComment', title: '操作说明'}
                ,{field: 'operateResult', title: '操作结果'}
                ,{field: 'createTime', title: '操作时间'}




            ]]
            ,where: {
                staffId: $('input[name = "staffId"]').val()


            }
            ,autoSort: false //禁用前端自动排序。注意：该参数为 layui 2.4.4 新增
        });



	  	
	});

	// 员工信息
	$.ajax({
		url: '/staff/staffDetail',
		data: {'staffId':$('input[name="staffId"]').val()},
		dataType: 'JSON',
		success: function (data) {
			console.log(data);
			if(data.message =="获取成功"){
				$('.inner .img img').attr('src',data.data.headPic);
				$('.info .up .name').html(data.data.staffName);
                $('.info .up .userid span').html(data.data.number);
                $('.info .down .staffGroupName span').html(data.data.staffGroupName);
                $('.info .down .bornYears span').html(data.data.bornYears);
                $('.info .down .age span').html(data.data.age);
                $('.info .down .position span').html(data.data.position);
                $('.info .down .mobile span').html(data.data.mobile);
                $('.info .down .sex span').html(data.data.sex);

                $('.info .down .attribution span').html(data.data.attribution);
                $('.info .down .warehouseName span').html(data.data.warehouseName);
			}
        }
	})

	// 操作记录


});