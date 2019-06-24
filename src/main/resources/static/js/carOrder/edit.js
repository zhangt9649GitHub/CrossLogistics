$(document).ready(function(){

	layui.use(['layer', 'form','table','laydate'], function(){
		var layer = layui.layer
		  	,form = layui.form
	  	  	,table = layui.table
	  	  	,laydate = layui.laydate;

	  	// 订单时间
	  	laydate.render({
	  		elem: "#order-time"
	  	})

	});



});