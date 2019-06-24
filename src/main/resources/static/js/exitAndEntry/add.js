$(document).ready(function(){

	layui.use(['layer', 'form','table'], function(){
		var layer = layui.layer
		  	,form = layui.form;
	  	  	

  		form.on('submit(add)', function(data){

            axios.post("/entryAndExit/insertEntryAndExit", data.field)
                .then(function (response) {
                    console.log(response)
                    //提示信息并跳转页面
                    layer.msg(response.data.msg);
                    if (response.data.msg == '添加成功') {
                        setTimeout(function () {
                            window.parent.location.reload(); //刷新父页面
                        }, 1000);
                    }
                    //重新赋值token数据
                    else {

                    }
                });
			return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
		});

	  
	});



})