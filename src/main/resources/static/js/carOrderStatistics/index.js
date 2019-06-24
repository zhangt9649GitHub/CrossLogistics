$(document).ready(function(){

	layui.use(['layer', 'form','table','upload','laydate'], function(){
		var layer = layui.layer
		  	,form = layui.form
	  	  	,table = layui.table
            ,laydate = layui.laydate;

		// 开始时间
        laydate.render({
            elem: '#startDate'
        });
        // 结束时间
        laydate.render({
            elem: '#endDate'
        });



        // 数据表格

        table.render({
            elem: '#idTest'
            ,height: 500
            ,url: "/statistics/getAppUserCarStatistics"//数据接口
            ,page: true
            ,method: 'post'
            ,cellMinWidth:80
            ,cols: [[
                // {type:'numbers'}
                {field: 'deliveryNumber', title: '快递单号',minWidth:150}
                ,{field: 'from', title: '货物来源',alert:'center'}
                ,{field: 'goodType', title: '货物类型',align:'center'}
                ,{field: 'sendPeople', title: '派送方',align:'center'}
                ,{field: 'itemValue', title: '货值',align:'right'}
                ,{field: 'isArrivalPay', title: '货到付款',align:'center',templet:'#isArrivalPay'}
                ,{field: 'sendTime', title: '派送时间',align:'center'}
                ,{field: 'isReceiveGoods', title: '货物',align:'center',templet:'#isReceiveGoods'}
                // ,{field: 'comment', title: '扣除原因'}
                // ,{align:'center', toolbar: '#barDemo',title: '操作',minWidth:350}
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

        var startDate='',endDate='',sendPeople='';
        form.on('submit(*)', function(data){
            layer.closeAll('page');
            data.field.userTrueName = $.trim(data.field.userTrueName);

            startDate = $.trim(data.field.startDate);
            endDate = $.trim(data.field.endDate);
            sendPeople = $.trim(data.field.sendPeople);

            table.reload('idTest', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    startDate: $.trim(data.field.startDate),
                    endDate: $.trim(data.field.endDate),
                    sendPeople: $.trim(data.field.sendPeople)
                }
            });
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });

        // 导出
        $('#export').click(function(){

            location.href="/statistics/getCarStatisticsFile?startDate="+startDate+"&endDate="+endDate+"&sendPeople="+sendPeople;
        });






    });





});


