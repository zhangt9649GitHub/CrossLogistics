$(document).ready(function(){
//全局定义一次, 加载formSelects
    layui.config({
        base: '../../layui/src/' //此处路径请自行处理, 可以使用绝对路径
    }).extend({
        formSelects: 'formSelects-v4'
    });
    layui.use(['layer', 'form','table','formSelects'], function(){
        var layer = layui.layer
            ,form = layui.form
            ,table = layui.table
            ,formSelects = layui.formSelects;


        // 数据表格

        table.render({
            elem: '#idTest'
            ,height: 500
            ,url: "/appUserAddress/appUserAddressAll"//数据接口
            ,page: true

            ,cols: [[
                {type:'numbers'}
                ,{field: 'name', title: '姓名',align:'center'}
                ,{field: 'mobile', title: '联系方式',align:'center'}
                ,{field: 'singaporeAreaName', title: '所属区域'}
                ,{field: 'zipCode', title: '邮编',align:'center'}
                ,{field: 'addressType', title: '房屋类型',align:'center',templet:'#addressType'}
                ,{field: 'houseNumber', title: '门牌号'}
                ,{field: 'addTime', title: '添加时间',align:'center'}
            ]]
            ,autoSort: false //禁用前端自动排序。注意：该参数为 layui 2.4.4 新增
        });

        //监听排序事件
        table.on('sort(demo)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"


            //尽管我们的 table 自带排序功能，但并没有请求服务端。
            //有些时候，你可能需要根据当前排序的字段，重新向服务端发送请求，从而实现服务端排序，如：
            table.reload('idTest', {
                initSort: obj //记录初始排序，如果不设的话，将无法标记表头的排序状态。
                ,where: { //请求参数（注意：这里面的参数可任意定义，并非下面固定的格式）
                    field: obj.field //排序字段
                    ,order: obj.type //排序方式
                }
            });
        });
        //搜索
        form.on('submit(add)', function(data){
            //执行重载

            table.reload('idTest', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    search: $.trim(data.field.search),
                    singaporeAreaIds: $.trim(data.field.singaporeAreaIds)


                }
            });


            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });

        // 查询区域下拉框数据
        $.ajax({
            url: '/appUserAddress/appUserAdressAreaAll',
            dataType: 'JSON',
            success: function (data) {
                if(data.data != null){
                    var res = data.data;
                    console.log(res);
                    console.log( $('#singaporeAreaIds'));
                    for(let i in res){
                        $('#singaporeAreaIds').append('<option value="'+ res[i].singaporeAreaId +'">'+ res[i].singaporeAreaName +'</option>');
                    }
                    formSelects.render('select1');
                }
            }
        })



 });



});