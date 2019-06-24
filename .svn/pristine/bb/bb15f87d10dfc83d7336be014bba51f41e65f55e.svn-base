$(document).ready(function(){

	layui.use(['layer', 'form','table'], function(){
		var layer = layui.layer
		  	,form = layui.form
	  	  	,table = layui.table;

        // 数据表格

        table.render({
            elem: '#idTest'
            ,height: 500
            ,url: "/staff/selectStaffAll"//数据接口
            ,page: true
            ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [[
                {field: 'number', title: '员工编号'}
                ,{field: 'staffName', title: '员工姓名',align:'center'}
                ,{field: 'sex', title: '性别',align:'center',width:80}
                ,{field: 'position', title: '职位角色',align:'center'}
                ,{field: 'attribution', title: '归属地'}
                ,{field: 'staffGroupName', title: '员工权限'}
                ,{field: 'statusName', title: '状态',align:'center'}
                ,{field: 'addTime', title: '创建时间', align:'center'}
                ,{align:'center', toolbar: '#barDemo',title: '操作',minWidth:400}
            ]],
            where: {
                staffGroupId: 0
            }
            ,autoSort: false //禁用前端自动排序。注意：该参数为 layui 2.4.4 新增
        });

        //监听排序事件
        table.on('sort(demo)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            // console.log(obj.field); //当前排序的字段名
            // console.log(obj.type); //当前排序类型：desc（降序）、asc（升序）、null（空对象，默认排序）
            // console.log(this); //当前排序的 th 对象

            //尽管我们的 table 自带排序功能，但并没有请求服务端。
            //有些时候，你可能需要根据当前排序的字段，重新向服务端发送请求，从而实现服务端排序，如：
            table.reload('idTest', {
                initSort: obj //记录初始排序，如果不设的话，将无法标记表头的排序状态。
                ,where: { //请求参数（注意：这里面的参数可任意定义，并非下面固定的格式）
                    field: obj.field //排序字段
                    ,order: obj.type //排序方式
                }
            });

            // layer.msg('服务端排序。order by '+ obj.field + ' ' + obj.type);
        });





        // 员工权限列表
        $.ajax({
            url: "/staff/staffGroupAll",
            dataType: 'JSON',
            success: function (data) {
                if(data.code == 0){
                    var res = data.data;
                    for( let i in res ){
                        $('select[name="staffGroupId"]').append('<option value="'+res[i].staffGroupId+'">'+res[i].staffGroupName+'</option>');
                    }
                    form.render('select');
                }
            }
        });


        //搜索
        form.on('submit(add)', function(data){

            if((data.field.staffGroupId).length == 0){
                data.field.staffGroupId = 0;
            }

            //执行重载
            table.reload('idTest', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    search: $.trim(data.field.search),
                    staffGroupId: $.trim(data.field.staffGroupId)


                }
            });


            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });

	  	
	});



});


// 禁用
function disabled(id) {
    layer.confirm('确认禁用？', {
        btn: ['确定', '取消'] //可以无限个按钮
    }, function (index, layero) {
        $.ajax({
            url: '/staff/editStaffStatus',
            type: 'post',
            data: {'staffId':id,'status':0},
            dataType: 'JSON',
            success: function (data) {
                layui.layer.msg(data.message);
                data.message == '操作成功'? layui.table.reload('idTest') : '';
            },
            error: function (data) {

            }
        })
    }, function (index) {
        //按钮【取消】的回调
    });
};

// 启用
function startusing(id) {
    layer.confirm('确认启用？', {
        btn: ['确定', '取消'] //可以无限个按钮
    }, function (index, layero) {
        $.ajax({
            url: '/staff/editStaffStatus',
            type: 'post',
            data: {'staffId':id,'status':1},
            dataType: 'JSON',
            success: function (data) {
                layui.layer.msg(data.message);
                data.message == '操作成功'? layui.table.reload('idTest') : '';
            },
            error: function (data) {

            }
        })
    }, function (index) {
        //按钮【取消】的回调
    });
};

// 删除
function delthis(id) {
    layer.confirm('确认删除吗？', {
        btn: ['确定', '取消'] //可以无限个按钮
    }, function (index, layero) {
        //按钮【确定】的回调
        $.ajax({
            url: '/staff/deleteStaff',
            type: 'post',
            data: {'staffId':id},
            dataType:"JSON",
            success:function(data){
                // console.log(data);
                layui.layer.msg(data.message);
                data.message == '删除成功' ? layui.table.reload('idTest') : '';
            },
            error:function(data){
                console.log("错误！！");
                // window.clearInterval(timer);
            }
        })
    }, function (index) {
        //按钮【取消】的回调
    });
};