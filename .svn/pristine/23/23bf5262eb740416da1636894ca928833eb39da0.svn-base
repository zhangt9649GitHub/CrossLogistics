$(document).ready(function () {

    layui.use(['layer','table'], function() {
        var layer = layui.layer
            , table = layui.table;

        // 查看节点操作人员信息
        // 数据表格

        if($('input[name="type"]').val() == 1){
            table.render({
                elem: '#idTest'
                ,height: 500
                ,url: "/goodsFrom/getGoodsFromInfo"//数据接口
                ,page: true

                ,cols: [[
                    {type:'numbers'}
                    ,{field: 'deliveryNumber', title: '快递单号',align:'center',width:200}

                    ,{field: 'receiptContact', title: '收货人',align:'center'}
                    ,{field: 'receiptContactMobile', title: '收货人联系方式'}
                    ,{field: 'zipCode', title: '邮编',align:'center'}
                    ,{field: 'receiptAddress', title: '收货地址',minWidth:400}
                ]]

                ,where: {
                    formId: $('input[name = "formId"]').val()
                }
                ,autoSort: false //禁用前端自动排序。注意：该参数为 layui 2.4.4 新增
            });
            return false;
        }else{
            table.render({
                elem: '#idTest'
                ,height: 500
                ,url: "/goodsFrom/getTruckDistributionGoodsFromInfo"//数据接口
                ,page: true
                ,minWidth:80
                ,cols: [[
                    {type:'numbers'}
                    ,{field: 'deliveryNumber', title: '快递单号',minWidth:130}
                    ,{field: 'bagNumber', title: '货袋编号',minWidth:120}
                    ,{field: 'totalGoods', title: '总箱数',align:'center',width:80}
                    ,{field: 'actualWeight', title: '货物重量（kg）',align:'center',width:150}
                    ,{field: 'itemValue', title: '货值',align:'right'}
                    ,{field: 'gstPrice', title: 'GST费用',align:'center',minWidth:100}
                    ,{field: 'isArrivalPay', title: '货到付款',align:'center',width:100,templet:'#isArrivalPay'}
                    ,{field: 'receiptContact', title: '收货人',align:'center'}
                    ,{field: 'receiptContactMobile', title: '收货人联系方式',minWidth:130}
                    ,{field: 'zipCode', title: '邮编',align:'center'}
                    ,{field: 'receiptAddress', title: '收货地址'}
                ]]

                ,where: {
                    formId: $('input[name = "formId"]').val()
                }
                ,autoSort: false //禁用前端自动排序。注意：该参数为 layui 2.4.4 新增
            });
            return false;
        }





    });


});